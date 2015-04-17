package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogItemUpdateTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Updating CatalogItem record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogitem-update.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"310");
			incomingRequest.put("owner", "Batman&robin");
			
			process.executeProcess(incomingRequest);

			CatalogItem catalogitem = (CatalogItem)incomingRequest.get("CatalogItemUpdate") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogItem Update process test");
			System.out.println(catalogitem.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemUpdateTest", "executeTask", null, exception);
		}
	}
}
