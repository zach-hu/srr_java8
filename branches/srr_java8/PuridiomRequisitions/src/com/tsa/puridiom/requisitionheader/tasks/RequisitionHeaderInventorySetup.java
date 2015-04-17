package com.tsa.puridiom.requisitionheader.tasks;
import java.util.Map;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHeaderInventorySetup extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;

		Object il_itemLocation = incomingRequest.get("InvLocation_itemLocation") ;
		String	itemLocation = new String();

		if (il_itemLocation != null && il_itemLocation instanceof String[]) {
			String itemLocationArray[] = (String[]) il_itemLocation;
			itemLocation = itemLocationArray[0];
		}
		else {
			itemLocation = (String) il_itemLocation;
		}


		incomingRequest.put("RequisitionHeader_taxCode"," ") ;
		if (!Utility.isEmpty(itemLocation)) {
			incomingRequest.put("RequisitionHeader_itemLocation",itemLocation) ;
		}


		return null ;
	}
}
