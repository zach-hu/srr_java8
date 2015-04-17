package com.tsa.puridiom.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CatalogItemExplodeKitTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("catalogitem-explode-kit.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "REQ");
			incomingRequest.put("icHeader", "1084365400000");

			incomingRequest.put("CatalogItem_catalogId", "OFFICE");
			incomingRequest.put("CatalogItem_itemNumber", "10");
			incomingRequest.put("quantity", "2");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			process.executeProcess(incomingRequest);
			
			System.out.println(incomingRequest);
			System.out.println("CatalogItemExplodeKitTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}