package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class DocAttachmentDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocAttachmentDeleteHandler test = new DocAttachmentDeleteHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId","PURIDIOM");;
			incomingRequest.put("userId","KELLI");	
			incomingRequest.put("DocAttachment_icHeader", "123");
			incomingRequest.put("DocAttachment_docIc", "2");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("DocAttachmentDeleteHandler - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}