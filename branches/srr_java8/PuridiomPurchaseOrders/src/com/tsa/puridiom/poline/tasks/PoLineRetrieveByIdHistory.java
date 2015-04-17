package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineRetrieveByIdHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoLineString = (String) incomingRequest.get("old_PoLine_icPoLine");
			if (Utility.isEmpty(icPoLineString))
			{
				throw new Exception("PoLine_icReqLine cannot be empty.  Po Line could not be retrieved.");
			}
			BigDecimal icPoLine = new BigDecimal ( icPoLineString );

			String queryString = "from PoLine as line where line.icPoLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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