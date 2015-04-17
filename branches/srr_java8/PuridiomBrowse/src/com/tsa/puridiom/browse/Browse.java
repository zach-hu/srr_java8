/*
 * Created May 2005
 */
package com.tsa.puridiom.browse;

import com.tsagate.foundation.utility.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class Browse {
    private BrowseObject browseObject;
    private BrowseColumn inputColumn;
    private List browseList = new ArrayList();
	private List costRangeList = new ArrayList();
	private List currentFilters = new ArrayList();
	private List groupFilterList = new ArrayList();
	private Map inputValues = new HashMap();
	private String browseName;
	private String browseId;
	private String currentSortColumn;
	private String currentSortType;
	private int pageSize;
	private int rowCount;
	private int rowStart;
	private int rowEnd;
	private int pageCount;
	private int currentPage;
	private long lastAccessed = (new Date()).getTime();
	
    public int getRowEnd() {
        return rowEnd;
    }
    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }
    public int getRowStart() {
        if (rowCount == 0) {
            rowStart = 0;
        }
        return rowStart;
    }
    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }
    public String getCurrentSortColumn() {
        return currentSortColumn;
    }
    public void setCurrentSortColumn(String currentSortColumn) {
        this.currentSortColumn = currentSortColumn;
    }
    public String getCurrentSortType() {
        return currentSortType;
    }
    public void setCurrentSortType(String currentSortType) {
        this.currentSortType = currentSortType;
    }
	public String getBrowseName() {
		return this.browseName;
	}
	public void setBrowseName(String value) {
		this.browseName = value;
	}
	public List getCurrentFilters() {
		return this.currentFilters;
	}
	public void setCurrentFilters(List value) {
		this.currentFilters = value;
	}
	public void addCurrentFilters(BrowseFilter value) {
		this.currentFilters.add(value);
	}
	public int getPageSize() {
		return this.pageSize;
	}
	public void setPageSize(int x) {
		this.pageSize = x;
	}
	public int getRowCount() {
		return this.rowCount;
	}
	public void setRowCount(int x) {
		this.rowCount = x;
	}
	public int getPageCount() {
		return this.pageCount;
	}
	public void setPageCount(int x) {
		this.pageCount = x;
	}
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public List getBrowseList() {
        return browseList;
    }
    public void setBrowseList(List browseList) {
        this.browseList = browseList;
    }
    public List getCostRangeList() {
        return costRangeList;
    }
    public void setCostRangeList(List costRangeList) {
        this.costRangeList = costRangeList;
    }
    public List getGroupFilterList() {
        return groupFilterList;
    }
    public void setGroupFilterList(List groupFilterList) {
        this.groupFilterList = groupFilterList;
    }
    public BrowseObject getBrowseObject() {
        return browseObject;
    }
    public void setBrowseObject(BrowseObject browseObject) {
        this.browseObject = browseObject;
    }
    public String getBrowseId() {
        return browseId;
    }
    public void setBrowseId(String browseId) {
        this.browseId = browseId;
    }
    
    //This obtains page results from retrieve that gets only registers by Page
    public List getPageResults() {
    	return this.browseList;
    	/*int startRow = (currentPage * pageSize) - pageSize;
		int endRow = currentPage * pageSize;
		
		if (endRow > this.getRowCount()) {
		    endRow =  this.getRowCount();
		}
		
		this.setRowStart(startRow + 1);
		this.setRowEnd(endRow);

		return browseList.subList(startRow, endRow);*/
    }
    
    //This obtains page results from retrieve that gets all registers
    public List getPageResultsFromAllRegisters() {
		int startRow = (currentPage * pageSize) - pageSize;
		int endRow = currentPage * pageSize;
		
		if (endRow > this.getRowCount()) {
		    endRow =  this.getRowCount();
		}
		
		this.setRowStart(startRow + 1);
		this.setRowEnd(endRow);

		return browseList.subList(startRow, endRow);
    }
    
    public Map getInputValues() {
        return inputValues;
    }
    public void setInputValues(Map inputValues) {
        this.inputValues = inputValues;
    }
    public void setInputValue(Map keyMap, String value) {
        if (Utility.isEmpty(value)) {
            this.inputValues.remove(keyMap);
        } else {
            this.inputValues.put(keyMap, value);
        }
    }
    public String getInputValue(Map keyMap) {
        String	value = (String) this.inputValues.get(keyMap);
        return Utility.ckNull(value);
    }
    public BrowseColumn getInputColumn() {
        return inputColumn;
    }
    public void setInputColumn(BrowseColumn inputColumn) {
        this.inputColumn = inputColumn;
    }
    public void setLastAccessed(long lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
    public long getLastAccessed() {
        return this.lastAccessed;
    }
}