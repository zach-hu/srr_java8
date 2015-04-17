package com.tsa.puridiom.address.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AddressAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("address-add.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}