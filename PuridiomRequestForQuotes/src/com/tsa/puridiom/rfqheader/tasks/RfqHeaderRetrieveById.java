package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RfqHeaderRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			if (Utility.isEmpty(icRfqHeaderString))
			{
				throw new Exception("The value for RfqHeader_icRfqHeader cannot be empty.");
			}

			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );

			String queryString = "from RfqHeader as RfqHeader where RfqHeader.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;

//			result = (RfqHeader) dbs.retrieveId(icRfqHeader,RfqHeader.class) ;
//			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}

}