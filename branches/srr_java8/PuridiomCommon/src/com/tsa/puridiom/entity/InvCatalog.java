package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvCatalog implements Serializable {

    /** identifier field */
    private String invCatid;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public InvCatalog(java.lang.String invCatid, java.lang.String description, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner) {
        this.invCatid = invCatid;
        this.description = description;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
    }

    /** default constructor */
    public InvCatalog() {
    }

    /** minimal constructor */
    public InvCatalog(java.lang.String invCatid) {
        this.invCatid = invCatid;
    }

    public java.lang.String getInvCatid() {
		return (java.lang.String)HiltonUtility.ckNull(this.invCatid).trim();
    }

    public void setInvCatid(java.lang.String invCatid) {
		if (!HiltonUtility.isEmpty(invCatid) && invCatid.length() > 15) {
			invCatid = invCatid.substring(0, 15);
		}
		this.invCatid = invCatid;
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

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
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
            .append("invCatid", getInvCatid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvCatalog) ) return false;
        InvCatalog castOther = (InvCatalog) other;
        return new EqualsBuilder()
            .append(this.getInvCatid(), castOther.getInvCatid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getInvCatid())
            .toHashCode();
    }

}
