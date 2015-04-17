package com.tsa.puridiom.assetcost.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetcost.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AssetCostDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetCostDeleteById test = new AssetCostDeleteById();
			Map incomingRequest = new HashMap();
		
			AssetCost assetCost = new AssetCost();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("assetCost", assetCost);
		
			assetCost = (AssetCost) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("AssetCostDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("AssetCostDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}