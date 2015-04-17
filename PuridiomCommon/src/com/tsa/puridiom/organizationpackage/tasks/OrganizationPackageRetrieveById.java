package com.tsa.puridiom.organizationpackage.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrganizationPackageRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icOrgPackageString = (String) incomingRequest.get("OrganizationPackage_icOrgPackage");
			BigDecimal icOrgPackage = new BigDecimal ( icOrgPackageString );

			String queryString = "from OrganizationPackage as OrganizationPackage where OrganizationPackage.icOrgPackage = ? ";
			List resultList = dbs.query(queryString, new Object[] {icOrgPackage, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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