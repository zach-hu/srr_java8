package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvBinLocationRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRcString = (String) incomingRequest.get("InvBinLocation_icRc");
			BigDecimal icRc = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(icRcString))
				icRc = new BigDecimal(icRcString);

			String queryString = "from InvBinLocation as InvBinLocation where InvBinLocation.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRc, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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