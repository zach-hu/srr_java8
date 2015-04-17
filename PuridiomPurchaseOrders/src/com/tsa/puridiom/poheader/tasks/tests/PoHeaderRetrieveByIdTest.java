package com.tsa.puridiom.poheader.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-by-id.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("PoHeader_ic_po_header", "2");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}