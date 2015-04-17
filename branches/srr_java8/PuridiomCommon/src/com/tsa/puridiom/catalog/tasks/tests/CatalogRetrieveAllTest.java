package com.tsa.puridiom.catalog.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author JEFF 
 */
public class CatalogRetrieveAllTest 
{
	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Retrieving Catalog all") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalog-retrieve-all.xml") ;
			Map incomingRequest = new HashMap() ;

			process.executeProcess(incomingRequest);
			ArrayList qry = (ArrayList)incomingRequest.get("catalogList");
            StringBuffer data = new StringBuffer("") ;
	        for (Iterator it = qry.iterator(); it.hasNext(); ) 
	        {
	        	data.setLength(0) ;
	            Catalog catalog = (Catalog) it.next() ;
	            data.append(catalog.getCatalogId());
				System.out.println(data.toString()) ;	            	
	        } 
            
			System.out.println(process.getStatus());
			System.out.println("end of  Property Retrieve section test");
		}
		catch(Exception exception)
		{
			System.out.println(exception.toString());
		}
	}
}
