/*
 *  Created on June 12, 2007
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class HistoryLogRetrieveBuyerRemarks extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		List result = null;
		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

	        String queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.status = ?";

			String icHeader = (String) incomingRequest.get("HistoryLog_icHeader");
			BigDecimal bdHeader = new BigDecimal(icHeader);
			String status = DocumentStatus.BUYER_REMARKS;

			result = dbs.query(queryString, new Object[] {bdHeader, status} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}
