package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class CatalogSecurityRetrieveByPoHeaderByFullAccess extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			if(poHeader != null)
			{
				String vendorId = HiltonUtility.ckNull(poHeader.getVendorId());
				String userId = HiltonUtility.ckNull(poHeader.getOwner());

				String queryString = "from CatalogSecurity as cs where cs.catalogId = ? and cs.accessId = ?";
				List resultList = dbs.query(queryString, new Object[] {vendorId, userId, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
				
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList;
					incomingRequest.put("CatalogExternalFullAccess", "Y");
				}
				this.setStatus(dbs.getStatus()) ;
			}	
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, "Error retrieve catalogSecurity: \r\n" + e.getMessage());
			throw e;
		}
		return result;
	}

}