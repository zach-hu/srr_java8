package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BrowseFindSortedValue extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	browseId = (String) incomingRequest.get("browseId") ;
            String userDateFormat = (String) incomingRequest.get("userDateFormat") ;
            Browse browse = BrowseManager.getInstance().getBrowse(browseId);
			String	findString = (String) incomingRequest.get("finder");
			String	sortedColumn = browse.getCurrentSortColumn();
			List browseList = browse.getBrowseList();
			int	recordMatched = -1;
			int	newPage = -1;

			if (browseList != null) {
				BrowseColumn browseColumns[] = browse.getBrowseObject().getBrowseColumns();
			    BrowseColumn sortedBrowseColumn = null;

				for (int ic = 0; ic < browseColumns.length; ic++) {
				    BrowseColumn browseColumn = browseColumns[ic];
				    if (browseColumn.getColumnName().equals(sortedColumn)) {
				        sortedBrowseColumn = browseColumn;
				        break;
				    }
				}
				if (sortedBrowseColumn == null){
				    throw new Exception("Sorted column not found");
				}

				for (int i=0; i < browseList.size(); i++) {
					Object obj = browseList.get(i);

					Object resultObj = BrowseUtility.getTestColumnValue(sortedBrowseColumn, obj, organizationId, browseColumns, browseId, userDateFormat);
					String	sortedValue = "";
					if (resultObj instanceof String) {
					    sortedValue = (String) resultObj;
					} else if (resultObj instanceof BigDecimal) {
					    sortedValue = ((BigDecimal) resultObj).toString();
					}
				    if (sortedValue != null && sortedValue.toUpperCase().indexOf(findString) >= 0) {
				        recordMatched = i;
				        break;
				    }
				}
			}
			if (recordMatched == -1) {
			    incomingRequest.put("errorMsg", "The value you entered was not found.");
			    newPage = 1;
			} else {
		        BigDecimal bdRecordMatch = new BigDecimal(recordMatched + 1);
		        BigDecimal bdPageSize = new BigDecimal(browse.getPageSize());

		        BigDecimal bdNewPage = bdRecordMatch.divide(bdPageSize, 0, BigDecimal.ROUND_CEILING);
		        newPage = bdNewPage.intValue();
			}

			if (newPage > browse.getPageCount()) {
			    incomingRequest.put("errorMsg", "Invalid Page [" + newPage + "]");
			    newPage = 1;
			} else if (newPage == -1) {
			    incomingRequest.put("errorMsg", "The value you entered was not found.");
			    newPage = 1;
			}

			browse.setCurrentPage(newPage);

			result = browse;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}