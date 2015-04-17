package com.tsa.puridiom.receipt.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.receipt.tasks.ReceiptForwardNotification;
import java.math.BigDecimal;
import java.util.*;

public class ReceiptForwardNotificationTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptHeader receiptHeader = new ReceiptHeader();
			receiptHeader.setOwner("KCULLEN");
			receiptHeader.setForwardTo("END-USERS");
			receiptHeader.setPackingSlip("555-9698-01");
			receiptHeader.setReceiptNumber("000001");
			receiptHeader.setPkgsReceived(new BigDecimal(5));
			receiptHeader.setRefNumber("2004-120052");
			
			PoHeader poHeader = new PoHeader();			
			List poLineList = new ArrayList();
			PoLine poLine1 = new PoLine(new BigDecimal(1));
			PoLine poLine2 = new PoLine(new BigDecimal(2));
			PoLine poLine3 = new PoLine(new BigDecimal(3));
			
			poLine1.setRequisitionerCode("KELLI");
			poLineList.add(poLine1);
			
			poLine2.setRequisitionerCode("RRAMOS");
			poLineList.add(poLine2);
			
			poLine3.setRequisitionerCode("");
			poLineList.add(poLine3);
			
			poHeader.setRequisitionerCode("KCULLEN");
			poHeader.setPoLineList(poLineList);
			
			Map incomingRequest = new HashMap();
			incomingRequest.put("userId", "KCULLEN");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("receiptHeader", receiptHeader);
			incomingRequest.put("poHeader", poHeader);

			ReceiptForwardNotification test = new ReceiptForwardNotification();
			test.executeTask(incomingRequest);
			
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}