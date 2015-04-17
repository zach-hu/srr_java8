package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/*
	UNSPSC Commodity Code pattern: SSFFGGCCBB
		SS	-	Segment	(%00000000)
		FF		-	Family	(SS%000000)
		GG	-	Class / Group	(SSFF%0000)
		CC	-	Commodity	(SSFFGG%00)
		BB	-	Business Function	(SSFFGGCC%)
*/

public class CommodityRetrieveUNSPSCTree extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Commodity as Commodity where 1=1 ");
		
		CommodityGetUNSPSCWhereClause commodityWhere = new CommodityGetUNSPSCWhereClause();
	    incomingRequest.put("retrieveAllCodes", "Y");
		String sqlWhere = (String) commodityWhere.executeTask(incomingRequest);
	    
		if (!Utility.isEmpty(sqlWhere)) {
		    queryString.append(" AND (" + sqlWhere.toString() + ")");
		}
		queryString.append(" ORDER BY Commodity.commodity ASC");
		
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}