package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class VendorRegisterRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("VendorRegister_vendorId");
			String contactEmailAddr = (String ) incomingRequest.get("VendorRegister_contactEmailAddr");

			String queryString = "from VendorRegister as VendorRegister where VendorRegister.id.vendorId = ? and VendorRegister.id.contactEmailAddr = ? ";
			List resultList = dbs.query(queryString, new Object[] {vendorId, contactEmailAddr, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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