package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineDateRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqLineDataRetrieveByIdHandler test = new RfqLineDataRetrieveByIdHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqLine_icRfqLine", "643991300000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineDataRetrieveById - SUCCESS");
			}
			
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			if (rfqLine != null) {
				List commentList = rfqLine.getDocCommentList();
				if (commentList != null)
				{
					for (int i = 0; i < commentList.size(); i++)
					{
						DocComment comment = (DocComment) commentList.get(i);
						System.out.println(comment.toString());
					}
				}
				List billToList = rfqLine.getBillToList();
				if (billToList != null)
				{
					for (int i = 0; i < billToList.size(); i++)
					{
						BillTo billTo = (BillTo) billToList.get(i);
						System.out.println(billTo.toString());
						Address billToAddress = billTo.getBillToAddress();
						if (billToAddress != null)
						{
							System.out.println(billToAddress.toString());
						}
						else
						{
							System.out.println("BILL TO ADDRESS NOT FOUND.");
						}
					}
				}
				List shipToList = rfqLine.getShipToList();
				if (shipToList != null)
				{
					for (int i = 0; i < shipToList.size(); i++)
					{
						ShipTo shipTo = (ShipTo) shipToList.get(i);
						System.out.println(shipTo.toString());
						Address shipToAddress = shipTo.getShipToAddress();
						if (shipToAddress != null)
						{
							System.out.println(shipToAddress.toString());
						}
						else
						{
							System.out.println("SHIP TO ADDRESS NOT FOUND.");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}