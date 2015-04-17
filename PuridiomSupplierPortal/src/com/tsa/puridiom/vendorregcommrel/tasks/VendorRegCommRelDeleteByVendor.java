package com.tsa.puridiom.vendorregcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

import org.hibernate.Hibernate;

public class VendorRegCommRelDeleteByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
		String	vendorId = (String)incomingRequest.get("VendorRegCommRel_vendorId");
		
		String queryString = "from VendorRegCommRel as v where v.id.vendorId = ?" ;

		dbs.delete(queryString, vendorId, Hibernate.STRING) ;
		
		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}