package com.tsa.puridiom.invlocation.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvLocationRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invlocation-retrieve-by-id.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("InvLocation_itemNumber", "test");
			incomingRequest.put("InvLocation_itemLocation", "60");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}