package com.tsa.puridiom.kititem.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.kititem.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class KitItemRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			KitItemRetrieveById kititemretrievebyid = new KitItemRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("KitItem_parentCatalogId", "INV");
			incomingRequest.put("KitItem_parentItemNumber", "F-1000");
			incomingRequest.put("KitItem_childCatalogId", "1000");
			incomingRequest.put("KitItem_childItemNumber", "M-1000");

			KitItem kitItem = (KitItem) kititemretrievebyid.executeTask(incomingRequest);
			System.out.println("KitItem - " + kitItem.toString());
			System.out.println(incomingRequest);
			System.out.println("KitItemTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}