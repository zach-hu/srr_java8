package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;



/** @author Hibernate CodeGenerator */
public class PrmReqWorkloadView extends Entity implements Serializable {

    /** identifier field */
    private String userId;

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private java.math.BigDecimal assigned;

    /** nullable persistent field */
    private java.math.BigDecimal sourcing;

    /** nullable persistent field */
    private java.math.BigDecimal pendingAward;

    /** nullable persistent field */
    private java.math.BigDecimal total;

    /** full constructor */
    public PrmReqWorkloadView( java.lang.String userId,	java.lang.String firstName,	java.lang.String lastName, java.math.BigDecimal assigned, java.math.BigDecimal sourcing, java.math.BigDecimal pendingAward, java.math.BigDecimal total  ) {
    	this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assigned = assigned;
        this.sourcing = sourcing;
        this.pendingAward = pendingAward;
        this.total = total;
    }

    /** default constructor */
    public PrmReqWorkloadView() {
    }

    /** minimal constructor */
    public PrmReqWorkloadView(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
        if (!HiltonUtility.isEmpty(userId)) {
            userId = userId.toUpperCase();
        }
        this.userId = userId;
    }

    public java.lang.String getFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.firstName).trim();
    }

    public void setFirstName(java.lang.String firstName) {
		if (!HiltonUtility.isEmpty(firstName) && firstName.length() > 20) {
			firstName = firstName.substring(0, 20);
		}
		this.firstName = firstName;
    }

    public java.lang.String getLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastName).trim();
    }

    public void setLastName(java.lang.String lastName) {
		if (!HiltonUtility.isEmpty(lastName) && lastName.length() > 20) {
			lastName = lastName.substring(0, 20);
		}
		this.lastName = lastName;
    }

    public java.math.BigDecimal getAssigned() {
		return this.assigned;
    }

    public void setAssigned(java.math.BigDecimal assigned) {
		this.assigned = assigned;
    }

    public java.math.BigDecimal getSourcing() {
		return this.sourcing;
    }

    public void setSourcing(java.math.BigDecimal sourcing) {
		this.sourcing = sourcing;
    }

    public java.math.BigDecimal getPendingAward() {
		return this.pendingAward;
    }

    public void setPendingAward(java.math.BigDecimal pendingAward) {
		this.pendingAward = pendingAward;
    }

    public java.math.BigDecimal getTotal() {
		return this.total;
    }

    public void setTotal(java.math.BigDecimal total) {
		this.total = total;
    }

}
