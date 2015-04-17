package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvReturn implements Serializable {

    /** identifier field */
	private java.math.BigDecimal icInvReturn;

	/** nullable persistent field */
    private String requisitionNumber;

    /** nullable persistent field */
    private String disbNumber;

    /** nullable persistent field */
    private java.math.BigDecimal lineNo;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbLine;

    /** nullable persistent field */
    private java.math.BigDecimal icBin;

    /** nullable persistent field */
    private String recBy;

    /** nullable persistent field */
    private java.util.Date recDate;

    /** nullable persistent field */
    private java.math.BigDecimal recAmount;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.math.BigDecimal duomQty;

    /** full constructor */
    public InvReturn(java.math.BigDecimal icInvReturn, java.lang.String requisitionNumber, java.lang.String disbNumber, java.math.BigDecimal lineNo, java.lang.String itemNumber, java.math.BigDecimal icReqHeader, java.math.BigDecimal icReqLine, java.math.BigDecimal icDsbHeader, java.math.BigDecimal icDsbLine, java.math.BigDecimal icBin, java.lang.String recBy, java.util.Date recDate, java.math.BigDecimal recAmount, java.lang.String owner, java.math.BigDecimal duomQty) {
        this.icInvReturn = icInvReturn;
        this.requisitionNumber = requisitionNumber;
        this.disbNumber = disbNumber;
        this.lineNo = lineNo;
        this.itemNumber = itemNumber;
        this.icReqHeader = icReqHeader;
        this.icReqLine = icReqLine;
        this.icDsbHeader = icDsbHeader;
        this.icDsbLine = icDsbLine;
        this.icBin = icBin;
        this.recBy = recBy;
        this.recDate = recDate;
        this.recAmount = recAmount;
        this.owner = owner;
        this.duomQty = duomQty ;
    }

    /** default constructor */
    public InvReturn() {
    }

    /** minimal constructor */
    public InvReturn(java.math.BigDecimal icInvReturn) {
        this.icInvReturn = icInvReturn;
    }

    public java.math.BigDecimal getIcInvReturn() {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.icInvReturn);
    }

    public void setIcInvReturn(java.math.BigDecimal icInvReturn) {
        this.icInvReturn = icInvReturn;
    }

    public java.lang.String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber);
    }

    public void setRequisitionNumber(java.lang.String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public java.lang.String getDisbNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.disbNumber);
    }

    public void setDisbNumber(java.lang.String disbNumber) {
        this.disbNumber = disbNumber;
    }

    public java.math.BigDecimal getLineNo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNo);
    }

    public void setLineNo(java.math.BigDecimal lineNo) {
        this.lineNo = lineNo;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
    }

    public java.math.BigDecimal getIcDsbHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbHeader);
    }

    public void setIcDsbHeader(java.math.BigDecimal icDsbHeader) {
        this.icDsbHeader = icDsbHeader;
    }

    public java.math.BigDecimal getIcDsbLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbLine);
    }

    public void setIcDsbLine(java.math.BigDecimal icDsbLine) {
        this.icDsbLine = icDsbLine;
    }

    public java.math.BigDecimal getIcBin() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icBin);
    }

    public void setIcBin(java.math.BigDecimal icBin) {
        this.icBin = icBin;
    }

    public java.lang.String getRecBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.recBy).trim();
    }

    public void setRecBy(java.lang.String recBy) {
		if (!HiltonUtility.isEmpty(recBy) && recBy.length() > 15) {
			recBy = recBy.substring(0, 15);
		}
		this.recBy = recBy;
    }

    public java.util.Date getRecDate() {
		return this.recDate;
//		return HiltonUtility.ckNull(this.recDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.recDate);
    }

    public void setRecDate(java.util.Date recDate) {
        this.recDate = recDate;
    }

    public java.math.BigDecimal getRecAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.recAmount);
    }

    public void setRecAmount(java.math.BigDecimal recAmount) {
        this.recAmount = recAmount;
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

    public java.math.BigDecimal getDuomQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(duomQty);
	}

	public void setDuomQty(java.math.BigDecimal duomQty) {
		this.duomQty = duomQty;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("icInvReturn", getIcInvReturn())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvReturn) ) return false;
        InvReturn castOther = (InvReturn) other;
        return new EqualsBuilder()
            .append(this.getIcInvReturn(), castOther.getIcInvReturn())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcInvReturn())
            .toHashCode();
    }

}
