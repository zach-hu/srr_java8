package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AccountAllocAmountRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			if (Utility.isEmpty(icHeaderString))
			{
				icHeaderString = (String) incomingRequest.get("icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					throw new Exception("Account_icHeader cannot be empty.");
				}
			}
			BigDecimal icHeader = new BigDecimal ( icHeaderString );

			//This query adds allocAmount Account String.
			String queryString = "select sum(a.allocAmount), a.fld1,a.fld2,a.fld3,a.fld4,a.fld5,a.fld6,a.fld7,a.fld8,a.fld9,a.fld10,a.fld11,a.fld12,a.fld13,a.fld14,a.fld15 "
				+ "from Account as a where a.id.icHeader = ? "
				+ "group by a.fld1,a.fld2,a.fld3,a.fld4,a.fld5,a.fld6,a.fld7,a.fld8,a.fld9,a.fld10,a.fld11,a.fld12,a.fld13,a.fld14,a.fld15";

			List resultList = dbs.query(queryString, new Object[] {icHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
			result = resultList;

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