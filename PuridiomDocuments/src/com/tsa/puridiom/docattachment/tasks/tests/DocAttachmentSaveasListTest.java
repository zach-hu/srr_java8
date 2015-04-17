package com.tsa.puridiom.docattachment.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.docattachment.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class DocAttachmentSaveasListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			DocAttachmentSaveasList test = new DocAttachmentSaveasList();
			Map incomingRequest = new HashMap();
			
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			List docAttachmentList = new ArrayList();
			
			for (int i=0; i < 3; i++) {
				DocAttachment docAttachment = new DocAttachment();
				DocAttachmentPK pk = new DocAttachmentPK();
				pk.setDocIc(new BigDecimal(ukg.getUniqueKey().toString()));
				pk.setIcHeader(new BigDecimal(ukg.getUniqueKey().toString()));
				docAttachment.setComp_id(pk);
				docAttachment.setDocFilename("TEST.DOC");
				docAttachment.setDocPost("Y");
				docAttachment.setDocPrint("Y");
				docAttachment.setDocSource("TST");
				docAttachment.setDocTitle("TESTING DOCUMENT SAVEAS LIST");
				
				docAttachmentList.add(docAttachment);
			}
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docAttachmentList", docAttachmentList);
			incomingRequest.put("newDocAttachment_icHeader", ukg.getUniqueKey().toString());
			
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