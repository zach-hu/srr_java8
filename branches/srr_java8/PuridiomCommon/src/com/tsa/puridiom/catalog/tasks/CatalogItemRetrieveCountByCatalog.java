package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class CatalogItemRetrieveCountByCatalog extends Task
{
	public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String catalogId = (String ) incomingRequest.get("CatalogItem_catalogId");

            if (Utility.isEmpty(catalogId)) {
                catalogId =  (String ) incomingRequest.get("Catalog_catalogId");
            }
            String queryString = "select count(*) from CatalogItem as CatalogItem where CatalogItem.id.catalogId = ? ";
            List resultList = dbs.query(queryString, new Object[] {catalogId} , new Type[] { Hibernate.STRING}) ;

            if (resultList != null && resultList.size() > 0)
            {
                result = (Long) resultList.get(0);
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