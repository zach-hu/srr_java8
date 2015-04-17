package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.catalog.tasks.CatalogItemRetrieveBy;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import java.util.*;

/**
 * @author Kelli
 */
public class CatalogItemRetrieveByTest
{

	public static void main(String[] args)
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			CatalogItemRetrieveBy test = new CatalogItemRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("CatalogItem_dateEntered", "2004-30-12");

			List list = (List) test.executeTask(incomingRequest);

			if (list != null)
			{
				for (int i=0; i < list.size(); i++)
				{
					CatalogItem catalogItem = (CatalogItem) list.get(i);
					System.out.println("Catalog Item: " + String.valueOf(catalogItem));
				}
			}
			System.out.println("database status: " + dbs.getStatus());
			System.out.println("end of  CatalogItem Retrieve By test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("CatalogItemRetrieveByTest", "executeTask", null, exception);
		}
	}
}
