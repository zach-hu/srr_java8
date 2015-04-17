package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class KeyIdPK implements Serializable {

    /** identifier field */
    private String tableName;

    /** identifier field */
    private java.math.BigDecimal keyRangeLow;

    /** full constructor */
    public KeyIdPK(java.lang.String tableName, java.math.BigDecimal keyRangeLow) {
        this.tableName = tableName;
        this.keyRangeLow = keyRangeLow;
    }

    /** default constructor */
    public KeyIdPK() {
    }


    public java.lang.String getTableName() {
		return (java.lang.String)HiltonUtility.ckNull(this.tableName).trim();
    }

    public void setTableName(java.lang.String tableName) {
		if (!HiltonUtility.isEmpty(tableName) && tableName.length() > 18) {
			tableName = tableName.substring(0, 18);
		}
		this.tableName = tableName;
    }

    public java.math.BigDecimal getKeyRangeLow() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.keyRangeLow);
    }

    public void setKeyRangeLow(java.math.BigDecimal keyRangeLow) {
        this.keyRangeLow = keyRangeLow;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("tableName", getTableName())
            .append("keyRangeLow", String.valueOf(getKeyRangeLow()))
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof KeyIdPK) ) return false;
        KeyIdPK castOther = (KeyIdPK) other;
        return new EqualsBuilder()
            .append(this.getTableName(), castOther.getTableName())
            .append(this.getKeyRangeLow(), castOther.getKeyRangeLow())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTableName())
            .append(getKeyRangeLow())
            .toHashCode();
    }

}
