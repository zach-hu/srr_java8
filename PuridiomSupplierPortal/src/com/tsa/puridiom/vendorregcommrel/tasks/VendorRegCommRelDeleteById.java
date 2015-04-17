package com.tsa.puridiom.vendorregcommrel.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorRegCommRelDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorRegCommRel vendorRegCommRel = (VendorRegCommRel)incomingRequest.get("vendorRegCommRel");
		if(vendorRegCommRel == null)
		{
			vendorRegCommRel = new VendorRegCommRel();
		}
		VendorRegCommRelSetValuesPK setValues = new VendorRegCommRelSetValuesPK();
		setValues.setValues(incomingRequest, vendorRegCommRel);
		dbs.delete(vendorRegCommRel);
		this.setStatus(dbs.getStatus()) ;
		return vendorRegCommRel ;
	}

}