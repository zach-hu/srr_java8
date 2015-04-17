package com.tsa.puridiom.rfq.worksheet;

import java.util.ArrayList;
import java.util.List;

import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RfqQuestion;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.rfq.RfqBidWorksheet;

public class WorkSheetManager
{
	public static List getWorkSheetHeaderList(RfqBidWorksheet rfqBidWorksheet, String organizationId)
	{
		List wrkSheetListByPage = new ArrayList();
		if (rfqBidWorksheet != null)
		{
			RfqHeader rfqHeader = rfqBidWorksheet.getRfqHeader();
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
			    WorkSheetHeader wrkSheetHeader = new WorkSheetHeader(rfqHeader, vendorList, organizationId);
			    wrkSheetListByPage.add(wrkSheetHeader);
			}
		}
		return wrkSheetListByPage;
	}
	public static List getWorkSheetList(RfqBidWorksheet rfqBidWorksheet)
	{
		List wrkSheetListByPage = new ArrayList();
		List lines = rfqBidWorksheet.getRfqLineGroupList();
		for (int ig = 0; ig < lines.size(); ig++)
		{
		    System.out.println("GROUP " + ig + " LINES: ");
		    List lineList = (List) lines.get(ig);
		    List wrkSheetHeaderList = new ArrayList();
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
					WorkSheet wrkSheet = new WorkSheet(rfqLine, bids);
					System.out.println("worksheet: " + wrkSheet);
					wrkSheetHeaderList.add(wrkSheet);
				}
			}
		    wrkSheetListByPage.add(wrkSheetHeaderList);
		}
		return wrkSheetListByPage;
	}

	public static List getWorkSheetQuestionList(RfqBidWorksheet rfqBidWorksheet)
	{
		List workSheetListByPage = new ArrayList();
		List questions = rfqBidWorksheet.getRfqQuestionGroupList();
		List questionList;
		List workSheetHeaderList;
		RfqQuestion rfqQuestion;
		List vendorResponses;
		List responseValues;
		WorkSheetQuestion workSheetQuestion;

		for (int ig = 0; ig < questions.size(); ig++)
		{
		    System.out.println("GROUP " + ig + " LINES: ");
		    questionList = (List) questions.get(ig);
		    workSheetHeaderList = new ArrayList();

		    for (int i = 0; i < questionList.size(); i++)
			{
		    	rfqQuestion = (RfqQuestion) questionList.get(i);
				System.out.println("		question " + i + " - " + rfqQuestion.toString());
				vendorResponses = rfqQuestion.getVendorResponseList();
				responseValues = rfqQuestion.getResponseValueList();

				if (vendorResponses != null)
				{
					workSheetQuestion = new WorkSheetQuestion(rfqQuestion, vendorResponses, responseValues);
					System.out.println("worksheetQuestion: " + workSheetQuestion);
					workSheetHeaderList.add(workSheetQuestion);
				}
			}

		    workSheetListByPage.add(workSheetHeaderList);
		}

		return workSheetListByPage;
	}
}
