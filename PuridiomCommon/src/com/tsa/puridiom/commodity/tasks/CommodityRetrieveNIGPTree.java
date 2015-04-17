package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/*
	NIGP Commodity Code pattern: CCCPP
	CCC	-	Category	(%00)
	PP	-	Product	(CCC%)
*/

public class CommodityRetrieveNIGPTree extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String organizationId = (String) incomingRequest.get("organizationId");
		StringBuffer queryString = new StringBuffer("from Commodity as Commodity where 1=1 ");

		CommodityGetNIGPWhereClause commodityWhere = new CommodityGetNIGPWhereClause();
	    incomingRequest.put("retrieveAllCodes", "Y");
		String sqlWhere = (String) commodityWhere.executeTask(incomingRequest);

		if (!Utility.isEmpty(sqlWhere)) {
		    queryString.append(" AND (" + sqlWhere.toString() + ")");
		}
		if (organizationId.equalsIgnoreCase("BLY07P")) {
			queryString.append(" ORDER BY Commodity.level3 ASC, Commodity.level1 ASC, Commodity.level2 ASC");
		}
		else {
			queryString.append(" ORDER BY Commodity.commodity ASC");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}