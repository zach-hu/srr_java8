package com.tsa.puridiom.vendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;

public class VendorRetrieveActiveVendorNames extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			result = dbs.query("SELECT Vendor.vendorName FROM Vendor as Vendor, Contact as Contact, Address as Address " +
					"WHERE Contact.id.vendorId = Address.id.addressType AND Contact.addressCode = Address.id.addressCode " +
					"AND Contact.id.contactType = 'DEFAULT' AND Contact.status <> '03' AND Vendor.status <> '03' " +
					"AND Vendor.status <> '09' AND Vendor.vendorId = Contact.id.vendorId AND Address.addrFld16 = 'Y' " +
					"ORDER BY Vendor.vendorName ASC") ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "VendorRetrieveVendorNames: " + e.getMessage());
		}
		return result ;
	}
}