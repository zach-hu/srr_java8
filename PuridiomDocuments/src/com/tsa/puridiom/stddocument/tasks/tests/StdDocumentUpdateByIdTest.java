package com.tsa.puridiom.stddocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stddocument.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class StdDocumentUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdDocumentUpdateById test = new StdDocumentUpdateById();
			Map incomingRequest = new HashMap();
			
			StdDocument stdDocument = new StdDocument();
			stdDocument.setDescription("THIS IS JUST A TEST FOR THE UPDATE TASK");
			stdDocument.setDocType("S");
			stdDocument.setFileName("ADDTEST102.DOC");
			stdDocument.setHits(new BigDecimal("1"));
			stdDocument.setTitle("TESTING DOCUMENT");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("stdDocument", stdDocument);
			
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