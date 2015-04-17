package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class DocAttachmentRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocAttachmentRetrieveByHeaderHandler test = new DocAttachmentRetrieveByHeaderHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocAttachment_icHeader", "123");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("DocAttachmentRetrieveByHeaderHandler - SUCCESS");
			}
			
			List list = (List) incomingRequest.get("docAttachmentList");
			for (int i=0; i < list.size(); i++)
			{
			    DocAttachment docAttachment = (DocAttachment) list.get(i);
				System.out.println(i + " - " + docAttachment.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}