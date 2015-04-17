/*
 * Created on April 17, 2009
 */
package com.tsa.puridiom.organization.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.entity.Organization;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

/**
 * @author Kelli
 */
public class OrganizationRegisterNew extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		boolean closeDBS = false;

		try {
			String companyName = (String)incomingRequest.get("companyName");
			String queryString = "select max(o.organizationId) from Organization as o where o.organizationId like 'PX%'";

			if (dbs == null || !dbs.getConfigId().equalsIgnoreCase("host")) {
				//	Always use host database configuration for this table
				dbs = new DBSession("host") ;
				dbs.startTransaction();
				closeDBS = true;
			}

			// Update table to lock while creating new organization to prevent duplicates
			String sqlUpdate = "UPDATE ORGANIZATION SET ORGANIZATION_NAME = ORGANIZATION_NAME WHERE DATE_EXPIRES <= ?";
			Object [] args = {new Date()};
			Integer [] types = {Types.DATE};
			int updstat = dbs.sqlUpdate(sqlUpdate, args, types) ;
			dbs.getSession().flush() ;

			if (updstat == Status.SUCCEEDED) {
				List resultList = dbs.query(queryString) ;

				if (dbs.getStatus() == Status.SUCCEEDED && resultList != null && resultList.size() > 0) {
					String	orgId = (String) resultList.get(0);
					BigDecimal orgIdNumber = new BigDecimal("0");

					if (orgId != null && orgId.length() > 2) {
						orgId = orgId.substring(2);
						orgIdNumber = new BigDecimal(orgId);
					}
					if (orgIdNumber == null) {
						orgIdNumber = new BigDecimal(0) ;
					}

					String nextOrgId = String.valueOf(orgIdNumber.add(new BigDecimal(1)));
					String newOrganizationId = this.formatOrganizationId(nextOrgId);

					/***********************************
					 * Create new organization record
					 ***********************************/
					Organization organization = new Organization();
					organization.setOrganizationId(newOrganizationId);
					organization.setOrganizationName(companyName);
					organization.setDateExpires(Dates.add(Dates.getCurrentDate(""), 31));
					organization.setStatus(GeneralStatus.STATUS_ON_HOLD);

					dbs.add(organization);
					if (closeDBS) {
						dbs.commit();
					}

					incomingRequest.put("organizationId", newOrganizationId);
					incomingRequest.put("organization", organization);
					result = newOrganizationId;
				}
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		} finally {
			if (dbs != null && closeDBS) {
				dbs.close();
			}
		}

		return result;
	}

	public String formatOrganizationId(String docNumber) {
		String result = "";
		try {
			String	format = "NNNN";
			String	formatStr = "0000000000000000";
			String	formatedNumber = "";
			String	prefix = "PX" ;

			int numPos = format.indexOf("N");
			int numLast = format.lastIndexOf("N") ;
			String	num = "" ;

			int	l = (numLast + 1) - numPos ;
			int	fl = formatStr.length() ;
			if (l < fl) {
				String fmt = formatStr.substring(formatStr.length() - l) ;
				num = Utility.formatString(fmt, docNumber);
			} else {
				num = docNumber ;
			}

			formatedNumber = format ;
			formatedNumber = formatedNumber.replaceAll(replicate("N",num.length()),num) ;

			result = prefix + formatedNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private String replicate(String st, int cnt) {
		StringBuffer result = new StringBuffer("") ;
		for (int i = 1; i <= cnt; i++) {
			result.append(st) ;
		}
		return result.toString() ;
	}
}
