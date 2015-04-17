/**
 * Created on Feb 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoTotalReleased.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class GetPoTotalReleased extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String)incomingRequest.get("poNumber");
			BigDecimal blanketIc = (BigDecimal)incomingRequest.get("blanketIc");

			String queryString = "Select sum(poHeader.total) FROM PoHeader as poHeader " +
					"WHERE poHeader.poNumber = ? AND " +
					"poHeader.icPoHeader <> ? AND poHeader.lastRevision= 'C' AND " +
					"(poHeader.status > '" + DocumentStatus.PO_APPROVING +"' AND" +
					"poHeader.status < '" + DocumentStatus.HISTORY +  "')" +
					"";
			List resultList = dbs.query(queryString, new Object[] {poNumber,  blanketIc} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
			if(resultList == null && resultList.size() < 1)
			{
				result = new BigDecimal(0);
			}
			else
			{
				BigDecimal totalReleased = (BigDecimal)resultList.get(0);
				if(totalReleased == null)
				{
					totalReleased = new BigDecimal(0);
				}
				result = totalReleased;

			}
			if (this.getStatus() != Status.FAILED) {
			    this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
