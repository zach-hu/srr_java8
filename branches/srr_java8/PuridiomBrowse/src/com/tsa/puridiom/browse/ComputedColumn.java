/*
 * Created on March 3, 2005
 */
package com.tsa.puridiom.browse;

import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class ComputedColumn 
{
    private String columnType = "";
	private Object value;
	
	public String getColumnType() {
		return Utility.ckNull(this.columnType);
	}	
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public Object getValue() {
		return this.value;
	}	
	public void setValue(Object value) {
		this.value = value;
	}
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String col = "Computed Column Type" + this.getColumnType() + ", value: "+  this.getValue();
        return col;
    }
}