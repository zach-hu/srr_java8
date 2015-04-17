package com.tsa.puridiom.invvendor.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvVendorAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvVendor invVendor = (InvVendor)incomingRequest.get("invVendor");
		if(invVendor == null)
		{
			invVendor = new InvVendor();
		}
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvVendorSetValues setValues = new InvVendorSetValues();
		setValues.setValues(incomingRequest, invVendor);
		dbs.add(invVendor);
		this.setStatus(dbs.getStatus()) ;
		return invVendor;
	}

}