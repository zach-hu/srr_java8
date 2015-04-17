package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoLineRetrieveByIcLineKey extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icLineKeyString = (String) incomingRequest.get("PoLine_icLineKey");
			String icPoHeaderString = (String) incomingRequest.get("PoLine_icPoHeader");
			if (Utility.isEmpty(icLineKeyString))
			{
				throw new Exception("PoLine_icLineKey cannot be empty.  PO line could not be retrieved.");
			}
			BigDecimal icLineKey = new BigDecimal ( icLineKeyString );
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );

			String queryString = "from PoLine as PoLine where PoLine.icLineKey = ? and PoLine.icPoHeader = ? and PoLine.status <> '" + DocumentStatus.PO_AMENDED + "'";
			List resultList = dbs.query(queryString, new Object[] { icLineKey, icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

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