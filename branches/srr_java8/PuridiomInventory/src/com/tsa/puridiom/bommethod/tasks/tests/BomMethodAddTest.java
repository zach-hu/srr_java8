package com.tsa.puridiom.bommethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomMethodAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomMethodAdd test = new BomMethodAdd();
			Map incomingRequest = new HashMap();
		
			BomMethod bomMethod = new BomMethod();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomMethod", bomMethod);
		
			bomMethod = (BomMethod) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomMethodAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomMethodAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}