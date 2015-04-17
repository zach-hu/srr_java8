package com.tsa.puridiom.assetcost.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetcost.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetCostRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetCostRetrieveById test = new AssetCostRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			AssetCost assetCost = (AssetCost) test.executeTask(incomingRequest);

			System.out.println("AssetCost: " + assetCost.toString());
			System.out.println("AssetCostRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}