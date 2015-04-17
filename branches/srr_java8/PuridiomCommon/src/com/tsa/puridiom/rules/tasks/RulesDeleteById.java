package com.tsa.puridiom.rules.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RulesDeleteById extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String icRuleString = (String) incomingRequest.get("Rules_icRule");
		if(Utility.isEmpty(icRuleString))
        {
			icRuleString = "0";
        }
		BigDecimal icRule = new BigDecimal(icRuleString);

		String queryString = "from Rules as rules where rules.icRule = ?";

		this.setStatus(dbs.delete(queryString, new Object[] {icRule}, new Type[] {Hibernate.BIG_DECIMAL}));

		return null;
	}
}
