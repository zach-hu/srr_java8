/**
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoRetrieveByFullNumber extends Task
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
			String poNumber = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_poNumber"));
			String releaseNumberString = (String) incomingRequest.get("PoHeader_releaseNumber");
			String revisionNumberString = (String) incomingRequest.get("PoHeader_revisionNumber");
			BigDecimal releaseNumber = new BigDecimal(0);
			BigDecimal revisionNumber = new BigDecimal(0);

			if (!HiltonUtility.isEmpty(releaseNumberString))
			{
				releaseNumber = new BigDecimal(releaseNumberString);
			}

			if (!HiltonUtility.isEmpty(revisionNumberString))
			{
				revisionNumber = new BigDecimal(revisionNumberString);
			}

			String queryString = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.releaseNumber = ? AND PoHeader.revisionNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] { poNumber, releaseNumber, revisionNumber }, new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}

			this.setStatus(dbs.getStatus());

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoRetrieveByFullNumber error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}