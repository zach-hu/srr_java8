package com.tsa.puridiom.assetservice.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetservice.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetServiceRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetServiceRetrieveBy test = new AssetServiceRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			List assetServiceList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < assetServiceList.size(); i++)
			{
				AssetService assetService = (AssetService) assetServiceList.get(i);
				System.out.println("AssetService: " + assetService.toString());
			}

			System.out.println("AssetServiceRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}