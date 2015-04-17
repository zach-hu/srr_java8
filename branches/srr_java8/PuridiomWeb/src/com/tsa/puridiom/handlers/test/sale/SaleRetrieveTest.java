package com.tsa.puridiom.handlers.test.sale;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class SaleRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			SaleRetrieveHandler test = new SaleRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("SaleHeader_icSaleHeader", "6329061600000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("SaleRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("saleHeader"))
			{
			    SaleHeader saleHeader = (SaleHeader) incomingRequest.get("saleHeader");
				System.out.println(saleHeader.toString());
				if (saleHeader != null)
				{
					List comments = saleHeader.getDocCommentList();
					if (comments != null)
					{
						for (int i = 0; i < comments.size(); i++)
						{
							DocComment comment = (DocComment) comments.get(i);
							DocText text = comment.getDocText();
							System.out.println(comment.getCommentTitle() + " - " + text.getStdText());
						}
					}
					
					SaleLine saleLine = saleHeader.getSaleLine();
					if (saleLine != null) {
					    System.out.println(saleLine.toString());
					}
/*					List bids = rfqHeader.getSaleBidList();
					if (bids != null)
					{
						for (int ib = 0; ib < bids.size(); ib++)
						{
							SaleBid bid = (SaleBid) bids.get(ib);
							System.out.println(bid.getComp_id().getBuyer() + " - " + String.valueOf(bid.getUnitPrice()));
						}
					}
*/
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}