package com.tsa.puridiom.invvendor.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvVendorRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invvendor-retrieve-by.xml");
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