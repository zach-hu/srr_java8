package com.tsa.puridiom.handlers.test.receipts;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class ReceiptRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptRetrieveHandler test = new ReceiptRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("ReceiptHeader_icPoHeader", "99");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("receiptHeaderList"))
			{
				List receiptHeaderList = (List) incomingRequest.get("receiptHeaderList");
				if (receiptHeaderList != null)
				{
					for (int i = 0; i < receiptHeaderList.size(); i++)
					{
						ReceiptHeader receiptHeader = (ReceiptHeader) receiptHeaderList.get(i);
						
						System.out.println("********************");
						if (receiptHeader != null)
						{
							System.out.println("ReceiptHeader: " + receiptHeader.toString());
							List receiptLineList = receiptHeader.getReceiptLineList();
							for (int il = 0; il < receiptLineList.size(); il++)
							{
								ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
								System.out.println("ReceiptLine: " + receiptLine.toString());
							}
						}
					}
				}
				
				System.out.println("********************");
				System.out.println("********************");
				
				PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
				if (poHeader != null)
				{
					System.out.println("PoHeader: " + poHeader.toString());
					List poLineList = poHeader.getPoLineList();
					for (int il = 0; il < poLineList.size(); il++)
					{
						PoLine poLine = (PoLine) poLineList.get(il);
						System.out.println("PoLine: " + poLine.toString());
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