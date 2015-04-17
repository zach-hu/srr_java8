package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RecentRequisition implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RecentRequisitionPK comp_id;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** full constructor */
    public RecentRequisition(com.tsa.puridiom.entity.RecentRequisitionPK comp_id, java.util.Date dateEntered) {
        this.comp_id = comp_id;
        this.dateEntered = dateEntered;
    }

    /** default constructor */
    public RecentRequisition() {
    }

    /** minimal constructor */
    public RecentRequisition(com.tsa.puridiom.entity.RecentRequisitionPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RecentRequisitionPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RecentRequisitionPK comp_id) {
        this.comp_id = comp_id;
    }

	public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String toString() {
        return new ToStringBuilder(this)
        	.append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentRequisition) ) return false;
        RecentRequisition castOther = (RecentRequisition) other;
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
