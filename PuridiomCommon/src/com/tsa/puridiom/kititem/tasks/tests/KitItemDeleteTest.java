package com.tsa.puridiom.kititem.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.kititem.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class KitItemDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			KitItemDeleteById kititemdelete = new KitItemDeleteById();
			Map incomingRequest = new HashMap();

			KitItem kitItem = new KitItem();
			KitItemPK pk = new KitItemPK();
			pk.setParentCatalogId("INV");
			pk.setParentItemNumber("F-1000");
			pk.setChildCatalogId("1000");
			pk.setChildItemNumber("M-1000");
			kitItem.setComp_id(pk);

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("kitItem", kitItem);

			kititemdelete.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
			System.out.println("KitItemDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}