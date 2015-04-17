package com.tsa.puridiom.catalog.tasks.tests;


import java.util.*;

import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.service.error.ErrorHandlingService;

/**
 * @author JEFF 
 */
public class CatalogRetrieveWhereTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Catalog with where clause") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-retrieve-where.xml") ;
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("where", "where cat.owner = 'JEFF'");
			//incomingRequest.put("where", "where cat.title like 'Te%'");
			process.executeProcess(incomingRequest);
			
			ArrayList qry = (ArrayList)incomingRequest.get("CatalogRetrieveWhere");
            StringBuffer data = new StringBuffer("") ;
	        int i = 0;
			for (Iterator it = qry.iterator(); it.hasNext(); ) 
			 {
				 data.setLength(0) ;
				 data.append("row: " + i++ + ": ");
				 Catalog catalog = (Catalog) it.next() ;
				 data.append(catalog.getCatalogId());
				 System.out.println(data.toString()) ;	            	
			 } 
            
			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  Catalog Retrieve By ID test");
		}
		catch(Exception exception)
		{
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("catalogitemAddTest", "executeTask", null, exception);
		}
	}
}
