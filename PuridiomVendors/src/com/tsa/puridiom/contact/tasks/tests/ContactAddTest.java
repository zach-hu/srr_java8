package com.tsa.puridiom.contact.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ContactAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("contact-add.xml");
			Map incomingRequest = new HashMap();
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