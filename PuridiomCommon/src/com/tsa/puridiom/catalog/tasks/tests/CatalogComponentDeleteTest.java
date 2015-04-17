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
public class CatalogComponentDeleteTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Delete catalogComopnent record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogComponent-delete.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"10");
			incomingRequest.put("sequence", new Integer(1));
			
			process.executeProcess(incomingRequest);

			CatalogComponent catalogComponent = (CatalogComponent)incomingRequest.get("catalogcomponentdelete") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogPriceBrk Delete process test");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogComponentDeleteTest", "executeTask", null, exception);
		}
	}
}
