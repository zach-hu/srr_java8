package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

public class VendorRegisterRetrieveByAltEmailAddr extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from VendorRegister as vendorregister where 1=1 ");
	
			if(incomingRequest.containsKey("VendorRegister_altEmailAddr"))
			{			
				String altEmailAddr = (String) incomingRequest.get("VendorRegister_altEmailAddr");
				altEmailAddr = Utility.ckNull(altEmailAddr).toLowerCase();
				queryString.append(" AND vendorregister.altEmailAddr = '" + altEmailAddr + "'");
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