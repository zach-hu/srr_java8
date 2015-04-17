package com.tsa.puridiom.receiptheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptHeaderRetrieveForAutoDelivery extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from ReceiptHeader r  " +
					"WHERE  r.receiptStatus = ? " +
					"AND EXISTS  (from PoHeader p WHERE p.icPoHeader = r.icPoHeader) " +
					" order by r.receiptNumber";
			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.RCV_STEP_3 } , new Type[] { Hibernate.STRING}) ;
			result = resultList;
			if (resultList != null) 	System.out.println("Receipts in delivery: " + resultList.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Receipt for auto delivery failed (" + e.getMessage() + ")", e);
		}
		return result;
	}
}