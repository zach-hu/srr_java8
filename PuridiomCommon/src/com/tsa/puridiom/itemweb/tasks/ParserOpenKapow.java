package com.tsa.puridiom.itemweb.tasks;

import java.io.InputStream;
import java.util.*;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.*;

import com.tsa.puridiom.property.PropertiesManager;

public class ParserOpenKapow implements IResponseParser
{
	public Object parse(Object paramWebService) throws Exception
	{
		HashMap parameters = (HashMap)paramWebService;

		String oid = (String)parameters.get("oid");

		String url = PropertiesManager.getInstance(oid).getProperty("WEBSERVICES", "OPENKAPOW_URL", "http://service.openkapow.com/demo/");

		String webSite = (String)parameters.get("webSite");
		webSite = PropertiesManager.getInstance(oid).getProperty("WEBSERVICES", "OPENKAPOW_REST_" + webSite.toUpperCase(), webSite + "REST.rest");

		String request = url + webSite + "?";
		request += "numberOfItems=" + (String)parameters.get("numberOfItems") + "&";
		request += "resultformat="  + (String)parameters.get("resultformat")  + "&";
		request += "searchText="    + URLEncoder.encode((String)parameters.get("searchText"));

		HttpClient httpClient = new HttpClient();
		GetMethod  getMethod  = new GetMethod(request);

		int statusCode = httpClient.executeMethod(getMethod);
		NodeList itemsList = null;
		if (statusCode != HttpStatus.SC_OK)
			System.err.println("Method failed: " + getMethod.getStatusLine());
		else
		{
			InputStream rstream   = getMethod.getResponseBodyAsStream();
			Document    response  = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
			itemsList = response.getElementsByTagName("item");
		}
		return itemsList;
	}
}