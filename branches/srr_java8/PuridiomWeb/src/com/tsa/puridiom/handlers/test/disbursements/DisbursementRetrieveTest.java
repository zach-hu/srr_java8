package com.tsa.puridiom.handlers.test.disbursements;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.DisbursementRetrieveHandler;

public class DisbursementRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DisbursementRetrieveHandler handler = new DisbursementRetrieveHandler();
			Map incomingRequest = new HashMap();
			incomingRequest.put("DisbHeader_icDsbHeader", "1336741000010");
			
			handler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}