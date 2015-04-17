package com.tsa.puridiom.poheader.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("po-retrieve.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("PoHeader_icPoHeader", "1179510800000");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}