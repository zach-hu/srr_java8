package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqQuestionUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqQuestionUpdateByIdHandler test = new RfqQuestionUpdateByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqQuestion_icRfqHeader", "99999900000");
			incomingRequest.put("RfqQuestion_icQuestion", "99999900111");
			incomingRequest.put("RfqQuestion_sequence", "2");
			incomingRequest.put("RfqQuestion_questionText", "Please enter a brief description of the types of industries you service or market your products to.");
			incomingRequest.put("RfqQuestion_responseType", "TEXT");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqQuestionUpdateById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}