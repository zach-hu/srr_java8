package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TrackingData implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.TrackingDataPK comp_id;

    /** persistent field */
    private String trackingNumber;

    /** nullable persistent field */
    private String trackingDesc;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public TrackingData(com.tsa.puridiom.entity.TrackingDataPK comp_id, java.lang.String trackingNumber, java.lang.String trackingDesc, java.lang.String owner) {
        this.comp_id = comp_id;
        this.trackingNumber = trackingNumber;
        this.trackingDesc = trackingDesc;
        this.owner = owner;
    }

    /** default constructor */
    public TrackingData() {
    }

    public com.tsa.puridiom.entity.TrackingDataPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.TrackingDataPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getTrackingNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.trackingNumber);
    }

    public void setTrackingNumber(java.lang.String trackingNumber) {
		if (!HiltonUtility.isEmpty(trackingNumber) && trackingNumber.length() > 50) {
			trackingNumber = trackingNumber.substring(0, 50);
		}
		this.trackingNumber = trackingNumber;
    }

    public java.lang.String getTrackingDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.trackingDesc);
    }

    public void setTrackingDesc(java.lang.String trackingDesc) {
		if (!HiltonUtility.isEmpty(trackingDesc) && trackingDesc.length() > 255) {
			trackingDesc = trackingDesc.substring(0, 255);
		}
		this.trackingDesc = trackingDesc;
    }

    public java.lang.String getOwner() {
    	return (java.lang.String)HiltonUtility.ckNull(this.owner);
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TrackingData) ) return false;
        TrackingData castOther = (TrackingData) other;
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
