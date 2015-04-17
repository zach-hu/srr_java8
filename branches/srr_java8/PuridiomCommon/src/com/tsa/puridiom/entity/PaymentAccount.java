package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PaymentAccount implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPayment;

    /** nullable persistent field */
    private String invoiceNumber;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

    /** nullable persistent field */
    private java.util.Date invoiceDate;

    /** nullable persistent field */
    private String tsrYear;

    /** nullable persistent field */
    private java.math.BigDecimal tsrMonth;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String cacCode;

    /** nullable persistent field */
    private String accountCode;

    /** nullable persistent field */
    private String costCenter;

    /** nullable persistent field */
    private String utilCode;

    /** nullable persistent field */
    private java.math.BigDecimal allocAmount;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceAmount;

    /** nullable persistent field */
    private String checkNumber;

    /** nullable persistent field */
    private java.math.BigDecimal checkAmount;

    /** nullable persistent field */
    private java.util.Date checkDate;

    /** nullable persistent field */
    private java.util.Date cancelDate;

    /** nullable persistent field */
    private String voucherNumber;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private String tsrFlag;

    /** nullable persistent field */
    private String transDate;

    /** nullable persistent field */
    private String voucherDesc;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public PaymentAccount(java.lang.String invoiceNumber, java.lang.String poNumber, java.math.BigDecimal releaseNumber, java.util.Date invoiceDate, java.lang.String tsrYear, java.math.BigDecimal tsrMonth, java.lang.String vendorId, java.lang.String cacCode, java.lang.String accountCode, java.lang.String costCenter, java.lang.String utilCode, java.math.BigDecimal allocAmount, java.math.BigDecimal invoiceAmount, java.lang.String checkNumber, java.math.BigDecimal checkAmount, java.util.Date checkDate, java.util.Date cancelDate, java.lang.String voucherNumber, java.math.BigDecimal icPoHeader, java.lang.String tsrFlag, java.lang.String transDate, java.lang.String voucherDesc, java.util.Date dateEntered, java.lang.String owner) {
        this.invoiceNumber = invoiceNumber;
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.invoiceDate = invoiceDate;
        this.tsrYear = tsrYear;
        this.tsrMonth = tsrMonth;
        this.vendorId = vendorId;
        this.cacCode = cacCode;
        this.accountCode = accountCode;
        this.costCenter = costCenter;
        this.utilCode = utilCode;
        this.allocAmount = allocAmount;
        this.invoiceAmount = invoiceAmount;
        this.checkNumber = checkNumber;
        this.checkAmount = checkAmount;
        this.checkDate = checkDate;
        this.cancelDate = cancelDate;
        this.voucherNumber = voucherNumber;
        this.icPoHeader = icPoHeader;
        this.tsrFlag = tsrFlag;
        this.transDate = transDate;
        this.voucherDesc = voucherDesc;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public PaymentAccount() {
    }

    public java.math.BigDecimal getIcPayment() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPayment);
    }

    public void setIcPayment(java.math.BigDecimal icPayment) {
        this.icPayment = icPayment;
    }

    public java.lang.String getInvoiceNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceNumber).trim();
    }

    public void setInvoiceNumber(java.lang.String invoiceNumber) {
		if (!HiltonUtility.isEmpty(invoiceNumber) && invoiceNumber.length() > 20) {
			invoiceNumber = invoiceNumber.substring(0, 20);
		}
		this.invoiceNumber = invoiceNumber;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 20) {
			poNumber = poNumber.substring(0, 20);
		}
		this.poNumber = poNumber;
    }

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public java.util.Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(java.util.Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public java.lang.String getTsrYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.tsrYear).trim();
    }

    public void setTsrYear(java.lang.String tsrYear) {
		if (!HiltonUtility.isEmpty(tsrYear) && tsrYear.length() > 4) {
			tsrYear = tsrYear.substring(0, 4);
		}
		this.tsrYear = tsrYear;
    }

    public java.math.BigDecimal getTsrMonth() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tsrMonth);
    }

    public void setTsrMonth(java.math.BigDecimal tsrMonth) {
        this.tsrMonth = tsrMonth;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }

    public java.lang.String getCacCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.cacCode).trim();
    }

    public void setCacCode(java.lang.String cacCode) {
		if (!HiltonUtility.isEmpty(cacCode) && cacCode.length() > 15) {
			cacCode = cacCode.substring(0, 15);
		}
		this.cacCode = cacCode;
    }

    public java.lang.String getAccountCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.accountCode).trim();
    }

    public void setAccountCode(java.lang.String accountCode) {
		if (!HiltonUtility.isEmpty(accountCode) && accountCode.length() > 15) {
			accountCode = accountCode.substring(0, 15);
		}
		this.accountCode = accountCode;
    }

    public java.lang.String getCostCenter() {
		return (java.lang.String)HiltonUtility.ckNull(this.costCenter).trim();
    }

    public void setCostCenter(java.lang.String costCenter) {
		if (!HiltonUtility.isEmpty(costCenter) && costCenter.length() > 15) {
			costCenter = costCenter.substring(0, 15);
		}
		this.costCenter = costCenter;
    }

    public java.lang.String getUtilCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.utilCode).trim();
    }

    public void setUtilCode(java.lang.String utilCode) {
		if (!HiltonUtility.isEmpty(utilCode) && utilCode.length() > 15) {
			utilCode = utilCode.substring(0, 15);
		}
		this.utilCode = utilCode;
    }

    public java.math.BigDecimal getAllocAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocAmount);
    }

    public void setAllocAmount(java.math.BigDecimal allocAmount) {
        this.allocAmount = allocAmount;
    }

    public java.math.BigDecimal getInvoiceAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceAmount);
    }

    public void setInvoiceAmount(java.math.BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public java.lang.String getCheckNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.checkNumber).trim();
    }

    public void setCheckNumber(java.lang.String checkNumber) {
		if (!HiltonUtility.isEmpty(checkNumber) && checkNumber.length() > 15) {
			checkNumber = checkNumber.substring(0, 15);
		}
		this.checkNumber = checkNumber;
    }

    public java.math.BigDecimal getCheckAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.checkAmount);
    }

    public void setCheckAmount(java.math.BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public java.util.Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(java.util.Date checkDate) {
        this.checkDate = checkDate;
    }

    public java.util.Date getCancelDate() {
        return this.cancelDate;
    }

    public void setCancelDate(java.util.Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public java.lang.String getVoucherNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.voucherNumber).trim();
    }

    public void setVoucherNumber(java.lang.String voucherNumber) {
		if (!HiltonUtility.isEmpty(voucherNumber) && voucherNumber.length() > 20) {
			voucherNumber = voucherNumber.substring(0, 20);
		}
		this.voucherNumber = voucherNumber;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.lang.String getTsrFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.tsrFlag).trim();
    }

    public void setTsrFlag(java.lang.String tsrFlag) {
		if (!HiltonUtility.isEmpty(tsrFlag) && tsrFlag.length() > 2) {
			tsrFlag = tsrFlag.substring(0, 2);
		}
		this.tsrFlag = tsrFlag;
    }

    public java.lang.String getTransDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.transDate).trim();
    }

    public void setTransDate(java.lang.String transDate) {
		if (!HiltonUtility.isEmpty(transDate) && transDate.length() > 20) {
			transDate = transDate.substring(0, 20);
		}
		this.transDate = transDate;
    }

    public java.lang.String getVoucherDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.voucherDesc).trim();
    }

    public void setVoucherDesc(java.lang.String voucherDesc) {
		if (!HiltonUtility.isEmpty(voucherDesc) && voucherDesc.length() > 60) {
			voucherDesc = voucherDesc.substring(0, 60);
		}
		this.voucherDesc = voucherDesc;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPayment", getIcPayment())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PaymentAccount) ) return false;
        PaymentAccount castOther = (PaymentAccount) other;
        return new EqualsBuilder()
            .append(this.getIcPayment(), castOther.getIcPayment())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPayment())
            .toHashCode();
    }

}
