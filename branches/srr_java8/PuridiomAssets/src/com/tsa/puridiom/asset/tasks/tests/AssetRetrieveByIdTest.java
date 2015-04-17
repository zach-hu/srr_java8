package com.tsa.puridiom.asset.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.asset.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetRetrieveById test = new AssetRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			Asset asset = (Asset) test.executeTask(incomingRequest);

			System.out.println("Asset: " + asset.toString());
			System.out.println("AssetRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}