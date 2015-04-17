package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.Map;

public class StdAccountRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object resultList = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("StdAccount_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String accType = "STD";

			String queryString = "from Account as Account where Account.id.icHeader = ? and Account.accountType = ? order by Account.id.sequence ASC";
			resultList = dbs.query(queryString, new Object[] {icHeader, accType, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}

}