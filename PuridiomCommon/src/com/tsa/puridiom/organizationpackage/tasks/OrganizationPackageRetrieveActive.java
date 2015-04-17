package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrganizationPackageRetrieveActive extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String organizationId = "";
		boolean closeDBS = false;
		
		if (dbs == null || !dbs.getSessionOrganizationId().equalsIgnoreCase("host")) {
			//	Always use host database configuration for this table
			dbs = new DBSession("host") ;
			closeDBS = true;
		}

		StringBuffer queryString = new StringBuffer("from OrganizationPackage as organizationpackage where 1=1 ");
		if(incomingRequest.containsKey("OrganizationPackage_organizationId"))
		{			
			organizationId = (String) incomingRequest.get("OrganizationPackage_organizationId");
		}
		if(HiltonUtility.isEmpty(organizationId) && incomingRequest.containsKey("Organization_organizationId"))
		{			
			organizationId = (String) incomingRequest.get("Organization_organizationId");
		}
		if(HiltonUtility.isEmpty(organizationId) && incomingRequest.containsKey("organizationId"))
		{			
			organizationId = (String) incomingRequest.get("organizationId");
		}

		Date[] dateArguments = new Date[2];
		dateArguments[0] = Dates.getCurrentDate("");
		dateArguments[1] = Dates.getCurrentDate("");
		
		queryString.append(" AND (organizationpackage.status = '" + GeneralStatus.STATUS_TEMPORARY + "'");
		queryString.append(" OR organizationpackage.status = '" + GeneralStatus.STATUS_PERMANENT + "')");
		queryString.append(" AND organizationpackage.dateActive <= ?");
		queryString.append(" AND organizationpackage.dateExpires >= ?");
		queryString.append(" AND organizationpackage.organizationId = '" + organizationId + "'");
		queryString.append(" order by organizationpackage.packageType ASC");
		
		List result = dbs.query(queryString.toString(), dateArguments) ;
		this.setStatus(dbs.getStatus()) ;
		
		if (closeDBS) {
			dbs.close();
		}

		return result ;
	}
}
