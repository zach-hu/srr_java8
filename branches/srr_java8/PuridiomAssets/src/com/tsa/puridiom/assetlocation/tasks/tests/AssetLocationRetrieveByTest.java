package com.tsa.puridiom.assetlocation.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetlocation.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetLocationRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetLocationRetrieveBy test = new AssetLocationRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			List assetLocationList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < assetLocationList.size(); i++)
			{
				AssetLocation assetLocation = (AssetLocation) assetLocationList.get(i);
				System.out.println("AssetLocation: " + assetLocation.toString());
			}

			System.out.println("AssetLocationRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}