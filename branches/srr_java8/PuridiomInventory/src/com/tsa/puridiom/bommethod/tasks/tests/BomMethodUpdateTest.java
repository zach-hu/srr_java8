package com.tsa.puridiom.bommethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomMethodUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomMethodUpdate test = new BomMethodUpdate();
			Map incomingRequest = new HashMap();
		
			BomMethod bomMethod = new BomMethod();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomMethod", bomMethod);
		
			bomMethod = (BomMethod) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomMethodUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomMethodUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}