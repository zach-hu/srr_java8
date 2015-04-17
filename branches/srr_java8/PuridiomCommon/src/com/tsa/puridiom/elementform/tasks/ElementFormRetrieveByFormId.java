package com.tsa.puridiom.elementform.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ElementFormRetrieveByFormId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String formId = (String ) incomingRequest.get("ElementForm_formId");

			String queryString = "from ElementForm as e where e.id.formId = ?   order by e.id.formId, e.id.elementIndex";
			List resultList = dbs.query(queryString, new Object[] {formId} , new Type[] { Hibernate.STRING}) ;

			result = resultList ;
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