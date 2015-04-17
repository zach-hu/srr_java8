package com.tsa.puridiom.vendor.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class VendorDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Vendor vendor = (Vendor)incomingRequest.get("vendor");
		if(vendor == null)
		{
			vendor = new Vendor();
		}
		VendorSetValuesPK setValues = new VendorSetValuesPK();
		setValues.setValues(incomingRequest, vendor);
		dbs.delete(vendor);
		this.setStatus(dbs.getStatus()) ;
		return vendor ;
	}

}