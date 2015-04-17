package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CommodityDepartmentBuyer implements Serializable {

	/** identifier field */
    private java.math.BigDecimal icHeader;

	/** identifier field */
	private String commodity;

	/** identifier field */
    private String departmentCode;

    /** identifier field */
    private String userId;

    /** full constructor */
    public CommodityDepartmentBuyer(java.math.BigDecimal icHeader, java.lang.String commodity, java.lang.String departmentCode, java.lang.String userId) {
    	this.icHeader = icHeader;
    	this.commodity = commodity;
    	this.departmentCode = departmentCode;
        this.userId = userId;
    }

    /** default constructor */
    public CommodityDepartmentBuyer() {
    }

    public java.math.BigDecimal getIcHeader() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
	}

	public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
	}


    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
    }

    public java.lang.String getDepartmentCode() {
		return this.departmentCode;
    }

    public void setDepartmentCode(java.lang.String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public java.lang.String getUserId() {
		return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("commodity", getCommodity())
        	.append("departmentCode", getDepartmentCode())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CommodityDepartmentBuyer) ) return false;
        CommodityDepartmentBuyer castOther = (CommodityDepartmentBuyer) other;
        return new EqualsBuilder()
        	.append(this.getCommodity(), castOther.getCommodity())
            .append(this.getDepartmentCode(), castOther.getDepartmentCode())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        	.append(getCommodity())
            .append(getDepartmentCode())
            .append(getUserId())
            .toHashCode();
    }
}
