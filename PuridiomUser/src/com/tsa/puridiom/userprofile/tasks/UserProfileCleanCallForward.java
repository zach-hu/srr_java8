package com.tsa.puridiom.userprofile.tasks;

import java.util.Map;

import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class UserProfileCleanCallForward extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			String approvalQueryString = "UPDATE ApprovalLog AS ApprovalLog SET ApprovalLog.callForward = ApprovalLog.id.userId  " +
			"WHERE ApprovalLog.callForward IS NOT NULL AND ApprovalLog.callForward <> '' AND " +
			"CONCAT(ApprovalLog.callForward,CONCAT('#', ApprovalLog.id.userId)) IN (SELECT CONCAT(UserProfile.callForward,CONCAT('#', UserProfile.id.userId)) " +
			"FROM UserProfile AS UserProfile where UserProfile.callForward IS NOT NULL AND UserProfile.forwardOffDate < CURRENT_DATE())";

			int approvalUpdateResult = dbs.update(approvalQueryString, new Object[] { } , new Type[] { }) ;
			Log.debug(this, "ApprovalLog updated: " + approvalUpdateResult);
			
			String userQueryString = "UPDATE UserProfile AS UserProfile SET UserProfile.callForward = '', UserProfile.forwardOffDate = NULL " +
			"WHERE UserProfile.forwardOffDate < CURRENT_DATE())";
			
			int userUpdateResult = dbs.update(userQueryString, new Object[] { } , new Type[] { }) ;
			Log.debug(this, "UserProfile updated: " + userUpdateResult);
			
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}