package com.tsa.puridiom.bomrouting.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomrouting.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomRoutingUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomRoutingUpdate test = new BomRoutingUpdate();
			Map incomingRequest = new HashMap();
		
			BomRouting bomRouting = new BomRouting();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomRouting", bomRouting);
		
			bomRouting = (BomRouting) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomRoutingUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomRoutingUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}