package com.tsa.puridiom.stddocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stddocument.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdDocumentRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdDocumentRetrieveAll test = new StdDocumentRetrieveAll();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			
			List list = (List) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);
			
			for (int i=0; i < list.size(); i++)
			{
				StdDocument stdDocument = (StdDocument) list.get(i);
				System.out.println(stdDocument.getFileName() + " - " + stdDocument.getDescription());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}