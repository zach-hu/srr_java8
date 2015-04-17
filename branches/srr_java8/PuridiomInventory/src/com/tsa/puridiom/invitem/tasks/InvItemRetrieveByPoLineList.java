package com.tsa.puridiom.invitem.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InvItemRetrieveByPoLineList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String itemNumber = null;
		List invItemList = new ArrayList();
		
		try
		{
			List poLineList = (List) incomingRequest.get("poLineList");
			
			for (int i = 0; i < poLineList.size(); i++) {
				PoLine poLine = (PoLine)poLineList.get(i);
				itemNumber = poLine.getItemNumber();
				incomingRequest.put("InvItem_itemNumber", itemNumber);
				
				InvItemRetrieveById retrieve = new InvItemRetrieveById();
				InvItem invItem = (InvItem)retrieve.executeTask(incomingRequest);
				
				invItemList.add(invItem);
			}
			
			result = invItemList;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}