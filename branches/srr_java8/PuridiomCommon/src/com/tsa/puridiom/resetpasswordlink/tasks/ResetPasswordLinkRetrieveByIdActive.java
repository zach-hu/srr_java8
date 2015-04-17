package com.tsa.puridiom.resetpasswordlink.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class ResetPasswordLinkRetrieveByIdActive extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icLinkString = (String) incomingRequest.get("ResetPasswordLink_icLink");
			BigDecimal icLink = new BigDecimal ( icLinkString );

			String queryString = "from ResetPasswordLink as ResetPasswordLink where ResetPasswordLink.id = ? " +
	                "and ResetPasswordLink.used = 'N' and dateadd(hour,5,ResetPasswordLink.linkDate)>= getdate()";
			List resultList = dbs.query(queryString, new Object[] {icLink } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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