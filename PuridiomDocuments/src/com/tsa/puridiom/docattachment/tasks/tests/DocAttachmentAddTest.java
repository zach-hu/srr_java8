package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.docattachment.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class DocAttachmentAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			DocAttachmentAdd test = new DocAttachmentAdd();
			Map incomingRequest = new HashMap();
			
			DocAttachment docAttachment = new DocAttachment();
			DocAttachmentPK pk = new DocAttachmentPK();
			pk.setDocIc(new BigDecimal(1));
			pk.setIcHeader(new BigDecimal(123456));
			docAttachment.setComp_id(pk);
			docAttachment.setDocTitle("THIS IS JUST A TEST");
			docAttachment.setDocFilename("ADDTEST1023.DOC");
			docAttachment.setDocPost("");
			docAttachment.setDocPrint("");
			docAttachment.setDocSource("");
			docAttachment.setDocTitle("TESTING DOCUMENT");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docAttachment", docAttachment);
			
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