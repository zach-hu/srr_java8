package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class VendorRegisterRetrieveAltByVendorId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("VendorRegister_vendorId");

			String queryString = "from VendorRegister as VendorRegister where VendorRegister.id.vendorId = ? and VendorRegister.contactType = ? ";
			List resultList = dbs.query(queryString, new Object[] {vendorId, "ALTERNATE" } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}