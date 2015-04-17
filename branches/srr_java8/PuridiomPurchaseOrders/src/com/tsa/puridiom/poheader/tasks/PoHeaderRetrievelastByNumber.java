/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrievelastByNumber.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderRetrievelastByNumber extends Task
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
			
			String queryString = "from PoHeader as poHeader where poHeader.lastRevision = 'C' AND poHeader.poNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {poNumber} , new Type[] { Hibernate.STRING}) ;
			
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
		return result;
	}

}
