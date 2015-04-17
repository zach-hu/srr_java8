package com.tsa.puridiom.disbheader.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("disbheader-retrieve-by-id.xml");
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