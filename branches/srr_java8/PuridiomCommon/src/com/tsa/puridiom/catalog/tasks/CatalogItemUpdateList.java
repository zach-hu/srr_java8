/**
 * Created on Mar 2, 2004
 * @author Kelli
 * com.tsa.puridiom.catalog.tasks.CatalogItemUpdateStatusByCatalogId.java
 *
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CatalogItemUpdateList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List	catalogItemList = (List) incomingRequest.get("catalogItemList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("catalog-item-update-by-entity.xml");
				
			for (int i=0; i < catalogItemList.size(); i++) {
				CatalogItem catalogItem = (CatalogItem) catalogItemList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("catalogItem", catalogItem);
				updateParameters.put("CatalogItem_catalogId", catalogItem.getComp_id().getCatalogId());
				updateParameters.put("CatalogItem_itemNumber", catalogItem.getComp_id().getItemNumber());
				
				Set keySet = incomingRequest.keySet();
				Iterator iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					if (key.indexOf("CatalogItem_") == 0) {
						//must be an CatalogItem attribute
						Object obj =  incomingRequest.get(key);
						if (obj instanceof String[]) {
							String arrayObj[] = (String[]) obj;
							updateParameters.put(key, arrayObj[i]);
						} else {
						    updateParameters.put(key, obj);
						}
					}
				}
				
				process.executeProcess(updateParameters);
				if(process.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(Status.FAILED);
					throw new Exception("Error ocurred updating catalog items!");
				}
			}

			result = catalogItemList;			
		    this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
