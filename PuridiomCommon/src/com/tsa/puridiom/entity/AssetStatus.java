package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetStatus implements Serializable {

	/** identifier field*/
    private String status;

    /** nullable persistent field */
    private java.math.BigDecimal count;

    /** full constructor */
    public AssetStatus(java.math.BigDecimal count) {
        this.count = count;
    }

    /** default constructor */
    public AssetStatus() {
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 1) {
			status = status.substring(0, 15);
		}
		this.status = status;
    }


    public java.math.BigDecimal getCount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.count);
    }

    public void setCount(java.math.BigDecimal count) {
        this.count = count;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("status", getStatus())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssetStatus) ) return false;
        AssetStatus castOther = (AssetStatus) other;
        return new EqualsBuilder()
            .append(this.getStatus(), castOther.getStatus())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getStatus())
            .toHashCode();
    }

}
