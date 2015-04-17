package com.tsa.puridiom.responsevalue.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ResponseValueRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icQuestionString = (String) incomingRequest.get("ResponseValue_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			String responseValue = (String ) incomingRequest.get("ResponseValue_responseValue");

			String queryString = "from ResponseValue as ResponseValue where ResponseValue.id.icQuestion = ? and ResponseValue.id.responseValue = ? ";
			List resultList = dbs.query(queryString, new Object[] {icQuestion, responseValue, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
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