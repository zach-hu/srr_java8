package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AccountGetNextSequence extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			String icLineString = (String) incomingRequest.get("Account_icLine");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			BigDecimal icLine = new BigDecimal( icLineString );
			BigDecimal sequence = new BigDecimal("0");

			String queryString = "select max(Account.id.sequence) from Account as Account where Account.id.icHeader = ? and Account.id.icLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
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