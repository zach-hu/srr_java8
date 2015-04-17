package com.tsa.puridiom.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.CurrCode;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.services.tasks.CurrencyRateExchangeTask;
import com.tsagate.foundation.utility.Log;

public class CurrencyRateExchangeJob extends ScheduledJob
{

	public void execute()
	{
		try
		{
			Log.debug(this, "CurrencyRateExchangeJob Job Start to execute...");
			CurrencyRateExchangeTask job = new CurrencyRateExchangeTask();
			String organizationId = this.getOrganizationId();
			Map parameters = new HashMap();
			List currCodesUpdated;
			String message;

			parameters.put("organizationId", organizationId);

			currCodesUpdated = (List) job.executeTask(parameters);

			message = this.buildEmailMessage(currCodesUpdated, organizationId);

			this.sendEmail(message, organizationId);

			Log.debug(this, "CurrencyRateExchangeJob Job End execute...");

		} catch (Exception exception)
		{
			Log.error(this, "CurrencyRateExchangeJob Job failed: " + exception.getMessage());
			exception.printStackTrace();
		}
	}

	private String buildEmailMessage(List currCodesUpdated, String organizationId)
	{
		StringBuffer message = new StringBuffer();
		CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		String currencyBase = currencyManager.getBaseCurrencyCode();
		String urlApplication = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", "");

		message.append("\r\n\r\n From URL " + urlApplication);
		
		message.append("\r\n\r\n CurrencyBase: " + currencyBase);

		for (Iterator iterator = currCodesUpdated.iterator(); iterator.hasNext();)
		{
			CurrCode currCode = (CurrCode) iterator.next();
			message.append("\r\n\r\n CurrencyCode: " + currCode.getCurrencyCode() + " >> ISO CurrCode: " + currCode.getIsoCurrency() + " >> ConversionToBase: "
					+ currCode.getConversionToBase().toString() + " >> BaseToCurrency: " + currCode.getBaseToCurrency().toString());
		}

		return message.toString();
	}

	private void sendEmail(String message, String organizationId) throws Exception
	{
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		String subject = propertiesManager.getProperty("COMPANY", "Name", "Puridiom") + "  CurrencyRateExchangeJob Report at " + new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aaa").format(Calendar.getInstance().getTime());
		String sendFrom = propertiesManager.getProperty("MAILEVENTS", "ADMINEMAILADDR", "support@puridiom.com");
		String sendTo = propertiesManager.getProperty("EMAILPROPS", "CURRENCYUPDATEEMAILREPORT", "support@puridiom.com");

		try
		{
			EmailManager.getInstance().sendEmail(sendFrom, sendTo, null, subject, message, null, organizationId);

		} catch (EmailsException exception)
		{
			Log.error(this, "Sent Email in CurrencyRateExchangeJob Job was wrong: " + exception.getMessage());

			EmailManager.getInstance().sendErrorEmail("Sent Email in CurrencyRateExchangeJob Job was wrong", organizationId);

			exception.printStackTrace();

			throw exception;
		}
	}
}
