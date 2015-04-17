package com.tsa.puridiom.handlers.test.catalog;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Kelli 
 */
public class CatalogItemRetrieveAllTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving CatalogItem All") ;
			CatalogItemRetrieveAllHandler test = new CatalogItemRetrieveAllHandler() ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			
			System.out.println(incomingRequest);
			
			System.out.println("CatalogItemRetrieveAll - " + (String) incomingRequest.get("viewPage"));

			if (incomingRequest.containsKey("catalogItemList"))
			{
				List list = (List) incomingRequest.get("catalogItemList");
				System.out.println("Found " + list.size() + " Catalog Items");
				
				for (int i=0; i < list.size(); i++) 
				{
					CatalogItem item = (CatalogItem) list.get(i);
					System.out.println(i + ") " + item.getComp_id().getCatalogId() + " - " + item.getComp_id().getItemNumber());
					System.out.println("	Status = "+ item.getStatus());
				}
			}
            
			System.out.println("COMPLETE");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemAddTest", "executeTask", null, exception);
		}
	}
}
