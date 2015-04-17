package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineCreateFromRequisitionLineListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CreateLinesFromRequisitionLineListHandler test = new CreateLinesFromRequisitionLineListHandler();
			Map incomingRequest = new HashMap();
			String	icReqLines[] = {"1438075400026", "1438075200024", "1379342200002"};
			String quantities[] = {"2", "20", "200"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "RFQ");
			incomingRequest.put("icHeader", "756864200000");
			incomingRequest.put("RequisitionLine_icReqLine", icReqLines);
			incomingRequest.put("quantity", quantities);
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineCreateFromRequisitionLineList - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}