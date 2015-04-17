package com.tsa.puridiom.receiptheader.tasks.tests;

import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.receiptheader.tasks.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.*;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.*;

public class ReceiptHeaderAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptHeaderAdd addTask = new ReceiptHeaderAdd();
			Map incomingRequest = new HashMap();
			
			ReceiptHeader receiptHeader = new ReceiptHeader();
			receiptHeader.setIcRecHeader(new BigDecimal("10"));
			receiptHeader.setCarrierCode("C001");
			receiptHeader.setFiscalYear("2004");
			receiptHeader.setForwardTo("ALAN");
			receiptHeader.setIcPoHeader(new BigDecimal("999999999"));
			receiptHeader.setOwner("KELLI");
			receiptHeader.setPackingSlip("PK-002");
			receiptHeader.setPkgsReceived(new BigDecimal("3"));
			receiptHeader.setReceiptDate(new Date());
			receiptHeader.setReceiptNumber("RC-0001");
			receiptHeader.setReceiptStatus(DocumentStatus.RCV_INPROGRESS);
			receiptHeader.setReceiptType("RR");
			receiptHeader.setReceivedBy("KELLI");
			receiptHeader.setRefDate(new Date());
			receiptHeader.setRefNumber("003");
			receiptHeader.setRefRelease("001");
			receiptHeader.setVendorId("STAPLES");
			receiptHeader.setVendorName("Staples");
						
			incomingRequest.put("receiptHeader", receiptHeader);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("dbsession", new DBSession("PURIDIOM"));

			addTask.executeTask(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
