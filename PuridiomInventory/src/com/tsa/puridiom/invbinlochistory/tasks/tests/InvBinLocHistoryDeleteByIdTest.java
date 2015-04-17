package com.tsa.puridiom.invbinlochistory.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invbinlochistory.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvBinLocHistoryDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvBinLocHistoryDeleteById test = new InvBinLocHistoryDeleteById();
			Map incomingRequest = new HashMap();
		
			InvBinLocHistory invBinLocHistory = new InvBinLocHistory();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invBinLocHistory", invBinLocHistory);
		
			invBinLocHistory = (InvBinLocHistory) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvBinLocHistoryDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvBinLocHistoryDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}