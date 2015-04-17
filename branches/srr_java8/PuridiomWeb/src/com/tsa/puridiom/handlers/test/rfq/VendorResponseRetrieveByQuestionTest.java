package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class VendorResponseRetrieveByQuestionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			VendorResponseRetrieveByQuestionHandler test = new VendorResponseRetrieveByQuestionHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icQuestion", "781911900000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("VendorResponseRetrieveByQuestion - SUCCESS");
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