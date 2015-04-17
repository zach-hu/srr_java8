package com.tsa.puridiom.rfqline.tasks.tests;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

public class RfqLineBidsTest
{
	public static void  main (String[] args) throws Exception
	{
		System.out.println("starting...");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("rfqline-retrieve-bids.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("RfqBid_icRfqHeader", "602703700000");
			incomingRequest.put("vendorId", "VENDOR-9999");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
			System.out.println("done!");
		}
		catch (Exception e)
		{
			System.out.println("errors-");
			e.printStackTrace();
		}
	}
}