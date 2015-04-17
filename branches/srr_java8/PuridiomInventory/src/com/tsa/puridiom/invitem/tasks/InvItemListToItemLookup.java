
package com.tsa.puridiom.invitem.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InvItemListToItemLookup extends Task
{

	public  Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map) object;
		List itemLookupList = new ArrayList();
		
		InvItem  invItem = null ;
//		InvVendor  invVendor = null ;
		InvLocation  invLocation = null ;

		String reqType = (String) incomingRequest.get("RequisitionHeader_requisitionType");
		List invItemList = (List) incomingRequest.get("invItemList") ;
//		List invVendorList = (List) incomingRequest.get("invVendorList");
		List invLocationList = (List) incomingRequest.get("invLocationList") ;
		
		if (invItemList != null && invLocationList != null) {
			Map newIncomingRequest = new HashMap();
			
			for (int i = 0; i < invItemList.size(); i++) {
				
				InvItemLookupSetValues setValues = new InvItemLookupSetValues();
				
				invItem = (InvItem) invItemList.get(i);
//				invVendor = (InvVendor) invVendorList.get(i) ;
				
				if(invLocationList.get(i) != null){
					invLocation = (InvLocation) invLocationList.get(i) ;
				} else {
					invLocation = new InvLocation();
				}
				
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("RequisitionHeader_requisitionType", reqType);
				newIncomingRequest.put("invItem", invItem);
//				newIncomingRequest.put("invVendor", invVendor);
				newIncomingRequest.put("invLocation", invLocation);

				ItemLookup itemLookup = (ItemLookup)setValues.executeTask(newIncomingRequest);
				
				if(itemLookup != null){
					itemLookupList.add(itemLookup);
				}
			}
		} 
		
		result = itemLookupList;
		
		return result;
	}
}
