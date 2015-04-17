package com.tsa.puridiom.handlers.test.receipts;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class ReceiptUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptUpdateHandler test = new ReceiptUpdateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("ReceiptHeader_icRecHeader", "2899169000000");
			incomingRequest.put("ReceiptHeader_icPoHeader", "1820627600008");
			incomingRequest.put("ReceiptHeader_pkgsReceived", "9");
			incomingRequest.put("ReceiptHeader_forwardTo", "KELLI");
			
			String	icRecHeaderArray[] = {"2899169000000", "2899169000000"};
			String	icRecLineArray[] = {"5599", "55501"};
			String	icPoLineArray[] = {"1820628200011", "1820628200013"};
			String	qtyReceivedArray[] = {"20", "11"};
			
			incomingRequest.put("ReceiptLine_icRecHeader", icRecHeaderArray);
			incomingRequest.put("ReceiptLine_icRecLine", icRecLineArray);
			incomingRequest.put("ReceiptLine_icPoLine", icPoLineArray);
			incomingRequest.put("ReceiptLine_qtyReceived", qtyReceivedArray);
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptHeaderUpdate - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}