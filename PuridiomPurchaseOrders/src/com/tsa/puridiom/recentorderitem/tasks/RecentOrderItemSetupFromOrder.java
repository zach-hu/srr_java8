package com.tsa.puridiom.recentorderitem.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class RecentOrderItemSetupFromOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    PoLine poLine = (PoLine) incomingRequest.get("poLine");

		    if (poHeader == null) {
		        throw new Exception ("Recent Order Item cannot be setup.  PoHeader was not found.");
		    }
		    if (poLine == null) {
		        throw new Exception ("Recent Order Item cannot be setup.  PoLine was not found.");
		    }

			String	source = poLine.getItemSource();
		    String	location = poLine.getItemLocation();
		    String	buyer = poHeader.getBuyerCode();
		    if (source.equals("CAT") || source.equals("XML")) {
		        location = poLine.getCatalogId();
		    }
		    if (Utility.isEmpty(buyer)) {
		        buyer = poHeader.getOwner();
		    }

		    incomingRequest.put("RecentOrderItem_buyerCode", buyer);
			incomingRequest.put("RecentOrderItem_itemNumber", poLine.getItemNumber());
		    incomingRequest.put("RecentOrderItem_itemSource", source);
		    incomingRequest.put("RecentOrderItem_itemLocation", location);
			incomingRequest.put("RecentOrderItem_description", poLine.getDescription());
			incomingRequest.put("RecentOrderItem_dateEntered", Dates.today("", ""));

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