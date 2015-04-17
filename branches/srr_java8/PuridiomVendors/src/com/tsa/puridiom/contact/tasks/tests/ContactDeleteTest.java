package com.tsa.puridiom.contact.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ContactDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("contact-delete-by-id.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("Contact_contactCode", "004");
			incomingRequest.put("Contact_vendorId", "TULA");
			incomingRequest.put("Contact_contactType", "OTHER");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}