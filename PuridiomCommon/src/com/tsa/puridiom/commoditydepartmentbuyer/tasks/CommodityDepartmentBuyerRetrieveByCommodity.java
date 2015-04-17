/*
 * Created on October 22, 2004
 */
package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class CommodityDepartmentBuyerRetrieveByCommodity extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String commodity = (String) incomingRequest.get("CommodityDepartmentBuyer_commodity");

        String queryString = "from CommodityDepartmentBuyer as cd where cd.commodity = ? order by cd.commodity ASC";

		List result = dbs.query(queryString,	new Object[] {commodity}, new Type[] {Hibernate.STRING});

		this.setStatus(dbs.getStatus());

		return result;
	}
}
