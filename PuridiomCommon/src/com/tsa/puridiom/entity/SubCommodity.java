package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SubCommodity implements Serializable {

    /** identifier field */
    private String commodity;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String nigplevel;

    /** nullable persistent field */
    private String level1;

    /** nullable persistent field */
    private String level2;

    /** nullable persistent field */
    private String level3;

    /** full constructor */
    public SubCommodity(java.lang.String commodity, java.lang.String description, java.lang.String buyerCode, java.lang.String status, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String nigplevel, java.lang.String level1, java.lang.String level2, java.lang.String level3) {
        this.commodity = commodity;
        this.description = description;
        this.status = status;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.lastChgBy = lastChgBy;
        this.lastChgDate = lastChgDate;
        this.nigplevel = nigplevel;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    /** default constructor */
    public SubCommodity() {
    }

    /** minimal constructor */
    public SubCommodity(java.lang.String commodity) {
        this.commodity = commodity;
    }

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
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

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 15) {
			lastChgBy = lastChgBy.substring(0, 15);
		}
		this.lastChgBy = lastChgBy;
    }

    public java.util.Date getLastChgDate() {
		return this.lastChgDate;
//		return HiltonUtility.ckNull(this.lastChgDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastChgDate);
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.lang.String getNigplevel() {
		return (java.lang.String)HiltonUtility.ckNull(this.nigplevel).trim();
    }

    public void setNigplevel(java.lang.String nigplevel) {
		if (!HiltonUtility.isEmpty(nigplevel) && nigplevel.length() > 1) {
		    nigplevel = nigplevel.substring(0, 1);
		}
		this.nigplevel = nigplevel;
    }

    public java.lang.String getLevel1() {
		return (java.lang.String)HiltonUtility.ckNull(this.level1).trim();
    }

    public void setLevel1(java.lang.String level1) {
		if (!HiltonUtility.isEmpty(level1) && level1.length() > 15) {
		    level1 = level1.substring(0, 15);
		}
		this.level1 = level1;
    }

    public java.lang.String getLevel2() {
		return (java.lang.String)HiltonUtility.ckNull(this.level2).trim();
    }

    public void setLevel2(java.lang.String level2) {
		if (!HiltonUtility.isEmpty(level2) && level2.length() > 15) {
		    level2 = level2.substring(0, 15);
		}
		this.level2 = level2;
    }

    public java.lang.String getLevel3() {
		return (java.lang.String)HiltonUtility.ckNull(this.level3).trim();
    }

    public void setLevel3(java.lang.String level3) {
		if (!HiltonUtility.isEmpty(level3) && level3.length() > 15) {
		    level3 = level3.substring(0, 15);
		}
		this.level3 = level3;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("subCommodity", getCommodity())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SubCommodity) ) return false;
        SubCommodity castOther = (SubCommodity) other;
        return new EqualsBuilder()
            .append(this.getCommodity(), castOther.getCommodity())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCommodity())
            .toHashCode();
    }

}
