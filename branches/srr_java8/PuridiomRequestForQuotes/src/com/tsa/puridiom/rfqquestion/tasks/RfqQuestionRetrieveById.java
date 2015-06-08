package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqQuestionRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icQuestionString = (String) incomingRequest.get("RfqQuestion_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );

			String queryString = "from RfqQuestion as RfqQuestion where RfqQuestion.id.icRfqHeader = ? and RfqQuestion.id.icQuestion = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, icQuestion, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
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