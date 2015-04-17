package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqQuestionGetNextSequence extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			BigDecimal sequence = new BigDecimal("0");

			String queryString = "select max(RfqQuestion.sequence) from RfqQuestion as RfqQuestion where RfqQuestion.id.icRfqHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				if (resultList.get(0) != null)
				{
					sequence = (BigDecimal) resultList.get(0);
				}
			}
			result = String.valueOf(sequence.add(new BigDecimal(1)));
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