package com.tsa.puridiom.graphs.tasks.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.graphs.tasks.TopSuppliers;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;

public class TopSuppliersQueryTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("BSC04P");
			dbs.startTransaction();

			TopSuppliers topSuppliersTask = new TopSuppliers();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "BSC04P");
			incomingRequest.put("userId", "RRAMOS");

			Object ret = topSuppliersTask.executeTask(incomingRequest);

			if (topSuppliersTask.getStatus() == Status.SUCCEEDED) {
				List suppliers = (List)ret;
				for (Iterator iter = suppliers.iterator(); iter.hasNext();) {
					System.out.println(iter.next());

				}
			}
			System.out.println("Status: " + topSuppliersTask.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}