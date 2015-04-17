package com.tsa.puridiom.vendor.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * Class to retrieve the VendorBuyerRel associated Vendor.
 * 
 * @author Alexander
 *
 */
public class VendorBuyerRelRetrieveById extends Task {
	
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Vendor_vendorId");
			
			String queryString = "from VendorBuyerRel vbr where vbr.id.vendorId = ?";
			List resultList = dbs.query(queryString, new Object[] {vendorId} , new Type[] { Hibernate.STRING}) ;
					
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
