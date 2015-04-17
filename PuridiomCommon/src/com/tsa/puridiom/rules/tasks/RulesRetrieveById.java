package com.tsa.puridiom.rules.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RulesRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icRuleString = (String) incomingRequest.get("Rules_icRule");
			if(Utility.isEmpty(icRuleString))
	        {
				icRuleString = "0";
	        }
			BigDecimal icRule = new BigDecimal(icRuleString);

			String queryString = "from Rules as Rules where Rules.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRule}, new Type[] {Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
