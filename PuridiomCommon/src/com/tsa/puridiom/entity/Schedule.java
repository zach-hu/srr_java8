package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Schedule implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.SchedulePK comp_id;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.util.Date scheduleDate;

    /** nullable persistent field */
    private java.util.Date completionDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date revisedDate;

    /** nullable persistent field */
    private java.math.BigDecimal scheduleAmount;

    /** full constructor */
    public Schedule(com.tsa.puridiom.entity.SchedulePK comp_id, java.lang.String description, java.util.Date scheduleDate, java.util.Date completionDate, java.lang.String status, java.util.Date revisedDate) {
        this.comp_id = comp_id;
        this.description = description;
        this.scheduleDate = scheduleDate;
        this.completionDate = completionDate;
        this.status = status;
        this.revisedDate = revisedDate;
    }

    /** default constructor */
    public Schedule() {
    }

    /** minimal constructor */
    public Schedule(com.tsa.puridiom.entity.SchedulePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.SchedulePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.SchedulePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.util.Date getScheduleDate() {
		return this.scheduleDate;
//		return HiltonUtility.ckNull(this.scheduleDate);
    }

    public void setScheduleDate(java.util.Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public java.util.Date getCompletionDate() {
		return this.completionDate;
//		return HiltonUtility.ckNull(this.completionDate);
    }

    public void setCompletionDate(java.util.Date completionDate) {
        this.completionDate = completionDate;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 10) {
			status = status.substring(0, 10);
		}
		this.status = status;
    }

    public java.util.Date getRevisedDate() {
		return this.revisedDate;
//		return HiltonUtility.ckNull(this.revisedDate);
    }

    public void setRevisedDate(java.util.Date revisedDate) {
        this.revisedDate = revisedDate;
    }
    
    public java.math.BigDecimal getScheduleAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.scheduleAmount);
	}

	public void setScheduleAmount(java.math.BigDecimal scheduleAmount) {
		this.scheduleAmount = scheduleAmount;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Schedule) ) return false;
        Schedule castOther = (Schedule) other;
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
