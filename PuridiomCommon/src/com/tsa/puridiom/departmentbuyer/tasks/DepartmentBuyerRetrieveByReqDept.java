/*
 * Created on January 17, 2007
 */
package com.tsa.puridiom.departmentbuyer.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class DepartmentBuyerRetrieveByReqDept extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			String department = (String) incomingRequest.get("RequisitionHeader_departmentCode");

	        String queryString = "from DepartmentBuyer as d where d.id.departmentCode = ? ";

			List resultList = dbs.query(queryString,	new Object[] {department}, new Type[] {Hibernate.STRING});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}

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
