package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class CatalogItemReview extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String catalogId = poLine.getCatalogId();
			String itemNumber = poLine.getItemNumber();

			String queryString = "Select CatalogItem.cost from CatalogItem as CatalogItem where CatalogItem.id.catalogId = ? and CatalogItem.id.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {catalogId, itemNumber } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			if( result != null)
			{
				BigDecimal catalogItem_cost = (BigDecimal) result;
				BigDecimal catalogItemDifference = poLine.getUnitPrice().subtract(catalogItem_cost);
				incomingRequest.put("catalogItemDifference", catalogItemDifference);
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