package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;

import java.util.*;

public class RequisitionCancelTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionCancelHandler test = new RequisitionCancelHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "1379396300004");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RequisitionRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("requisitionHeader"))
			{
				RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
				System.out.println(reqHeader.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}