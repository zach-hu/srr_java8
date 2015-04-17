package com.tsa.puridiom.invbinlocation.tasks.tests;

import com.tsa.puridiom.invbinlocation.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvBinLocationDeleteByTempIcTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("hilton");
			InvBinLocationDeleteItemByTempIc test = new InvBinLocationDeleteItemByTempIc();
			Map incomingRequest = new HashMap();
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "hilton");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("InvBinLocation_tempIc", "6233108400075");
			incomingRequest.put("InvBinLocation_itemNumber", "UNIQUE-ORDER");
			incomingRequest.put("InvBinLocation_itemLocation", "MAIN");
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}