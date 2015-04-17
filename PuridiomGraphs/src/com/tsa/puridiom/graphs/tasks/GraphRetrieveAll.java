package com.tsa.puridiom.graphs.tasks;


import java.util.List;
import java.util.Map;

import com.tsa.puridiom.graphs.LoadGraphic;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class GraphRetrieveAll extends Task {
	public Object executeTask(Object object) throws Exception {

		try{
			Map incomingRequest = (Map)object;

			DBSession dbSession = (DBSession)incomingRequest.get("dbsession");

			String oid = (String) incomingRequest.get("organizationId");
			String uid = (String) incomingRequest.get("userId");
			String contextPath = (String) incomingRequest.get("contextPath");
			LoadGraphic loadGraphic = new LoadGraphic();
			loadGraphic.setIncomingRequest(incomingRequest);
			loadGraphic.setOrganizationId(oid);
			loadGraphic.setUserId(uid);
			loadGraphic.setDbSession(dbSession);

			List graphics = loadGraphic.loadMe(oid,uid,contextPath);
			this.setStatus(Status.SUCCEEDED);
			return graphics;
		}
		catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
	}
}
