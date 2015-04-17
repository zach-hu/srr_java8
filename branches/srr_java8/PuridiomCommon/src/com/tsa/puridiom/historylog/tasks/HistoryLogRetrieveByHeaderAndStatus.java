/*
 *
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

/**
 * author eBS Group mcvz
 */
public class HistoryLogRetrieveByHeaderAndStatus extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

	        String queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.status = ? order by hst.icHistory ASC";

			String icHeader = (String)incomingRequest.get("HistoryLog_icHeader");
			String status = (String)incomingRequest.get("HistoryLog_status");
			BigDecimal bdHeader = new BigDecimal(icHeader);

			List resultList = dbs.query(queryString, new Object[] {bdHeader, status} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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
