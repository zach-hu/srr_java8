package com.tsa.puridiom.invbinlochistory.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invbinlochistory.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvBinLocHistoryRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvBinLocHistoryRetrieveById test = new InvBinLocHistoryRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvBinLocHistory invBinLocHistory = (InvBinLocHistory) test.executeTask(incomingRequest);
		
			System.out.println("InvBinLocHistory: " + invBinLocHistory.toString());
			System.out.println("InvBinLocHistoryRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}