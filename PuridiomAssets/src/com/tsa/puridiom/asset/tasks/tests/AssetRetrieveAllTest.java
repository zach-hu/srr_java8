package com.tsa.puridiom.asset.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.asset.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetRetrieveAll test = new AssetRetrieveAll();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			List assetList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < assetList.size(); i++)
			{
				Asset asset = (Asset) assetList.get(i);
				System.out.println("Asset: " + asset.toString());
			}

			System.out.println("AssetRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}