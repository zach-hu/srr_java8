package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AccountRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("Account_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String sequenceString = (String) incomingRequest.get("Account_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );

			String queryString = "from Account as Account where Account.id.icHeader = ? and Account.id.icLine = ? and Account.id.sequence = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine, sequence, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
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