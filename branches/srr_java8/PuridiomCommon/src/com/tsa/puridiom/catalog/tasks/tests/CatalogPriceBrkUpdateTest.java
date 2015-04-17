package com.tsa.puridiom.catalog.tasks.tests;

import java.math.BigDecimal;
import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;
import com.tsa.puridiom.entity.*;

/**
 * @author JEFF 
 */
public class CatalogPriceBrkUpdateTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Updating catalogPriceBrk record") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogPriceBrk-update.xml") ;
			Map incomingRequest = new HashMap() ;
			
			incomingRequest.put("catalogID", "936");
			incomingRequest.put("itemNumber",	"10");
			incomingRequest.put("sequence", new Integer(2));
			incomingRequest.put("status", "06");
			incomingRequest.put("unitPrice", new BigDecimal("10.5"));
			
			process.executeProcess(incomingRequest);

			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk)incomingRequest.get("catalogPriceBrkUpdate") ;
			System.out.println("status: " + process.getStatus());
			System.out.println("end of catalogPriceBrk Update process test");
			System.out.println(catalogPriceBrk.toString()) ;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogPriceBrkUpdateTest", "executeTask", null, exception);
		}
	}
}
