package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PoSecurity implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.PoSecurityPK comp_id;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private String lastChangedBy;

    /** nullable persistent field */
    private String timeZone;

    /** full constructor */
    public PoSecurity(com.tsa.puridiom.entity.PoSecurityPK comp_id, java.lang.String owner, java.util.Date dateEntered, java.util.Date dateChanged, java.lang.String lastChangedBy, java.lang.String timeZone) {
        this.comp_id = comp_id;
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.dateChanged = dateChanged;
        this.lastChangedBy = lastChangedBy;
        this.timeZone = timeZone;
    }

    /** default constructor */
    public PoSecurity() {
    }

    /** minimal constructor */
    public PoSecurity(com.tsa.puridiom.entity.PoSecurityPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.PoSecurityPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.PoSecurityPK comp_id) {
        this.comp_id = comp_id;
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

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateChanged() {
		return this.dateChanged;
//		return HiltonUtility.ckNull(this.dateChanged);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateChanged);
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.lang.String getLastChangedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangedBy).trim();
    }

    public void setLastChangedBy(java.lang.String lastChangedBy) {
		if (!HiltonUtility.isEmpty(lastChangedBy) && lastChangedBy.length() > 15) {
			lastChangedBy = lastChangedBy.substring(0, 15);
		}
		this.lastChangedBy = lastChangedBy;
    }

    public String getTimeZone() {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone) {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoSecurity) ) return false;
        PoSecurity castOther = (PoSecurity) other;
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
