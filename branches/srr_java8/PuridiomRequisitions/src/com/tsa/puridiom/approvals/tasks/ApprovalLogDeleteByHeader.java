/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
/**
 * @author Administrator
 */
public class ApprovalLogDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;


			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");
			BigDecimal bdReqHeader = new BigDecimal(icHeader) ;
			if(bdReqHeader == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("ApprovalLog_ic was not found.");
			}

	        String queryString = "from ApprovalLog as a where a.id.icHeader = ?" ;

			dbs.delete(queryString, bdReqHeader, Hibernate.BIG_DECIMAL) ;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Error Rebuilding Routing List. ", e);
		}

		return object	;
	}
}
