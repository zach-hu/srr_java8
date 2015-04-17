package com.tsa.puridiom.checklistresponse.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ChecklistResponseRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("ChecklistResponse_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icQuestionString = (String) incomingRequest.get("ChecklistResponse_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );

			String queryString = "from ChecklistResponse as ChecklistResponse where ChecklistResponse.id.icHeader = ? and ChecklistResponse.id.icQuestion = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icQuestion, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
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