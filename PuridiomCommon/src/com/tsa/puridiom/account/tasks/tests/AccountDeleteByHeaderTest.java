package com.tsa.puridiom.account.tasks.tests;

import com.tsa.puridiom.account.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AccountDeleteByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			AccountDeleteByHeader task = new AccountDeleteByHeader();
			Map incomingRequest = new HashMap();
			DBSession dbs = new DBSession("puridiom");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("Account_icHeader", "1");
						
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