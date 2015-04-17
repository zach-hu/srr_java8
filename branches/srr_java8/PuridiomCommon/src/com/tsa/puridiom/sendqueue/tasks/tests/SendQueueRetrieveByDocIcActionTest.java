package com.tsa.puridiom.sendqueue.tasks.tests;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.*;

public class SendQueueRetrieveByDocIcActionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			System.out.println("start");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reset_sendqueue_hten.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("organizationId", "TEST");

			/* The following fields will need to be populated */

			incomingRequest.put("SendQueue_docic", "6778383300056");
			incomingRequest.put("SendQueue_action", "HTEN");


			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);

			System.out.println("SendQueueAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}