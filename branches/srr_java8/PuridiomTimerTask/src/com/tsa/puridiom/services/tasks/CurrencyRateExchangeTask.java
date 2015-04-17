package com.tsa.puridiom.services.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.CurrCode;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class CurrencyRateExchangeTask extends Task
{
	public CurrencyRateExchangeTask()
	{
	}

	public Object executeTask(Object object) throws Exception
	{
		List currCodesUpdated = new ArrayList(); 
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			String conversion = "";

			CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
			HashMap currencies = currencyManager.getCurrencies();
			String currencyBase = currencyManager.getBaseCurrencyCode();

			if (!HiltonUtility.isEmpty(currencyBase))
			{
				for (Iterator iter = currencies.keySet().iterator(); iter.hasNext();)
				{
					String currencyCode = (String) iter.next();
					CurrCode currCode = (CurrCode) currencies.get(currencyCode);

					if ((currCode != null) && (!HiltonUtility.isEmpty(currCode.getIsoCurrency())))
					{
						Log.debug(this, currencyBase + " >> " + currencyCode + " >> ISO >> " + currCode.getIsoCurrency());
						conversion = this.getCurrencyConversion(currencyBase, currCode.getIsoCurrency(), organizationId);
						Log.debug(this, currencyBase + " >> " + currencyCode + " >> ISO >> " + currCode.getIsoCurrency() + " = " + conversion);

						if (!HiltonUtility.isEmpty(conversion) && (Float.parseFloat(conversion) > 0.00F))
						{
							BigDecimal baseToCurrency = new BigDecimal(conversion);

							BigDecimal currencyToBase = (new BigDecimal("1")).divide(baseToCurrency, 9, BigDecimal.ROUND_HALF_UP);
							currCode.setConversionToBase(currencyToBase);
							currCode.setBaseToCurrency(baseToCurrency);

							this.updateCurrency(currCode, incomingRequest, organizationId);
							
							currCodesUpdated.add(currCode);
						} else
						{
							Log.debug(this, "There are no updates for " + currencyCode + " currency >> " + conversion);
						}
					} else
					{
						Log.error(this, "There is no currency code to convert (currencyCode is empty)");
					}
				}
			} else
			{
				Log.error(this, "There is no currency base (currencyBase is empty)");
			}

			this.setStatus(Status.SUCCEEDED);
		}

		catch (Exception e)
		{
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			Log.debug(this, "CurrencyRateExchange failed ...");
		}

		return currCodesUpdated;
	}

	private int updateCurrency(CurrCode currCode, Map incomingRequest, String organizationId) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("currcode-update-rate.xml");

		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("currCode", currCode);

		process.executeProcess(incomingRequest);

		return process.getStatus();
	}

	private String getCurrencyConversion(String from, String to, String organizationId)
	{
		String conversion = "";
		String url = DictionaryManager.getInstance("host", organizationId).getProperty("urlPuridiomClient", "http://localhost/PuridiomClient");
		url += "/GetCurrencyRateServlet?from=" + from + "&to=" + to + "&oid=" + organizationId;

		Log.debug(this, "URL Servlet: " + url);

		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

		try
		{
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK)
			{
				Log.error(this, "Method failed" + method.getStatusLine());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), method.getResponseCharSet()));

			// read the contents of the URL through a BufferedReader
			String response = "";
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				response += line;
			}
			// close the reader
			reader.close();

			String divResult = "<div id=\"result\">";

			int start = response.indexOf(divResult);
			if (start > -1)
			{
				response = response.substring(start + divResult.length());
				response = response.substring(0, response.indexOf("</div>"));

				conversion = response;
			}

		} catch (HttpException e)
		{
			Log.error(this, "Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			Log.error(this, "Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			method.releaseConnection();
		}

		Log.debug(this, "Response from servlet: " + conversion);

		return conversion;
	}

}