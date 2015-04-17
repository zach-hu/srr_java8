package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogItemAddTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Adding CatalogItem record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-item-add.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "sobe");
			incomingRequest.put("itemNumber",	"energy2");
			incomingRequest.put("owner", "bob");
			incomingRequest.put("cost", "1.50");
			
			process.executeProcess(incomingRequest);

			CatalogItem catalogitem = (CatalogItem)incomingRequest.get("catalogItemadd") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogItemAdd process test");
			System.out.println(catalogitem.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemAddTest", "executeTask", null, exception);
		}
	}
}
