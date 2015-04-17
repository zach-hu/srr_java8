package com.tsa.puridiom.subcommodity.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class SubCommodityRetrieveTree extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String oid = (String) incomingRequest.get("organizationId");
        StringBuffer queryString = new StringBuffer("from SubCommodity as SubCommodity where 1=1 ");

        SubCommodityGetTreeViewWhereClause commodityWhere = new SubCommodityGetTreeViewWhereClause();
        String sqlWhere = (String) commodityWhere.executeTask(incomingRequest);

        if (!Utility.isEmpty(sqlWhere)) {
            queryString.append(" AND (" + sqlWhere.toString() + ")");
        }
        if (oid.equalsIgnoreCase("BLY07P")) {
        	queryString.append(" ORDER BY SubCommodity.description ASC");
        }
        else {
        	queryString.append(" ORDER BY SubCommodity.commodity ASC");
        }

        List result = dbs.query(queryString.toString()) ;
        this.setStatus(dbs.getStatus()) ;
        return result ;
    }
}