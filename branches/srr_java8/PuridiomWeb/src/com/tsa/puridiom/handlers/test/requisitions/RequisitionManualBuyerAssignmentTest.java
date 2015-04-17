package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionManualBuyerAssignmentTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionManualBuyerAssignmentHandler test = new RequisitionManualBuyerAssignmentHandler();
			Map incomingRequest = new HashMap();
			
			String icReqHeader[] = {"1319399600020", "1327453500031"};
			
			// Assignment by Header
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
			incomingRequest.put("assignTo", "TAMY");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			
			System.out.println("RequisitionManualBuyerAssignment - " + viewPage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}