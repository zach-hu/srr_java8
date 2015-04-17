package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
public class CommodityDepartmentBuyerRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CommodityDepartmentBuyer as commoditydepartmentbuyer where 1=1 ");

		if (incomingRequest.containsKey("CommodityDepartmentBuyer_icHeader"))
		{
			String icHeader = (String) incomingRequest.get("CommodityDepartmentBuyer_icHeader");
			queryString.append(" AND commoditydepartmentbuyer.icHeader = '" + icHeader + "'");
		}
		if (incomingRequest.containsKey("CommodityDepartmentBuyer_commodity"))
		{
			String commodityCode = (String) incomingRequest.get("CommodityDepartmentBuyer_commodity");
			queryString.append(" AND commoditydepartmentbuyer.commodity = '" + commodityCode + "'");
		}
		if(incomingRequest.containsKey("CommodityDepartmentBuyer_departmentCode"))
		{
			String departmentCode = (String) incomingRequest.get("DepartmentBuyer_departmentCode");
			queryString.append(" AND commoditydepartmentbuyer.departmentCode = '" + departmentCode + "'");
		}
		if(incomingRequest.containsKey("CommodityDepartmentBuyer_userId"))
		{
			String userId = (String) incomingRequest.get("DepartmentBuyer_userId");
			queryString.append(" AND commoditydepartmentbuyer.userId = '" + userId + "'");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}