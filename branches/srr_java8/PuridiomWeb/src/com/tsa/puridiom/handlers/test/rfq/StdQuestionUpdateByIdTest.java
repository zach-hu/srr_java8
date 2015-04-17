package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class StdQuestionUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			StdQuestionUpdateByIdHandler test = new StdQuestionUpdateByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdQuestion_icQuestion", "999999999901");
			incomingRequest.put("StdQuestion_questionText", "THIS IS JUST A TEST FOR THE UPDATE HANDLER");
			incomingRequest.put("StdQuestion_questionTitle", "STD QUESTION TEST - UPD");
			incomingRequest.put("StdQuestion_responseType", "Y/N");			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("StdQuestionUpdateById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}