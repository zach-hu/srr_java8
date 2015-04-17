package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/*
	6 Digit NIGP Commodity Code pattern: 11-22-33
	Level 1	(xx2233)
	Level 2 	(11xx33)
	Level 3			(1122xx)
*/

public class CommodityRetrieve6DigitNIGPTree extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		String	oid = (String) incomingRequest.get("organizationId");

		CommodityGet6DigitNIGPWhereClause commodityWhere = new CommodityGet6DigitNIGPWhereClause();
		String sqlWhere = (String) commodityWhere.executeTask(incomingRequest);
		StringBuffer queryString = new StringBuffer("from Commodity as Commodity where 1=1 ");

		if (!Utility.isEmpty(sqlWhere)) {
			queryString.append(" AND (" + sqlWhere.toString() + ")");
		}
		queryString.append(" ORDER BY Commodity.commodity ASC");

		List result = dbs.query(queryString.toString());

		this.setStatus(dbs.getStatus());
		return result;
	}
}
