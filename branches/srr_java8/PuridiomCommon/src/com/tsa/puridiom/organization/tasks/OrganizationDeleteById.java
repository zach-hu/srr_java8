package com.tsa.puridiom.organization.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Organization;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class OrganizationDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Organization organization = (Organization)incomingRequest.get("organization");
		if (organization == null) {
			organization = new Organization();
			String	organizationId = (String) incomingRequest.get("Organization_organizationId");
			organization.setOrganizationId(organizationId);
		}

		dbs.delete(organization);
		this.setStatus(dbs.getStatus()) ;
		return organization ;
	}
}