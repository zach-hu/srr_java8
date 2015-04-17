package com.tsa.puridiom.bomcomponent.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomcomponent.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomComponentUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomComponentUpdate test = new BomComponentUpdate();
			Map incomingRequest = new HashMap();
		
			BomComponent bomComponent = new BomComponent();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomComponent", bomComponent);
		
			bomComponent = (BomComponent) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomComponentUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomComponentUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}