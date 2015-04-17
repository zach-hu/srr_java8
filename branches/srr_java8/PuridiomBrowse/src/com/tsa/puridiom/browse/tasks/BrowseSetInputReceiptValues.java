package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowseSetInputReceiptValues extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	browseId = (String) incomingRequest.get("browseId") ;
            String  userDateFormat = (String) incomingRequest.get("userDateFormat") ;
			Browse browse = BrowseManager.getInstance().getBrowse(browseId);
			List pageResultsList = browse.getPageResults();

			if (pageResultsList != null) {
				BrowseColumn browseColumns[] = browse.getBrowseObject().getBrowseColumns();
			    BrowseColumn sortedBrowseColumn = null;

				for (int ic = 0; ic < browseColumns.length; ic++) {
				    BrowseColumn browseColumn = browseColumns[ic];
				    if (browseColumn.storeRequestValue()) {
				        String	columnName = browseColumn.getColumnName();
				        List browseKeys = browse.getBrowseObject().getBrowseKeys();

				        if (browseColumn.getType().equals("Checkbox") && columnName.indexOf("c_") == 0) {
				            columnName = "Input_" + columnName.substring(2);
				        }

					    if (incomingRequest.containsKey(columnName)) {
					        Object inputValueObject = incomingRequest.get(columnName);
					        if (inputValueObject instanceof String[]) {
					            String inputValues[] = (String[]) inputValueObject;
					            for (int ir = 0; ir < inputValues.length; ir++) {
					            	String inputVal = (String) inputValues[ir];
					            	if(inputVal.equalsIgnoreCase("Y"))
					            	{
						                Object resultObj = pageResultsList.get(ir);
						                //Map	keyMap = BrowseUtility.getColumnKeyValue(browseKeys, resultObj, organizationId, browseColumns, browseId, userDateFormat);
						                Map	keyMap = BrowseUtility.getColumnKeyValue(browseKeys, resultObj, organizationId, browseColumns, browseId, userDateFormat, browseColumn.getColumnName());
					                    browse.setInputValue(keyMap, (String) inputValues[ir]);
					            	}
					            }
					        } else {
					            Map	keyMap = BrowseUtility.getColumnKeyValue(browseKeys, pageResultsList.get(0), organizationId, browseColumns, browseId, userDateFormat);
				                browse.setInputValue(keyMap, (String) inputValueObject);
					        }
					    }
					    browse.setInputColumn(browseColumn);
					    //break;
				    }
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