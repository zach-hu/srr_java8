package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.List;
import java.util.Map;

public class VendorRegisterRetrieveByContactEmailAddr extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from VendorRegister as vendorregister where 1=1 ");
	
			if(incomingRequest.containsKey("VendorRegister_contactEmailAddr"))
			{			
				String contactEmailAddr = (String) incomingRequest.get("VendorRegister_contactEmailAddr");
				contactEmailAddr = Utility.ckNull(contactEmailAddr).toLowerCase();
				queryString.append(" AND vendorregister.id.contactEmailAddr = '" + contactEmailAddr + "'");
			}
	
			List resultList = dbs.query(queryString.toString()) ;
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

		return result ;
	}
}
