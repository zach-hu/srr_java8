package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqRetrieveHandler test = new RfqRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
//			incomingRequest.put("RfqHeader_icRfqHeader", "602703700000");
			incomingRequest.put("RfqHeader_icRfqHeader", "842856900000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("rfqHeader"))
			{
				RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
				System.out.println(rfqHeader.toString());
				if (rfqHeader != null)
				{
					List comments = rfqHeader.getDocCommentList();
					if (comments != null)
					{
						for (int i = 0; i < comments.size(); i++)
						{
							DocComment comment = (DocComment) comments.get(i);
							DocText text = comment.getDocText();
							System.out.println(comment.getCommentTitle() + " - " + text.getStdText());
						}
					}
					
					List list = rfqHeader.getRfqLineList();
					for (int i = 0; i < list.size(); i++)
					{
						RfqLine rfqLine = (RfqLine) list.get(i);
						System.out.println(rfqLine.toString());
						comments = rfqLine.getDocCommentList();
						if (comments != null)
						{
							for (int ic = 0; ic < comments.size(); ic++)
							{
								DocComment comment = (DocComment) comments.get(ic);
								DocText text = comment.getDocText();
								System.out.println(comment.getCommentTitle() + " - " + text.getStdText());
							}
						}
						List bids = rfqLine.getRfqBidList();
						if (bids != null)
						{
							for (int ib = 0; ib < bids.size(); ib++)
							{
								RfqBid bid = (RfqBid) bids.get(ib);
								System.out.println(bid.getComp_id().getVendorId() + " - " + String.valueOf(bid.getUnitPrice()));
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