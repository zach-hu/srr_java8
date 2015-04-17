package com.tsa.puridiom.sendqueue.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.Map;

public class SendQueueRetrieveByDocicAction extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String docicString = (String) incomingRequest.get("SendQueue_docic");
			BigDecimal docic = new BigDecimal ( docicString );
			String qAction = (String)incomingRequest.get("SendQueue_action");
			if(Utility.isEmpty(qAction)){		qAction = EmailActionCodes.HTMLEMAIL;	}

			String queryString = "from SendQueue as SendQueue where SendQueue.docic = ? AND SendQueue.action = ? order by SendQueue.id DESC";
			result = dbs.query(queryString, new Object[] {docic, qAction} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

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