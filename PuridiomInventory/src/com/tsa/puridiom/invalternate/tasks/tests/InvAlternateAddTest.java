package com.tsa.puridiom.invalternate.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invalternate.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvAlternateAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvAlternateAdd test = new InvAlternateAdd();
			Map incomingRequest = new HashMap();
		
			InvAlternate invAlternate = new InvAlternate();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invAlternate", invAlternate);
		
			invAlternate = (InvAlternate) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvAlternateAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvAlternateAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}