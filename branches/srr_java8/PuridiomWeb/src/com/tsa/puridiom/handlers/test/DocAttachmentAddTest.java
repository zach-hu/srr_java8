package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class DocAttachmentAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocAttachmentAddHandler test = new DocAttachmentAddHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId","PURIDIOM");;
			incomingRequest.put("userId","KELLI");	
			incomingRequest.put("DocAttachment_icHeader", "123");
			incomingRequest.put("DocAttachment_docIc", "3");
			incomingRequest.put("DocAttachment_docTitle","This is a handler test.") ;
			incomingRequest.put("DocAttachment_docFilename","TESTFILENAME3.DOC") ;
			incomingRequest.put("DocAttachment_docSource","RQH") ;
			incomingRequest.put("DocAttachment_docPrint","Y") ;
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("DocAttachmentAddHandler - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}