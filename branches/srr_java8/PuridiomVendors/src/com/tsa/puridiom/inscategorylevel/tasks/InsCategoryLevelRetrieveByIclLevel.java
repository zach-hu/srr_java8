package com.tsa.puridiom.inscategorylevel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InsCategoryLevelRetrieveByIclLevel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String iclLevelString = (String) incomingRequest.get("InsCategoryLevel_iclLevel");
			if(Utility.isEmpty(iclLevelString))
	        {
				iclLevelString = "0";
	        }
			BigDecimal iclLevel = new BigDecimal(iclLevelString);

			String queryString = "from InsCategoryLevel as InsCategoryLevel where InsCategoryLevel.iclLevel = ? ";
			List resultList = dbs.query(queryString, new Object[] {iclLevel}, new Type[] {Hibernate.BIG_DECIMAL});

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
