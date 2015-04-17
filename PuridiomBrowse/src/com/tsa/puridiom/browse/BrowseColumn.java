/*
 * Created on Feb 2, 2004
 */
package com.tsa.puridiom.browse;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.InlineView;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class BrowseColumn
{
	private String columnName = "";
	private String className = "";
	private String methodName = "";
	private String label = "";
	private String link = "";
	private String linkImage = "" ;
	private String sort = "";
	private String type = "";
	private String allowFilter = "";
	private int size = 0;
	private int inputSize = 0;
	private int trim = 0;
	private Object value;
	private boolean hidden = false;
	private boolean hiddenInput = false;
	private boolean detail = false;
	private int index = -1;
	private String alignment = "left";
	private int iWidth = 10;
	private ArrayList linkedColumns = null;
	private boolean sqlPart = true;
	private boolean computed = false;
	private String computeType = "";
	private ArrayList concatenateColumns = null;
	private List argumentColumns = new ArrayList();
	private String alias = "";
	private boolean storeRequestValue = false;
	private boolean distinct = false;
	private boolean filterDefault = false;
	private boolean selectInput = false;
	private boolean textInput = false;
	private boolean checkbox = false;
	private String oid;
	private String language = "en";

	/** Drpdown Implementation */
	private String selecthql = "";
	private String methodOptionKey = "";
	private String methodOptionValue = "";
	private String valueOption = "";
	private String idName = "";

	public BrowseColumn()
	{
	}

	public BrowseColumn(String inOid, String inLanguage)
	{
		this.oid = inOid;
		this.language = inLanguage;
	}

    public int getIndex()
    {
        return index;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }
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
	public String getMethodName() {
		return this.methodName;
	}
	public void setMethodName(String value) {
		this.methodName = value;
	}
	public String getLabel() {
	    return this.label;
	}
	public String getLabel(String oid) {
	    return DictionaryManager.getLabel(oid,  this.label,  this.label, false);
	}
	public String getLabel(String oid, String language) {
	    return DictionaryManager.getLabelsInstance(oid, language).getLabel(oid,  this.label,  this.label, false);
	}
	public void setLabel(String value) {
		this.label = value;
	}
	public String getLink() {
		return this.link;
	}
	public void setLink(String value) {
		this.link = value;
		//this.setLinkedColumns(BrowseUtility.getColumnNamesFromLink(this));
	}
	public String getSort() {
		return this.sort;
	}
	public void setSort(String value) {
		this.sort = value;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String value) {
		this.type = value;
	}
	public String getAllowFilter() {
		return this.allowFilter;
	}
	public void setAllowFilter(String value) {
		this.allowFilter = value;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int value) {
		this.size = value;
	}
	public int getInputSize() {
		return this.inputSize;
	}
	public void setInputSize(int value) {
		this.inputSize = value;
	}
	public int getTrim() {
		return this.trim;
	}
	public void setTrim(int value) {
		this.trim = value;
	}
	public Object getValue() {
		return this.value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isHidden() {
		return this.hidden;
	}
	public void setHidden(boolean value) {
		this.hidden = value;
	}
	public boolean isHiddenInput() {
		return this.hiddenInput;
	}
	public void setHiddenInput(boolean value) {
		this.hiddenInput = value;
	}
	public boolean isSelectInput() {
		return this.selectInput;
	}
	public void setSelectInput(boolean value) {
		this.selectInput = value;
	}
	public boolean isTextInput() {
		return this.textInput;
	}
	public void setTextInput(boolean value) {
		this.textInput = value;
	}
	public boolean isCheckbox() {
		return this.checkbox;
	}
	public void setCheckbox(boolean value) {
		this.checkbox = value;
	}
	public boolean isDetail() {
		return this.detail;
	}
	public void setDetail(boolean value) {
		this.detail = value;
	}
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String col = "Column Name: " + this.getColumnName() + ",type: "+  this.getType();
        return col;
    }
    public String getAlignment()
    {
        return alignment;
    }
    public void setAlignment(String alignment)
    {
        this.alignment = alignment;
    }
    public int getIWidth()
    {
        if(this.getSize() > 0)
        {
            return this.getSize();
        }
        return this.iWidth;
    }
    public void setIWidth(int iwidth)
    {
        this.iWidth = iwidth;
    }
    public ArrayList getLinkedColumns()
    {
        return linkedColumns;
    }
    public void setLinkedColumns(ArrayList linkedColumns)
    {
        this.linkedColumns = linkedColumns;
    }
	/**
	 * @return Returns the sqlPart.
	 */
	public boolean isSqlPart() {
		return sqlPart;
	}
	/**
	 * @param sqlPart The sqlPart to set.
	 */
	public void setSqlPart(boolean sqlPart) {
		this.sqlPart = sqlPart;
	}
	/**
	 * @return Returns the computed.
	 */
	public boolean isComputed() {
		return computed;
	}
	/**
	 * @param computed The computed to set.
	 */
	public void setComputed(boolean computed) {
		this.computed = computed;
	}
	/**
	 * @return Returns the concatenateColumns.
	 */
	public ArrayList getConcatenateColumns() {
		return concatenateColumns;
	}
	/**
	 * @param concatenateColumns The concatenateColumns to set.
	 */
	public void setConcatenateColumns(ArrayList concatenateColumns) {
		this.concatenateColumns = concatenateColumns;
	}
    /**
	 * @return the argumentColumns
	 */
	public List getArgumentColumns()
	{
		return argumentColumns;
	}
	/**
	 * @param argumentColumns the argumentColumns to set
	 */
	public void setArgumentColumns(List argumentColumns)
	{
		this.argumentColumns = argumentColumns;
	}
	/**
     * @return Returns the computeType.
     */
    public String getComputeType()
    {
        return computeType;
    }
    /**
     * @param computeType The computeType to set.
     */
    public void setComputeType(String computeType)
    {
        this.computeType = computeType;
    }
    public String getAlias()
    {
        if(Utility.isEmpty(this.alias))
        {
            this.alias = this.getColumnName();
        }
        return this.alias;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    public boolean storeRequestValue() {
        return storeRequestValue;
    }
    public void setStoreRequestValue(boolean storeRequestValue) {
        this.storeRequestValue = storeRequestValue;
    }
    public boolean isDistinct() {
        return distinct;
    }
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    public boolean isFilterDefault() {
        return filterDefault;
    }
    public void setFilterDefault(boolean filterDefault) {
        this.filterDefault = filterDefault;
    }

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}
	
	/** DropDown implementation */

	public String getSelecthql() {
		return selecthql;
	}

	public void setSelecthql(String selecthql) {
		this.selecthql = selecthql;
	}

	public String getMethodOptionKey() {
		return methodOptionKey;
	}

	public void setMethodOptionKey(String methodOptionKey) {
		this.methodOptionKey = methodOptionKey;
	}

	public String getMethodOptionValue() {
		return methodOptionValue;
	}

	public void setMethodOptionValue(String methodOptionValue) {
		this.methodOptionValue = methodOptionValue;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getValueOption() {
		return valueOption;
	}

	public void setValueOption(String valueOption) {
		this.valueOption = valueOption;
	}
}