package com.tsa.puridiom.documentfile.tasks.tests;

import com.tsa.puridiom.documentfile.tasks.DocumentFileCopy;

import java.util.*;

public class DocumentFileCopyTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    DocumentFileCopy test = new DocumentFileCopy();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocAttachment_docFilename", "TEST.DOC");
			
			String	newFilename = (String) test.executeTask(incomingRequest);

			System.out.println("File copied: " + newFilename);
			
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}