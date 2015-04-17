/*
 * Created on January 18, 2007
 */
package com.tsa.puridiom.organizationpackage.tasks;

import java.util.*;


import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kathleen
 */
public class OrganizationPackageDeleteByOrganization extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String organizationId = (String) incomingRequest.get("OrganizationPackage_organizationId");

		if (Utility.isEmpty(organizationId)) {
			organizationId = (String) incomingRequest.get("organizationId");
		}
        String queryString = "from OrganizationPackage as op where op.id.organizationId = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {organizationId}, new Type[] {Hibernate.STRING}));

		return object;
	}
}
