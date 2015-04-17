package com.tsa.puridiom.invoiceaddress.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceAddressGetNextAddressCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String) incomingRequest.get("InvoiceAddress_vendorId");
			BigDecimal bdCount = new BigDecimal(0);

			String queryString = "select count(InvoiceAddress.id.addressCode) from InvoiceAddress as InvoiceAddress where InvoiceAddress.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {vendorId} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				if (resultList.get(0) != null)
				{
					Long iCount = (Long) resultList.get(0);
				    bdCount = new BigDecimal(iCount.intValue());
				}
			}
			result = String.valueOf(bdCount.add(new BigDecimal(1)));
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}