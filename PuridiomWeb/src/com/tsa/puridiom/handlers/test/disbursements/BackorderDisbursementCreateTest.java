package com.tsa.puridiom.handlers.test.disbursements;

import com.tsa.puridiom.handlers.BackorderDisbCreateHandler;

import java.util.*;

public class BackorderDisbursementCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			BackorderDisbCreateHandler handler = new BackorderDisbCreateHandler();
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionHeader_icReqHeader", "574074000000");
			handler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}