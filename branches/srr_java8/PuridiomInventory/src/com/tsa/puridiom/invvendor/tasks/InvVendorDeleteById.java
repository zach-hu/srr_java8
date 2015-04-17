package com.tsa.puridiom.invvendor.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvVendorDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvVendor invVendor = (InvVendor)incomingRequest.get("invVendor");
		if(invVendor == null)
		{
			invVendor = new InvVendor();
		}
		InvVendorSetValuesPK setValues = new InvVendorSetValuesPK();
		setValues.setValues(incomingRequest, invVendor);
		dbs.delete(invVendor);
		this.setStatus(dbs.getStatus()) ;
		return invVendor ;
	}

}