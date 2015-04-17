package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PoAsset implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.PoAssetPK comp_id;

    /** persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private String assetNumber;

    /** nullable persistent field */
    private String serialNumber;

    /** nullable persistent field */
    private String endUser;

    /** nullable persistent field */
    private java.math.BigDecimal icRecHeader;

    /** nullable persistent field */
    private String paymentStatus;

    /** nullable persistent field */
    private String faBatchId;

    /** nullable persistent field */
    private java.math.BigDecimal icInvHeader;

    /** full constructor */
    public PoAsset(com.tsa.puridiom.entity.PoAssetPK comp_id, java.math.BigDecimal icPoHeader, java.lang.String assetNumber, java.lang.String serialNumber, java.lang.String endUser, java.math.BigDecimal icRecHeader, java.lang.String paymentStatus, java.lang.String faBatchId, java.math.BigDecimal icInvHeader) {
        this.comp_id = comp_id;
        this.icPoHeader = icPoHeader;
        this.assetNumber = assetNumber;
        this.serialNumber = serialNumber;
        this.endUser = endUser;
        this.icRecHeader = icRecHeader;
        this.paymentStatus = paymentStatus;
        this.faBatchId = faBatchId;
        this.icInvHeader = icInvHeader;
    }

    /** default constructor */
    public PoAsset() {
    }

    /** minimal constructor */
    public PoAsset(com.tsa.puridiom.entity.PoAssetPK comp_id, java.math.BigDecimal icPoHeader) {
        this.comp_id = comp_id;
        this.icPoHeader = icPoHeader;
    }

    public com.tsa.puridiom.entity.PoAssetPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.PoAssetPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.lang.String getAssetNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetNumber);
    }

    public void setAssetNumber(java.lang.String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public java.lang.String getSerialNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.serialNumber);
    }

    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public java.lang.String getEndUser() {
		return (java.lang.String)HiltonUtility.ckNull(this.endUser);
    }

    public void setEndUser(java.lang.String endUser) {
        this.endUser = endUser;
    }

    public java.math.BigDecimal getIcRecHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecHeader);
    }

    public void setIcRecHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public java.lang.String getPaymentStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.paymentStatus);
    }

    public void setPaymentStatus(java.lang.String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public java.lang.String getFaBatchId() {
		return (java.lang.String)HiltonUtility.ckNull(this.faBatchId);
    }

    public void setFaBatchId(java.lang.String faBatchId) {
        this.faBatchId = faBatchId;
    }

    public java.math.BigDecimal getIcInvHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icInvHeader);
    }

    public void setIcInvHeader(java.math.BigDecimal icInvHeader) {
        this.icInvHeader = icInvHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoAsset) ) return false;
        PoAsset castOther = (PoAsset) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
