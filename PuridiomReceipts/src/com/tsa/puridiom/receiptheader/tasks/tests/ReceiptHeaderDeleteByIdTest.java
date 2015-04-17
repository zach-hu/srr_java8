package com.tsa.puridiom.receiptheader.tasks.tests;

import com.tsa.puridiom.receiptheader.tasks.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.*;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.*;

public class ReceiptHeaderDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptHeaderDeleteById deleteByIdTask = new ReceiptHeaderDeleteById();
			Map incomingRequest = new HashMap();
			
			ReceiptHeader receiptHeader = new ReceiptHeader();
			receiptHeader.setIcRecHeader(new BigDecimal("10"));
			
			incomingRequest.put("ReceiptHeader_icRecHeader", "10");
			incomingRequest.put("receiptHeader", receiptHeader);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("dbsession", new DBSession("PURIDIOM"));

			deleteByIdTask.executeTask(incomingRequest);
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
