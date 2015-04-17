package com.tsa.puridiom.rfqheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqHeaderRetrieveRequiredBy extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			if (Utility.isEmpty(icRfqHeaderString))
			{
				throw new Exception("RfqHeader_icRfqHeader cannot be empty.  Rfq header could not be retrieved.");
			}
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );

			String queryString = "select RfqHeader.requiredDate from RfqHeader as RfqHeader where RfqHeader.icRfqHeader = ? ";
			List resultList = dbs.query(queryString, icRfqHeader, Hibernate.BIG_DECIMAL) ;
					
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