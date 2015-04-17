/**
 * 
 */
package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Johnny
 */
public class XlsData implements Serializable
{
	private List labels;

	private List columns;

	private List types;

	private List content;

	/**
	 * @return the labels
	 */
	public List getLabels()
	{
		return labels;
	}

	/**
	 * @param labels
	 *            the labels to set
	 */
	public void setLabels(List labels)
	{
		this.labels = labels;
	}

	/**
	 * @return the columns
	 */
	public List getColumns()
	{
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List columns)
	{
		this.columns = columns;
	}

	/**
	 * @return the types
	 */
	public List getTypes()
	{
		return types;
	}

	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(List types)
	{
		this.types = types;
	}

	/**
	 * @return the content
	 */
	public List getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(List content)
	{
		this.content = content;
	}
}
