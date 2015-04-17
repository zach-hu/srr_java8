package com.tsa.puridiom.shipto.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ShipToRetrieveByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("shipto-retrieve-by-line.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("ShipTo_icHeader","549101601000") ;
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}