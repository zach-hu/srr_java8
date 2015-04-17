package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsa.puridiom.receiptline.tasks.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.*;

public class ReceiptLineAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptLineAdd addTask = new ReceiptLineAdd();
			Map incomingRequest = new HashMap();
			DBSession dbsession = new DBSession("PURIDIOM");
			
			dbsession.startTransaction();
			
			ReceiptLine receiptLine = new ReceiptLine();
			receiptLine.setIcRecHeader(new BigDecimal("1119590600000"));
			receiptLine.setIcRecLine(new BigDecimal("222"));
//			receiptLine.setReceiptLine(new BigDecimal("1"));
			
			incomingRequest.put("receiptLine", receiptLine);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("dbsession", dbsession);

			addTask.executeTask(incomingRequest);
			
			if (dbsession.getStatus() == Status.SUCCEEDED)
			{
				dbsession.commit();
			}
			
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

