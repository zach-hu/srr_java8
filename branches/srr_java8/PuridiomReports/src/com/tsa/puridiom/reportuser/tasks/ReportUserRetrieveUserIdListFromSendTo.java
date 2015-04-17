package com.tsa.puridiom.reportuser.tasks;

import java.util.*;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.userprofile.tasks.*;

public class ReportUserRetrieveUserIdListFromSendTo extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		List ret = new ArrayList();
		try
		{
			Map incomingRequest = (Map) object;
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			String oid = (String) incomingRequest.get("organizationId");
			String rqSendTo = reportQueue.getSendTo();
			String usersEmails[] = rqSendTo.split(";");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organizationId", oid);
			newIncomingRequest.put("dbsession", (DBSession)incomingRequest.get("dbsession"));

			for (int x=0; x<usersEmails.length; x++) {
				newIncomingRequest.put("UserProfile_mailId", usersEmails[x]);
				UserProfile userProfile = new UserProfile();
				UserProfileRetrieveByMailId retrieveUsers = new UserProfileRetrieveByMailId();
				userProfile = (UserProfile) retrieveUsers.executeTask(newIncomingRequest);
				if (userProfile != null) {
					ret.add(userProfile.getUserId());
				}
			}

			if (!ret.contains(reportQueue.getOwner())) {
				ret.add(reportQueue.getOwner());
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
