package com.tsa.puridiom.kititem.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.kititem.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class KitItemRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			KitItemRetrieveBy kititemretrieveby = new KitItemRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("KitItem_parentCatalogId", "INV");
			incomingRequest.put("KitItem_parentItemNumber", "F-1000");

			List list = (List) kititemretrieveby.executeTask(incomingRequest);

			if (list != null)
			{
				for (int i=0; i < list.size(); i++)
				{
					KitItem kitItem = (KitItem) list.get(i);
					System.out.println(kitItem.toString());
				}
			}

			System.out.println(incomingRequest);
			System.out.println("KitItemRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}