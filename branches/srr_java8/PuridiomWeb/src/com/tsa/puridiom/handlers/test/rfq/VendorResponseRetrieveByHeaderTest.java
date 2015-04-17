package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class VendorResponseRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			VendorResponseRetrieveByHeaderHandler test = new VendorResponseRetrieveByHeaderHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", "756864200000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("VendorResponseRetrieveByHeader - SUCCESS");
			}
			
			List list = (List) incomingRequest.get("vendorResponseList");
			for (int i=0; i < list.size(); i++)
			{
				VendorResponse vendorResponse = (VendorResponse) list.get(i);
				System.out.println(vendorResponse.getComp_id().getIcQuestion() + "/" + vendorResponse.getComp_id().getVendorId() + " - " + vendorResponse.getTextResponse());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}