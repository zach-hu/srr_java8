package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ItemCrossRef implements Serializable {

    /** identifier field */
    private String altItemNumber;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** default constructor */
    public ItemCrossRef() {
    }

    /** minimal constructor */
    public ItemCrossRef(java.lang.String altItemNumber, java.lang.String description, java.lang.String itemNumber, java.lang.String source, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner) {
        this.altItemNumber = altItemNumber;
        this.description = description;
        this.itemNumber = itemNumber;
        this.source = source;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
    }


    public java.lang.String getAltItemNumber() {
        return (java.lang.String)HiltonUtility.ckNull(this.altItemNumber).trim();
    }

    public void setAltItemNumber(java.lang.String altItemNumber) {
        if (!HiltonUtility.isEmpty(itemNumber) && altItemNumber.length() > 30) {
            altItemNumber = altItemNumber.substring(0, 30);
        }
        this.altItemNumber = altItemNumber;
    }

    public java.lang.String getDescription() {
        return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
        if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
            description = description.substring(0, 2000);
        }
        this.description = description;
    }

    public java.lang.String getItemNumber() {
        return (java.lang.String)HiltonUtility.ckNull(this.itemNumber).trim();
    }

    public void setItemNumber(java.lang.String itemNumber) {
        if (!HiltonUtility.isEmpty(itemNumber) && itemNumber.length() > 30) {
            itemNumber = itemNumber.substring(0, 30);
        }
        this.itemNumber = itemNumber;
    }

    public java.lang.String getSource() {
        return (java.lang.String)HiltonUtility.ckNull(this.source).trim();
    }

    public void setSource(java.lang.String source) {
        if (!HiltonUtility.isEmpty(source) && source.length() > 20) {
            source = source.substring(0, 20);
        }
        this.source = source;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("altItemNumber", getAltItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ItemCrossRef) ) return false;
        ItemCrossRef castOther = (ItemCrossRef) other;
        return new EqualsBuilder()
            .append(this.getAltItemNumber(), castOther.getAltItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAltItemNumber())
            .toHashCode();
    }

}
