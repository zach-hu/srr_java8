package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AutoGen implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AutoGenPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal nextNumber;

    /** nullable persistent field */
    private String activeYear;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String char1;

    /** nullable persistent field */
    private String char2;

    /** nullable persistent field */
    private String char3;

    /** nullable persistent field */
    private String char4;

    /** nullable persistent field */
    private java.math.BigDecimal rangeMax;

    /** full constructor */
    public AutoGen(com.tsa.puridiom.entity.AutoGenPK comp_id, java.math.BigDecimal nextNumber, java.lang.String activeYear, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String char1, java.lang.String char2, java.lang.String char3, java.lang.String char4, java.math.BigDecimal rangeMax) {
        this.comp_id = comp_id;
        this.nextNumber = nextNumber;
        this.activeYear = activeYear;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.char1 = char1;
        this.char2 = char2;
        this.char3 = char3;
        this.char4 = char4;
        this.rangeMax = rangeMax;
    }

    /** default constructor */
    public AutoGen() {
    }

    /** minimal constructor */
    public AutoGen(com.tsa.puridiom.entity.AutoGenPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AutoGenPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AutoGenPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getNextNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.nextNumber);
    }

    public void setNextNumber(java.math.BigDecimal nextNumber) {
        this.nextNumber = nextNumber;
    }

    public java.lang.String getActiveYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.activeYear).trim();
    }

    public void setActiveYear(java.lang.String activeYear) {
		if (!HiltonUtility.isEmpty(activeYear) && activeYear.length() > 1) {
			activeYear = activeYear.substring(0, 1);
		}
		this.activeYear = activeYear;
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

    public java.lang.String getChar1() {
		return (java.lang.String)HiltonUtility.ckNull(this.char1).trim();
    }

    public void setChar1(java.lang.String char1) {
		if (!HiltonUtility.isEmpty(char1) && char1.length() > 255) {
			char1 = char1.substring(0, 255);
		}
		this.char1 = char1;
    }

    public java.lang.String getChar2() {
		return (java.lang.String)HiltonUtility.ckNull(this.char2).trim();
    }

    public void setChar2(java.lang.String char2) {
		if (!HiltonUtility.isEmpty(char2) && char2.length() > 255) {
			char2 = char2.substring(0, 255);
		}
		this.char2 = char2;
    }

    public java.lang.String getChar3() {
		return (java.lang.String)HiltonUtility.ckNull(this.char3).trim();
    }

    public void setChar3(java.lang.String char3) {
		if (!HiltonUtility.isEmpty(char3) && char3.length() > 255) {
			char3 = char3.substring(0, 255);
		}
		this.char3 = char3;
    }

    public java.lang.String getChar4() {
		return (java.lang.String)HiltonUtility.ckNull(this.char4).trim();
    }

    public void setChar4(java.lang.String char4) {
		if (!HiltonUtility.isEmpty(char4) && char4.length() > 255) {
			char4 = char4.substring(0, 255);
		}
		this.char4 = char4;
    }

    public java.math.BigDecimal getRangeMax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.rangeMax);
    }

    public void setRangeMax(java.math.BigDecimal rangeMax) {
        this.rangeMax = rangeMax;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AutoGen) ) return false;
        AutoGen castOther = (AutoGen) other;
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
