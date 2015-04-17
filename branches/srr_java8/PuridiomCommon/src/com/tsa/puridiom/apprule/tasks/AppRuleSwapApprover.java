/*
 * Created on March 28, 2007
 */
package com.tsa.puridiom.apprule.tasks;

import java.sql.Types;
import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author KC
 */
public class AppRuleSwapApprover extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	originalApproverUserId = (String) incomingRequest.get("OriginalApprover_userId");
			String	newApproverUserId = (String) incomingRequest.get("NewApprover_userId");

			String	sql = "update app_rule set app_rule.user_id = ? where app_rule.user_id = ?";
			Object [] args = new Object[2];
			Integer [] types = new Integer[2];
			args[0] = newApproverUserId;
			types[0] = Types.VARCHAR;
			args[1] = originalApproverUserId;
			types[1] = Types.VARCHAR;
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}
