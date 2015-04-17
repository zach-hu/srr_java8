package com.tsa.puridiom.kititem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class KitItemSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			KitItemPK comp_id = new KitItemPK();
			KitItem kitItem = (KitItem) incomingRequest.get("kitItem");
			if (kitItem == null)
			{
				kitItem = new KitItem();
			}

			if (incomingRequest.containsKey("KitItem_parentCatalogId"))
			{
				String parentCatalogId = (String ) incomingRequest.get("KitItem_parentCatalogId");
				comp_id.setParentCatalogId(parentCatalogId);
			}
			if (incomingRequest.containsKey("KitItem_parentItemNumber"))
			{
				String parentItemNumber = (String ) incomingRequest.get("KitItem_parentItemNumber");
				comp_id.setParentItemNumber(parentItemNumber);
			}
			if (incomingRequest.containsKey("KitItem_childCatalogId"))
			{
				String childCatalogId = (String ) incomingRequest.get("KitItem_childCatalogId");
				comp_id.setChildCatalogId(childCatalogId);
			}
			if (incomingRequest.containsKey("KitItem_childItemNumber"))
			{
				String childItemNumber = (String ) incomingRequest.get("KitItem_childItemNumber");
				comp_id.setChildItemNumber(childItemNumber);
			}
			if (incomingRequest.containsKey("KitItem_childQuantity"))
			{
				String childQuantityString = (String) incomingRequest.get("KitItem_childQuantity");
				if (Utility.isEmpty(childQuantityString))
				{
					childQuantityString = "0";
				}
				BigDecimal childQuantity = new BigDecimal ( childQuantityString );
				kitItem.setChildQuantity(childQuantity);
			}
			kitItem.setComp_id(comp_id);

			result = kitItem;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}