package com.tsa.puridiom.catalogpricebrk.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class CatalogPriceBrkRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CatalogPriceBrk as catalogpricebrk where 1=1 ");
		if(incomingRequest.containsKey("CatalogPriceBrk_catalogId"))
		{			
			String catalogId = (String) incomingRequest.get("CatalogPriceBrk_catalogId");
			queryString.append(" AND catalogpricebrk.id.catalogId = '" + catalogId + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("CatalogPriceBrk_itemNumber");
			queryString.append(" AND catalogpricebrk.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_sequence"))
		{			
			String sequence = (String) incomingRequest.get("CatalogPriceBrk_sequence");
			queryString.append(" AND catalogpricebrk.id.sequence = '" + sequence + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_unitPrice"))
		{			
			String unitPrice = (String) incomingRequest.get("CatalogPriceBrk_unitPrice");
			queryString.append(" AND catalogpricebrk.unitPrice = '" + unitPrice + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_status"))
		{			
			String status = (String) incomingRequest.get("CatalogPriceBrk_status");
			queryString.append(" AND catalogpricebrk.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_breakType"))
		{			
			String breakType = (String) incomingRequest.get("CatalogPriceBrk_breakType");
			queryString.append(" AND catalogpricebrk.breakType = '" + breakType + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_breakFrom"))
		{			
			String breakFrom = (String) incomingRequest.get("CatalogPriceBrk_breakFrom");
			queryString.append(" AND catalogpricebrk.breakFrom = '" + breakFrom + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_breakTo"))
		{			
			String breakTo = (String) incomingRequest.get("CatalogPriceBrk_breakTo");
			queryString.append(" AND catalogpricebrk.breakTo = '" + breakTo + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_breakNote"))
		{			
			String breakNote = (String) incomingRequest.get("CatalogPriceBrk_breakNote");
			queryString.append(" AND catalogpricebrk.breakNote = '" + breakNote + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_qtyFrom"))
		{			
			String qtyFrom = (String) incomingRequest.get("CatalogPriceBrk_qtyFrom");
			queryString.append(" AND catalogpricebrk.qtyFrom = '" + qtyFrom + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_qtyTo"))
		{			
			String qtyTo = (String) incomingRequest.get("CatalogPriceBrk_qtyTo");
			queryString.append(" AND catalogpricebrk.qtyTo = '" + qtyTo + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_dateFrom"))
		{			
			String dateFrom = (String) incomingRequest.get("CatalogPriceBrk_dateFrom");
			queryString.append(" AND catalogpricebrk.dateFrom = '" + dateFrom + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_dateTo"))
		{			
			String dateTo = (String) incomingRequest.get("CatalogPriceBrk_dateTo");
			queryString.append(" AND catalogpricebrk.dateTo = '" + dateTo + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_umConv"))
		{			
			String umConv = (String) incomingRequest.get("CatalogPriceBrk_umConv");
			queryString.append(" AND catalogpricebrk.umConv = '" + umConv + "'");
		}
		if(incomingRequest.containsKey("CatalogPriceBrk_umFactor"))
		{			
			String umFactor = (String) incomingRequest.get("CatalogPriceBrk_umFactor");
			queryString.append(" AND catalogpricebrk.umFactor = '" + umFactor + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}