package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PoPaymentDetailView implements Serializable {

    /** identifier field */
    private String poNumber;

    /** nullable persistent field */
    private BigDecimal releaseNumber;

    /** nullable persistent field */
    private BigDecimal revisionNumber;

    /** nullable persistent field */
    private Date poDate;

    /** nullable persistent field */
    private Date requiredDate;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String lineStatus;

    /** nullable persistent field */
    private BigDecimal total;

    /** nullable persistent field */
    private BigDecimal blanketLimit;

    /** nullable persistent field */
    private Date effectiveDate;

    /** nullable persistent field */
    private Date expirationDate;

    /** nullable persistent field */
    private BigDecimal lineNumber;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private BigDecimal quantity;

    /** nullable persistent field */
    private BigDecimal unitPrice;

    /** nullable persistent field */
    private BigDecimal lineTotal;

    /** nullable persistent field */
    private String fld1;

    /** nullable persistent field */
    private String fld2;

    /** nullable persistent field */
    private String fld3;

    /** nullable persistent field */
    private String fld4;

    /** nullable persistent field */
    private BigDecimal allocPercent;

    /** nullable persistent field */
    private String requisitionNumber;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private BigDecimal allocAmount;

    /** nullable persistent field */
    private String invoiceNumber;

    /** nullable persistent field */
    private Date invoiceDate;

    /** nullable persistent field */
    private BigDecimal invoiceAmount;

    /** nullable persistent field */
    private String voucherNumber;

    /** nullable persistent field */
    private String checkNumber;

    /** nullable persistent field */
    private BigDecimal checkAmount;

    /** nullable persistent field */
    private Date checkDate;

    /** nullable persistent field */
    private Date cancelDate;

    /** nullable persistent field */
    private String accountCode;

    /** nullable persistent field */
    private String costCenter;

    /** nullable persistent field */
    private String cacCode;

    /** nullable persistent field */
    private String utilCode;

    /** nullable persistent field */
    private BigDecimal paymentAmount;

    /** nullable persistent field */
    private int tsrMonth;

    /** nullable persistent field */
    private String tsrYear;

    /** nullable persistent field */
    private BigDecimal icPoHeader;

    /** nullable persistent field */
    private String tsrFlag;

    /** nullable persistent field */
    private String poType;

    /** nullable persistent field */
    private BigDecimal releaseTotal;

    private String internalComments;

    private String budgeted;

    private String contractNo;

    /** full constructor */
    public PoPaymentDetailView(String poNumber, BigDecimal releaseNumber, BigDecimal revisionNumber, Date poDate, Date requiredDate, String vendorId, String vendorName, String status, String lineStatus, BigDecimal total, BigDecimal blanketLimit, Date effectiveDate, Date expirationDate, BigDecimal lineNumber, String itemNumber, String description, String umCode, BigDecimal quantity, BigDecimal unitPrice, BigDecimal lineTotal, String fld1, String fld2, String fld3, String fld4, BigDecimal allocPercent, String requisitionNumber, String requisitionerCode, String udf1Code, BigDecimal allocAmount, String invoiceNumber, Date invoiceDate, BigDecimal invoiceAmount, String voucherNumber, String checkNumber, BigDecimal checkAmount, Date checkDate, Date cancelDate, String accountCode, String costCenter, String cacCode, String utilCode, BigDecimal paymentAmount, int tsrMonth, String tsrYear, BigDecimal icPoHeader, String tsrFlag, String poType, BigDecimal releaseTotal, String internalComments, String budgeted, String contractNo) {
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.revisionNumber = revisionNumber;
        this.poDate = poDate;
        this.requiredDate = requiredDate;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.status = status;
        this.lineStatus = lineStatus;
        this.total = total;
        this.blanketLimit = blanketLimit;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.lineNumber = lineNumber;
        this.itemNumber = itemNumber;
        this.description = description;
        this.umCode = umCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.fld1 = fld1;
        this.fld2 = fld2;
        this.fld3 = fld3;
        this.fld4 = fld4;
        this.allocPercent = allocPercent;
        this.requisitionNumber = requisitionNumber;
        this.requisitionerCode = requisitionerCode;
        this.udf1Code = udf1Code;
        this.allocAmount = allocAmount;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceAmount = invoiceAmount;
        this.voucherNumber = voucherNumber;
        this.checkNumber = checkNumber;
        this.checkAmount = checkAmount;
        this.checkDate = checkDate;
        this.cancelDate = cancelDate;
        this.accountCode = accountCode;
        this.costCenter = costCenter;
        this.cacCode = cacCode;
        this.utilCode = utilCode;
        this.paymentAmount = paymentAmount;
        this.tsrMonth = tsrMonth;
        this.tsrYear = tsrYear;
        this.icPoHeader = icPoHeader;
        this.tsrFlag = tsrFlag;
        this.poType = poType;
        this.releaseTotal = releaseTotal;
        this.internalComments = internalComments;
        this.budgeted = budgeted;
        this.contractNo = contractNo;
    }

    /** default constructor */
    public PoPaymentDetailView() {
    }

    /** minimal constructor */
    public PoPaymentDetailView(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getReleaseNumber() {
        return this.releaseNumber;
    }

    public void setReleaseNumber(BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public BigDecimal getRevisionNumber() {
        return this.revisionNumber;
    }

    public void setRevisionNumber(BigDecimal revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Date getPoDate() {
        return this.poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public Date getRequiredDate() {
        return this.requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLineStatus() {
        return this.lineStatus;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBlanketLimit() {
        return this.blanketLimit;
    }

    public void setBlanketLimit(BigDecimal blanketLimit) {
        this.blanketLimit = blanketLimit;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUmCode() {
        return this.umCode;
    }

    public void setUmCode(String umCode) {
        this.umCode = umCode;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return this.lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getFld1() {
        return this.fld1;
    }

    public void setFld1(String fld1) {
        this.fld1 = fld1;
    }

    public String getFld2() {
        return this.fld2;
    }

    public void setFld2(String fld2) {
        this.fld2 = fld2;
    }

    public String getFld3() {
        return this.fld3;
    }

    public void setFld3(String fld3) {
        this.fld3 = fld3;
    }

    public String getFld4() {
        return this.fld4;
    }

    public void setFld4(String fld4) {
        this.fld4 = fld4;
    }

    public BigDecimal getAllocPercent() {
        return this.allocPercent;
    }

    public void setAllocPercent(BigDecimal allocPercent) {
        this.allocPercent = allocPercent;
    }

    public String getRequisitionNumber() {
        return this.requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getRequisitionerCode() {
        return this.requisitionerCode;
    }

    public void setRequisitionerCode(String requisitionerCode) {
        this.requisitionerCode = requisitionerCode;
    }

    public String getUdf1Code() {
        return this.udf1Code;
    }

    public void setUdf1Code(String udf1Code) {
        this.udf1Code = udf1Code;
    }

    public BigDecimal getAllocAmount() {
        return this.allocAmount;
    }

    public void setAllocAmount(BigDecimal allocAmount) {
        this.allocAmount = allocAmount;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceAmount() {
        return this.invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getVoucherNumber() {
        return this.voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getCheckNumber() {
        return this.checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public BigDecimal getCheckAmount() {
        return this.checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCancelDate() {
        return this.cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getAccountCode() {
        return this.accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCostCenter() {
        return this.costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCacCode() {
        return this.cacCode;
    }

    public void setCacCode(String cacCode) {
        this.cacCode = cacCode;
    }

    public String getUtilCode() {
        return this.utilCode;
    }

    public void setUtilCode(String utilCode) {
        this.utilCode = utilCode;
    }

    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymetAmount) {
        this.paymentAmount = paymetAmount;
    }

    public int getTsrMonth() {
        return this.tsrMonth;
    }

    public void setTsrMonth(int tsrMonth) {
        this.tsrMonth = tsrMonth;
    }

    public String getTsrYear() {
        return this.tsrYear;
    }

    public void setTsrYear(String tsrYear) {
        this.tsrYear = tsrYear;
    }

    public BigDecimal getIcPoHeader() {
        return this.icPoHeader;
    }

    public void setIcPoHeader(BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public String getTsrFlag() {
        return this.tsrFlag;
    }

    public void setTsrFlag(String tsrFlag) {
        this.tsrFlag = tsrFlag;
    }

    public String getPoType() {
        return this.poType;
    }

    public void setPoType(String poType) {
        this.poType = poType;
    }

    public BigDecimal getReleaseTotal() {
        return this.releaseTotal;
    }

    public void setReleaseTotal(BigDecimal releaseTotal) {
        this.releaseTotal = releaseTotal;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("poNumber", getPoNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoPaymentDetailView) ) return false;
        PoPaymentDetailView castOther = (PoPaymentDetailView) other;
        return new EqualsBuilder()
            .append(this.getPoNumber(), castOther.getPoNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPoNumber())
            .toHashCode();
    }

    public String getInternalComments()
    {
        return internalComments;
    }
    public void setInternalComments(String internalComments)
    {
        this.internalComments = internalComments;
    }
    public String getBudgeted()
    {
        return budgeted;
    }
    public void setBudgeted(String budgeted)
    {
        this.budgeted = budgeted;
    }
    public String getContractNo()
    {
        return contractNo;
    }
    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }
}
