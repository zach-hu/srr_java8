package com.tsa.puridiom.assetservice.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetservice.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetServiceRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetServiceRetrieveById test = new AssetServiceRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			AssetService assetService = (AssetService) test.executeTask(incomingRequest);

			System.out.println("AssetService: " + assetService.toString());
			System.out.println("AssetServiceRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}