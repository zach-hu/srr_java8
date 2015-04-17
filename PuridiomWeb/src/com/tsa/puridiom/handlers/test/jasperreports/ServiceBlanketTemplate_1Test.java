package com.tsa.puridiom.handlers.test.jasperreports;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.ServiceBlanketTemplateHandler;

public class ServiceBlanketTemplate_1Test
{
	public static void  main (String[] args) throws Exception
	{
		try
		{//ServiceBlanketTemplate_1
		    ServiceBlanketTemplateHandler test = new ServiceBlanketTemplateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			incomingRequest.put("PoHeader_icPoHeader", "1559715900012");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) 
			{
				System.out.println("ReceiptCreate - SUCCESS");
			}
			else
			{
			    System.out.println("nope");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}