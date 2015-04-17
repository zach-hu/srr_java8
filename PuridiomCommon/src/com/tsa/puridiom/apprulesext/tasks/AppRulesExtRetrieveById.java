package com.tsa.puridiom.apprulesext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AppRulesExtRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String ruleNumberString = (String) incomingRequest.get("AppRulesExt_ruleNumber");
			BigDecimal ruleNumber = new BigDecimal ( ruleNumberString );
			String ruleType = (String ) incomingRequest.get("AppRulesExt_ruleType");

			String queryString = "from AppRulesExt as AppRulesExt where AppRulesExt.id.ruleNumber = ? and AppRulesExt.id.ruleType = ? ";
			List resultList = dbs.query(queryString, new Object[] {ruleNumber, ruleType, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
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