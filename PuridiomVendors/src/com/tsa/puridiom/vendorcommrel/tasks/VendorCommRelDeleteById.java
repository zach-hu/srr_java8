package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;
import java.util.Map;

public class VendorCommRelDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorCommRel vendorCommRel = (VendorCommRel)incomingRequest.get("vendorCommRel");
		if(vendorCommRel == null)
		{
			vendorCommRel = new VendorCommRel();
		}
		VendorCommRelSetValuesPK setValues = new VendorCommRelSetValuesPK();
		setValues.setValues(incomingRequest, vendorCommRel);
		dbs.delete(vendorCommRel);
		this.setStatus(dbs.getStatus()) ;
		return vendorCommRel ;
	}

}