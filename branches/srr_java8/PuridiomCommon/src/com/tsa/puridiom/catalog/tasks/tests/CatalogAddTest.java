package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import java.util.*;

/**
 * @author JEFF 
 */
public class CatalogAddTest 
{

	public static void main(String[] args) 
	{
		try 
		{
		
			System.out.println("Adding Catalog record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-add.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "5454");
			//incomingRequest.put("icpoheader",	new BigDecimal("10"));
			incomingRequest.put("title",	"title test");
			incomingRequest.put("owner", "bobie");
			
			process.executeProcess(incomingRequest);

			Catalog catalog = (Catalog)incomingRequest.get("catalogadd") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogAdd process test");
			System.out.println(catalog.toString()) ;

		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalog.AddTest", "executeTask", null, exception);
		}
	}
}
