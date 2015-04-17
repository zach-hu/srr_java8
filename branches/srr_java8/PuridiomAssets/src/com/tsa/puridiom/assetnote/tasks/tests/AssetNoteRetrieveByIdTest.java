package com.tsa.puridiom.assetnote.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetnote.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetNoteRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetNoteRetrieveById test = new AssetNoteRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			AssetNote assetNote = (AssetNote) test.executeTask(incomingRequest);

			System.out.println("AssetNote: " + assetNote.toString());
			System.out.println("AssetNoteRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}