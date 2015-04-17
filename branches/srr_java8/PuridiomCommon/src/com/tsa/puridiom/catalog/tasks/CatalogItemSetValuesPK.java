package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class CatalogItemSetValuesPK
{
	public void setValues(Map incomingRequest, CatalogItem catalogitem ) throws Exception
	{
		try
		{
			String catalogId = (String ) incomingRequest.get("CatalogItem_catalogId");
			String itemNumber = (String ) incomingRequest.get("CatalogItem_itemNumber");
			CatalogItemPK comp_id = new CatalogItemPK();
			comp_id.setCatalogId(catalogId);
			comp_id.setItemNumber(itemNumber);
			catalogitem.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}