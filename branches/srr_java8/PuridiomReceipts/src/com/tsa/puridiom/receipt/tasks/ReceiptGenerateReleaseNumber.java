package com.tsa.puridiom.receipt.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ReceiptGenerateReleaseNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("originalReceiptHeader");
			BigDecimal releaseNumber = new BigDecimal("0");

			String queryString = "select max(r.releaseNumber) from ReceiptHeader as r  where receiptNumber = ?";
			List resultList = dbs.query(queryString, receiptHeader.getReceiptNumber(), Hibernate.STRING) ;

			if (resultList != null && resultList.size() > 0)
			{
				releaseNumber = (BigDecimal) resultList.get(0);
				if (releaseNumber == null) {
					releaseNumber = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(releaseNumber.add(new BigDecimal(1)));
			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}
