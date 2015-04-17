package com.tsa.puridiom.catalogpricebrk.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CatalogPriceBrkDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("catalogpricebrk-delete.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("CatalogPriceBrk_itemNumber", "10");
			incomingRequest.put("CatalogPriceBrk_catalogId", "936");
			incomingRequest.put("CatalogPriceBrk_sequence", "4");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}