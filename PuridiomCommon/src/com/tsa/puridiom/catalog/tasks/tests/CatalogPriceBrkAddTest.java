package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogPriceBrkAddTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Adding catalogPriceBrk record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-add.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "test");
			incomingRequest.put("itemNumber",	"item");
			incomingRequest.put("sequence", new Integer(3));
			incomingRequest.put("status", "02");
			
			process.executeProcess(incomingRequest);

			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk)incomingRequest.get("catalogPriceBrkadd") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogPriceBrk Add process test");
			System.out.println(catalogPriceBrk.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogPriceBrkaddTest", "executeTask", null, exception);
		}
	}
}
