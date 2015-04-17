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
public class CatalogItemRetrieveWhereTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving CatalogItem Where") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogitem-retrieve-where.xml") ;
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("where", " where catItem.description like 'Short%' ");
			process.executeProcess(incomingRequest);
			
			ArrayList qry = (ArrayList)incomingRequest.get("CatalogItemRetrieveWhere");
            CatalogItemDisplayValues.display(qry);
            
			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  CatalogItem Retrieve Where test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemWhereTest", "executeTask", null, exception);
		}
	}
}
