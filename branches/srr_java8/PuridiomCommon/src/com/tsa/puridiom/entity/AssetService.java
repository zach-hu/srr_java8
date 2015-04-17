package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetService implements Serializable {

	 /** identifier field */
    private com.tsa.puridiom.entity.AssetServicePK comp_id;

    /** nullable persistent field */
    private java.util.Date serviceCallDate;

    /** nullable persistent field */
    private String callInitiatedBy;

    /** nullable persistent field */
    private java.util.Date dateInitiated;

    /** nullable persistent field */
    private java.util.Date responseDate;

    /** nullable persistent field */
    private java.util.Date completionDate;

    /** nullable persistent field */
    private String serviceAction;

    /** nullable persistent field */
    private java.math.BigDecimal serviceCost;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** full constructor */
    public AssetService(com.tsa.puridiom.entity.AssetServicePK comp_Id, java.util.Date serviceCallDate, java.lang.String callInitiatedBy, java.util.Date dateInitiated, java.util.Date responseDate, java.util.Date completionDate, java.lang.String serviceAction, java.math.BigDecimal serviceCost, java.util.Date dateChanged, java.lang.String lastChgBy) {
    	this.comp_id = comp_Id;
        this.serviceCallDate = serviceCallDate;
        this.callInitiatedBy = callInitiatedBy;
        this.dateInitiated = dateInitiated;
        this.responseDate = responseDate;
        this.completionDate = completionDate;
        this.serviceAction = serviceAction;
        this.serviceCost = serviceCost;
        this.lastChgBy = lastChgBy;
        this.dateChanged = dateChanged;
    }

    /** default constructor */
    public AssetService() {
    }

    /** minimal constructor */
    public AssetService(com.tsa.puridiom.entity.AssetServicePK comp_Id) {
    	this.comp_id = comp_Id;
    }

    public java.util.Date getServiceCallDate() {
        return this.serviceCallDate;
    }

    public void setServiceCallDate(java.util.Date serviceCallDate) {
        this.serviceCallDate = serviceCallDate;
    }

    public java.lang.String getCallInitiatedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.callInitiatedBy).trim();
    }

    public void setCallInitiatedBy(java.lang.String callInitiatedBy) {
		if (!HiltonUtility.isEmpty(callInitiatedBy) && callInitiatedBy.length() > 25) {
			callInitiatedBy = callInitiatedBy.substring(0, 25);
		}
		this.callInitiatedBy = callInitiatedBy;
    }

    public java.util.Date getDateInitiated() {
        return this.dateInitiated;
    }

    public void setDateInitiated(java.util.Date dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    public java.util.Date getResponseDate() {
        return this.responseDate;
    }

    public void setResponseDate(java.util.Date responseDate) {
        this.responseDate = responseDate;
    }

    public java.util.Date getCompletionDate() {
        return this.completionDate;
    }

    public void setCompletionDate(java.util.Date completionDate) {
        this.completionDate = completionDate;
    }

    public java.lang.String getServiceAction() {
		return (java.lang.String)HiltonUtility.ckNull(this.serviceAction).trim();
    }

    public void setServiceAction(java.lang.String serviceAction) {
		if (!HiltonUtility.isEmpty(serviceAction) && serviceAction.length() > 80) {
			serviceAction = serviceAction.substring(0, 80);
		}
		this.serviceAction = serviceAction;
    }

    public java.math.BigDecimal getServiceCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.serviceCost);
    }

    public void setServiceCost(java.math.BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 80) {
			lastChgBy = lastChgBy.substring(0, 25);
		}
		this.lastChgBy = lastChgBy;
    }

    public java.util.Date getDateChanged() {
        return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssetService) ) return false;
        AssetService castOther = (AssetService) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public com.tsa.puridiom.entity.AssetServicePK getComp_id() {
		return comp_id;
	}

	public void setComp_id(com.tsa.puridiom.entity.AssetServicePK comp_id) {
		this.comp_id = comp_id;
	}

}
