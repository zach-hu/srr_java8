/*
 * Created on October 22, 2004
 */
package com.tsa.puridiom.departmentbuyer.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DepartmentBuyerRetrieveByDept extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
        
		String department = (String) incomingRequest.get("DepartmentBuyer_departmentCode");
		
        String queryString = "from DepartmentBuyer as d where d.id.departmentCode = ? order by d.assignAmount ASC";

		List result = dbs.query(queryString,	new Object[] {department}, new Type[] {Hibernate.STRING});

		this.setStatus(dbs.getStatus());
					
		return result;
	}
}
