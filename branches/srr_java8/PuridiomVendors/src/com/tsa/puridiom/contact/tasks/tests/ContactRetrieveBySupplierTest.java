package com.tsa.puridiom.contact.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ContactRetrieveBySupplierTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("contact-retrieve-by-supplier.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("Contact_vendorId", "TULA");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}