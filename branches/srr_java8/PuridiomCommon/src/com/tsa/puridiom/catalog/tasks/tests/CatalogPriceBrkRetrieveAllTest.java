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
public class CatalogPriceBrkRetrieveAllTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving CatalogPriceBrk All") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-retrieve-all.xml") ;
			Map incomingRequest = new HashMap() ;
			process.executeProcess(incomingRequest);
			
			ArrayList qry = (ArrayList)incomingRequest.get("CatalogPriceBrkRetrieveAll");
			CatalogPriceBrkDisplayValues.display(qry);
            
			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  CatalogPriceBrk Retrieve All test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogPriceBrkRetrieveAllTest", "executeTask", null, exception);
		}
	}
}
