package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class StdQuestionAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			StdQuestionAddHandler test = new StdQuestionAddHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdQuestion_icQuestion", "999999999901");
			incomingRequest.put("StdQuestion_questionText", "THIS IS JUST A TEST");
			incomingRequest.put("StdQuestion_questionTitle", "STD QUESTION TEST");
			incomingRequest.put("StdQuestion_responseType", "TEXT");			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("StdQuestionAdd - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}