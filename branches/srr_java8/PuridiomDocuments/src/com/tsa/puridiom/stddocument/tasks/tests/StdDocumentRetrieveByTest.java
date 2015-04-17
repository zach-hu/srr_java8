package com.tsa.puridiom.stddocument.tasks.tests;

import com.tsa.puridiom.common.documents.StdDocumentType;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stddocument.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdDocumentRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdDocumentRetrieveBy test = new StdDocumentRetrieveBy();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdDocument_docType", StdDocumentType.STANDARD_DOCUMENT);

			List list = (List) test.executeTask(incomingRequest);
			if (list != null) {
			    for (int i=0; i < list.size(); i++) {
			        StdDocument stdDocument = (StdDocument) list.get(i);
			        System.out.println(stdDocument.toString());
			    }
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}