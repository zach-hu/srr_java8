package com.tsa.puridiom.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfq.RfqBidWorksheet;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RfqWorksheetDataRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    Map incomingRequest = new HashMap();
		    String	oid = "PURIDIOM";

		    incomingRequest.put("organizationId", oid);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader", "6882901100033");

		    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			PuridiomProcess process = processLoader.loadProcess("rfq-worksheet-data-retrieve.xml");
			process.executeProcess(incomingRequest);

			System.out.println(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED) {
				System.out.println("RfqWorksheetDataRetrieve SUCCEEDED!");
			} else {
				System.out.println("RfqWorksheetDataRetrieve FAILED!");
			}

			if (incomingRequest.containsKey("rfqBidWorksheet"))
			{
			    RfqBidWorksheet rfqBidWorksheet = (RfqBidWorksheet) incomingRequest.get("rfqBidWorksheet");
			    RfqHeader rfqHeader = rfqBidWorksheet.getRfqHeader();

			    System.out.println("");
				System.out.println("RFQ HEADER = " + rfqHeader.toString());
				System.out.println("");

				if (rfqBidWorksheet != null)
				{
					List vendors = rfqBidWorksheet.getRfqVendorGroupList();
					for (int ig = 0; ig < vendors.size(); ig++)
					{
					    System.out.println("GROUP " + ig + " VENDORS: ");
					    List vendorList = (List) vendors.get(ig);
					    for (int i = 0; i < vendorList.size(); i++)
						{
							RfqVendor rfqVendor = (RfqVendor) vendorList.get(i);
							System.out.println("		" + rfqVendor.getComp_id().getVendorId() + " - BID SUBTOTAL: " + rfqVendor.getBidSubtotal());
						}
					}

					System.out.println("");

					List lines = rfqBidWorksheet.getRfqLineGroupList();
					for (int ig = 0; ig < lines.size(); ig++)
					{
					    System.out.println("GROUP " + ig + " LINES: ");
					    List lineList = (List) lines.get(ig);
					    for (int i = 0; i < lineList.size(); i++)
						{
					        RfqLine rfqLine = (RfqLine) lineList.get(i);
							System.out.println("		LINE BIDS " + i + " - " + rfqLine.toString());
							List bids = rfqLine.getRfqBidList();
							if (bids != null)
							{
								for (int ib = 0; ib < bids.size(); ib++)
								{
									RfqBid bid = (RfqBid) bids.get(ib);
									System.out.println("			" + bid.getComp_id().getVendorId() + " - " + String.valueOf(bid.getUnitPrice()) +  " - " + String.valueOf(bid.getUnitPrice().multiply(bid.getQuantity())));
								}
							}
						}
					}
				    /*
					List list = rfqHeader.getRfqLineList();
					for (int i = 0; i < list.size(); i++)
					{
						RfqLine rfqLine = (RfqLine) list.get(i);
						System.out.println(rfqLine.toString());
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