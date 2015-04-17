package com.tsa.puridiom.elementform.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ElementFormRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String formId = (String ) incomingRequest.get("ElementForm_formId");
			String elementIndexString = (String) incomingRequest.get("ElementForm_elementIndex");
			BigDecimal elementIndex = new BigDecimal ( elementIndexString );

			String queryString = "from ElementForm as ElementForm where ElementForm.id.formId = ? and ElementForm.id.elementIndex = ? ";
			List resultList = dbs.query(queryString, new Object[] {formId, elementIndex, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
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