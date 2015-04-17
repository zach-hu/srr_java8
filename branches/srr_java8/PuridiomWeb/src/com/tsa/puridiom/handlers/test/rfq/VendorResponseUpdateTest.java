package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class VendorResponseUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			VendorResponseUpdateHandler test = new VendorResponseUpdateHandler();
			Map incomingRequest = new HashMap();
			
			String	icRfqHeader[] = {"756864200000", "756864200000"};
			String	icQuestion[] = {"781911900001","781911900001"};
			String	vendorId[] = {"OFFICEMAX", "STAPLES"};
			String	response[] = {"Y","N"};
			String	textResponse[] = {"OFFICEMAX - See attachments.", "STAPLES - See attached document for special rates."};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", icRfqHeader);
			incomingRequest.put("VendorResponse_icQuestion", icQuestion);
			incomingRequest.put("VendorResponse_vendorId", vendorId);
			incomingRequest.put("VendorResponse_response", response);
			incomingRequest.put("VendorResponse_textResponse", textResponse);
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("VendorResponseUpdate - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}