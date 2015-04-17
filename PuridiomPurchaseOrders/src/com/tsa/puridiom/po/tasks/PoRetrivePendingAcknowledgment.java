/**
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoRetrivePendingAcknowledgment extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String serviceType = "posupack";

			String queryString = "from PoHeader as PoHeader, ApprovalLink as ApprovalLink "
					+ " where PoHeader.icPoHeader = ApprovalLink.icHeader and PoHeader.dateAcknowledged is null and "
					+ " (PoHeader.acknowledgedBy is null or PoHeader.acknowledgedBy = '') and PoHeader.vendorId = ? and ApprovalLink.action = ? ";
			
			result = dbs.query(queryString, new Object[] { poHeader.getVendorId(), serviceType }, new Type[] { Hibernate.STRING, Hibernate.STRING });

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoRetrivePendingAcknowledgment error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
