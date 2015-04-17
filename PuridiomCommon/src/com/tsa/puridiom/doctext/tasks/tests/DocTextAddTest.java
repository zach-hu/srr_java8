package com.tsa.puridiom.doctext.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.doctext.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.*;

public class DocTextAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocTextAdd task = new DocTextAdd();
			Map incomingRequest = new HashMap();
			
			DocText docText = new DocText();
			DBSession dbs = new DBSession("PURIDIOM");
			
			dbs.startTransaction();
			
			docText.setIcText(new BigDecimal("1"));
			docText.setReferenceType("RQH") ;
			docText.setStdText("This is the text for the comment!") ;
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docText", docText);
						
			Object result = task.executeTask(incomingRequest);
			
			if (task.getStatus() == Status.SUCCEEDED)
			{
				dbs = (DBSession) incomingRequest.get("dbsession");
				
				dbs.commit();
				dbs.close();				
			}
			
			System.out.println("RESULT: " + result.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}