package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionHeaderRetrieveByIdHandler test = new RequisitionHeaderRetrieveByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "472282000000");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			System.out.println("VIEW PAGE = " + (String) incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}