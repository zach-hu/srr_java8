package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionLineRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionLineRetrieveHandler test = new RequisitionLineRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionLine_icReqLine", "1120759500010");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RequisitionLineRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("requisitionLine"))
			{
				RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
				System.out.println(reqLine.toString());
				if (reqLine != null)
				{
					List accountList = reqLine.getAccountList();
					if (accountList != null)
					{
						for (int i = 0; i < accountList.size(); i++)
						{
							Account account = (Account) accountList.get(i);
							System.out.println(account.toString());
						}
					}
					List commentList = reqLine.getDocCommentList();
					if (commentList != null)
					{
						for (int i = 0; i < commentList.size(); i++)
						{
							DocComment comment = (DocComment) commentList.get(i);
							System.out.println(comment.toString());
						}
					}
					List billToList = reqLine.getBillToList();
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
					List shipToList = reqLine.getShipToList();
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}