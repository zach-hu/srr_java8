package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.docattachment.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class DocAttachmentSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			DocAttachmentSaveas test = new DocAttachmentSaveas();
			Map incomingRequest = new HashMap();
			
			DocAttachment docAttachment = new DocAttachment();
			DocAttachmentPK pk = new DocAttachmentPK();
			pk.setDocIc(new BigDecimal(1));
			pk.setIcHeader(new BigDecimal(123456));
			docAttachment.setComp_id(pk);
			docAttachment.setDocTitle("THIS IS JUST A TEST");
			docAttachment.setDocFilename("TEST.DOC");
			docAttachment.setDocPost("Y");
			docAttachment.setDocPrint("Y");
			docAttachment.setDocSource("Y");
			docAttachment.setDocTitle("TESTING DOCUMENT SAVEAS");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docAttachment", docAttachment);
			incomingRequest.put("newDocAttachment_icHeader", "2123456");
			incomingRequest.put("newDocAttachment_docFilename", "SAVEASTEST.DOC");
			
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