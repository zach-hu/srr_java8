package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VwBlanketReleases implements Serializable {

    private String poNumber;

    private java.math.BigDecimal lastRelease;

    private java.math.BigDecimal releaseCount;

    private java.math.BigDecimal totalReleased;

    /** full constructor */
    public VwBlanketReleases(java.lang.String poNumber, java.math.BigDecimal lastRelease, java.math.BigDecimal releaseCount, java.math.BigDecimal totalReleased) {
        this.poNumber = poNumber;
        this.lastRelease = lastRelease;
        this.releaseCount = releaseCount;
        this.totalReleased = totalReleased;
    }

    /** default constructor */
    public VwBlanketReleases() {
    }

    public java.lang.String getPoNumber() {
		return this.poNumber;
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.math.BigDecimal getLastRelease() {
		return this.lastRelease;
    }

    public void setLastRelease(java.math.BigDecimal lastRelease) {
        this.lastRelease = lastRelease;
    }

    public java.math.BigDecimal getReleaseCount() {
		return this.releaseCount;
    }

    public void setReleaseCount(java.math.BigDecimal releaseCount) {
        this.releaseCount = releaseCount;
    }


    public java.math.BigDecimal getTotalReleased() {
		return this.totalReleased;
    }

    public void setTotalReleased(java.math.BigDecimal totalReleased) {
        this.totalReleased = totalReleased;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("poNumber", getPoNumber())
            .append("lastRelease", getLastRelease())
            .append("releaseCount", getReleaseCount())
            .append("totalReleased", getTotalReleased())
            .toString();
    }
/*
    public boolean equals(Object other) {
        if ( !(other instanceof VwBlanketReleases) ) return false;
        VwBlanketReleases castOther = (VwBlanketReleases) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getIcQuestion())
            .append(getVendorId())
            .toHashCode();
    }

    */
}
