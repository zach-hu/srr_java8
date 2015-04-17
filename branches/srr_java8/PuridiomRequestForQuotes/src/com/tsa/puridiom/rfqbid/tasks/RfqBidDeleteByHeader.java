package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.*;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.Map;

public class RfqBidDeleteByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String)incomingRequest.get("RfqBid_icRfqHeader");
		
		String queryString = "from RfqBid as rfqBid where rfqBid.id.icRfqHeader = ?" ;

		BigDecimal bdHeader = new BigDecimal(icRfqHeader);

		this.setStatus(dbs.delete(queryString, new Object[] {bdHeader}, new Type[] {Hibernate.BIG_DECIMAL})) ;

		return object  ;
	}

}