package com.tsa.puridiom.vendorinsurance.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorInsuranceDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorInsurance vendorInsurance = (VendorInsurance)incomingRequest.get("vendorInsurance");
		if(vendorInsurance == null)
		{
			vendorInsurance = new VendorInsurance();
		}
		VendorInsuranceSetValuesPK setValues = new VendorInsuranceSetValuesPK();
		setValues.setValues(incomingRequest, vendorInsurance);
		dbs.delete(vendorInsurance);
		this.setStatus(dbs.getStatus()) ;
		return vendorInsurance ;
	}

}