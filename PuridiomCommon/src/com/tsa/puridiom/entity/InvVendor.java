package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvVendor implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvVendorPK comp_id;

    /** nullable persistent field */
    private java.util.Date lastDate;

    /** nullable persistent field */
    private java.math.BigDecimal lastPrice;

    /** nullable persistent field */
    private String mfgNumber;

    /** nullable persistent field */
    private java.math.BigDecimal leadTime;

    /** nullable persistent field */
    private String primaryVendor;

    /** full constructor */
    public InvVendor(com.tsa.puridiom.entity.InvVendorPK comp_id, java.util.Date lastDate, java.math.BigDecimal lastPrice, java.lang.String mfgNumber, java.math.BigDecimal leadTime, java.lang.String primaryVendor) {
        this.comp_id = comp_id;
        this.lastDate = lastDate;
        this.lastPrice = lastPrice;
        this.mfgNumber = mfgNumber;
        this.leadTime = leadTime;
        this.primaryVendor = primaryVendor;
    }

    /** default constructor */
    public InvVendor() {
    }

    /** minimal constructor */
    public InvVendor(com.tsa.puridiom.entity.InvVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvVendorPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.util.Date getLastDate() {
		return this.lastDate;
//		return HiltonUtility.ckNull(this.lastDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastDate);
    }

    public void setLastDate(java.util.Date lastDate) {
        this.lastDate = lastDate;
    }

    public java.math.BigDecimal getLastPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastPrice);
    }

    public void setLastPrice(java.math.BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public java.lang.String getMfgNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgNumber).trim();
    }

    public void setMfgNumber(java.lang.String mfgNumber) {
		if (!HiltonUtility.isEmpty(mfgNumber) && mfgNumber.length() > 20) {
			mfgNumber = mfgNumber.substring(0, 20);
		}
		this.mfgNumber = mfgNumber;
    }

    public java.math.BigDecimal getLeadTime() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.leadTime);
    }

    public void setLeadTime(java.math.BigDecimal leadTime) {
        this.leadTime = leadTime;
    }

    public java.lang.String getPrimaryVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.primaryVendor).trim();
    }

    public void setPrimaryVendor(java.lang.String primaryVendor) {
		if (!HiltonUtility.isEmpty(primaryVendor) && primaryVendor.length() > 1) {
			primaryVendor = primaryVendor.substring(0, 1);
		}
		this.primaryVendor = primaryVendor;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvVendor) ) return false;
        InvVendor castOther = (InvVendor) other;
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
