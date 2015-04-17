package com.tsa.puridiom.catalog.tasks.tests;


import java.math.BigDecimal;
import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogUpdateTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Updating Catalog record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-update.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "testupdate");
			incomingRequest.put("icpoheader",	new BigDecimal("1090"));
			incomingRequest.put("owner",	"bob-");
			
			process.executeProcess(incomingRequest);

			Catalog catalog = (Catalog)incomingRequest.get("catalogupdate") ;
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
