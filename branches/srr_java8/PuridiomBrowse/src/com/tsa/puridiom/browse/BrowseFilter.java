/*
 * Created on Feb 2, 2004
 */
package com.tsa.puridiom.browse;

/**
 * @author Kelli
 */
public class BrowseFilter {
	private String columnName = "";
	private String className = "";
	private String label = "";
	private String operator = "";
	private String logicalOperator = "";
	private String type = "";
	private String value = "";
	private String sort = "";
	private boolean originalFilter = false;
	
	public String getColumnName() {
		return this.columnName;
	}
	public void setColumnName(String value) {
		this.columnName = value;
	}
	public String getClassName() {
		return this.className;
	}
	public void setClassName(String value) {
		this.className = value;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(String value) {
		this.label = value;
	}
	public String getOperator() {
		return this.operator;
	}
	public void setOperator(String value) {
		this.operator = value;
	}
	public String getLogicalOperator() {
		return this.logicalOperator;
	}
	public void setLogicalOperator(String value) {
		this.logicalOperator = value;
	}
	public String getType() {
		return this.type;
	}	
	public void setType(String value) {
		this.type = value;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value  = value;
	}
	public String getSort() {
		return this.sort;
	}
	public void setSort(String value) {
		this.sort  = value;
	}
	public boolean isOriginalFilter() {
		return this.originalFilter;
	}
	public void setOriginalFilter(boolean value) {
		this.originalFilter = value;
	}
}