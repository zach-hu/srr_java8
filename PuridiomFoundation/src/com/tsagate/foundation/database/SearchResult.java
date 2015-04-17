package com.tsagate.foundation.database;

import java.util.List;

/**
 * 
 * @author Alexander
 *
 */
public class SearchResult {

	private List list;
	private int index; 
	private int recordPerPage;
	private long totalRecords;
	
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the recordPerPage
	 */
	public int getRecordPerPage() {
		return recordPerPage;
	}
	/**
	 * @param recordPerPage the recordPerPage to set
	 */
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	/**
	 * @return the totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
}
