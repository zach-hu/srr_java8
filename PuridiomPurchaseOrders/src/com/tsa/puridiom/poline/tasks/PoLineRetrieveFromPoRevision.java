/**
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoLineRetrieveFromPoRevision extends Task
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
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			BigDecimal releaseNumber = new BigDecimal((String) incomingRequest.get("PoHeader_releaseNumber"));
			BigDecimal revisionNumber = new BigDecimal((String) incomingRequest.get("PoHeader_revisionNumber"));
			BigDecimal icLineKeyString = new BigDecimal((String) incomingRequest.get("PoLine_icLineKey"));
			
			revisionNumber = revisionNumber.subtract(new BigDecimal(1));

			String queryString = "from PoLine as PoLine where PoLine.icLineKey = ? and PoLine.icPoHeader = (select PoHeader.icPoHeader from PoHeader as PoHeader where PoHeader.poNumber = ? and PoHeader.releaseNumber = ? and PoHeader.revisionNumber = ? ) ";

			List resultList = dbs.query(queryString, new Object[] { icLineKeyString, poNumber, releaseNumber, revisionNumber }, new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING,
					Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}

			this.setStatus(dbs.getStatus());

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoLineRetrieveFromPoRevision error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
