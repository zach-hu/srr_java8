package com.tsa.puridiom.address.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AddressRetrieveBySupplierTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("address-retrieve-by-supplier.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("Address_addressType", "TULA");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}