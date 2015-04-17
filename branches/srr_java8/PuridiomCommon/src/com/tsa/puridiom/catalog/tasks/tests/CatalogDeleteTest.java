package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import java.util.*;

/**
 * @author JEFF 
 */
public class CatalogDeleteTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Deleting Catalog record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-delete.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "test");
			incomingRequest.put("itemNumber", "item");
			
			process.executeProcess(incomingRequest);

			Catalog catalog = (Catalog)incomingRequest.get("catalogdelete") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogDelete process test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalog.CatalogDeleteTest", "executeTask", null, exception);
		}
	}
}
