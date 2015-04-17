package com.tsa.puridiom.catalog.tasks.tests;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.service.error.ErrorHandlingService;

/**
 * @author JEFF 
 */
public class CatalogItemRetrieveByPKTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving CatalogItem By ID") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogitem-retrieve-byPK.xml") ;
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber", "10");
			process.executeProcess(incomingRequest);
			
			ArrayList qry = (ArrayList)incomingRequest.get("CatalogItemRetrieveByPK");
			CatalogItemDisplayValues.display(qry); 
            
			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  CatalogItem Retrieve By ID test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemAddTest", "executeTask", null, exception);
		}
	}
}
