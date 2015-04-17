package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

public class ReceiptLineRetrieveByHeaderPoLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			String icPoLine = (String) incomingRequest.get("ReceiptLine_icPoLine");

			String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = " + icRecHeader + " and receiptline.icPoLine = " + icPoLine ;

			List resultList = dbs.query(queryString.toString()) ;
			if (resultList.size() == 0) resultList = null ;
			if (resultList != null && resultList.size() > 0)
			{
				ret = resultList.get(0);
			} else {
				ret = resultList ;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw new TsaException("ReceiptLine was not Retrieved", e);
		}
		return ret ;
	}
}