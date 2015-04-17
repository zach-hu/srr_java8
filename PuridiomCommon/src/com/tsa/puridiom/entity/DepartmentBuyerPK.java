package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DepartmentBuyerPK implements Serializable {

    /** identifier field */
    private String departmentCode;

    /** identifier field */
    private String userId;

    /** full constructor */
    public DepartmentBuyerPK(java.lang.String departmentCode, java.lang.String userId) {
        this.departmentCode = departmentCode;
        this.userId = userId;
    }

    /** default constructor */
    public DepartmentBuyerPK() {
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
            .append("departmentCode", getDepartmentCode())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DepartmentBuyerPK) ) return false;
        DepartmentBuyerPK castOther = (DepartmentBuyerPK) other;
        return new EqualsBuilder()
            .append(this.getDepartmentCode(), castOther.getDepartmentCode())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDepartmentCode())
            .append(getUserId())
            .toHashCode();
    }

}
