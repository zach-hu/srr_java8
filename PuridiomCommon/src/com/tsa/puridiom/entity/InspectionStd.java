package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionStd implements Serializable {

    /** identifier field */
    private String standardCode;

    /** identifier field */
    private String inspectType;

    /** identifier field */
    private String inspectCode;

    /** identifier field */
    private String critNo;

    private java.math.BigDecimal icInspStd ;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String critText;

    /** nullable persistent field */
    private String defaultFlag;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String lastChangeBy;

    private String cgdNo ;

    private java.math.BigDecimal cgdRev ;

    /** Set externally */
    private String critDesc;

    /** full constructor */
    public InspectionStd(java.math.BigDecimal icInspStd, java.lang.String standardCode, java.lang.String inspectType, java.lang.String inspectCode, java.lang.String critNo, java.lang.String description, java.lang.String defaultFlag, java.util.Date dateEntered, java.lang.String status, java.lang.String owner, java.util.Date lastChange, java.lang.String lastChangeBy) {
        this.icInspStd = icInspStd ;
        this.standardCode = standardCode;
        this.inspectType = inspectType;
        this.inspectCode = inspectCode;
        this.critNo = critNo;
        this.description = description;
        this.defaultFlag = defaultFlag;
        this.dateEntered = dateEntered;
        this.status = status;
        this.owner = owner;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
    }

    /** default constructor */
    public InspectionStd() {
    }

    /** minimal constructor */
    public InspectionStd(java.math.BigDecimal icInspStd) {
        this.icInspStd = icInspStd ;
    }

    public java.math.BigDecimal getIcInspStd() {
		return icInspStd;
	}

	public void setIcInspStd(java.math.BigDecimal icInspStd) {
		this.icInspStd = icInspStd;
	}

    public java.lang.String getStandardCode() {
        return this.standardCode;
    }

    public void setStandardCode(java.lang.String standardCode) {
        this.standardCode = standardCode;
    }

    public java.lang.String getInspectType() {
        return this.inspectType;
    }

    public void setInspectType(java.lang.String inspectType) {
        this.inspectType = inspectType;
    }

    public java.lang.String getInspectCode() {
        return this.inspectCode;
    }

    public void setInspectCode(java.lang.String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public java.lang.String getCritNo() {
        return this.critNo;
    }

    public void setCritNo(java.lang.String critNo) {
        this.critNo = critNo;
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

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.util.Date getLastChange() {
        return this.lastChange;
    }

    public void setLastChange(java.util.Date lastChange) {
        this.lastChange = lastChange;
    }

    public java.lang.String getLastChangeBy() {
        return this.lastChangeBy;
    }

    public void setLastChangeBy(java.lang.String lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
    }

    public String getCritDesc(){
    	return critDesc;
    }

    public void setCritDesc(String critDesc){
    	this.critDesc = critDesc;
    }

    public String getCgdNo() {
		return cgdNo;
	}

	public void setCgdNo(String cgdNo) {
		this.cgdNo = cgdNo;
	}

	public java.math.BigDecimal getCgdRev() {
		return cgdRev;
	}

	public void setCgdRev(java.math.BigDecimal cgdRev) {
		this.cgdRev = cgdRev;
	}

	public String getCritText() {
		return critText;
	}

	public void setCritText(String critText) {
		this.critText = critText;
	}


	   public String toString() {
	        return new ToStringBuilder(this)
            .append("icInspStd",this.getIcInspStd())
	            .toString();
	    }

	    public boolean equals(Object other) {
	        if ( !(other instanceof InspectionStd) ) return false;
	        InspectionStd castOther = (InspectionStd) other;
	        return new EqualsBuilder()
	            .append(this.icInspStd, castOther.getIcInspStd())
	            .isEquals();
	    }

	    public int hashCode() {
	        return new HashCodeBuilder()
	            .append(this.getIcInspStd())
	            .toHashCode();
	    }


}
