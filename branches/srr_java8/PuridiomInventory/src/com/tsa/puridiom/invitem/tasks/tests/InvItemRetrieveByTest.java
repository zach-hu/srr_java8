package com.tsa.puridiom.invitem.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-by.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("InvItem.itemNumber", "test");
			incomingRequest.put("InvLocation.itemNumber", "test");
			incomingRequest.put("InvVendor.itemNumber", "test");			

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}