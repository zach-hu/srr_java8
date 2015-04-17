package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoHeaderRetrieveByNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = Utility.ckNull((String) incomingRequest.get("PoHeader_poNumber"));
			String releaseNumberString = (String) incomingRequest.get("PoHeader_releaseNumber");
			if (Utility.isEmpty(releaseNumberString))
			{
				releaseNumberString = "0";
			}
			BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );

			String queryString = "select PoHeader.icPoHeader from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.releaseNumber = ? AND PoHeader.lastRevision = 'C' ";
			List resultList = dbs.query(queryString, new Object[] { poNumber, releaseNumber } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL });

			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal icPoHeader = (BigDecimal)resultList.get(0);
				result = String.valueOf(icPoHeader);
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