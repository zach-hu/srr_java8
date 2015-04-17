package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StdTablePK implements Serializable {

    /** identifier field */
    private String tableType;

    /** identifier field */
    private String tableKey;

    /** full constructor */
    public StdTablePK(java.lang.String tableType, java.lang.String tableKey) {
        this.tableType = tableType;
        this.tableKey = tableKey;
    }

    /** default constructor */
    public StdTablePK() {
    }

    public java.lang.String getTableType() {
		return (java.lang.String)HiltonUtility.ckNull(this.tableType);
    }

    public void setTableType(java.lang.String tableType) {
        if (!HiltonUtility.isEmpty(tableType) && tableType.length() > 255) {
            tableType = tableType.substring(0, 255);
        }
        this.tableType = tableType;
    }

    public java.lang.String getTableKey() {
		return (java.lang.String)HiltonUtility.ckNull(this.tableKey);
    }

    public void setTableKey(java.lang.String tableKey) {
        if (!HiltonUtility.isEmpty(tableKey) && tableKey.length() > 30) {
            tableKey = tableKey.substring(0, 30);
        }
        this.tableKey = tableKey;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("tableType", getTableType())
            .append("tableKey", getTableKey())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdTablePK) ) return false;
        StdTablePK castOther = (StdTablePK) other;
        return new EqualsBuilder()
            .append(this.getTableType(), castOther.getTableType())
            .append(this.getTableKey(), castOther.getTableKey())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTableType())
            .append(getTableKey())
            .toHashCode();
    }

	//Engineering Review 

}
