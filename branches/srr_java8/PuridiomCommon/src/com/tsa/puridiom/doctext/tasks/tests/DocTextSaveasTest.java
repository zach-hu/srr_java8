package com.tsa.puridiom.doctext.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DocTextSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			//PuridiomProcess process = processLoader.loadProcess("doctext-saveas.xml");
			PuridiomProcess process = processLoader.loadProcess("doctext-retrieve-by-id.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocText_icText", "9");
			
			process.executeProcess(incomingRequest);
						
			System.out.println("RESULT: " + process.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}