package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Location implements Serializable {

    /** persistent field */
    private String locationCode;

    /** persistent field */
    private String locationTypeCode;

    /** persistent field */
    private String shortDescription;

    /** persistent field */
    private String description;
    
    /** full constructor */
    public Location(java.lang.String locationCode, java.lang.String locationTypeCode, java.lang.String shortDescription, java.lang.String description) {
        this.locationCode = locationCode;
        this.locationTypeCode = locationTypeCode;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    /** default constructor */
    public Location() {
    }

    public java.lang.String getLocationCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.locationCode).trim();
    }

    public void setLocationCode(java.lang.String locationCode) {
		if (!HiltonUtility.isEmpty(locationCode) && locationCode.length() > 15) {
		    locationCode = locationCode.substring(0, 15);
		}
		this.locationCode = locationCode;
    }

    public java.lang.String getLocationTypeCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.locationTypeCode).trim();
    }

    public void setLocationTypeCode(java.lang.String locationCode) {
		if (!HiltonUtility.isEmpty(locationCode) && locationCode.length() > 5) {
		    locationCode = locationCode.substring(0, 5);
		}
		this.locationCode = locationCode;
    }

    public java.lang.String getShortDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.shortDescription).trim();
    }

    public void setShortDescription(java.lang.String shortDescription) {
		if (!HiltonUtility.isEmpty(shortDescription) && shortDescription.length() > 20) {
			shortDescription = shortDescription.substring(0, 20);
		}
		this.shortDescription = shortDescription;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 50) {
			description = description.substring(0, 50);
		}
		this.description = description;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("locationCode", getLocationCode())
        .toString();
    }

}
