/**
 * Created on Mar 31, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoGetOriginalIc.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PoGetOriginalIc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String sql = "Select poHeader.icPoHeader, poHeader.lastRevision from PoHeader as poHeader where poHeader.poNumber = ? AND poHeader.releaseNumber = ?" +
					" AND poHeader.revisionNumber = ?";
			String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			String release = (String)incomingRequest.get("PoHeader_releaseNumber");
			BigDecimal releaseNumber = new BigDecimal(release);
			BigDecimal revisionNumber = new BigDecimal(0);
			List resultList = dbs.query(sql, new Object[] {poNumber,  releaseNumber, revisionNumber} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});
			if (resultList != null && resultList.size() > 0)
			{
			    Object data[] = (Object[])resultList.get(0);
			    incomingRequest.put("originalIc", data[0]);
			    incomingRequest.put("lastRevision", data[1]);
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}
