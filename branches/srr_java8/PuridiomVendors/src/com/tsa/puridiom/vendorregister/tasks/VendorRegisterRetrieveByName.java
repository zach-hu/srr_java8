package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorRegisterRetrieveByName extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorName = (String) incomingRequest.get("VendorRegister_vendorName");
			StringBuffer query = new StringBuffer();
			
			vendorName = vendorName.replaceAll("'", "%");
			
			query.append("from VendorRegister as vendorregister where ");
			query.append("vendorregister.vendorName like '%" + vendorName + "%' and vendorregister.contactType = 'MAIN'");
			
			result = dbs.query(query.toString()) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}