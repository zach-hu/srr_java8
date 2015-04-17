package com.tsa.puridiom.assetnote.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetnote.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class AssetNoteRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetNoteRetrieveAll test = new AssetNoteRetrieveAll();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");

			System.out.println("Database Status: " + dbs.getStatus());

			List assetNoteList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < assetNoteList.size(); i++)
			{
				AssetNote assetNote = (AssetNote) assetNoteList.get(i);
				System.out.println("AssetNote: " + assetNote.toString());
			}

			System.out.println("AssetNoteRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}