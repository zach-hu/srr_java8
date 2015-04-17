package com.tsa.puridiom.stddocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stddocument.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdDocumentRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdDocumentRetrieveById test = new StdDocumentRetrieveById();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdDocument_fileName", "ADDTEST102.DOC");

			StdDocument stdDocument = (StdDocument) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}