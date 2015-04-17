package com.tsa.puridiom.rfqvendor.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RfqVendorDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RfqVendor rfqVendor = (RfqVendor)incomingRequest.get("rfqVendor");
		if(rfqVendor == null)
		{
			rfqVendor = new RfqVendor();
		}
		RfqVendorSetValuesPK setValues = new RfqVendorSetValuesPK();
		setValues.setValues(incomingRequest, rfqVendor);
		dbs.delete(rfqVendor);
		this.setStatus(dbs.getStatus()) ;
		return rfqVendor ;
	}

}