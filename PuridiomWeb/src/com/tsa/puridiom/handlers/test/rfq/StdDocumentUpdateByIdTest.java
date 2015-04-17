package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class StdDocumentUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			StdDocumentUpdateHandler test = new StdDocumentUpdateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdDocument_fileName", "ADDTEST01.DOC");
			incomingRequest.put("StdDocument_title", "INTERNAL BACKUP PROCEDURES REVISED");
			incomingRequest.put("StdDocument_description", "Standards for internal backup procedures - revision.");
			incomingRequest.put("StdDocument_docType", "S");
			incomingRequest.put("StdDocument_hits", "2");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("StdDocumentUpdateById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}