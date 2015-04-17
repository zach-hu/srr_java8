package com.tsa.puridiom.autogen.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.Map;

public class AutoGenRetrieveByDocumentType extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String documentType = (String) incomingRequest.get("AutoGen_documentType");

			String queryString = "from AutoGen as AutoGen where AutoGen.id.documentType = ? and AutoGen.activeYear = 'Y'";
			result = dbs.query(queryString, new Object[] {documentType}, new Type[] {Hibernate.STRING});

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