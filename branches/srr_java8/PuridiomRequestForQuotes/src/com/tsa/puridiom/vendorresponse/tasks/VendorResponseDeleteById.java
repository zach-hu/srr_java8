package com.tsa.puridiom.vendorresponse.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class VendorResponseDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorResponse vendorResponse = (VendorResponse)incomingRequest.get("vendorResponse");
		if(vendorResponse == null)
		{
			vendorResponse = new VendorResponse();
		}
		VendorResponseSetValuesPK setValues = new VendorResponseSetValuesPK();
		setValues.setValues(incomingRequest, vendorResponse);
		dbs.delete(vendorResponse);
		this.setStatus(dbs.getStatus()) ;
		return vendorResponse ;
	}

}