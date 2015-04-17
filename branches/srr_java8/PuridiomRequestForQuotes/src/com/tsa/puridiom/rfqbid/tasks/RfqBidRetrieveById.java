package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icRfqLineString = (String) incomingRequest.get("RfqBid_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
			String vendorId = (String ) incomingRequest.get("RfqBid_vendorId");

			String queryString = "from RfqBid as RfqBid where RfqBid.id.icRfqHeader = ? and RfqBid.id.icRfqLine = ? and RfqBid.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, icRfqLine, vendorId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
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