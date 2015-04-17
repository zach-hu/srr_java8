/*
 * Created on December 3, 2009
 */
package com.tsa.puridiom.organizationpackage.tasks;

import java.sql.Types;
import java.util.*;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class OrganizationPackageInactivateByOrganization extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		boolean closeDBS = false;
		
		try
		{
			String organizationId = (String) incomingRequest.get("OrganizationPackage_organizationId");

			if (Utility.isEmpty(organizationId)) {
				organizationId = (String) incomingRequest.get("organizationId");
			}
			
			String	sql = "update ORGANIZATION_PACKAGE set ORGANIZATION_PACKAGE.STATUS = ? where ORGANIZATION_PACKAGE.ORGANIZATION_ID = ?";
		
			if (dbs == null || !dbs.getConfigId().equalsIgnoreCase("host")) {
				//	Always use host database configuration for this table
				dbs = new DBSession("host") ;
				dbs.startTransaction();
				closeDBS = true;
			}
			Object [] args = new Object[2];
			Integer [] types = new Integer[2];
			args[0] = GeneralStatus.STATUS_INACTIVE;
			types[0] = Types.VARCHAR;
			args[1] = organizationId;
			types[1] = Types.VARCHAR;
			
			this.setStatus(dbs.sqlUpdate(sql, args, types)) ;
			
			if (closeDBS) {
				dbs.commit();
			}
			
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
		    if (dbs != null)
		    {
		        dbs.rollback();
		    }
			this.setStatus(Status.FAILED);
			throw e;
		} finally {
			if (dbs != null && closeDBS) {
				dbs.close();
			}
		}
		return object;
	}
}
