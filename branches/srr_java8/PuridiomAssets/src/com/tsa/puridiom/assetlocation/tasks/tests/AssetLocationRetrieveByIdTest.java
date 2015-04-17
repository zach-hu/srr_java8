package com.tsa.puridiom.assetlocation.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetlocation.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetLocationRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetLocationRetrieveById test = new AssetLocationRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			AssetLocation assetLocation = (AssetLocation) test.executeTask(incomingRequest);

			System.out.println("AssetLocation: " + assetLocation.toString());
			System.out.println("AssetLocationRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}