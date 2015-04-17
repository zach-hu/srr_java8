package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionHeaderVendorRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionHeaderVendorRetrieveByIdHandler test = new RequisitionHeaderVendorRetrieveByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "472282000000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			System.out.println("RequisitionHeaderVendorRetrieveById - " +  (String) incomingRequest.get("viewPage"));
			
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			Address address = reqHeader.getVendorAddress();
			System.out.println("Vendor Address: " + address.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}