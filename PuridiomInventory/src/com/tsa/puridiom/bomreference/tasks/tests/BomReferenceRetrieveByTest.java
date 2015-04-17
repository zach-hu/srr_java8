package com.tsa.puridiom.bomreference.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomreference.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomReferenceRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomReferenceRetrieveBy test = new BomReferenceRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomReferenceList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomReferenceList.size(); i++)
			{
				BomReference bomReference = (BomReference) bomReferenceList.get(i);
				System.out.println("BomReference: " + bomReferenceList.toString());
			}
		
			System.out.println("BomReferenceRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}