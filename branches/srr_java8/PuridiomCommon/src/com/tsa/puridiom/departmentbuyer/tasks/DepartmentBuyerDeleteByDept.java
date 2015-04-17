/*
 * Created on October 22, 2004 
 */
package com.tsa.puridiom.departmentbuyer.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class DepartmentBuyerDeleteByDept extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String department = (String) incomingRequest.get("DepartmentBuyer_departmentCode");
		
        String queryString = "from DepartmentBuyer as d where d.id.departmentCode = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {department}, new Type[] {Hibernate.STRING}));
					
		return object;
	}
}
