/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrievelastByNumber.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderRetrieveByNumberAndReleaseAndRevision extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			BigDecimal releaseNumber = (BigDecimal) incomingRequest.get("PoHeader_releaseNumber");
			BigDecimal revisionNumber = (BigDecimal) incomingRequest.get("PoHeader_revisionNumber");

			String queryString = "Select poHeader.id from PoHeader as poHeader where poHeader.poNumber = ? AND poHeader.releaseNumber = ? AND poHeader.revisionNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {poNumber,releaseNumber,revisionNumber} , new Type[] {Hibernate.STRING,Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal icPoHeader = (BigDecimal)resultList.get(0);
				incomingRequest.put("PoHeader_icPoHeader", icPoHeader.toString());
				incomingRequest.put("PoLine_icPoHeader", icPoHeader.toString());
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
