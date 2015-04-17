package com.tsa.puridiom.handlers.test.disbursements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.handlers.DisbursementCreatePreviewHandler;

public class DisbursementCreatePreviewTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DisbursementCreatePreviewHandler handler = new DisbursementCreatePreviewHandler();
			
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionHeader_icReqHeader", "2389057700000");
			incomingRequest.put("preview", "TRUE");
			
			handler.handleRequest(incomingRequest);
			
			//System.out.println(incomingRequest);
			DisbHeader head = (DisbHeader)incomingRequest.get("disbHeader");
			List lines = (List)incomingRequest.get("disbLines");
			System.out.println("header: " + head.toString());
			System.out.println("lines: " + lines);
			System.out.println("done!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}