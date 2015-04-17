package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RfqVendorAwardedNotOnOrder extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String sIcRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			if (sIcRfqHeader == null)	{		sIcRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");		}
			BigDecimal icRfqHeader = new BigDecimal(sIcRfqHeader);

			String vendorId = (String)incomingRequest.get("awardedVendor");

			String queryString = "from RfqVendor as rfqVendor where rfqVendor.id.icRfqHeader = ? and rfqVendor.id.vendorId = ?";
			ret = dbs.query(queryString, new Object[] {icRfqHeader, vendorId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Awarded Vendors List couls not be retrieved. " + e.getMessage(), e);
		}
		return ret;
	}
}