package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.docattachment.tasks.DocAttachmentRetrieveByHeader;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.database.DBSession;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DocAttachmentRetrieveByHeaderTest
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
			incomingRequest.put("DocAttachment_icHeader", "123456");
						
			List list = (List) task.executeTask(incomingRequest);
			if (list != null ) {
			    for (int i = 0; i < list.size(); i++) {
			        DocAttachment docAttachment = (DocAttachment) list.get(i);
			        System.out.println(i + " - " + docAttachment.toString());
			    }
			}
			
			System.out.println("DocAttachmentRetrieveByHeaderTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}