package com.tsa.puridiom.apprule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AppRuleRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String userId = (String ) incomingRequest.get("AppRule_userId");
			String udf1Code = (String ) incomingRequest.get("AppRule_udf1Code");
			String udf2Code = (String ) incomingRequest.get("AppRule_udf2Code");
			String udf3Code = (String ) incomingRequest.get("AppRule_udf3Code");
			String udf4Code = (String ) incomingRequest.get("AppRule_udf4Code");
			String udf5Code = (String ) incomingRequest.get("AppRule_udf5Code");
			String udf6Code = (String ) incomingRequest.get("AppRule_udf6Code");
			String udf7Code = (String ) incomingRequest.get("AppRule_udf7Code");

			String queryString = "from AppRule as AppRule where AppRule.id.userId = ? and AppRule.id.udf1Code = ? and AppRule.id.udf2Code = ? and AppRule.id.udf3Code = ? and AppRule.id.udf4Code = ? and AppRule.id.udf5Code = ? and AppRule.id.udf6Code = ? and AppRule.id.udf7Code = ? ";
			List resultList = dbs.query(queryString, new Object[] {userId, udf1Code, udf2Code, udf3Code, udf4Code, udf5Code, udf6Code, udf7Code, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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