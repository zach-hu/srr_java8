package com.tsa.puridiom.poheader.tasks.tests;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class PoTotalReleasedTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("po-total-released.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("poNumber", "000027");
			incomingRequest.put("blanketIc", new BigDecimal("1320412600001"));

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}