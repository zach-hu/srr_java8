package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetLocation implements Serializable {

	 /** identifier field */
    private com.tsa.puridiom.entity.AssetLocationPK comp_id;

    /** persistent field */
    private String locationType;

    /** nullable persistent field */
    private String division;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String facility;

    /** nullable persistent field */
    private String building;

    /** nullable persistent field */
    private String room;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private java.util.Date dateAssigned;

    /** nullable persistent field */
    private String telephone;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private String lastChgBy;

    /** full constructor */
    public AssetLocation(com.tsa.puridiom.entity.AssetLocationPK comp_Id, java.lang.String locationType, java.lang.String division, java.lang.String department, java.lang.String facility, java.lang.String building, java.lang.String room, java.lang.String userId, java.util.Date dateAssigned, java.lang.String telephone, java.lang.String lastChgBy, java.util.Date dateChanged) {
    	this.comp_id = comp_Id;
        this.locationType = locationType;
        this.division = division;
        this.department = department;
        this.facility = facility;
        this.building = building;
        this.room = room;
        this.userId = userId;
        this.dateAssigned = dateAssigned;
        this.telephone = telephone;
        this.dateChanged = dateChanged;
        this.lastChgBy = lastChgBy;
    }

    /** default constructor */
    public AssetLocation() {
    }

    public java.lang.String getLocationType() {
		return (java.lang.String)HiltonUtility.ckNull(this.locationType).trim();
    }

    public void setLocationType(java.lang.String locationType) {
		if (!HiltonUtility.isEmpty(locationType) && locationType.length() > 2) {
			locationType = locationType.substring(0, 2);
		}
		this.locationType = locationType;
    }

    public java.lang.String getDivision() {
		return (java.lang.String)HiltonUtility.ckNull(this.division).trim();
    }

    public void setDivision(java.lang.String division) {
		if (!HiltonUtility.isEmpty(division) && division.length() > 20) {
			division = division.substring(0, 20);
		}
		this.division = division;
    }

    public java.lang.String getDepartment() {
		return (java.lang.String)HiltonUtility.ckNull(this.department).trim();
    }

    public void setDepartment(java.lang.String department) {
		if (!HiltonUtility.isEmpty(department) && department.length() > 20) {
			department = department.substring(0, 20);
		}
		this.department = department;
    }

    public java.lang.String getFacility() {
		return (java.lang.String)HiltonUtility.ckNull(this.facility).trim();
    }

    public void setFacility(java.lang.String facility) {
		if (!HiltonUtility.isEmpty(facility) && facility.length() > 20) {
			facility = facility.substring(0, 20);
		}
		this.facility = facility;
    }

    public java.lang.String getBuilding() {
		return (java.lang.String)HiltonUtility.ckNull(this.building).trim();
    }

    public void setBuilding(java.lang.String building) {
		if (!HiltonUtility.isEmpty(building) && building.length() > 20) {
			building = building.substring(0, 20);
		}
		this.building = building;
    }

    public java.lang.String getRoom() {
		return (java.lang.String)HiltonUtility.ckNull(this.room).trim();
    }

    public void setRoom(java.lang.String room) {
		if (!HiltonUtility.isEmpty(room) && room.length() > 20) {
			room = room.substring(0, 20);
		}
		this.room = room;
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

    public java.util.Date getDateAssigned() {
        return this.dateAssigned;
    }

    public void setDateAssigned(java.util.Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public java.lang.String getTelephone() {
		return (java.lang.String)HiltonUtility.ckNull(this.telephone).trim();
    }

    public void setTelephone(java.lang.String telephone) {
		if (!HiltonUtility.isEmpty(telephone) && telephone.length() > 30) {
			telephone = telephone.substring(0, 30);
		}
		this.telephone = telephone;
    }

    public java.util.Date getDateChanged() {
        return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
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
        if ( !(other instanceof AssetLocation) ) return false;
        AssetLocation castOther = (AssetLocation) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public com.tsa.puridiom.entity.AssetLocationPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(com.tsa.puridiom.entity.AssetLocationPK comp_id) {
		this.comp_id = comp_id;
	}

}
