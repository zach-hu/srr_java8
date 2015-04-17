package com.tsa.puridiom.assetlocation.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetlocation.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AssetLocationAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetLocationAdd test = new AssetLocationAdd();
			Map incomingRequest = new HashMap();
		
			AssetLocation assetLocation = new AssetLocation();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("assetLocation", assetLocation);
		
			assetLocation = (AssetLocation) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("AssetLocationAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("AssetLocationAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}