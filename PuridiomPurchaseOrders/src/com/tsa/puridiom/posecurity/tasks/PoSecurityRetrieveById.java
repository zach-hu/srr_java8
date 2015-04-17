package com.tsa.puridiom.posecurity.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoSecurityRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String ) incomingRequest.get("PoSecurity_poNumber");
			String accessType = (String ) incomingRequest.get("PoSecurity_accessType");
			String accessId = (String ) incomingRequest.get("PoSecurity_accessId");

			String queryString = "from PoSecurity as PoSecurity where PoSecurity.id.poNumber = ? and PoSecurity.id.accessType = ? and PoSecurity.id.accessId = ? ";
			List resultList = dbs.query(queryString, new Object[] {poNumber, accessType, accessId, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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