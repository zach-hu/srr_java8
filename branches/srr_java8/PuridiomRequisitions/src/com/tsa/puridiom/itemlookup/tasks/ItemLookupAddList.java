package com.tsa.puridiom.itemlookup.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ItemLookupAddList extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			
			List browseList = (List) incomingRequest.get("browseList");
			if(browseList == null){
				browseList = new ArrayList();
			}
			
			List itemLookupList = (List) incomingRequest.get("itemLookupList");
			
			if(itemLookupList != null){
				browseList.addAll(itemLookupList);
			}
			
			result = browseList;
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}
}
