package com.tsa.puridiom.documentfile.tasks.tests;

import com.tsa.puridiom.docattachment.tasks.*;
import com.tsa.puridiom.documentfile.tasks.DocumentFileDelete;

import java.util.*;

public class DocumentFileDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocumentFileDelete test = new DocumentFileDelete();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocAttachment_docFilename", "HiltonReceivingDoc.doc");
			
			test.executeTask(incomingRequest);

			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}