package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineRequisitionItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionItemLookupHandler test = new RequisitionItemLookupHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("icHeader", "3736700600053");
			incomingRequest.put("RfqLine_icRfqHeader", "3736700600053");
			incomingRequest.put("RequisitionLine_icReqLine", "3917200200063");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("formType", "RFQ");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineRequisitionItemLookup - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}