package com.tsa.puridiom.vendordocument.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorDocumentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorDocument vendorDocument = (VendorDocument)incomingRequest.get("vendorDocument");
		if(vendorDocument == null)
		{
			vendorDocument = new VendorDocument();
		}
		VendorDocumentSetValuesPK setValues = new VendorDocumentSetValuesPK();
		setValues.setValues(incomingRequest, vendorDocument);
		dbs.delete(vendorDocument);
		this.setStatus(dbs.getStatus()) ;
		return vendorDocument ;
	}

}