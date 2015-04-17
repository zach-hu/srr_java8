package com.tsa.puridiom.report.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReportRetrieveByModule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String organizationId = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");

			String reportModule = (String ) incomingRequest.get("Report_reportModule");
			String queryString = "from Report as Report where Report.id.reportModule = ? ";
			if (UserManager.getInstance().getUserRole(organizationId, userId).getAccessRights("POCT") < 1 && reportModule.indexOf("PO") >= 0) {
				queryString = queryString + " and ( Report.userAccessControl <> 'POCT' or Report.userAccessControl IS NULL )";
        	}
			List resultList = dbs.query(queryString, new Object[] {reportModule } , new Type[] { Hibernate.STRING}) ;

			result = resultList;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}