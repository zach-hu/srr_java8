package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionCrit implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InspectionCritPK comp_id;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String defaultFlag;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public InspectionCrit(com.tsa.puridiom.entity.InspectionCritPK comp_id, java.lang.String description, java.lang.String defaultFlag, java.lang.String status, java.util.Date dateEntered, java.lang.String owner) {
        this.comp_id = comp_id;
        this.description = description;
        this.defaultFlag = defaultFlag;
        this.status = status;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public InspectionCrit() {
    }

    /** minimal constructor */
    public InspectionCrit(com.tsa.puridiom.entity.InspectionCritPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InspectionCritPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InspectionCritPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getDefaultFlag() {
        return this.defaultFlag;
    }

    public void setDefaultFlag(java.lang.String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getOwner() {
        return this.owner;
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
        if ( !(other instanceof InspectionCrit) ) return false;
        InspectionCrit castOther = (InspectionCrit) other;
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
