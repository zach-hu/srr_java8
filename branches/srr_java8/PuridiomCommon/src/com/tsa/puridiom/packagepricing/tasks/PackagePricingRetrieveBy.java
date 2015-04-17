package com.tsa.puridiom.packagepricing.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class PackagePricingRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		//	Always use host database configuration for this table
		DBSession dbs = new DBSession("host") ;
		StringBuffer queryString = new StringBuffer("from PackagePricing as packagepricing where 1=1 ");
		if(incomingRequest.containsKey("PackagePricing_icPackage"))
		{			
			String icPackage = (String) incomingRequest.get("PackagePricing_icPackage");
			queryString.append(" AND packagepricing.id.icPackage = '" + icPackage + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packageName"))
		{			
			String packageName = (String) incomingRequest.get("PackagePricing_packageName");
			queryString.append(" AND packagepricing.packageName = '" + packageName + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packageDescription"))
		{			
			String packageDescription = (String) incomingRequest.get("PackagePricing_packageDescription");
			queryString.append(" AND packagepricing.packageDescription = '" + packageDescription + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packagePrice"))
		{			
			String packagePrice = (String) incomingRequest.get("PackagePricing_packagePrice");
			queryString.append(" AND packagepricing.packagePrice = '" + packagePrice + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packageSavings"))
		{			
			String packageSavings = (String) incomingRequest.get("PackagePricing_packageSavings");
			queryString.append(" AND packagepricing.packageSavings = '" + packageSavings + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packageDiscount"))
		{			
			String packageDiscount = (String) incomingRequest.get("PackagePricing_packageDiscount");
			queryString.append(" AND packagepricing.packageDiscount = '" + packageDiscount + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_buyerCount"))
		{			
			String buyerCount = (String) incomingRequest.get("PackagePricing_buyerCount");
			queryString.append(" AND packagepricing.buyerCount = '" + buyerCount + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_requisitionerCount"))
		{			
			String requisitionerCount = (String) incomingRequest.get("PackagePricing_requisitionerCount");
			queryString.append(" AND packagepricing.requisitionerCount = '" + requisitionerCount + "'");
		}
		if(incomingRequest.containsKey("PackagePricing_packageType"))
		{			
			String packageType = (String) incomingRequest.get("PackagePricing_packageType");
			queryString.append(" AND packagepricing.packageType = '" + packageType + "'");
		}
		queryString.append(" ORDER BY packagepricing.packageType, packagepricing.packagePrice, packagepricing.packageSavings");
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}