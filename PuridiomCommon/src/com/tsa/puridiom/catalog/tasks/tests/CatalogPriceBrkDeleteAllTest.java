package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;

/**
 * @author JEFF
 *
 * Deletes a specific row from the CatalogPriceBrk table
 */
public class CatalogPriceBrkDeleteAllTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Delete All catalogPriceBrk record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-delete-all.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"10");
			//incomingRequest.put("sequence", new Integer(3));
			
			process.executeProcess(incomingRequest);

			Object catalogPriceBrk = incomingRequest.get("catalogPriceBrkDeleteAll") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogPriceBrk Delete process test");
			System.out.println(catalogPriceBrk.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogPriceBrkaddTest", "executeTask", null, exception);
		}
	}
}
