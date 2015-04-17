package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class DocAttachmentUpdateByHeaderTest
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
			incomingRequest.put("DocAttachment_docTitle","This is a handler test for the update.") ;
			incomingRequest.put("DocAttachment_docFilename","TESTFILENAME3.DOC") ;
			incomingRequest.put("DocAttachment_docSource","RQH") ;
			incomingRequest.put("DocAttachment_docPrint","N") ;
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			System.out.println("DocAttachmentUpdateByHeaderHandler(Single Record) - " + viewPage);
			
			/*	MULTIPLE RECORD TEST */
			
			String	icHeaderValues[] = {"123", "123"};
			String	docIcValues[] = {"1", "3"};
			String	docTitleValues[] = {"This is a handler test for the update.", "This is another test."};
			String	docFilenameValues[] = {"TESTFILENAME1.DOC", "TESTFILENAME3.DOC"};
			String	docSourceValues[] = {"RQH", "RQH"};
			String	docPrintValues[] = {"N","Y"};
			
			incomingRequest.put("DocAttachment_icHeader", icHeaderValues);
			incomingRequest.put("DocAttachment_docIc", docIcValues);
			incomingRequest.put("DocAttachment_docTitle",docTitleValues) ;
			incomingRequest.put("DocAttachment_docFilename", docFilenameValues) ;
			incomingRequest.put("DocAttachment_docSource", docSourceValues) ;
			incomingRequest.put("DocAttachment_docPrint", docPrintValues) ;
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			viewPage = (String) incomingRequest.get("viewPage");
			System.out.println("DocAttachmentUpdateByHeaderHandler(Multiple Record) - " + viewPage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}