package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class ReceiptLineRetrieveAdjustments extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List	result = null ;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String receiptNumber = (String) incomingRequest.get("ReceiptLine_receiptNumber");

			String queryString = "from ReceiptLine as ReceiptLine where ReceiptLine.receiptNumber = ? and " +
				"(ReceiptLine.receiptType = 'A' or ReceiptLine.receiptType = 'R')";
			result = dbs.query(queryString, new Object[] {receiptNumber } , new Type[] { Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}