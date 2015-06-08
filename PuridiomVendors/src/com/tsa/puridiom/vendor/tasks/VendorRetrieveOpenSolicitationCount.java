package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class VendorRetrieveOpenSolicitationCount extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Vendor_vendorId");

			String queryString = "select count(RfqVendor.id.vendorId) FROM RfqHeader as RfqHeader, RfqVendor as RfqVendor" + 
				" WHERE RfqHeader.icRfqHeader = RfqVendor.id.icRfqHeader AND RfqVendor.id.vendorId = ?" +
				" AND (RfqHeader.status = '" + DocumentStatus.RFQ_OPENSOLICITATION + "' OR RfqHeader.status = '" + DocumentStatus.RFQ_OPENAMENDMENT + "')";
			List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;
			
			if (resultList != null && resultList.size() > 0)
			{
				int poCount = 0;

				Object resultObj = resultList.get(0);
				if (resultObj instanceof Integer) {
					poCount = ((Integer)resultObj).intValue();
				} else if (resultObj instanceof Long) {
					poCount = ((Long)resultObj).intValue();
				}

				if (poCount > 0)
				{
					incomingRequest.put("deleteSupplier", "FALSE");
				}
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}