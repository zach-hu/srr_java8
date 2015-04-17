package com.tsa.puridiom.catalogpricebrk.tasks;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CatalogPriceBrkRetrieveForItemLookup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CatalogPriceBrk as catalogpricebrk where 1=1 ");

		String catalogId = (String) incomingRequest.get("CatalogPriceBrk_catalogId");
		String itemNumber = (String) incomingRequest.get("CatalogPriceBrk_itemNumber");
		String quantity = (String) incomingRequest.get("quantity");

		if (Utility.isEmpty(quantity)) {
		    quantity = "0";
		}

		queryString.append(" AND catalogpricebrk.id.catalogId = '" + catalogId + "'");
		queryString.append(" AND catalogpricebrk.id.itemNumber = '" + itemNumber + "'");
		queryString.append(" AND catalogpricebrk.status = '02'");
		queryString.append(" AND ((catalogpricebrk.breakType = 'Q'");
		queryString.append(" AND catalogpricebrk.qtyFrom <= '" + quantity + "'");
		queryString.append(" AND catalogpricebrk.qtyTo >= '" + quantity + "')");
		queryString.append(" OR (catalogpricebrk.breakType = 'D'");
		queryString.append(" AND catalogpricebrk.dateFrom <= '" + Dates.today("yyyy-MM-dd", (String) incomingRequest.get("userTimeZone")) + "'");
		queryString.append(" AND catalogpricebrk.dateTo >= '" + Dates.today("yyyy-MM-dd", (String) incomingRequest.get("userTimeZone")) + "'))");

		List list = dbs.query(queryString.toString()) ;
		Object result = null;

		if (list != null && list.size() > 0) {
		    BigDecimal	lowestPrice = null;
		    int index = 0;
		    for (int i=0; i < list.size(); i++) {
		        CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) list.get(i);
		        if (lowestPrice == null || catalogPriceBrk.getUnitPrice().compareTo(lowestPrice) == -1) {
		            lowestPrice = catalogPriceBrk.getUnitPrice();
		            index = i;
		        }
		    }
		    if (index > -1) {
		        result = list.get(index);
		    }
		}

		incomingRequest.put("catalogPriceBrk", result);

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}