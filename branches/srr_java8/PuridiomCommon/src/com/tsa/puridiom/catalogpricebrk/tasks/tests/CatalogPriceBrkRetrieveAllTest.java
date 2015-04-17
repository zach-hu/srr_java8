package com.tsa.puridiom.catalogpricebrk.tasks.tests;
import java.util.*;
import com.tsagate.foundation.processengine.*;

public class CatalogPriceBrkRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("catalogpricebrk-retrieve-all.xml") ;
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}