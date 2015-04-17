package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionCritPK implements Serializable {

    /** identifier field */
    private String inspectCode;

    /** identifier field */
    private String critNo;

    /** full constructor */
    public InspectionCritPK(java.lang.String inspectCode, java.lang.String critNo) {
        this.inspectCode = inspectCode;
        this.critNo = critNo;
    }

    /** default constructor */
    public InspectionCritPK() {
    }

    public java.lang.String getInspectCode() {
        return this.inspectCode;
    }

    public void setInspectCode(java.lang.String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public java.lang.String getCritNo() {
        return this.critNo;
    }

    public void setCritNo(java.lang.String critNo) {
        this.critNo = critNo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("inspectCode", getInspectCode())
            .append("critNo", getCritNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionCritPK) ) return false;
        InspectionCritPK castOther = (InspectionCritPK) other;
        return new EqualsBuilder()
            .append(this.getInspectCode(), castOther.getInspectCode())
            .append(this.getCritNo(), castOther.getCritNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getInspectCode())
            .append(getCritNo())
            .toHashCode();
    }

}
