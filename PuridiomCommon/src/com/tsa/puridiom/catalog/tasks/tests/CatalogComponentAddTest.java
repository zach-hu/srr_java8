package com.tsa.puridiom.catalog.tasks.tests;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogComponentAddTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Adding catalogComponent record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogComponent-add.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "test");
			incomingRequest.put("itemNumber",	"item");
			incomingRequest.put("sequence", new Integer(3));
			
			process.executeProcess(incomingRequest);

			CatalogComponent catalogComponent = (CatalogComponent)incomingRequest.get("catalogComponentadd") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogComponent Add process test");
			System.out.println(catalogComponent.toString()) ;
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogComponentAddTest", "executeTask", null, exception);
		}
	}
}
