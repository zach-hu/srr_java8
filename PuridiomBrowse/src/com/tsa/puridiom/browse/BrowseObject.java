/*
 * Created on Feb 2, 2004
 */
package com.tsa.puridiom.browse;

import com.tsagate.properties.DictionaryManager;

import java.util.*;

/**
 * @author Kelli
 */
public class BrowseObject {
	private String browseName;
	private BrowseColumn browseColumns[];
	private BrowseColumn labels[];
	private List browseFilters = new ArrayList();
	private List groupFilters = new ArrayList();
	private Map columnTypes = new HashMap();
	private Map columnLabels = new HashMap();
	private String sqlSelect = "";
	private String sqlFrom;
	private String sqlWhere;
	private String sqlOrderBy;
	private String sqlGroupBy;
	private String title;
	private String foreignDatabase;
	private String noAttributeErrorMsg;
	private int pageSize;
	private int maxRows;
	private int rowCount;
	private int pageCount;
	private int currentPage;
	private boolean detailIncluded = false;
	private boolean detailVisible = false;
	private boolean orderByDefault = false;
	private boolean attributeSet = true;
	private boolean requireAttributeValues = true;
	private String queryFilter = "";
	private List browseKeys = new ArrayList();

	public String getBrowseName() {
		return this.browseName;
	}
	public void setBrowseName(String value) {
		this.browseName = value;
	}
	public BrowseColumn[] getBrowseColumns() {
		return this.browseColumns;
	}
	public void setBrowseColumns(BrowseColumn[] x) {
		this.browseColumns = x;
	}
	public BrowseColumn[] getLabels() {
		return this.labels;
	}
	public void setLabels(BrowseColumn[] x) {
		this.labels = x;
	}
	public List getBrowseFilters() {
		return this.browseFilters;
	}
	public void setBrowseFilters(List value) {
		this.browseFilters = value;
	}
	public void addGroupFilter(BrowseColumn value) {
		this.groupFilters.add(value);
	}
	public List getGroupFilters() {
		return this.groupFilters;
	}
	public void setGroupFilters(List value) {
		this.groupFilters = value;
	}
	public Map getColumnTypes() {
		return this.columnTypes;
	}
	public void setColumnTypes(Map value) {
		this.columnTypes = value;
	}
	public Map getColumnLabels() {
		return this.columnLabels;
	}
	public void setColumnLabels(Map value) {
		this.columnLabels = value;
	}
	public void addBrowseFilter(BrowseFilter value) {
		this.browseFilters.add(value);
	}
	public String getSqlFrom() {
		return this.sqlFrom;
	}
	public void setSqlFrom(String x) {
		this.sqlFrom = x;
	}
	public String getSqlWhere() {
		return this.sqlWhere;
	}
	public void setSqlWhere(String x) {
		this.sqlWhere = x;
	}
	public String getSqlOrderBy() {
		return this.sqlOrderBy;
	}
	public void setSqlOrderBy(String x) {
		this.sqlOrderBy = x;
	}
	public String getTitle() {
		return this.title;
	}
	public String getTitle(String oid) {
	    return DictionaryManager.getLabel(oid,  this.title,  this.title, false);
	}
    public String getTitle(String oid, String language) {
        return DictionaryManager.getLabelsInstance(oid, language).getLabel(oid,  this.title,  this.title, false);
    }

	public void setTitle(String x) {
		this.title = x;
	}
    public String getForeignDatabase() {
        return foreignDatabase;
    }
    public void setForeignDatabase(String foreignDatabase) {
        this.foreignDatabase = foreignDatabase;
    }
    public String getNoAttributeErrorMsg() {
        return noAttributeErrorMsg;
    }
    public void setNoAttributeErrorMsg(String noAttributeErrorMsg) {
        this.noAttributeErrorMsg = noAttributeErrorMsg;
    }
	public int getPageSize() {
		return this.pageSize;
	}
	public void setPageSize(int x) {
		this.pageSize = x;
	}
	public int getMaxRows() {
		return this.maxRows;
	}
	public void setMaxRows(int x) {
		this.maxRows = x;
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
	public boolean isDetailIncluded() {
		return this.detailIncluded;
	}
	public void setDetailIncluded(boolean x) {
		this.detailIncluded = x;
	}
	public boolean isDetailVisible() {
		return this.detailVisible;
	}
	public void setDetailVisible(boolean x) {
		this.detailVisible = x;
	}
    public boolean isAttributeSet() {
        return attributeSet;
    }
    public void setAttributeSet(boolean attributeSet) {
        this.attributeSet = attributeSet;
    }
    public boolean requireAttributeValues() {
        return this.requireAttributeValues;
    }
    public void setRequireAttributeValues(boolean requireAttributeValues) {
        this.requireAttributeValues = requireAttributeValues;
    }
    public String getSqlSelect()
    {
        return sqlSelect;
    }
    public void setSqlSelect(String sqlSelect)
    {
        this.sqlSelect = sqlSelect;
    }
    public boolean isOrderByDefault()
    {
        return orderByDefault;
    }
    public void setOrderByDefault(boolean orderByDefault)
    {
        this.orderByDefault = orderByDefault;
    }
    public String getSqlGroupBy()
    {
        return sqlGroupBy;
    }
    public void setSqlGroupBy(String sqlGroupBy)
    {
        this.sqlGroupBy = sqlGroupBy;
    }
    public String getQueryFilter()
    {
        return queryFilter;
    }
    public void setQueryFilter(String queryFilter)
    {
        this.queryFilter = queryFilter;
    }
    public List getBrowseKeys() {
        return browseKeys;
    }
    public void setBrowseKeys(List browseKeys) {
        this.browseKeys = browseKeys;
    }
    public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public boolean validateColumn(String columnName) {
		columnName = columnName.replaceAll("\\.", "_");
		if (browseColumns != null && browseColumns.length > 0) {
			for (BrowseColumn browseColumn: browseColumns) {
				if (columnName.equalsIgnoreCase(browseColumn.getColumnName())) {
					return true;
				}
			}
		}
		
		return false;
	}
}