package com.tsa.puridiom.handlers.test.po;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.PoGetBlanketInfoHandler;
import com.tsa.puridiom.po.tasks.PoBlanketInfo;

public class PoGetDeliveryReleaseInfoTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    PoGetBlanketInfoHandler test = new PoGetBlanketInfoHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("PoLine_icRelKey", "3183262600004");
			
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptCreate - SUCCESS");
			}
			if (incomingRequest.containsKey("blanketInfo"))
			{
				PoBlanketInfo blanketInfo = (PoBlanketInfo) incomingRequest.get("blanketInfo");
				if (blanketInfo != null)
				{
					System.out.println("ReceiptHeader: " + blanketInfo.toString());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}