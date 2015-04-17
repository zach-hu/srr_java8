package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import java.util.*;

/**
 * @author JEFF 
 */
public class CatalogItemRetrieveByIdTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving CatalogItem By Id") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-item-retrieve-by-id.xml") ;
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("CatalogItem_catalogId", "936");
			incomingRequest.put("CatalogItem_itemNumber", "test");
			process.executeProcess(incomingRequest);
			
			CatalogItem catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			System.out.println("Catalog Item: " + String.valueOf(catalogItem));

			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  CatalogItem Retrieve By Id test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("CatalogItemRetrieveByIdTest", "executeTask", null, exception);
		}
	}
}
