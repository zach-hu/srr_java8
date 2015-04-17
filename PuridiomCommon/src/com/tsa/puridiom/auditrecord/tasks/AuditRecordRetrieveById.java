package com.tsa.puridiom.auditrecord.tasks;
/**
 * Created on January 11, 2007
 */
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kathleen
 *
 */

public class AuditRecordRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icString = (String) incomingRequest.get("AuditRecord_ic");
			if (Utility.isEmpty(icString))
			{
				throw new Exception("AuditRecord_ic cannot be empty.  AuditRecord could not be retrieved.");
			}
			BigDecimal ic = new BigDecimal(icString);

			String queryString = "from AuditRecord as AuditRecord where AuditRecord.ic = ? ";
			List resultList = dbs.query(queryString, new Object[] { ic } , new Type[] { Hibernate.BIG_DECIMAL });

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