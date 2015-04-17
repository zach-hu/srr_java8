package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetNote implements Serializable {

	 /** identifier field */
    private com.tsa.puridiom.entity.AssetNotePK comp_id;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String stdText;

    /** nullable persistent field */
    private String lastChgBy;

    /** full constructor */
    public AssetNote(com.tsa.puridiom.entity.AssetNotePK comp_Id, java.util.Date dateEntered, java.util.Date dateChanged, java.lang.String userId, java.lang.String stdText, java.lang.String lastChgBy) {
    	this.comp_id = comp_Id;
        this.dateEntered = dateEntered;
        this.dateChanged = dateChanged;
        this.userId = userId;
        this.stdText = stdText;
        this.lastChgBy = lastChgBy;
    }

    /** default constructor */
    public AssetNote() {
    }

    /** minimal constructor */
    public AssetNote(com.tsa.puridiom.entity.AssetNotePK comp_Id) {
    	this.comp_id = comp_Id;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateChanged() {
        return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
		if (!HiltonUtility.isEmpty(userId) && userId.length() > 15) {
			userId = userId.substring(0, 15);
		}
		this.userId = userId;
    }

    public java.lang.String getStdText() {
		return (java.lang.String)HiltonUtility.ckNull(this.stdText).trim();
    }

    public void setStdText(java.lang.String stdText) {
		if (!HiltonUtility.isEmpty(stdText) && stdText.length() > 2000) {
			stdText = stdText.substring(0, 2000);
		}
		this.stdText = stdText;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 25) {
			lastChgBy = lastChgBy.substring(0, 25);
		}
		this.lastChgBy = lastChgBy;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssetNote) ) return false;
        AssetNote castOther = (AssetNote) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public com.tsa.puridiom.entity.AssetNotePK getComp_id() {
		return comp_id;
	}

	public void setComp_id(com.tsa.puridiom.entity.AssetNotePK comp_id) {
		this.comp_id = comp_id;
	}

}
