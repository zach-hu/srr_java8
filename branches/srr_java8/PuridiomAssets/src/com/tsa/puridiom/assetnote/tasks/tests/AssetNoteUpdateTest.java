package com.tsa.puridiom.assetnote.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.assetnote.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AssetNoteUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			AssetNoteUpdate test = new AssetNoteUpdate();
			Map incomingRequest = new HashMap();
		
			AssetNote assetNote = new AssetNote();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("assetNote", assetNote);
		
			assetNote = (AssetNote) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("AssetNoteUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("AssetNoteUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}