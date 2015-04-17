package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrowseSetKeyInputValues extends Task {
    
	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	browseId = (String) incomingRequest.get("browseId") ;
			Browse browse = BrowseManager.getInstance().getBrowse(browseId);
			BrowseColumn inputColumn = browse.getInputColumn();
			String	inputColumnName = inputColumn.getColumnName();
			Map requestValueMap = new HashMap();
			
			if (inputColumnName.indexOf("Input_") >= 0) {
			    inputColumnName = inputColumnName.substring(inputColumnName.indexOf("Input_") + 6);
			}
			
			Map inputValues = browse.getInputValues();
			Iterator iterator = inputValues.keySet().iterator();
			
			// Get HashMap keys and put values into the HashMap containing a list for each key
			//  Also put input value into the HashMap as a list
			//	Need to get the name of the input value key from incomingRequest
			while (iterator.hasNext()) {
			    Map keyMap = (Map) iterator.next();
			    Iterator keyIterator = keyMap.keySet().iterator();
			    String inputValue = (String) inputValues.get(keyMap);
			    
			    while (keyIterator.hasNext()) {
			        if (inputColumn.getType().equals("Checkbox") && !Utility.ckNull(inputValue).equals("Y")) {
			            // Only add values for checkbox browses if checkbox field was selected (value will be Y)
			            break;
			        }
				    String keyName = (String) keyIterator.next();
				    String	keyValue = (String) keyMap.get(keyName);
				    List list = new ArrayList();
				    int	ind = keyName.indexOf("_id_"); 
				    if (ind > 0) {
				        keyName = keyName.substring(0, ind) + keyName.substring(ind + 3);
				    }
				    
				    if (requestValueMap.containsKey(keyName)) {
				        list = (List) requestValueMap.get(keyName);
				        list.add(keyValue);
				    } else {
				        list.add(keyValue);
				    }
			        requestValueMap.put(keyName, list);
				}
			    
			    if (!inputColumn.getType().equals("Checkbox")) {
				    List inputList = new ArrayList();
				    if (requestValueMap.containsKey(inputColumnName)) {
				        inputList = (List) requestValueMap.get(inputColumnName);
				        inputList.add(inputValue);
				    } else {
				        inputList.add(inputValue);
				    }
				    requestValueMap.put(inputColumnName, inputList);
			    }
			}
			
			// Loop through new HashMap to set values into incomingRequest as a List (if size > 1) or a String
			Iterator requestMapIterator = requestValueMap.keySet().iterator();
			while (requestMapIterator.hasNext()) {
			    String	requestKey = (String) requestMapIterator.next();
			    List	requestListValue = (List) requestValueMap.get(requestKey);
			    
			    if (requestListValue.size() > 1) {
			        String requestValueArray[] = new String[requestListValue.size()];
			        incomingRequest.put(requestKey, requestListValue.toArray(requestValueArray));
			    } else {
			        incomingRequest.put(requestKey, (String) requestListValue.get(0));
			    }
			}
			
			result = browse;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}