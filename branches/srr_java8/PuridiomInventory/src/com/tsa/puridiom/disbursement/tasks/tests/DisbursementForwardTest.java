package com.tsa.puridiom.disbursement.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbursementForwardTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("disb-forward.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("DisbHeader_icDsbHeader", "697885300000");
										  
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}