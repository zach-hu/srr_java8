package com.tsa.puridiom.handlers.test.disbursements;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.OtcSaveHandler;

public class OtcSaveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			OtcSaveHandler handler = new OtcSaveHandler();
			Map incomingRequest = new HashMap();
			incomingRequest.put("DisbHeader_icDsbHeader", "2648019900000");
			handler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}