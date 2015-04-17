package com.tsa.puridiom.kititem.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class KitItemSetValuesPK
{
	public void setValues(Map incomingRequest, KitItem kititem ) throws Exception
	{
		try
		{
			String parentCatalogId = (String ) incomingRequest.get("KitItem_parentCatalogId");
			String parentItemNumber = (String ) incomingRequest.get("KitItem_parentItemNumber");
			String childCatalogId = (String ) incomingRequest.get("KitItem_childCatalogId");
			String childItemNumber = (String ) incomingRequest.get("KitItem_childItemNumber");
			KitItemPK comp_id = new KitItemPK();
			comp_id.setChildCatalogId(childCatalogId);
			comp_id.setChildItemNumber(childItemNumber);
			comp_id.setParentCatalogId(parentCatalogId);
			comp_id.setParentItemNumber(parentItemNumber);
			kititem.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}