package com.tsa.puridiom.catalog.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class CatalogItemListToItemLookup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map) object;
		List itemLookupList = new ArrayList();
		
		CatalogItem  catalogItem = null ;

		List catalogItemList = (List) incomingRequest.get("catalogItemList") ;
		
		if (catalogItemList != null) {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("catalogitem-by-itemlookup.xml");
			
			Map newIncomingRequest = new HashMap();
			
			for (int i = 0; i < catalogItemList.size(); i++) {
				
				catalogItem = (CatalogItem) catalogItemList.get(i);
				
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("catalogItem", catalogItem);
				newIncomingRequest.put("Catalog_catalogId", incomingRequest.get("Catalog_catalogId"));

				process.executeProcess(newIncomingRequest);
				
				ItemLookup itemLookup = (ItemLookup)newIncomingRequest.get("itemLookup");
				
				if(itemLookup != null){
					itemLookupList.add(itemLookup);
				}
			}
		} 
		
		result = itemLookupList;
		
		return result;
	}
}