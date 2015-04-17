package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.docattachment.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class DocAttachmentDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			DocAttachmentDeleteById test = new DocAttachmentDeleteById();
			Map incomingRequest = new HashMap();

			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocAttachment_docIc", "2");
			incomingRequest.put("DocAttachment_icHeader", "123456");
			
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