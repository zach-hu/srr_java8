package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqQuestionCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqQuestionCreateHandler test = new RfqQuestionCreateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader","99999900000") ;
			incomingRequest.put("RfqQuestion_sequence","1") ;
			incomingRequest.put("RfqQuestion_questionText","This is only a test.") ;
			incomingRequest.put("RfqQuestion_responseType","YN") ;
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqQuestionCreate - SUCCESS");
			}
			RfqQuestion rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
			System.out.println(rfqQuestion.getQuestionText() + " - " + rfqQuestion.getResponseType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}