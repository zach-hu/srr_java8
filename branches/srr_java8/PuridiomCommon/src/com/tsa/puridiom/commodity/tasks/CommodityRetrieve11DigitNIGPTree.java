package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/*
	11 Digit NIGP Commodity Code pattern: 111-22-33-4444
	Level 1	(xxx00000000)
	Level 2 	(111xx000000)
	Level 3	(11122xx0000)
	Level 4	(1112233xxxx)
*/

public class CommodityRetrieve11DigitNIGPTree extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String	oid = (String) incomingRequest.get("organizationId");
		
		CommodityGet11DigitNIGPWhereClause commodityWhere = new CommodityGet11DigitNIGPWhereClause();
	    String sqlWhere = (String) commodityWhere.executeTask(incomingRequest);
	    StringBuffer queryString = new StringBuffer("from Commodity as Commodity where 1=1 ");
	    
		if (!Utility.isEmpty(sqlWhere)) {
		    queryString.append(" AND (" + sqlWhere.toString() + ")");
		}
		queryString.append(" ORDER BY Commodity.commodity ASC");

		List result = dbs.query(queryString.toString()) ;

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}