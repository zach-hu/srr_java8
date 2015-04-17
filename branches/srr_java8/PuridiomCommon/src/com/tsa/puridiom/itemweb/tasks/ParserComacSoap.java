package com.tsa.puridiom.itemweb.tasks;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class ParserComacSoap implements IResponseParser
{
	public Object parse(Object paramWebService) throws Exception
	{
		HashMap parameters = (HashMap)paramWebService;
		String webServicesUrl = "http://comacsoap.litorders.com/ListReceiveService.asmx?op=ListReceive";

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = null;
		String     response   = "";

		try
		{
			StringBuffer  requestFileContents = new StringBuffer();
			BufferedReader bufferedReader     = new BufferedReader(new FileReader((String)parameters.get("requestXMLPath")));
			String line = null;
			while((line = bufferedReader.readLine()) != null)
			{
				requestFileContents.append(line);
			}

			RequestEntity entity = new StringRequestEntity(requestFileContents.toString(), "text/xml",  "utf-8");

			postMethod = new PostMethod(webServicesUrl);
			postMethod.setRequestHeader("Accept"    , "application/soap+xml,application/dime, multipart/related, text/*");
			postMethod.setRequestHeader("SOAPAction", "http://comacsoap.litorders.com/webservices/ListReceiveWebService/2005/08/05/WebService/ListReceive");
			postMethod.setRequestEntity(entity);

			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK)
				System.err.println("Method failed: " + postMethod.getStatusLine());

			response = postMethod.getResponseBodyAsString();
			return response;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			postMethod.releaseConnection();
		}
		return response;
	}
}