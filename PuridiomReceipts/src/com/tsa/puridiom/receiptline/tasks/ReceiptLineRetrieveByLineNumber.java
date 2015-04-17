package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import org.hibernate.*;
import org.hibernate.type.Type;

public class ReceiptLineRetrieveByLineNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String icHeader = (String)incomingRequest.get("ReceiptLine_icRecHeader");
			String lineNumber = (String) incomingRequest.get("lineToRetrieve");
			BigDecimal bdHeader = new BigDecimal(icHeader);
			BigDecimal bdNumber = new BigDecimal(lineNumber);

			String queryString = "from ReceiptLine as ReceiptLine where ReceiptLine.icRecHeader = ? and ReceiptLine.receiptLine = ?";
			List resultList = dbs.query(queryString, new Object[] {bdHeader, bdNumber}, new Type[] {Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}
}