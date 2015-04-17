package com.tsa.puridiom.packagepricing.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PackagePricingRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		boolean closeDBS = false;

		try
		{
			if (dbs == null || !dbs.getConfigId().equalsIgnoreCase("host")) {
				//	Always use host database configuration for this table
				dbs = new DBSession("host") ;
				closeDBS = true;
			}

			String icPackageString = (String) incomingRequest.get("PackagePricing_icPackage");
			BigDecimal icPackage = new BigDecimal ( icPackageString );

			String queryString = "from PackagePricing as PackagePricing where PackagePricing.icPackage = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPackage, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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
		} finally {
			if (dbs != null && closeDBS) {
				dbs.close();
			}
		}
		return result;
	}

}