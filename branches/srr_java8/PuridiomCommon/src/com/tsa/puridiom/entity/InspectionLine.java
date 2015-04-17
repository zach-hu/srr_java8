package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionLine implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icInspLine;

    /** persistent field */
    private java.math.BigDecimal icInspNo;

    /** nullable persistent field */
    private String inspectCode;

    /** nullable persistent field */
    private java.math.BigDecimal seqNo;

    /** nullable persistent field */
    private String critNo;

    /** nullable persistent field */
    private String standardCode;

    /** nullable persistent field */
    private String lockFlag;

    /** nullable persistent field */
    private String udf01;

    /** nullable persistent field */
    private String udf02;

    /** nullable persistent field */
    private String udf03;

    /** nullable persistent field */
    private String udf04;

    /** nullable persistent field */
    private String udf05;

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

    private String critDescription ;

    // inspCritDesc / inspCodeDesc set externally
     private String inspCritDesc ;

	private String inspCodeDesc ;

    /** full constructor */
    public InspectionLine(java.math.BigDecimal icInspLine, java.math.BigDecimal icInspNo, java.lang.String inspectCode, java.math.BigDecimal seqNo, java.lang.String critNo, java.lang.String standardCode, java.lang.String lockFlag, java.lang.String udf01, java.lang.String udf02, java.lang.String udf03, java.lang.String udf04, java.lang.String udf05, java.util.Date dateEntered, java.lang.String status, java.lang.String owner, java.util.Date lastChange, java.lang.String lastChangeBy, java.lang.String critDescription) {
        this.icInspLine = icInspLine;
        this.icInspNo = icInspNo;
        this.inspectCode = inspectCode;
        this.seqNo = seqNo;
        this.critNo = critNo;
        this.standardCode = standardCode;
        this.lockFlag = lockFlag;
        this.udf01 = udf01;
        this.udf02 = udf02;
        this.udf03 = udf03;
        this.udf04 = udf04;
        this.udf05 = udf05;
        this.dateEntered = dateEntered;
        this.status = status;
        this.owner = owner;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
        this.critDescription = critDescription ;
    }

    /** default constructor */
    public InspectionLine() {
    }

    /** minimal constructor */
    public InspectionLine(java.math.BigDecimal icInspLine, java.math.BigDecimal icInspNo) {
        this.icInspLine = icInspLine;
        this.icInspNo = icInspNo;
    }

    public java.math.BigDecimal getIcInspLine() {
        return this.icInspLine;
    }

    public void setIcInspLine(java.math.BigDecimal icInspLine) {
        this.icInspLine = icInspLine;
    }

    public java.math.BigDecimal getIcInspNo() {
        return this.icInspNo;
    }

    public void setIcInspNo(java.math.BigDecimal icInspNo) {
        this.icInspNo = icInspNo;
    }

    public java.lang.String getInspectCode() {
        return this.inspectCode;
    }

    public void setInspectCode(java.lang.String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public java.math.BigDecimal getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(java.math.BigDecimal seqNo) {
        this.seqNo = seqNo;
    }

    public java.lang.String getCritNo() {
        return this.critNo;
    }

    public void setCritNo(java.lang.String critNo) {
        this.critNo = critNo;
    }

    public java.lang.String getStandardCode() {
        return this.standardCode;
    }

    public void setStandardCode(java.lang.String standardCode) {
        this.standardCode = standardCode;
    }

    public java.lang.String getLockFlag() {
        return this.lockFlag;
    }

    public void setLockFlag(java.lang.String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public java.lang.String getUdf01() {
        return this.udf01;
    }

    public void setUdf01(java.lang.String udf01) {
        this.udf01 = udf01;
    }

    public java.lang.String getUdf02() {
        return this.udf02;
    }

    public void setUdf02(java.lang.String udf02) {
        this.udf02 = udf02;
    }

    public java.lang.String getUdf03() {
        return this.udf03;
    }

    public void setUdf03(java.lang.String udf03) {
        this.udf03 = udf03;
    }

    public java.lang.String getUdf04() {
        return this.udf04;
    }

    public void setUdf04(java.lang.String udf04) {
        this.udf04 = udf04;
    }

    public java.lang.String getUdf05() {
        return this.udf05;
    }

    public void setUdf05(java.lang.String udf05) {
        this.udf05 = udf05;
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

    public String getInspCritDesc() {
		return inspCritDesc;
	}

	public void setInspCritDesc(String inspCritDesc) {
		this.inspCritDesc = inspCritDesc;
	}

	public String getInspCodeDesc() {
		return inspCodeDesc;
	}

	public void setInspCodeDesc(String inspCodeDesc) {
		this.inspCodeDesc = inspCodeDesc;
	}

    public String getCritDescription() {
    	if (this.critDescription == null) this.critDescription = "" ;
		return this.critDescription;
	}

	public void setCritDescription(String critDescription) {
		if (critDescription == null) critDescription = "" ;
		this.critDescription = critDescription;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("icInspLine", getIcInspLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionLine) ) return false;
        InspectionLine castOther = (InspectionLine) other;
        return new EqualsBuilder()
            .append(this.getIcInspLine(), castOther.getIcInspLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcInspLine())
            .toHashCode();
    }

}
