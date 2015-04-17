package com.tsa.puridiom.packagepricing.tasks;

import com.tsagate.foundation.database.DBSession;

import com.tsagate.foundation.processengine.Task;
import java.util.List;

public class PackagePricingRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		//	Always use host database configuration for this table
		DBSession dbs = new DBSession("host") ;
		String queryString = "from PackagePricing as packagePricing order by packagePricing.packageType, packagePricing.packageSavings, packagePricing.packagePrice";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		
		dbs.close();
		return result ;
	}

}