package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

public class RfqVendorAwardedRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			if (icRfqHeader == null)
			{
				icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			}
			StringBuffer queryString = new StringBuffer("from RfqVendor as rfqvendor where rfqvendor.id.icRfqHeader = " + icRfqHeader +
					" AND rfqvendor.id.vendorId IN (SELECT RfqLine.vendorAwarded FROM RfqLine RfqLine WHERE RfqLine.icRfqHeader = " +
					icRfqHeader + ")");
			queryString.append(" order by rfqvendor.id.vendorId ASC");

			List result = dbs.query(queryString.toString()) ;
			ret = result;

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