package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class StdDocumentDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			StdDocumentDeleteHandler test = new StdDocumentDeleteHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdDocument_filename","ADDTEST01.DOC") ;
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("StdDocumentDelete - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}