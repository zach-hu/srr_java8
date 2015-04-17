package com.tsa.puridiom.sendqueue.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class SendQueueRetrieveByActionStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String action = (String) incomingRequest.get("SendQueue_action");
			String status = (String) incomingRequest.get("SendQueue_status");

			if(HiltonUtility.isEmpty(status)){		status = DocumentStatus.SENDQUEUE_NEW;		}

			String queryString = "from SendQueue SendQueue where SendQueue.action = ? AND SendQueue.status = ?";
			ret = dbs.query(queryString, new Object[] {action, status} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue list By ActionStatus could not be retrieved");
		}

		incomingRequest.put("sendqueueList",ret);

		return ret;
	}
}