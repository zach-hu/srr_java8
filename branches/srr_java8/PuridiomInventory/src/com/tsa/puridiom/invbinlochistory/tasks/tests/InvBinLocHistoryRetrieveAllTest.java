package com.tsa.puridiom.invbinlochistory.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invbinlochistory.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvBinLocHistoryRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvBinLocHistoryRetrieveAll test = new InvBinLocHistoryRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invBinLocHistoryList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invBinLocHistoryList.size(); i++)
			{
				InvBinLocHistory invBinLocHistory = (InvBinLocHistory) invBinLocHistoryList.get(i);
				System.out.println("InvBinLocHistory: " + invBinLocHistoryList.toString());
			}
		
			System.out.println("InvBinLocHistoryRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}