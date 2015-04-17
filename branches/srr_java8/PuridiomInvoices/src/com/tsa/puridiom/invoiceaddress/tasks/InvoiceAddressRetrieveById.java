package com.tsa.puridiom.invoiceaddress.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceAddressRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("InvoiceAddress_vendorId");
			String addressCode = (String ) incomingRequest.get("InvoiceAddress_addressCode");

			Log.debug(this, "*** BEFORE RETRIEVE \nInvoiceAddress_vendorId = "+ vendorId + ", InvoiceAddress_addressCode = " + addressCode);
			if(!HiltonUtility.isEmpty(vendorId) && !HiltonUtility.isEmpty(addressCode)){
				String queryString = "from InvoiceAddress as InvoiceAddress where InvoiceAddress.id.vendorId = ? and InvoiceAddress.id.addressCode = ? ";
				List resultList = dbs.query(queryString, new Object[] {vendorId, addressCode, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
	
				if (resultList != null && resultList.size() > 0)
				{
					InvoiceAddress invoiceAddress = (InvoiceAddress)resultList.get(0);
					Log.debug(invoiceAddress, "*** AFTER RETRIVE \nInvoiceAddress_vendorId = "+invoiceAddress.getComp_id().getVendorId()+"; InvoiceAddress_addressCode = "+invoiceAddress.getComp_id().getAddressCode() + "; Object: "+invoiceAddress);
					if(!HiltonUtility.isEmpty(invoiceAddress.getComp_id().getVendorId()) && !HiltonUtility.isEmpty(invoiceAddress.getComp_id().getAddressCode())){
						result = invoiceAddress;
					} else {
						result = null;
					}
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