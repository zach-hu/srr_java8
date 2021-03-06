package com.tsa.puridiom.bomrouting.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomrouting.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomRoutingDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomRoutingDeleteById test = new BomRoutingDeleteById();
			Map incomingRequest = new HashMap();
		
			BomRouting bomRouting = new BomRouting();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomRouting", bomRouting);
		
			bomRouting = (BomRouting) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomRoutingDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomRoutingDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}