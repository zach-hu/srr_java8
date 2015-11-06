package com.tsa.puridiom.rfqbid.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RfqBidRetrieveByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			
			BigDecimal icRfqHeader = null;
			try {
				icRfqHeader = new BigDecimal(icRfqHeaderString);
			} catch (Exception e) {
				Log.error(this, e);
			}
			
			String vendorId = (String) incomingRequest.get("RfqBid_vendorId");
			
			List rfqBidList = null;
			if (icRfqHeader !=  null) {
				StringBuffer queryString = new StringBuffer("from RfqBid as rfqbid where rfqbid.id.vendorId = ? and rfqbid.id.icRfqHeader = ?");
				rfqBidList = dbs.query(queryString.toString(), new Object[] {vendorId, icRfqHeader} , new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
			} else {
				rfqBidList = new ArrayList();
				dbs.setStatus(Status.SUCCEEDED);
			}
			
			result = rfqBidList;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
		}		
		return result ;
	}
}