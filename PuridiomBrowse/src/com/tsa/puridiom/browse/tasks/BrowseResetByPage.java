package com.tsa.puridiom.browse.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsagate.foundation.database.SearchResult;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class BrowseResetByPage extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			BrowseObject	browseObject = (BrowseObject) incomingRequest.get("browseObject") ;
			SearchResult searchResult = (SearchResult)incomingRequest.get("browseSearchResult");
			List browseList = (List) incomingRequest.get("browseList");
			List costRangeList = (List) incomingRequest.get("costRangeList");
			List groupFilterList = (List) incomingRequest.get("groupFilterList");
			List filterList = browseObject.getBrowseFilters();
			String	sortedColumn = "";
			String	sortedType = "";
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			int	rowCount = 0;
			int	pageCount =0;
			int	pageSize =browseObject.getPageSize();

			if (pageSize == 0) {
				pageSize = 10;
			}
			if (browseList != null) {
				rowCount = Long.valueOf(searchResult.getTotalRecords()).intValue();
			    //rowCount = browseList.size();
			}
			if (pageSize > 0) {
			    pageCount = ((rowCount - 1) / pageSize) + 1;
			}

			if (filterList != null) {
				for (int ix=0; ix < filterList.size(); ix++) {
					BrowseFilter filter = (BrowseFilter) filterList.get(ix);
					String	sort = filter.getSort();

					if (sort != null && !sort.equalsIgnoreCase("N")) {
						sortedColumn = filter.getColumnName().replace('.', '_');
						sortedType = sort;
					}
				}
			}

			String	browseId = (String) incomingRequest.get("browseId");
			Browse browse =BrowseManager.getInstance().getBrowse(browseId);
			browse.setBrowseName(browseObject.getBrowseName());
			browse.setBrowseList(browseList);
			browse.setBrowseObject(browseObject);
			browse.setCostRangeList(costRangeList);
			browse.setCurrentFilters(browseObject.getBrowseFilters());
			browse.setCurrentPage(1);
			browse.setCurrentSortColumn(sortedColumn);
			browse.setCurrentSortType(sortedType);
			browse.setGroupFilterList(groupFilterList);
			browse.setPageCount(pageCount);
			browse.setPageSize(pageSize);
			browse.setRowCount(rowCount);

			browse.setRowStart(1);
			if (rowCount < pageSize)
				browse.setRowEnd(rowCount);
			else
				browse.setRowEnd(pageSize);

			result = browse;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}