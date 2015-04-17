package com.tsa.puridiom.catalogpricebrk.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CatalogPriceBrkAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("catalogpricebrk-add.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("CatalogPriceBrk_catalogId", "936");
			incomingRequest.put("CatalogPriceBrk_itemNumber", "10");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}