package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class OrganizationPackageRetrieveActiveTotal extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		DBSession dbs = null;
		boolean closeDBS = false;

		try {
			Map incomingRequest = (Map)object;
			String organizationId = "";

			dbs = (DBSession)incomingRequest.get("dbsession") ;

			if (dbs == null || !dbs.getSessionOrganizationId().equalsIgnoreCase("host")) {
				//	Always use host database configuration for this table
				dbs = new DBSession("host") ;
				closeDBS = true;
			}

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
			//dateArguments[1] = OrganizationManager.getInstance().getOrganization(organizationId).getDateExpires();

			StringBuffer queryString = new StringBuffer("select sum(organizationpackage.unitPrice) from OrganizationPackage organizationpackage where 1=1 ");
			queryString.append(" AND (organizationpackage.status = '" + GeneralStatus.STATUS_TEMPORARY + "'");
			queryString.append(" OR organizationpackage.status = '" + GeneralStatus.STATUS_PERMANENT + "')");
			queryString.append(" AND organizationpackage.dateActive <= ?");
			queryString.append(" AND organizationpackage.dateExpires >= ?");
			queryString.append(" AND organizationpackage.organizationId = '" + organizationId + "'");

			List resultList = dbs.query(queryString.toString(), dateArguments, new Type[] { Hibernate.DATE, Hibernate.DATE }) ;

			BigDecimal packageTotal = new BigDecimal(0);

			if (resultList != null && resultList.size() > 0)
			{
				packageTotal = (BigDecimal) resultList.get(0);
				if (packageTotal == null) {
					packageTotal = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(packageTotal);

			this.setStatus(dbs.getStatus()) ;
		} catch (Exception e) {
			Log.error(this, e.getMessage());
			throw e;
		} finally {
			if (closeDBS && dbs != null) {
				dbs.close();
			}
		}

		return result ;
	}
}
