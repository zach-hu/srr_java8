package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoHeaderRetrieveByIdHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("old_PoHeader_icPoHeader");
			if (Utility.isEmpty(icPoHeaderString))
			{
				throw new Exception("PoHeader_icPoHeader cannot be empty.  Po Header could not be retrieved.");
			}
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );

			String queryString = "from PoHeader as header where header.icPoHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}