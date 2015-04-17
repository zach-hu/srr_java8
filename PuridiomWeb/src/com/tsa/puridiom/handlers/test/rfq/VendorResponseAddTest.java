package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class VendorResponseAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			VendorResponseAddHandler test = new VendorResponseAddHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", "756864200000");
			incomingRequest.put("VendorResponse_icQuestion", "781911900001");
			incomingRequest.put("VendorResponse_vendorId", "OFFICEMAX");
			incomingRequest.put("VendorResponse_response", "Y");
			incomingRequest.put("VendorResponse_textResponse", "");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("VendorResponseAdd - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}