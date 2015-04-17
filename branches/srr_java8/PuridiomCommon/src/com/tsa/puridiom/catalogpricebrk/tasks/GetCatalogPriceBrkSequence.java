/*
 * Created on Nov 10, 2003 
 */
package com.tsa.puridiom.catalogpricebrk.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo 
 */
public class GetCatalogPriceBrkSequence extends Task
{
	public Object  executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String catalogId = (String ) incomingRequest.get("CatalogPriceBrk_catalogId");
		String itemNumber = (String ) incomingRequest.get("CatalogPriceBrk_itemNumber");
		
		String queryString = "select max(catalogPriceBrk.id.sequence) from CatalogPriceBrk as catalogPriceBrk " +			"where catalogPriceBrk.id.catalogId = '" +  catalogId + "' AND catalogPriceBrk.id.itemNumber = '" + itemNumber + "'";
		
		List result = dbs.query(queryString);
		this.setStatus(dbs.getStatus()) ;
		//system.out.println("task status: " + this.getStatus());
		
		if(result.size() > 0)
		{
			return ((BigDecimal)result.get(0)).add(new BigDecimal(1)).toString();
		}
		else
		{
			return new BigDecimal(1).toString();	
		}
	}
	public static void main(String[] args)
	{
	}
}
