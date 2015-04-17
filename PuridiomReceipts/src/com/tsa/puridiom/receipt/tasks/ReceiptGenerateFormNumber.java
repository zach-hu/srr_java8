package com.tsa.puridiom.receipt.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ReceiptGenerateFormNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			BigDecimal receiptNumber = new BigDecimal("0");

			String queryString = "select max(cast(r.receiptNumber, integer)) from ReceiptHeader as r ";
			List resultList = dbs.query(queryString) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				Integer	number = (Integer) resultList.get(0);
				if (number != null) {
					receiptNumber = new BigDecimal(number);	
				} else {
					receiptNumber = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(receiptNumber.add(new BigDecimal("1")));
			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result ;
	}
}
