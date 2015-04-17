package com.tsa.puridiom.sendalert.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SendAlertRetrieveByDocicAction extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String docicString = (String) incomingRequest.get("SendAlert_docic");
			BigDecimal docic = new BigDecimal ( docicString );
			String qAction = (String)incomingRequest.get("SendAlert_action");
			if(Utility.isEmpty(qAction)){		qAction = EmailActionCodes.HTMLEMAIL;	}

			String queryString = "from SendAlert as SendAlert where SendAlert.docic = ? AND SendAlert.action = ? ";
			List resultList = dbs.query(queryString, new Object[] {docic, qAction} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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