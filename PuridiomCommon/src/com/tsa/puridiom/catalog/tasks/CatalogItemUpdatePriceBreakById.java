package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.sql.Types;
import java.util.Map;

public class CatalogItemUpdatePriceBreakById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	catalogId = (String) incomingRequest.get("CatalogItem_catalogId");
			String	itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");
			String	priceBreak = (String) incomingRequest.get("CatalogItem_priceBrk");

			String	sql = "update catalog_item set catalog_item.price_brk = ? where catalog_item.catalog_id = ? and catalog_item.item_number = ?";
			Object [] args = new Object[3];
			Integer [] types = new Integer[3];
			args[0] = priceBreak;
			types[0] = Types.VARCHAR;
			args[1] = catalogId;
			types[1] = Types.VARCHAR;
			args[2] = itemNumber;
			types[2] = Types.VARCHAR;
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}