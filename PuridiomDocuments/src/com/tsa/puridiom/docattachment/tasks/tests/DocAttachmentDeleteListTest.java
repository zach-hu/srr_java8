package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.docattachment.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DocAttachmentDeleteListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    DocAttachmentRetrieveByHeader task = new DocAttachmentRetrieveByHeader();
			Map incomingRequest = new HashMap();
			DBSession dbs = new DBSession("PURIDIOM");
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocAttachment_icHeader", "3735735400006");
						
			List docAttachmentList = (List) task.executeTask(incomingRequest);
			
			DocAttachmentDeleteList test = new DocAttachmentDeleteList();
			
			dbs = new DBSession("PURIDIOM");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docAttachmentList", docAttachmentList);
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}