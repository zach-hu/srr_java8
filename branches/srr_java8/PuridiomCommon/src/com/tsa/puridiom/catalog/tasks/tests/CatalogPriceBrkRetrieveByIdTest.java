package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;

/**
 * @author JEFF
 *
 * Deletes a specific row from the CatalogPriceBrk table
 */
public class CatalogPriceBrkRetrieveByIdTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieve By ID catalogPriceBrk record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-retrieve-ById.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"10");
			incomingRequest.put("sequence", new Integer(3));
			
			process.executeProcess(incomingRequest);

			CatalogPriceBrkDisplayValues.display((List)incomingRequest.get("CatalogPriceBrkRetrieveById"));
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogPriceBrk Retrieve byID process test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogPriceBrkRetrieveByIdTest", "executeTask", null, exception);
		}
	}
}
