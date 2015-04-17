package com.tsa.puridiom.doctext.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.doctext.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class DocTextRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocTextRetrieveById task = new DocTextRetrieveById();
			DBSession dbs = new DBSession("PURIDIOM");
			Map incomingRequest = new HashMap();
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocText_icText", "9");
						
			DocText docText = (DocText) task.executeTask(incomingRequest);
			
			if (task.getStatus() == Status.SUCCEEDED)
			{
				dbs.close();
				System.out.println("Text: " + docText.getStdText());		
			}
			
			System.out.println("RESULT: " + task.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}