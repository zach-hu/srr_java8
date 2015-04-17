package com.tsa.puridiom.reportuser.tasks;

import java.util.*;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportUserListFromUserIdList extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String oid = (String) incomingRequest.get("organizationId");
			List userIdList = (List) incomingRequest.get("userIdList");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("reportuser-create.xml");

			for (int x=0; x<userIdList.size(); x++) {
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", oid);
				newIncomingRequest.put("dbsession", (DBSession)incomingRequest.get("dbsession"));
				newIncomingRequest.put("reportQueue", (ReportQueue) incomingRequest.get("reportQueue"));
				newIncomingRequest.put("report", (String) incomingRequest.get("report"));
				newIncomingRequest.put("ReportUser_userId", userIdList.get(x));
				process.executeProcess(newIncomingRequest);
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
