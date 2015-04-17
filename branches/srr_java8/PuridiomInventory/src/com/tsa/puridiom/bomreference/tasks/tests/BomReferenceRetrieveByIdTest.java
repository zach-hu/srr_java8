package com.tsa.puridiom.bomreference.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomreference.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomReferenceRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomReferenceRetrieveById test = new BomReferenceRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomReference bomReference = (BomReference) test.executeTask(incomingRequest);
		
			System.out.println("BomReference: " + bomReference.toString());
			System.out.println("BomReferenceRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}