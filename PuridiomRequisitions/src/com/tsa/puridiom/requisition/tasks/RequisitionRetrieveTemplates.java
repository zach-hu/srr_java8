/*
 * Created on Marc 22, 2007
 */
package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.*;
import org.hibernate.type.Type;

import java.util.Map;

/**
 * @author KC
 */
public class RequisitionRetrieveTemplates extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			String userId = (String) incomingRequest.get("userId");

	        String queryString = "from RequisitionHeader as rqh where rqh.status = '" + DocumentStatus.TEMPLATE +  "' " +
	        								"AND (rqh.rqSubType = 'U' OR (rqh.rqSubType = 'R' AND (rqh.requisitionerCode = ? OR rqh.owner = ?)))";

			result = dbs.query(queryString, new Object[] { userId, userId } , new Type[] { Hibernate.STRING, Hibernate.STRING} );

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
