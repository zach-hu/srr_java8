package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentRequisitionPK implements Serializable {

    /** identifier field */
    private java.lang.String requisitionerCode;

    /** identifier field */
    private java.math.BigDecimal icReqHeader;

    /** full constructor */
    public RecentRequisitionPK(java.lang.String requisitionerCode, java.math.BigDecimal icReqHeader) {
        this.requisitionerCode = requisitionerCode;
        this.icReqHeader = icReqHeader;
    }

    /** default constructor */
    public RecentRequisitionPK() {
    }

    public java.lang.String getRequisitionerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionerCode);
    }

    public void setRequisitionerCode(java.lang.String requisitionerCode) {
        this.requisitionerCode = requisitionerCode;
    }
    
    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("requisitionerCode", getRequisitionerCode())
            .append("icReqHeader", getIcReqHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentRequisitionPK) ) return false;
        RecentRequisitionPK castOther = (RecentRequisitionPK) other;
        return new EqualsBuilder()
            .append(this.getRequisitionerCode(), castOther.getRequisitionerCode())
            .append(this.getIcReqHeader(), castOther.getIcReqHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRequisitionerCode())
            .append(getIcReqHeader())
            .toHashCode();
    }

}
