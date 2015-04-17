package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.*;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.Map;

public class RfqBidDeleteByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String)incomingRequest.get("RfqBid_icRfqHeader");
		String vendorId = (String)incomingRequest.get("RfqBid_vendorId") ;
		
		String queryString = "from RfqBid as rfqBid where rfqBid.id.icRfqHeader = ? AND rfqBid.id.vendorId = ?" ;

		BigDecimal bdHeader = new BigDecimal(icRfqHeader);

		this.setStatus(dbs.delete(queryString, new Object[] {bdHeader, vendorId}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.STRING})) ;

		return object  ;
	}

}