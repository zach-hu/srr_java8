package com.tsa.puridiom.handlers.test.po;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoForwardTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("po-forward.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("PoHeader_icPoHeader", "1320025300000");
			incomingRequest.put("goforward", "Y");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}