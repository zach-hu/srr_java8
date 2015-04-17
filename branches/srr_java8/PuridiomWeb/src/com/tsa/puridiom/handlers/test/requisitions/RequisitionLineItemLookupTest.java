package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionLineItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionLineItemLookupHandler test = new RequisitionLineItemLookupHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "1084365400000");
			//incomingRequest.put("RequisitionLine_itemNumber", "energy");
			incomingRequest.put("RequisitionLine_itemNumber", "test");
			incomingRequest.put("RequisitionLine_itemLocation", "1087888");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineItemLookup - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}