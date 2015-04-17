/*
 * Created on October 22, 2004
 */
package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class CommodityDepartmentBuyerDeleteByCommodity extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String commodity = (String) incomingRequest.get("Commodity_commodity");
			String queryString = "from CommodityDepartmentBuyer as cdb where cdb.commodity = ?";
			this.setStatus(dbs.delete(queryString, new Object[] { commodity }, new Type[] { Hibernate.STRING }));

		} catch (Exception e)
		{
			// TODO: handle exception
			this.setStatus(Status.FAILED);
			throw e;
		}

		return object;
	}
}
