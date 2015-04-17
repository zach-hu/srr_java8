package com.tsa.puridiom.sendqueue.tasks.tests;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.*;

public class SendQueueAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("userId", "JHUBBARD");

			/* The following fields will need to be populated */
			
			incomingRequest.put("SendQueue_doctype", "REQ");
			incomingRequest.put("SendQueue_docic", "1");
			incomingRequest.put("SendQueue_subject", "Testing sendqueue add");
			incomingRequest.put("SendQueue_messagetext", "Message text for sendqueue add");
			incomingRequest.put("SendQueue_sendfromtype", "E");
			incomingRequest.put("SendQueue_sendfrom", "hubbardj@tsagate.com");
			incomingRequest.put("SendQueue_sendtotype", "E");
			incomingRequest.put("SendQueue_sendto", "hubbardj@tsagate.com");
			
			incomingRequest.put("SendQueue_action", "EN");
			incomingRequest.put("SendQueue_datesent", "");
			incomingRequest.put("SendQueue_timesent", "");
			incomingRequest.put("SendQueue_attachment", "");
			incomingRequest.put("SendQueue_vendorId", "");

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