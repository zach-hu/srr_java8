package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvoiceVendorRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String) incomingRequest.get("InvoiceVendor_vendorId");

			if (!HiltonUtility.isEmpty(vendorId))
			{
				String queryString = "from InvoiceVendor as InvoiceVendor where InvoiceVendor.vendorId = ? ";
				List resultList = dbs.query(queryString, new Object[] {vendorId, } , new Type[] { Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
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