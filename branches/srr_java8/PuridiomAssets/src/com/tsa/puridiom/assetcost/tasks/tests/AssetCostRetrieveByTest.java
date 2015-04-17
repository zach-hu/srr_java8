package com.tsa.puridiom.assetcost.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetcost.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetCostRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetCostRetrieveBy test = new AssetCostRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			List assetCostList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < assetCostList.size(); i++)
			{
				AssetCost assetCost = (AssetCost) assetCostList.get(i);
				System.out.println("AssetCost: " + assetCost.toString());
			}

			System.out.println("AssetCostRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}