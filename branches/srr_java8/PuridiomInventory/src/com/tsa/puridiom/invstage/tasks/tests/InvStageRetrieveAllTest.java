package com.tsa.puridiom.invstage.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invstage.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvStageRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvStageRetrieveAll test = new InvStageRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invStageList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invStageList.size(); i++)
			{
				InvStage invStage = (InvStage) invStageList.get(i);
				System.out.println("InvStage: " + invStageList.toString());
			}
		
			System.out.println("InvStageRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}