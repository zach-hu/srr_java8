package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvItemClass implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** nullable persistent field */
    private java.math.BigDecimal agent;

    /** nullable persistent field */
    private java.math.BigDecimal dsrtMgr;

    /** nullable persistent field */
    private java.math.BigDecimal bcos;

    /** nullable persistent field */
    private java.math.BigDecimal csc;

    /** nullable persistent field */
    private java.math.BigDecimal blos;

    /** nullable persistent field */
    private java.math.BigDecimal stOff;

    /** nullable persistent field */
    private java.math.BigDecimal srvCenter;

    /** nullable persistent field */
    private java.math.BigDecimal homeOff;

    /** nullable persistent field */
    private java.math.BigDecimal udf1Code;

    /** nullable persistent field */
    private java.math.BigDecimal udf2Code;

    /** full constructor */
    public InvItemClass(java.lang.String itemNumber, java.math.BigDecimal agent, java.math.BigDecimal dsrtMgr, java.math.BigDecimal bcos, java.math.BigDecimal csc, java.math.BigDecimal blos, java.math.BigDecimal stOff, java.math.BigDecimal srvCenter, java.math.BigDecimal homeOff, java.math.BigDecimal udf1Code, java.math.BigDecimal udf2Code) {
        this.itemNumber = itemNumber;
        this.agent = agent;
        this.dsrtMgr = dsrtMgr;
        this.bcos = bcos;
        this.csc = csc;
        this.blos = blos;
        this.stOff = stOff;
        this.srvCenter = srvCenter;
        this.homeOff = homeOff;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
    }

    /** default constructor */
    public InvItemClass() {
    }

    /** minimal constructor */
    public InvItemClass(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
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

    public java.math.BigDecimal getAgent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.agent);
    }

    public void setAgent(java.math.BigDecimal agent) {
        this.agent = agent;
    }

    public java.math.BigDecimal getDsrtMgr() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dsrtMgr);
    }

    public void setDsrtMgr(java.math.BigDecimal dsrtMgr) {
        this.dsrtMgr = dsrtMgr;
    }

    public java.math.BigDecimal getBcos() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bcos);
    }

    public void setBcos(java.math.BigDecimal bcos) {
        this.bcos = bcos;
    }

    public java.math.BigDecimal getCsc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.csc);
    }

    public void setCsc(java.math.BigDecimal csc) {
        this.csc = csc;
    }

    public java.math.BigDecimal getBlos() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.blos);
    }

    public void setBlos(java.math.BigDecimal blos) {
        this.blos = blos;
    }

    public java.math.BigDecimal getStOff() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.stOff);
    }

    public void setStOff(java.math.BigDecimal stOff) {
        this.stOff = stOff;
    }

    public java.math.BigDecimal getSrvCenter() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.srvCenter);
    }

    public void setSrvCenter(java.math.BigDecimal srvCenter) {
        this.srvCenter = srvCenter;
    }

    public java.math.BigDecimal getHomeOff() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.homeOff);
    }

    public void setHomeOff(java.math.BigDecimal homeOff) {
        this.homeOff = homeOff;
    }

    public java.math.BigDecimal getUdf1Code() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.udf1Code);
    }

    public void setUdf1Code(java.math.BigDecimal udf1Code) {
        this.udf1Code = udf1Code;
    }

    public java.math.BigDecimal getUdf2Code() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.udf2Code);
    }

    public void setUdf2Code(java.math.BigDecimal udf2Code) {
        this.udf2Code = udf2Code;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvItemClass) ) return false;
        InvItemClass castOther = (InvItemClass) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .toHashCode();
    }

}
