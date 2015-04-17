package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF
 *
 * Deletes a specific row from the CatalogPriceBrk table
 */
public class CatalogPriceBrkDeleteTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Delete catalogPriceBrk record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-delete.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"10");
			//incomingRequest.put("sequence", new Integer(3));
			
			process.executeProcess(incomingRequest);

			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk)incomingRequest.get("catalogPriceBrkDelete") ;
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
