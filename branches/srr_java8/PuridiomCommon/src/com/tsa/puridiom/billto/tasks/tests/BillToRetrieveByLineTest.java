package com.tsa.puridiom.billto.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BillToRetrieveByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("billto-retrieve-by-line.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("BillTo_icHeader","549101601000") ;
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}