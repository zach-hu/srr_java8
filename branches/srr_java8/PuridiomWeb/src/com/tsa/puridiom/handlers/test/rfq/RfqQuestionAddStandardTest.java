package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqQuestionAddStandardTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqQuestionAddStandardHandler test = new RfqQuestionAddStandardHandler();
			Map incomingRequest = new HashMap();
			String	icQuestions[] = {"999999999999", "999999999901", "791091100000"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader","99999900000") ;
			incomingRequest.put("StdQuestion_icQuestion", icQuestions) ;
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqQuestionAddStandard - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}