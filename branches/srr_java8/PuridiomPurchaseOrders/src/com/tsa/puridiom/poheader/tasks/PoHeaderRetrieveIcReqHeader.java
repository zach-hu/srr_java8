package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoHeaderRetrieveIcReqHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
			if (Utility.isEmpty(icPoHeaderString))
			{
				throw new Exception("PoHeader_icPoHeader cannot be empty.  Po header could not be retrieved.");
			}
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );

			String queryString = "select PoHeader.icReqHeader from PoHeader as PoHeader where PoHeader.icPoHeader = ? ";
			List resultList = dbs.query(queryString, icPoHeader, Hibernate.BIG_DECIMAL) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = String.valueOf(resultList.get(0));
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