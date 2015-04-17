package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemExplodeKitSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			String	itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
	
			incomingRequest.put("KitItem_parentCatalogId", "INV");
			incomingRequest.put("KitItem_parentItemNumber", itemNumber);
		}
		catch(Exception e) {
			throw e;
		}
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}