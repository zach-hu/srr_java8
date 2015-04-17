package com.tsa.puridiom.invoicevendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvoiceVendorRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvoiceVendor as invoicevendor where 1=1 ");
		if(incomingRequest.containsKey("InvoiceVendor_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("InvoiceVendor_vendorId");
			queryString.append(" AND invoicevendor.id.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_vendorName"))
		{			
			String vendorName = (String) incomingRequest.get("InvoiceVendor_vendorName");
			queryString.append(" AND invoicevendor.vendorName = '" + vendorName + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_addressLine1"))
		{			
			String addressLine1 = (String) incomingRequest.get("InvoiceVendor_addressLine1");
			queryString.append(" AND invoicevendor.addressLine1 = '" + addressLine1 + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_addressLine2"))
		{			
			String addressLine2 = (String) incomingRequest.get("InvoiceVendor_addressLine2");
			queryString.append(" AND invoicevendor.addressLine2 = '" + addressLine2 + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_addressLine3"))
		{			
			String addressLine3 = (String) incomingRequest.get("InvoiceVendor_addressLine3");
			queryString.append(" AND invoicevendor.addressLine3 = '" + addressLine3 + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_addressLine4"))
		{			
			String addressLine4 = (String) incomingRequest.get("InvoiceVendor_addressLine4");
			queryString.append(" AND invoicevendor.addressLine4 = '" + addressLine4 + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_city"))
		{			
			String city = (String) incomingRequest.get("InvoiceVendor_city");
			queryString.append(" AND invoicevendor.city = '" + city + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_state"))
		{			
			String state = (String) incomingRequest.get("InvoiceVendor_state");
			queryString.append(" AND invoicevendor.state = '" + state + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_postalCode"))
		{			
			String postalCode = (String) incomingRequest.get("InvoiceVendor_postalCode");
			queryString.append(" AND invoicevendor.postalCode = '" + postalCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_country"))
		{			
			String country = (String) incomingRequest.get("InvoiceVendor_country");
			queryString.append(" AND invoicevendor.country = '" + country + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_fobCode"))
		{			
			String fobCode = (String) incomingRequest.get("InvoiceVendor_fobCode");
			queryString.append(" AND invoicevendor.fobCode = '" + fobCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("InvoiceVendor_dateEntered");
			queryString.append(" AND invoicevendor.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_status"))
		{			
			String status = (String) incomingRequest.get("InvoiceVendor_status");
			queryString.append(" AND invoicevendor.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_ownerEmail"))
		{			
			String ownerEmail = (String) incomingRequest.get("InvoiceVendor_ownerEmail");
			queryString.append(" AND invoicevendor.ownerEmail = '" + ownerEmail + "'");
		}
		if(incomingRequest.containsKey("InvoiceVendor_notes"))
		{			
			String notes = (String) incomingRequest.get("InvoiceVendor_notes");
			queryString.append(" AND invoicevendor.notes = '" + notes + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}