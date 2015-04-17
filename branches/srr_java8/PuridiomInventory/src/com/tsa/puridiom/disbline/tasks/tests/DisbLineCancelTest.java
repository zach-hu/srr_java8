package com.tsa.puridiom.disbline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbLineCancelTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("disb-cancel.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("DisbLine_icDsbLine", "818659000001");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}