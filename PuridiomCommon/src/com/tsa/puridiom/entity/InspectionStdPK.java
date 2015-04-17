package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionStdPK implements Serializable {

    /** identifier field */
    private String standardCode;

    /** identifier field */
    private String inspectType;

    /** identifier field */
    private String inspectCode;

    /** identifier field */
    private String critNo;

    private java.math.BigDecimal icInspStd ;

    /** full constructor */
    public InspectionStdPK(java.lang.String standardCode, java.lang.String inspectType, java.lang.String inspectCode, java.lang.String critNo, java.math.BigDecimal icInpsStd ) {
        this.standardCode = standardCode;
        this.inspectType = inspectType;
        this.inspectCode = inspectCode;
        this.critNo = critNo;
        this.icInspStd = icInspStd ;

    }

    /** default constructor */
    public InspectionStdPK() {
    }

    public java.lang.String getStandardCode() {
        return this.standardCode;
    }

    public void setStandardCode(java.lang.String standardCode) {
        this.standardCode = standardCode;
    }

    public java.lang.String getInspectType() {
        return this.inspectType;
    }

    public void setInspectType(java.lang.String inspectType) {
        this.inspectType = inspectType;
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

    public java.math.BigDecimal getIcInspStd() {
		return icInspStd;
	}

	public void setIcInspStd(java.math.BigDecimal icInspStd) {
		this.icInspStd = icInspStd;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("standardCode", getStandardCode())
            .append("inspectType", getInspectType())
            .append("inspectCode", getInspectCode())
            .append("critNo", getCritNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionStdPK) ) return false;
        InspectionStdPK castOther = (InspectionStdPK) other;
        return new EqualsBuilder()
            .append(this.getStandardCode(), castOther.getStandardCode())
            .append(this.getInspectType(), castOther.getInspectType())
            .append(this.getInspectCode(), castOther.getInspectCode())
            .append(this.getCritNo(), castOther.getCritNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getStandardCode())
            .append(getInspectType())
            .append(getInspectCode())
            .append(getCritNo())
            .toHashCode();
    }

}
