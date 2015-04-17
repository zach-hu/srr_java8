package com.tsa.puridiom.address.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AddressDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("address-delete.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("Address_addressType", "TULA1");
			incomingRequest.put("Address_addressCode", "DEFAULT");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}