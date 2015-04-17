package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RfqVendor implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RfqVendorPK comp_id;

    /** nullable persistent field */
    private java.util.Date dateResponseRecv;

    /** nullable persistent field */
    private String bidResponseCode;

    /** nullable persistent field */
    private String contactId;

    /** nullable persistent field */
    private String discountSource;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String otherDescription;

    /** nullable persistent field */
    private String taxShipping;

    /** nullable persistent field */
    private String taxOther;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private java.util.Date datePromised;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTaxAmt;

    /** nullable persistent field */
    private java.math.BigDecimal otherTaxAmount;

    /** nullable persistent field */
    private String vendCurrency;

    /** nullable persistent field */
    private String fob;

    /** nullable persistent field */
    private String paymentTerms;

    /** nullable persistent field */
    private java.util.Date bidValidTo;

    /** nullable persistent field */
    private String addressCode;

    /** nullable persistent field */
    private String ediRfq;

    /** nullable persistent field */
    private java.util.Date dateEdiXmit;

    /** nullable persistent field */
    private java.util.Date ediDateResponse;

    /** nullable persistent field */
    private String ediStatus;

    /** nullable persistent field */
    private String discountTerms;

    /** nullable persistent field */
    private String notificationCode;

    /** nullable persistent field */
    private String contactName;

    /** nullable persistent field */
    private String bidsEntered;

    /** nullable persistent field */
    private String vendorClass;
    
    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;
    
    /** nullable persistent field */
    private java.math.BigDecimal totalScore;

    /** nullable persistent field */
    private String evaluationStatus;
    
    private java.math.BigDecimal bidTotal;

    /** nullable persistent field */
    private String bidCode;
    
    private List rfqBidList;

    private java.math.BigDecimal bidSubtotal;
    
    private Address address;

    /** full constructor */
    public RfqVendor(com.tsa.puridiom.entity.RfqVendorPK comp_id, java.util.Date dateResponseRecv, java.lang.String bidResponseCode, java.lang.String contactId, java.lang.String discountSource, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal shippingCharges, java.math.BigDecimal otherCharges, java.lang.String otherDescription, java.lang.String taxShipping, java.lang.String taxOther, java.lang.String taxCode, java.util.Date datePromised, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal shippingTaxAmt, java.math.BigDecimal otherTaxAmount, java.lang.String vendCurrency, java.lang.String fob, java.lang.String paymentTerms, java.util.Date bidValidTo, java.lang.String addressCode, java.lang.String ediRfq, java.util.Date dateEdiXmit, java.util.Date ediDateResponse, java.lang.String ediStatus, java.lang.String discountTerms, java.lang.String notificationCode, java.lang.String contactName, java.lang.String bidsEntered, java.lang.String vendorClass, java.math.BigDecimal currencyFactor, java.math.BigDecimal totalScore, java.lang.String evaluationStatus, java.lang.String bidCode) {
        this.comp_id = comp_id;
        this.dateResponseRecv = dateResponseRecv;
        this.bidResponseCode = bidResponseCode;
        this.contactId = contactId;
        this.discountSource = discountSource;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.shippingCharges = shippingCharges;
        this.otherCharges = otherCharges;
        this.otherDescription = otherDescription;
        this.taxShipping = taxShipping;
        this.taxOther = taxOther;
        this.taxCode = taxCode;
        this.datePromised = datePromised;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.shippingTaxAmt = shippingTaxAmt;
        this.otherTaxAmount = otherTaxAmount;
        this.vendCurrency = vendCurrency;
        this.fob = fob;
        this.paymentTerms = paymentTerms;
        this.bidValidTo = bidValidTo;
        this.addressCode = addressCode;
        this.ediRfq = ediRfq;
        this.dateEdiXmit = dateEdiXmit;
        this.ediDateResponse = ediDateResponse;
        this.ediStatus = ediStatus;
        this.discountTerms = discountTerms;
        this.notificationCode = notificationCode;
        this.contactName = contactName;
        this.bidsEntered = bidsEntered;
        this.vendorClass = vendorClass;
        this.currencyFactor = currencyFactor;
        this.totalScore = totalScore;
        this.evaluationStatus = evaluationStatus;
        this.bidCode = bidCode;
    }

    /** default constructor */
    public RfqVendor() {
    }

    /** minimal constructor */
    public RfqVendor(com.tsa.puridiom.entity.RfqVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RfqVendorPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RfqVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.util.Date getDateResponseRecv() {
		return this.dateResponseRecv;
//		return HiltonUtility.ckNull(this.dateResponseRecv);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateResponseRecv);
    }

    public void setDateResponseRecv(java.util.Date dateResponseRecv) {
        this.dateResponseRecv = dateResponseRecv;
    }

    public java.lang.String getBidResponseCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidResponseCode).trim();
    }

    public void setBidResponseCode(java.lang.String bidResponseCode) {
		if (!HiltonUtility.isEmpty(bidResponseCode) && bidResponseCode.length() > 15) {
			bidResponseCode = bidResponseCode.substring(0, 15);
		}
		this.bidResponseCode = bidResponseCode;
    }

    public java.lang.String getContactId() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactId).trim();
    }

    public void setContactId(java.lang.String contactId) {
		if (!HiltonUtility.isEmpty(contactId) && contactId.length() > 15) {
			contactId = contactId.substring(0, 15);
		}
		this.contactId = contactId;
    }

    public java.lang.String getDiscountSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountSource).trim();
    }

    public void setDiscountSource(java.lang.String discountSource) {
		if (!HiltonUtility.isEmpty(discountSource) && discountSource.length() > 1) {
			discountSource = discountSource.substring(0, 1);
		}
		this.discountSource = discountSource;
    }

    public java.math.BigDecimal getDiscountPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountPercent);
    }

    public void setDiscountPercent(java.math.BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public java.math.BigDecimal getDiscountAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountAmount);
    }

    public void setDiscountAmount(java.math.BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public java.math.BigDecimal getShippingCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingCharges);
    }

    public void setShippingCharges(java.math.BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public java.math.BigDecimal getOtherCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherCharges);
    }

    public void setOtherCharges(java.math.BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public java.lang.String getOtherDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherDescription).trim();
    }

    public void setOtherDescription(java.lang.String otherDescription) {
		if (!HiltonUtility.isEmpty(otherDescription) && otherDescription.length() > 30) {
			otherDescription = otherDescription.substring(0, 30);
		}
		this.otherDescription = otherDescription;
    }

    public java.lang.String getTaxShipping() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxShipping).trim();
    }

    public void setTaxShipping(java.lang.String taxShipping) {
		if (!HiltonUtility.isEmpty(taxShipping) && taxShipping.length() > 1) {
			taxShipping = taxShipping.substring(0, 1);
		}
		this.taxShipping = taxShipping;
    }

    public java.lang.String getTaxOther() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxOther).trim();
    }

    public void setTaxOther(java.lang.String taxOther) {
		if (!HiltonUtility.isEmpty(taxOther) && taxOther.length() > 1) {
			taxOther = taxOther.substring(0, 1);
		}
		this.taxOther = taxOther;
    }

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

    public java.util.Date getDatePromised() {
		return this.datePromised;
//		return HiltonUtility.ckNull(this.datePromised);
//		return (java.sql.Date)HiltonUtility.ckNull(this.datePromised);
    }

    public void setDatePromised(java.util.Date datePromised) {
        this.datePromised = datePromised;
    }

    public java.math.BigDecimal getTaxPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxPercent);
    }

    public void setTaxPercent(java.math.BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public java.math.BigDecimal getTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxAmount);
    }

    public void setTaxAmount(java.math.BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public java.math.BigDecimal getShippingTaxAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTaxAmt);
    }

    public void setShippingTaxAmt(java.math.BigDecimal shippingTaxAmt) {
        this.shippingTaxAmt = shippingTaxAmt;
    }

    public java.math.BigDecimal getOtherTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherTaxAmount);
    }

    public void setOtherTaxAmount(java.math.BigDecimal otherTaxAmount) {
        this.otherTaxAmount = otherTaxAmount;
    }

    public java.lang.String getVendCurrency() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendCurrency).trim();
    }

    public void setVendCurrency(java.lang.String vendCurrency) {
		if (!HiltonUtility.isEmpty(vendCurrency) && vendCurrency.length() > 15) {
			vendCurrency = vendCurrency.substring(0, 15);
		}
		this.vendCurrency = vendCurrency;
    }

    public java.lang.String getFob() {
		return (java.lang.String)HiltonUtility.ckNull(this.fob).trim();
    }

    public void setFob(java.lang.String fob) {
		if (!HiltonUtility.isEmpty(fob) && fob.length() > 15) {
			fob = fob.substring(0, 15);
		}
		this.fob = fob;
    }

    public java.lang.String getPaymentTerms() {
		return (java.lang.String)HiltonUtility.ckNull(this.paymentTerms).trim();
    }

    public void setPaymentTerms(java.lang.String paymentTerms) {
		if (!HiltonUtility.isEmpty(paymentTerms) && paymentTerms.length() > 15) {
			paymentTerms = paymentTerms.substring(0, 15);
		}
		this.paymentTerms = paymentTerms;
    }

    public java.util.Date getBidValidTo() {
		return this.bidValidTo;
//		return HiltonUtility.ckNull(this.bidValidTo);
//		return (java.sql.Date)HiltonUtility.ckNull(this.bidValidTo);
    }

    public void setBidValidTo(java.util.Date bidValidTo) {
        this.bidValidTo = bidValidTo;
    }

    public java.lang.String getAddressCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCode).trim();
    }

    public void setAddressCode(java.lang.String addressCode) {
		if (!HiltonUtility.isEmpty(addressCode) && addressCode.length() > 15) {
			addressCode = addressCode.substring(0, 15);
		}
		this.addressCode = addressCode;
    }

    public java.lang.String getEdiRfq() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediRfq).trim();
    }

    public void setEdiRfq(java.lang.String ediRfq) {
		if (!HiltonUtility.isEmpty(ediRfq) && ediRfq.length() > 1) {
			ediRfq = ediRfq.substring(0, 1);
		}
		this.ediRfq = ediRfq;
    }

    public java.util.Date getDateEdiXmit() {
		return this.dateEdiXmit;
//		return HiltonUtility.ckNull(this.dateEdiXmit);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEdiXmit);
    }

    public void setDateEdiXmit(java.util.Date dateEdiXmit) {
        this.dateEdiXmit = dateEdiXmit;
    }

    public java.util.Date getEdiDateResponse() {
		return this.ediDateResponse;
//		return HiltonUtility.ckNull(this.ediDateResponse);
//		return (java.sql.Date)HiltonUtility.ckNull(this.ediDateResponse);
    }

    public void setEdiDateResponse(java.util.Date ediDateResponse) {
        this.ediDateResponse = ediDateResponse;
    }

    public java.lang.String getEdiStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediStatus).trim();
    }

    public void setEdiStatus(java.lang.String ediStatus) {
		if (!HiltonUtility.isEmpty(ediStatus) && ediStatus.length() > 2) {
			ediStatus = ediStatus.substring(0, 2);
		}
		this.ediStatus = ediStatus;
    }

    public java.lang.String getDiscountTerms() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountTerms).trim();
    }

    public void setDiscountTerms(java.lang.String discountTerms) {
		if (!HiltonUtility.isEmpty(discountTerms) && discountTerms.length() > 20) {
			discountTerms = discountTerms.substring(0, 20);
		}
		this.discountTerms = discountTerms;
    }

    public java.lang.String getNotificationCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.notificationCode).trim();
    }

    public void setNotificationCode(java.lang.String notificationCode) {
		if (!HiltonUtility.isEmpty(notificationCode) && notificationCode.length() > 1) {
			notificationCode = notificationCode.substring(0, 1);
		}
		this.notificationCode = notificationCode;
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

    public java.lang.String getVendorClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorClass).trim();
    }

    public void setVendorClass(java.lang.String vendorClass) {
		if (!HiltonUtility.isEmpty(vendorClass) && vendorClass.length() > 40) {
			vendorClass = vendorClass.substring(0, 40);
		}
		this.vendorClass = vendorClass;
    }

    public java.math.BigDecimal getBidTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bidTotal);
    }

    public void setBidTotal(java.math.BigDecimal bidTotal) {
        this.bidTotal = bidTotal;
    }

    public java.lang.String getBidsEntered() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidsEntered).trim();
    }

    public void setBidsEntered(java.lang.String bidsEntered) {
		if (!HiltonUtility.isEmpty(bidsEntered) && bidsEntered.length() > 1) {
			bidsEntered = bidsEntered.substring(0, 1);
		}
		this.bidsEntered = bidsEntered;
    }

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
    }

    public java.math.BigDecimal getTotalScore() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.totalScore);
    }

    public void setTotalScore(java.math.BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public java.lang.String getEvaluationStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.evaluationStatus).trim();
    }

    public void setEvaluationStatus(java.lang.String evaluationStatus) {
		if (!HiltonUtility.isEmpty(evaluationStatus) && evaluationStatus.length() > 5) {
		    evaluationStatus = evaluationStatus.substring(0, 5);
		}
		this.evaluationStatus = evaluationStatus;
    }
    public java.lang.String getBidCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidCode).trim();
    }

    public void setBidCode(java.lang.String bidCode) {
		if (!HiltonUtility.isEmpty(bidCode) && bidCode.length() > 15) {
			bidCode = bidCode.substring(0, 15);
		}
		this.bidCode = bidCode;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqVendor) ) return false;
        RfqVendor castOther = (RfqVendor) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public List getRfqBidList() {
		return this.rfqBidList;
	}

	public void setRfqBidList(List rfqBidList) {
		this.rfqBidList = rfqBidList;
	}

    public java.math.BigDecimal getBidSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bidSubtotal);
    }

    public void setBidSubtotal(java.math.BigDecimal bidSubtotal) {
        this.bidSubtotal = bidSubtotal;
    }
    
    public java.lang.String getVendorId() {
		return this.getComp_id().getVendorId();
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
