/*
 * Created on November 16, 2005 
 */
package com.tsa.puridiom.apprule.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class AppRuleAmountUpdateByUser extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	appRuleUserId = (String) incomingRequest.get("AppRule_userId");
			String	approvalAmountString = (String) incomingRequest.get("UserProfile_approvalAmount");
			String	excludeLessString = (String) incomingRequest.get("UserProfile_excludeLess");
			BigDecimal approvalAmount = new BigDecimal(approvalAmountString);
			BigDecimal excludeLess = new BigDecimal(excludeLessString);
			String	sql = "update app_rule set app_rule.amount = ?, app_rule.exclude_less = ? where app_rule.user_id = ?";
			Object [] args = new Object[3];
			Integer [] types = new Integer[3];
			args[0] = approvalAmount;
			types[0] = Types.DECIMAL;
			args[1] = excludeLess;
			types[1] = Types.DECIMAL;
			args[2] = appRuleUserId;
			types[2] = Types.VARCHAR;
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
