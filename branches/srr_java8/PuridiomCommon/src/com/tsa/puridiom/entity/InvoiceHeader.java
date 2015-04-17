package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvoiceHeader implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icIvcHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private String invoiceNumber;

    /** nullable persistent field */
    private java.util.Date invoiceDate;

    /** nullable persistent field */
    private String invoiceTime;

    /** nullable persistent field */
    private String invoiceDesc;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceSubtotal;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceDiscount;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceTax;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceShipping;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceOther;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceRejecting;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceTotal;

    /** nullable persistent field */
    private java.math.BigDecimal invoicePaid;

    /** nullable persistent field */
    private java.math.BigDecimal invoiceAdjustment;

    /** nullable persistent field */
    private String specialInst;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExported;

    /** nullable persistent field */
    private java.util.Date paymentDueDate;
    
    /** nullable persistent field */
    private java.util.Date paymentDate;

    /** nullable persistent field */
    private String termsCode;

    /** nullable persistent field */
    private java.math.BigDecimal termsDiscperc;

    /** nullable persistent field */
    private java.math.BigDecimal termsDiscdays;

    /** nullable persistent field */
    private java.math.BigDecimal terms2Discperc;

    /** nullable persistent field */
    private java.math.BigDecimal terms2Discdays;

    /** nullable persistent field */
    private java.math.BigDecimal termsNetdays;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal poRelease;

    /** nullable persistent field */
    private java.util.Date poDate;

    /** nullable persistent field */
    private java.math.BigDecimal poTotal;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String contactName;

    /** nullable persistent field */
    private String contactEmail;

    /** nullable persistent field */
    private String contactPhone;

    /** nullable persistent field */
    private String contactFax;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String shipToContact;

    /** nullable persistent field */
    private String billToCode;

    /** nullable persistent field */
    private String billToContact;

    /** nullable persistent field */
    private String fobCode;

    /** nullable persistent field */
    private String pcardName;

    /** nullable persistent field */
    private String pcardHolder;

    /** nullable persistent field */
    private String pcardNumber;

    /** nullable persistent field */
    private String pcardExpires;

    /** nullable persistent field */
    private String eftBankName;

    /** nullable persistent field */
    private String eftBankAccount;

    /** nullable persistent field */
    private String eftAbaAch;

    /** nullable persistent field */
    private String eftAbaWire;

    /** nullable persistent field */
    private String eftHolderAccount;

    /** nullable persistent field */
    private String federalId;

    /** nullable persistent field */
    private String orderByName;

    /** nullable persistent field */
    private String orderByEmail;

    /** nullable persistent field */
    private String orderByPhone;

    /** nullable persistent field */
    private String ownerEmail;

    /** nullable persistent field */
    private String enteredBy;

    /** nullable persistent field */
    private String prePaid;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** nullable persistent field */
    private String udf4Code;

    /** nullable persistent field */
    private String udf5Code;

    /** nullable persistent field */
    private String udf6Code;

    /** nullable persistent field */
    private String udf7Code;

    /** nullable persistent field */
    private String udf8Code;

    /** nullable persistent field */
    private String udf9Code;

    /** nullable persistent field */
    private String udf10Code;

    /** nullable persistent field */
    private String reasonCode;

    /** nullable persistent field */
    private String apReference;

    /** nullable persistent field */
    private String invoiceType;

    /** nullable persistent field */
    private String vendorAddrCode;

    /** nullable persistent field */
    private String lastExtract;

    /** nullable persistent field */
    private java.util.Date invoiceReceivedDate;

    /** nullable persistent field */
    private java.util.Date invoicePrintedDate;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String vendorAccount;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String checkBatchId;

    /** nullable persistent field */
    private java.math.BigDecimal useTax;

    /** nullable persistent field */
    private String obmoNumber;

    /** nullable persistent field */
    private java.util.Date obmoDate;

    /** nullable persistent field */
    private java.math.BigDecimal obmoTotal;

    /** nullable persistent field */
    private java.util.Date inspectionDate;

    /** nullable persistent field */
    private java.util.Date processedDate;

    /** nullable persistent field */
    private java.util.Date imisApprovedDate;

    /** nullable persistent field */
    private java.util.Date imisPaymentDate;

    /** nullable persistent field */
    private java.util.Date discountDate;

    /** nullable persistent field */
    private String timeZone;

    private String ip;
    
    private String apBatchId;

    private Address shipToAddress;
    private Address billToAddress;
    private List invoiceLineList;
    private List accountList;
    private List taxAccountList;
    private List useTaxAccountList;
    private List shippingAccountList;
    private List otherAccountList;
    private List docAttachmentList;
    private List docCommentList;

    private PoHeader poHeader;

    /** full constructor */
    public InvoiceHeader(java.math.BigDecimal icIvcHeader, java.math.BigDecimal icPoHeader, java.lang.String invoiceNumber, java.util.Date invoiceDate, java.lang.String invoiceTime, java.lang.String invoiceDesc, java.math.BigDecimal invoiceSubtotal, java.math.BigDecimal invoiceDiscount, java.math.BigDecimal invoiceTax, java.math.BigDecimal invoiceShipping, java.math.BigDecimal invoiceOther, java.math.BigDecimal invoiceRejecting, java.math.BigDecimal invoiceTotal, java.lang.String specialInst, java.lang.String status, java.util.Date dateEntered, java.util.Date dateExported, java.util.Date paymentDueDate, java.util.Date paymentDate, java.lang.String termsCode, java.math.BigDecimal termsDiscperc, java.math.BigDecimal termsDiscdays, java.math.BigDecimal terms2Discperc, java.math.BigDecimal terms2Discdays, java.math.BigDecimal termsNetdays, java.lang.String poNumber, java.math.BigDecimal poRelease, java.util.Date poDate, java.math.BigDecimal poTotal, java.lang.String vendorId, java.lang.String vendorName, java.lang.String contactName, java.lang.String contactEmail, java.lang.String contactPhone, java.lang.String contactFax, java.lang.String shipToCode, java.lang.String shipToContact, java.lang.String billToCode, java.lang.String billToContact, java.lang.String fobCode, java.lang.String pcardName, java.lang.String pcardHolder, java.lang.String pcardNumber, java.lang.String pcardExpires, java.lang.String eftBankName, java.lang.String eftBankAccount, java.lang.String eftAbaAch, java.lang.String eftAbaWire, java.lang.String eftHolderAccount, java.lang.String federalId, java.lang.String orderByName, java.lang.String orderByEmail, java.lang.String orderByPhone, java.lang.String ownerEmail, String enteredBy, java.lang.String prePaid, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String reasonCode, java.lang.String apReference, java.lang.String invoiceType, java.lang.String vendorAddrCode, java.lang.String lastExtract, java.util.Date invoiceReceivedDate, java.util.Date invoicePrintedDate, java.math.BigDecimal icHeaderHistory, java.lang.String currencyCode, java.math.BigDecimal currencyFactor, java.lang.String departmentCode, java.lang.String vendorAccount, java.math.BigDecimal useTax, java.lang.String fiscalYear, java.lang.String obmoNumber, java.util.Date obmoDate, java.math.BigDecimal obmoTotal, java.util.Date inspectionDate, java.util.Date processedDate, java.util.Date imisApprovedDate, java.util.Date imisPaymentDate, java.util.Date discountDate, String checkBatchId, java.math.BigDecimal invoicePaid, java.math.BigDecimal invoiceAdjustment, java.lang.String timeZone) {
        this.icIvcHeader = icIvcHeader;
        this.icPoHeader = icPoHeader;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceTime = invoiceTime;
        this.invoiceDesc = invoiceDesc;
        this.invoiceSubtotal = invoiceSubtotal;
        this.invoiceDiscount = invoiceDiscount;
        this.invoiceTax = invoiceTax;
        this.invoiceShipping = invoiceShipping;
        this.invoiceOther = invoiceOther;
        this.invoiceRejecting = invoiceRejecting;
        this.invoiceTotal = invoiceTotal;
        this.specialInst = specialInst;
        this.status = status;
        this.dateEntered = dateEntered;
        this.dateExported = dateExported;
        this.paymentDueDate = paymentDueDate;
        this.paymentDate = paymentDate;
        this.termsCode = termsCode;
        this.termsDiscperc = termsDiscperc;
        this.termsDiscdays = termsDiscdays;
        this.terms2Discperc = terms2Discperc;
        this.terms2Discdays = terms2Discdays;
        this.termsNetdays = termsNetdays;
        this.poNumber = poNumber;
        this.poRelease = poRelease;
        this.poDate = poDate;
        this.poTotal = poTotal;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactFax = contactFax;
        this.shipToCode = shipToCode;
        this.shipToContact = shipToContact;
        this.billToCode = billToCode;
        this.billToContact = billToContact;
        this.fobCode = fobCode;
        this.pcardName = pcardName;
        this.pcardHolder = pcardHolder;
        this.pcardNumber = pcardNumber;
        this.pcardExpires = pcardExpires;
        this.eftBankName = eftBankName;
        this.eftBankAccount = eftBankAccount;
        this.eftAbaAch = eftAbaAch;
        this.eftAbaWire = eftAbaWire;
        this.eftHolderAccount = eftHolderAccount;
        this.federalId = federalId;
        this.orderByName = orderByName;
        this.orderByEmail = orderByEmail;
        this.orderByPhone = orderByPhone;
        this.ownerEmail = ownerEmail;
        this.enteredBy = enteredBy;
        this.prePaid = prePaid;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.udf6Code = udf6Code;
        this.udf7Code = udf7Code;
        this.udf8Code = udf8Code;
        this.udf9Code = udf9Code;
        this.udf10Code = udf10Code;
        this.reasonCode = reasonCode;
        this.apReference = apReference;
        this.invoiceType = invoiceType;
        this.vendorAddrCode = vendorAddrCode;
        this.lastExtract = lastExtract;
        this.invoiceReceivedDate = invoiceReceivedDate;
        this.invoicePrintedDate = invoicePrintedDate;
        this.icHeaderHistory = icHeaderHistory;
        this.currencyFactor = currencyFactor;
        this.departmentCode = departmentCode;
        this.vendorAccount = vendorAccount;
        this.useTax = useTax;
        this.fiscalYear = fiscalYear;
        this.obmoNumber = obmoNumber;
        this.obmoDate = obmoDate;
        this.obmoTotal = obmoTotal;
        this.inspectionDate = inspectionDate;
        this.processedDate = processedDate;
        this.imisApprovedDate = imisApprovedDate;
        this.imisPaymentDate = imisPaymentDate;
        this.discountDate = discountDate;
        this.checkBatchId = checkBatchId;
        this.invoicePaid = invoicePaid;
        this.invoiceAdjustment = invoiceAdjustment;
        this.timeZone = timeZone;
    }

    /** default constructor */
    public InvoiceHeader() {
    }

    /** minimal constructor */
    public InvoiceHeader(java.math.BigDecimal icIvcHeader) {
        this.icIvcHeader = icIvcHeader;
    }

    public java.math.BigDecimal getIcIvcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icIvcHeader);
    }

    public void setIcIvcHeader(java.math.BigDecimal icIvcHeader) {
        this.icIvcHeader = icIvcHeader;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
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

    public java.util.Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(java.util.Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public java.lang.String getInvoiceTime() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceTime).trim();
    }

    public void setInvoiceTime(java.lang.String invoiceTime) {
		if (!HiltonUtility.isEmpty(invoiceTime) && invoiceTime.length() > 10) {
			invoiceTime = invoiceTime.substring(0, 10);
		}
		this.invoiceTime = invoiceTime;
    }

    public java.lang.String getInvoiceDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceDesc).trim();
    }

    public void setInvoiceDesc(java.lang.String invoiceDesc) {
		if (!HiltonUtility.isEmpty(invoiceDesc) && invoiceDesc.length() > 255) {
			invoiceDesc = invoiceDesc.substring(0, 255);
		}
		this.invoiceDesc = invoiceDesc;
    }

    public java.math.BigDecimal getInvoiceSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceSubtotal);
    }

    public void setInvoiceSubtotal(java.math.BigDecimal invoiceSubtotal) {
        this.invoiceSubtotal = invoiceSubtotal;
    }

    public java.math.BigDecimal getInvoiceDiscount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceDiscount);
    }

    public void setInvoiceDiscount(java.math.BigDecimal invoiceDiscount) {
        this.invoiceDiscount = invoiceDiscount;
    }

    public java.math.BigDecimal getInvoiceTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceTax);
    }

    public void setInvoiceTax(java.math.BigDecimal invoiceTax) {
        this.invoiceTax = invoiceTax;
    }

    public java.math.BigDecimal getInvoiceShipping() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceShipping);
    }

    public void setInvoiceShipping(java.math.BigDecimal invoiceShipping) {
        this.invoiceShipping = invoiceShipping;
    }

    public java.math.BigDecimal getInvoiceOther() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceOther);
    }

    public void setInvoiceOther(java.math.BigDecimal invoiceOther) {
        this.invoiceOther = invoiceOther;
    }

    public java.math.BigDecimal getInvoiceRejecting() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceRejecting);
    }

    public void setInvoiceRejecting(java.math.BigDecimal invoiceRejecting) {
        this.invoiceRejecting = invoiceRejecting;
    }

    public java.math.BigDecimal getInvoiceTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceTotal);
    }

    public void setInvoiceTotal(java.math.BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public java.math.BigDecimal getInvoicePaid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoicePaid);
    }

    public void setInvoiceAdjustment(java.math.BigDecimal invoiceAdjustment) {
        this.invoiceAdjustment = invoiceAdjustment;
    }

    public java.math.BigDecimal getInvoiceAdjustment() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceAdjustment);
    }

    public void setInvoicePaid(java.math.BigDecimal invoicePaid) {
        this.invoicePaid = invoicePaid;
    }

    public java.lang.String getSpecialInst() {
		return (java.lang.String)HiltonUtility.ckNull(this.specialInst).trim();
    }

    public void setSpecialInst(java.lang.String specialInst) {
		if (!HiltonUtility.isEmpty(specialInst) && specialInst.length() > 255) {
			specialInst = specialInst.substring(0, 255);
		}
		this.specialInst = specialInst;
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

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExported() {
        return this.dateExported;
    }

    public void setDateExported(java.util.Date dateExported) {
        this.dateExported = dateExported;
    }

    public java.util.Date getPaymentDueDate() {
        return this.paymentDueDate;
    }

    public void setPaymentDueDate(java.util.Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }
    
    public java.util.Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(java.util.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public java.lang.String getTermsCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsCode).trim();
    }

    public void setTermsCode(java.lang.String termsCode) {
		if (!HiltonUtility.isEmpty(termsCode) && termsCode.length() > 15) {
			termsCode = termsCode.substring(0, 15);
		}
		this.termsCode = termsCode;
    }

    public java.math.BigDecimal getTermsDiscperc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.termsDiscperc);
    }

    public void setTermsDiscperc(java.math.BigDecimal termsDiscperc) {
        this.termsDiscperc = termsDiscperc;
    }

    public java.math.BigDecimal getTermsDiscdays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.termsDiscdays);
    }

    public void setTermsDiscdays(java.math.BigDecimal termsDiscdays) {
        this.termsDiscdays = termsDiscdays;
    }

    public java.math.BigDecimal getTerms2Discperc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.terms2Discperc);
    }

    public void setTerms2Discperc(java.math.BigDecimal terms2Discperc) {
        this.terms2Discperc = terms2Discperc;
    }

    public java.math.BigDecimal getTerms2Discdays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.terms2Discdays);
    }

    public void setTerms2Discdays(java.math.BigDecimal terms2Discdays) {
        this.terms2Discdays = terms2Discdays;
    }

    public java.math.BigDecimal getTermsNetdays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.termsNetdays);
    }

    public void setTermsNetdays(java.math.BigDecimal termsNetdays) {
        this.termsNetdays = termsNetdays;
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

    public java.math.BigDecimal getPoRelease() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.poRelease);
    }

    public void setPoRelease(java.math.BigDecimal poRelease) {
        this.poRelease = poRelease;
    }

    public java.util.Date getPoDate() {
		return this.poDate;
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
    }

    public java.math.BigDecimal getPoTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.poTotal);
    }

    public void setPoTotal(java.math.BigDecimal poTotal) {
        this.poTotal = poTotal;
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

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
    }

    public java.lang.String getContactName() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactName).trim();
    }

    public void setContactName(java.lang.String contactName) {
		if (!HiltonUtility.isEmpty(contactName) && contactName.length() > 40) {
			contactName = contactName.substring(0, 40);
		}
		this.contactName = contactName;
    }

    public java.lang.String getContactEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactEmail).trim();
    }

    public void setContactEmail(java.lang.String contactEmail) {
		if (!HiltonUtility.isEmpty(contactEmail) && contactEmail.length() > 60) {
			contactEmail = contactEmail.substring(0, 60);
		}
		this.contactEmail = contactEmail;
    }

    public java.lang.String getContactPhone() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactPhone).trim();
    }

    public void setContactPhone(java.lang.String contactPhone) {
		if (!HiltonUtility.isEmpty(contactPhone) && contactPhone.length() > 40) {
			contactPhone = contactPhone.substring(0, 40);
		}
		this.contactPhone = contactPhone;
    }

    public java.lang.String getContactFax() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactFax).trim();
    }

    public void setContactFax(java.lang.String contactFax) {
		if (!HiltonUtility.isEmpty(contactFax) && contactFax.length() > 14) {
			contactFax = contactFax.substring(0, 14);
		}
		this.contactFax = contactFax;
    }

    public java.lang.String getShipToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToCode).trim();
    }

    public void setShipToCode(java.lang.String shipToCode) {
		if (!HiltonUtility.isEmpty(shipToCode) && shipToCode.length() > 15) {
			shipToCode = shipToCode.substring(0, 15);
		}
		this.shipToCode = shipToCode;
    }

    public java.lang.String getShipToContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToContact).trim();
    }

    public void setShipToContact(java.lang.String shipToContact) {
		if (!HiltonUtility.isEmpty(shipToContact) && shipToContact.length() > 40) {
			shipToContact = shipToContact.substring(0, 40);
		}
		this.shipToContact = shipToContact;
    }

    public java.lang.String getBillToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.billToCode).trim();
    }

    public void setBillToCode(java.lang.String billToCode) {
		if (!HiltonUtility.isEmpty(billToCode) && billToCode.length() > 15) {
			billToCode = billToCode.substring(0, 15);
		}
		this.billToCode = billToCode;
    }

    public java.lang.String getBillToContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.billToContact).trim();
    }

    public void setBillToContact(java.lang.String billToContact) {
		if (!HiltonUtility.isEmpty(billToContact) && billToContact.length() > 40) {
			billToContact = billToContact.substring(0, 40);
		}
		this.billToContact = billToContact;
    }

    public java.lang.String getFobCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.fobCode).trim();
    }

    public void setFobCode(java.lang.String fobCode) {
		if (!HiltonUtility.isEmpty(fobCode) && fobCode.length() > 15) {
			fobCode = fobCode.substring(0, 15);
		}
		this.fobCode = fobCode;
    }

    public java.lang.String getPcardName() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardName).trim();
    }

    public void setPcardName(java.lang.String pcardName) {
		if (!HiltonUtility.isEmpty(pcardName) && pcardName.length() > 20) {
			pcardName = pcardName.substring(0, 20);
		}
		this.pcardName = pcardName;
    }

    public java.lang.String getPcardHolder() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardHolder).trim();
    }

    public void setPcardHolder(java.lang.String pcardHolder) {
		if (!HiltonUtility.isEmpty(pcardHolder) && pcardHolder.length() > 45) {
			pcardHolder = pcardHolder.substring(0, 45);
		}
		this.pcardHolder = pcardHolder;
    }

    public java.lang.String getPcardNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardNumber).trim();
    }

    public void setPcardNumber(java.lang.String pcardNumber) {
		if (!HiltonUtility.isEmpty(pcardNumber) && pcardNumber.length() > 25) {
			pcardNumber = pcardNumber.substring(0, 25);
		}
		this.pcardNumber = pcardNumber;
    }

    public java.lang.String getPcardExpires() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardExpires).trim();
    }

    public void setPcardExpires(java.lang.String pcardExpires) {
		if (!HiltonUtility.isEmpty(pcardExpires) && pcardExpires.length() > 10) {
			pcardExpires = pcardExpires.substring(0, 10);
		}
		this.pcardExpires = pcardExpires;
    }

    public java.lang.String getEftBankName() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftBankName).trim();
    }

    public void setEftBankName(java.lang.String eftBankName) {
		if (!HiltonUtility.isEmpty(eftBankName) && eftBankName.length() > 40) {
			eftBankName = eftBankName.substring(0, 40);
		}
		this.eftBankName = eftBankName;
    }

    public java.lang.String getEftBankAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftBankAccount).trim();
    }

    public void setEftBankAccount(java.lang.String eftBankAccount) {
		if (!HiltonUtility.isEmpty(eftBankAccount) && eftBankAccount.length() > 20) {
			eftBankAccount = eftBankAccount.substring(0, 20);
		}
		this.eftBankAccount = eftBankAccount;
    }

    public java.lang.String getEftAbaAch() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAbaAch).trim();
    }

    public void setEftAbaAch(java.lang.String eftAbaAch) {
		if (!HiltonUtility.isEmpty(eftAbaAch) && eftAbaAch.length() > 20) {
			eftAbaAch = eftAbaAch.substring(0, 20);
		}
		this.eftAbaAch = eftAbaAch;
    }

    public java.lang.String getEftAbaWire() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAbaWire).trim();
    }

    public void setEftAbaWire(java.lang.String eftAbaWire) {
		if (!HiltonUtility.isEmpty(eftAbaWire) && eftAbaWire.length() > 20) {
			eftAbaWire = eftAbaWire.substring(0, 20);
		}
		this.eftAbaWire = eftAbaWire;
    }

    public java.lang.String getEftHolderAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftHolderAccount).trim();
    }

    public void setEftHolderAccount(java.lang.String eftHolderAccount) {
		if (!HiltonUtility.isEmpty(eftHolderAccount) && eftHolderAccount.length() > 20) {
			eftHolderAccount = eftHolderAccount.substring(0, 20);
		}
		this.eftHolderAccount = eftHolderAccount;
    }

    public java.lang.String getFederalId() {
		return (java.lang.String)HiltonUtility.ckNull(this.federalId).trim();
    }

    public void setFederalId(java.lang.String federalId) {
		if (!HiltonUtility.isEmpty(federalId) && federalId.length() > 20) {
			federalId = federalId.substring(0, 20);
		}
		this.federalId = federalId;
    }

    public java.lang.String getOrderByName() {
		return (java.lang.String)HiltonUtility.ckNull(this.orderByName).trim();
    }

    public void setOrderByName(java.lang.String orderByName) {
		if (!HiltonUtility.isEmpty(orderByName) && orderByName.length() > 40) {
			orderByName = orderByName.substring(0, 40);
		}
		this.orderByName = orderByName;
    }

    public java.lang.String getOrderByEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.orderByEmail).trim();
    }

    public void setOrderByEmail(java.lang.String orderByEmail) {
		if (!HiltonUtility.isEmpty(orderByEmail) && orderByEmail.length() > 60) {
			orderByEmail = orderByEmail.substring(0, 60);
		}
		this.orderByEmail = orderByEmail;
    }

    public java.lang.String getOrderByPhone() {
		return (java.lang.String)HiltonUtility.ckNull(this.orderByPhone).trim();
    }

    public void setOrderByPhone(java.lang.String orderByPhone) {
		if (!HiltonUtility.isEmpty(orderByPhone) && orderByPhone.length() > 40) {
			orderByPhone = orderByPhone.substring(0, 40);
		}
		this.orderByPhone = orderByPhone;
    }

    public java.lang.String getOwnerEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.ownerEmail).trim();
    }

    public void setOwnerEmail(java.lang.String ownerEmail) {
		if (!HiltonUtility.isEmpty(ownerEmail) && ownerEmail.length() > 60) {
			ownerEmail = ownerEmail.substring(0, 60);
		}
		this.ownerEmail = ownerEmail;
    }

    public java.lang.String getEnteredBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.enteredBy).trim();
    }

    public void setEnteredBy(java.lang.String enteredBy) {
		if (!HiltonUtility.isEmpty(enteredBy) && enteredBy.length() > 15) {
			enteredBy = enteredBy.substring(0, 15);
		}
		this.enteredBy = enteredBy;
    }

    public java.lang.String getPrePaid() {
		return (java.lang.String)HiltonUtility.ckNull(this.prePaid).trim();
    }

    public void setPrePaid(java.lang.String prePaid) {
		if (!HiltonUtility.isEmpty(prePaid) && prePaid.length() > 1) {
			prePaid = prePaid.substring(0, 1);
		}
		this.prePaid = prePaid;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 15) {
			udf1Code = udf1Code.substring(0, 15);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 15) {
			udf2Code = udf2Code.substring(0, 15);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 15) {
			udf3Code = udf3Code.substring(0, 15);
		}
		this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
    }

    public void setUdf4Code(java.lang.String udf4Code) {
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 15) {
			udf4Code = udf4Code.substring(0, 15);
		}
		this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
    }

    public void setUdf5Code(java.lang.String udf5Code) {
		if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 15) {
			udf5Code = udf5Code.substring(0, 15);
		}
		this.udf5Code = udf5Code;
    }

    public java.lang.String getUdf6Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf6Code).trim();
    }

    public void setUdf6Code(java.lang.String udf6Code) {
		if (!HiltonUtility.isEmpty(udf6Code) && udf6Code.length() > 15) {
			udf6Code = udf6Code.substring(0, 15);
		}
		this.udf6Code = udf6Code;
    }

    public java.lang.String getUdf7Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf7Code).trim();
    }

    public void setUdf7Code(java.lang.String udf7Code) {
		if (!HiltonUtility.isEmpty(udf7Code) && udf7Code.length() > 15) {
			udf7Code = udf7Code.substring(0, 15);
		}
		this.udf7Code = udf7Code;
    }

    public java.lang.String getUdf8Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf8Code).trim();
    }

    public void setUdf8Code(java.lang.String udf8Code) {
		if (!HiltonUtility.isEmpty(udf8Code) && udf8Code.length() > 15) {
			udf8Code = udf8Code.substring(0, 15);
		}
		this.udf8Code = udf8Code;
    }

    public java.lang.String getUdf9Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf9Code).trim();
    }

    public void setUdf9Code(java.lang.String udf9Code) {
		if (!HiltonUtility.isEmpty(udf9Code) && udf9Code.length() > 15) {
			udf9Code = udf9Code.substring(0, 15);
		}
		this.udf9Code = udf9Code;
    }

    public java.lang.String getUdf10Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf10Code).trim();
    }

    public void setUdf10Code(java.lang.String udf10Code) {
		if (!HiltonUtility.isEmpty(udf10Code) && udf10Code.length() > 15) {
			udf10Code = udf10Code.substring(0, 15);
		}
		this.udf10Code = udf10Code;
    }

    public java.lang.String getReasonCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.reasonCode).trim();
    }

    public void setReasonCode(java.lang.String reasonCode) {
		if (!HiltonUtility.isEmpty(reasonCode) && reasonCode.length() > 18) {
			reasonCode = reasonCode.substring(0, 18);
		}
		this.reasonCode = reasonCode;
    }

    public String getApReference() {
		return (java.lang.String)HiltonUtility.ckNull(this.apReference).trim();
    }

    public void setApReference(String apReference) {
		if (!HiltonUtility.isEmpty(apReference) && apReference.length() > 20) {
			apReference = apReference.substring(0, 20);
		}
		this.apReference = apReference;
    }

    public String getInvoiceType() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceType).trim();
    }

    public void setInvoiceType(String invoiceType) {
		if (!HiltonUtility.isEmpty(invoiceType) && invoiceType.length() > 1) {
			invoiceType = invoiceType.substring(0, 1);
		}
		this.invoiceType = invoiceType;
    }

    public String getVendorAddrCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAddrCode).trim();
    }

    public void setVendorAddrCode(String vendorAddrCode) {
		if (!HiltonUtility.isEmpty(vendorAddrCode) && vendorAddrCode.length() > 15) {
			vendorAddrCode = vendorAddrCode.substring(0, 15);
		}
		this.vendorAddrCode = vendorAddrCode;
    }

    public String getLastExtract() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastExtract).trim();
    }

    public void setLastExtract(String lastExtract) {
		if (!HiltonUtility.isEmpty(lastExtract) && lastExtract.length() > 22) {
			lastExtract = lastExtract.substring(0, 22);
		}
		this.lastExtract = lastExtract;
    }

    public java.util.Date getInvoiceReceivedDate() {
        return this.invoiceReceivedDate;
    }

    public void setInvoiceReceivedDate(java.util.Date invoiceReceivedDate) {
        this.invoiceReceivedDate = invoiceReceivedDate;
    }

    public java.util.Date getInvoicePrintedDate() {
        return this.invoicePrintedDate;
    }

    public void setInvoicePrintedDate(java.util.Date invoicePrintedDate) {
        this.invoicePrintedDate = invoicePrintedDate;
    }

    public String getTimeZone() {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone) {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public Address getBillToAddress() {
		return this.billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

    public Address getShipToAddress() {
		return this.shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

    public List getInvoiceLineList() {
		return this.invoiceLineList;
	}

	public void setInvoiceLineList(List invoiceLineList) {
		this.invoiceLineList = invoiceLineList;
	}

	public List getAccountList() {
		return this.accountList;
	}

	public void setAccountList(List accountList) {
		this.accountList = accountList;
	}

	public List getTaxAccountList() {
		return this.taxAccountList;
	}

	public void setTaxAccountList(List taxAccountList) {
		this.taxAccountList = taxAccountList;
	}

	public List getUseTaxAccountList() {
		return this.useTaxAccountList;
	}

	public void setUseTaxAccountList(List useTaxAccountList) {
		this.useTaxAccountList = useTaxAccountList;
	}

	public List getShippingAccountList() {
		return this.shippingAccountList;
	}

	public void setShippingAccountList(List shippingAccountList) {
		this.shippingAccountList = shippingAccountList;
	}

	public List getOtherAccountList() {
		return this.otherAccountList;
	}

	public void setOtherAccountList(List otherAccountList) {
		this.otherAccountList = otherAccountList;
	}

	public List getDocAttachmentList() {
		return docAttachmentList;
	}

	public List getDocCommentList() {
		return docCommentList;
	}

	public void setDocAttachmentList(List docAttachmentList) {
		this.docAttachmentList = docAttachmentList;
	}

	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icIvcHeader", getIcIvcHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvoiceHeader) ) return false;
        InvoiceHeader castOther = (InvoiceHeader) other;
        return new EqualsBuilder()
            .append(this.getIcIvcHeader(), castOther.getIcIvcHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcIvcHeader())
            .toHashCode();
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

	public PoHeader getPoHeader()
	{
		return poHeader;
	}

	public void setPoHeader(PoHeader poHeader)
	{
		this.poHeader = poHeader;
	}

	public java.lang.String getCurrencyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.currencyCode).trim();
    }

    public void setCurrencyCode(java.lang.String currencyCode) {
		if (!HiltonUtility.isEmpty(currencyCode) && currencyCode.length() > 15) {
			currencyCode = currencyCode.substring(0, 15);
		}
		this.currencyCode = currencyCode;
    }

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
    }

    public java.lang.String getDepartmentCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
    }

    public void setDepartmentCode(java.lang.String departmentCode) {
		if (!HiltonUtility.isEmpty(departmentCode) && departmentCode.length() > 15) {
			departmentCode = departmentCode.substring(0, 15);
		}
		this.departmentCode = departmentCode;
    }

    public java.lang.String getVendorAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAccount).trim();
    }

    public void setVendorAccount(java.lang.String vendorAccount) {
		if (!HiltonUtility.isEmpty(vendorAccount) && vendorAccount.length() > 20) {
			vendorAccount = vendorAccount.substring(0, 20);
		}
		this.vendorAccount = vendorAccount;
    }

    public java.math.BigDecimal getUseTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.useTax);
    }

    public void setUseTax(java.math.BigDecimal useTax) {
        this.useTax = useTax;
    }

    public java.lang.String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
    }

    public java.lang.String getObmoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.obmoNumber).trim();
    }

    public void setObmoNumber(java.lang.String obmoNumber) {
		if (!HiltonUtility.isEmpty(obmoNumber) && obmoNumber.length() > 15) {
			obmoNumber = obmoNumber.substring(0, 15);
		}
		this.obmoNumber = obmoNumber;
    }

    public java.util.Date getObmoDate() {
        return this.obmoDate;
    }

    public void setObmoDate(java.util.Date obmoDate) {
        this.obmoDate = obmoDate;
    }

    public java.math.BigDecimal getObmoTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.obmoTotal);
    }

    public void setObmoTotal(java.math.BigDecimal obmoTotal) {
        this.obmoTotal = obmoTotal;
    }

    public java.util.Date getInspectionDate() {
        return this.inspectionDate;
    }

    public void setInspectionDate(java.util.Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public java.util.Date getProcessedDate() {
        return this.processedDate;
    }

    public void setProcessedDate(java.util.Date processedDate) {
        this.processedDate = processedDate;
    }

    public java.util.Date getImisApprovedDate() {
        return this.imisApprovedDate;
    }

    public void setImisApprovedDate(java.util.Date imisApprovedDate) {
        this.imisApprovedDate = imisApprovedDate;
    }

    public java.util.Date getImisPaymentDate() {
        return this.imisPaymentDate;
    }

    public void setImisPaymentDate(java.util.Date imisPaymentDate) {
        this.imisPaymentDate = imisPaymentDate;
    }

    public java.util.Date getDiscountDate() {
        return this.discountDate;
    }

    public void setDiscountDate(java.util.Date discountDate) {
        this.discountDate = discountDate;
    }

    public void setCheckBatchId(String checkBatchId) {
		if (!HiltonUtility.isEmpty(checkBatchId) && checkBatchId.length() > 15) {
			checkBatchId = checkBatchId.substring(0, 15);
		}
		this.checkBatchId = checkBatchId;
    }

    public String getCheckBatchId() {
		return (String)HiltonUtility.ckNull(this.checkBatchId).trim();
    }

    public void setIp(String ip) {
		this.ip = ip;
    }

    public String getId() {
		return this.ip;
    }

    public void setApBatchId(String apBatchId) {
    	this.apBatchId = apBatchId;
    }
    
    public String getApBatchId() {
    	return apBatchId;
    }
}
