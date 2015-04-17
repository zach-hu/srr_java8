package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogItemDeleteTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Deleting CatalogItem record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogitem-delete.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"310");
			
			process.executeProcess(incomingRequest);

			CatalogItem catalogitem = (CatalogItem)incomingRequest.get("CatalogItemDelete") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogItem Delete process test");
			System.out.println(catalogitem.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemDeleteTest", "executeTask", null, exception);
		}
	}
}
