package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionHeaderBillToRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionHeaderBillToRetrieveByIdHandler test = new RequisitionHeaderBillToRetrieveByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "472282000000");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			System.out.println("VIEW PAGE = " + (String) incomingRequest.get("viewPage"));
			
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			Address address = reqHeader.getBillToAddress();
			System.out.println("Bill To Address: " + address.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}