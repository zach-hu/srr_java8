/*
 * Created on Feb 25, 2004
 */
package com.tsa.puridiom.browse;

import java.util.*;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class BrowseGroupFilter {
	private String columnName;
	private String label;
	private String type;
	private String sqlSelect;
	private String sqlFrom;
	private String sqlWhere;
	private String sqlOrderBy;
	private String sqlGroupBy;
	private String query;
	private List	selectionValues = new ArrayList();
	
	public String getLabel() {
		return this.label;
	}
	public void setLabel(String x) {
		this.label = x;
	}
	public String getType() {
		return Utility.ckNull(this.type);
	}
	public void setType(String x) {
		this.type = x;
	}
	public String getColumnName() {
		return this.columnName;
	}
	public void setColumnName(String x) {
		this.columnName = x;
	}
	public String getSqlSelect() {
		return this.sqlSelect;
	}
	public void setSqlSelect(String x) {
		this.sqlSelect = x;
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
	public String getSqlGroupBy() {
		return this.sqlGroupBy;
	}
	public void setSqlGroupBy(String x) {
		this.sqlGroupBy = x;
	}
	public String getQuery() {
		return this.query;
	}
	public void setQuery(String x) {
		this.query = x;
	}
	public List getSelectionValues() {
		return this.selectionValues;
	}
	public void setSelectionValues(List x) {
		this.selectionValues = x;
	}
	public void addSelectionValue(Object x) {
		this.selectionValues.add(x);
	}
}