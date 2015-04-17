/*
 * Created on Nov 26, 2003
 * @author renzo
 * project: TSACommon
 * com.tsagate.common.utilityFilter.java
 */
 
package com.tsagate.foundation.utility;

public class Filter
{
	private String columnName = "";
	private int oper = 0;
	private Object value = null;
	
	/**
	 * getColumnName
	 * @return
	 */
	public String getColumnName()
	{
		return columnName;
	}

	/**
	 * setColumnName
	 * @param columnName
	 */
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	/**
	 * getOper
	 * @return
	 */
	public int getOper()
	{
		return oper;
	}

	/**
	 * setOper
	 * @param oper
	 */
	public void setOper(int oper)
	{
		this.oper = oper;
	}

	/**
	 * getValue
	 * @return
	 */
	public Object getValue()
	{
		return value;
	}

	/**
	 * setValue
	 * @param value
	 */
	public void setValue(Object value)
	{
		this.value = value;
	}
	public Filter(String column, int Oper, Object val)
	{
		this.setColumnName(column);
		this.setOper(Oper);
		this.setValue(val);
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[Filter:");
		buffer.append(" columnName: ");
		buffer.append(columnName);
		buffer.append(" oper: ");
		buffer.append(oper);
		buffer.append(" value: ");
		buffer.append(value);
		buffer.append("]");
		return buffer.toString();
	}
}
