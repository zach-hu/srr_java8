package com.tsa.puridiom.browse.tasks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class GenerateBrowseListFromItemLookup extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			BrowseObject	browseObject = (BrowseObject) incomingRequest.get("browseObject");
			BrowseColumn[] browseColumns = browseObject.getBrowseColumns();
			List browseList = (List) incomingRequest.get("browseList");

			List newBrowseList = new ArrayList();
			
			for (int i = 0; i < browseList.size(); i++) {
				ItemLookup itemLookup = (ItemLookup)browseList.get(i);
				Class cls = itemLookup.getClass() ;
				List valueList = new ArrayList();
				for (int j = 0; j < browseColumns.length; j++) {
					BrowseColumn browseColumn = browseColumns[j];
					
					Method mth = cls.getMethod(browseColumn.getMethodName(),null);
					Object value = mth.invoke(itemLookup, null);
					valueList.add(value);
				}
				newBrowseList.add(valueList.toArray());
			}
			
			result = newBrowseList;
	    } catch (Exception e) {
	    	
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}
