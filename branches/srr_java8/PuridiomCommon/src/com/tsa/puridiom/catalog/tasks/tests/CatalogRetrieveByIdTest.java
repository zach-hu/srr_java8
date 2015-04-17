package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author JEFF 
 */
public class CatalogRetrieveByIdTest 
{

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving Catalog By ID") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-retrieve-by-id.xml") ;
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("Catalog_catalogId", "BOISE-WEB");
			process.executeProcess(incomingRequest);
			
			Catalog catalog = (Catalog) incomingRequest.get("catalog");

			System.out.println("Catalog: " + String.valueOf(catalog));            
			System.out.println("process status: " + process.getStatus());
			System.out.println("end of  Catalog Retrieve By ID test");
		}
		catch(Exception exception)
		{
			System.out.println(exception.toString());
		}
	}
}
