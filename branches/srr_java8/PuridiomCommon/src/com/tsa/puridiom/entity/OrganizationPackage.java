package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrganizationPackage implements Serializable {

	/** identifier field */
    private java.math.BigDecimal icOrgPackage;

    /** nullable persistent field */
    private java.lang.String organizationId;

    /** nullable persistent field */
    private java.math.BigDecimal icPackage;

    /** nullable persistent field */
    private String packageType;

    /** nullable persistent field */
    private BigDecimal unitPrice;

    /** nullable persistent field */
    private BigDecimal quantity;

    /** nullable persistent field */
    private BigDecimal total;

    /** nullable persistent field */
    private BigDecimal buyerCount;

    /** nullable persistent field */
    private BigDecimal requisitionerCount;

    /** nullable persistent field */
    private java.util.Date datePurchased;

    /** nullable persistent field */
    private java.util.Date datePaid;

    /** nullable persistent field */
    private String purchasedBy;

    /** nullable persistent field */
    private String transactionId;
    
    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateActive;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** full constructor */
    public OrganizationPackage(java.math.BigDecimal icOrgPackage, java.lang.String organizationId, java.math.BigDecimal icPackage, java.lang.String packageType, java.math.BigDecimal unitPrice, java.math.BigDecimal quantity, java.math.BigDecimal total, java.math.BigDecimal buyerCount, java.math.BigDecimal requisitionerCount, java.util.Date datePurchased, java.util.Date datePaid, java.lang.String purchasedBy, java.lang.String transactionId, java.lang.String status, java.util.Date dateActive, java.util.Date dateExpires) {
        this.icOrgPackage = icOrgPackage;
        this.organizationId = organizationId;
        this.icPackage = icPackage;
        this.packageType = packageType;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.buyerCount = buyerCount;
        this.requisitionerCount = requisitionerCount;
        this.datePurchased = datePurchased;
        this.datePaid = datePaid;
        this.purchasedBy = purchasedBy;
        this.transactionId = transactionId;
        this.status = status;
        this.dateActive = dateActive;
        this.dateExpires = dateExpires;
    }

    /** default constructor */
    public OrganizationPackage() {
    }

    /** minimal constructor */
    public OrganizationPackage(java.math.BigDecimal icOrgPackage) {
        this.icOrgPackage = icOrgPackage;
    }
    
	public BigDecimal getIcOrgPackage() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icOrgPackage);
	}

	public void setIcOrgPackage(BigDecimal icOrgPackage) {
		this.icOrgPackage = icOrgPackage;
	}

	public String getOrganizationId() {
		return (java.lang.String)HiltonUtility.ckNull(this.organizationId).trim();
	}

	public void setOrganizationId(String organizationId) {
		if (!HiltonUtility.isEmpty(organizationId) && organizationId.length() > 15) {
			organizationId = organizationId.substring(0, 15);
		}
		this.organizationId = organizationId;
	}

	public BigDecimal getIcPackage() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPackage);
	}

	public void setIcPackage(BigDecimal icPackage) {
		this.icPackage = icPackage;
	}

	public String getPackageType() {
		return (java.lang.String)HiltonUtility.ckNull(this.packageType).trim();
	}

	public void setPackageType(String packageType) {
		if (!HiltonUtility.isEmpty(packageType) && packageType.length() > 1) {
			packageType = packageType.substring(0, 1);
		}
		this.packageType = packageType;
	}

	public BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.total);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getBuyerCount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.buyerCount);
	}

	public void setBuyerCount(BigDecimal buyerCount) {
		this.buyerCount = buyerCount;
	}

	public BigDecimal getRequisitionerCount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.requisitionerCount);
	}

	public void setRequisitionerCount(BigDecimal requisitionerCount) {
		this.requisitionerCount = requisitionerCount;
	}

	public String getPurchasedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.purchasedBy).trim();
	}

	public void setPurchasedBy(String purchasedBy) {
		if (!HiltonUtility.isEmpty(purchasedBy) && purchasedBy.length() > 60) {
			purchasedBy = purchasedBy.substring(0, 60);
		}
		this.purchasedBy = purchasedBy;
	}
	
    public java.util.Date getDatePurchased() {
        return this.datePurchased;
    }

    public void setDatePurchased(java.util.Date datePurchased) {
        this.datePurchased = datePurchased;
    }
	
    public java.util.Date getDatePaid() {
        return this.datePaid;
    }

    public void setDatePaid(java.util.Date datePaid) {
        this.datePaid = datePaid;
    }

	public String getTransactionId() {
		return (java.lang.String)HiltonUtility.ckNull(this.transactionId).trim();
	}

	public void setTransactionId(String transactionId) {
		if (!HiltonUtility.isEmpty(transactionId) && transactionId.length() > 30) {
			transactionId = transactionId.substring(0, 30);
		}
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
	}

	public void setStatus(String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
	}

    public java.util.Date getDateActive() {
        return this.dateActive;
    }

    public void setDateActive(java.util.Date dateActive) {
        this.dateActive = dateActive;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("icOrgPackage", getIcOrgPackage())
        .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PackagePricing) ) return false;
        OrganizationPackage castOther = (OrganizationPackage) other;
        return new EqualsBuilder()
        .append(this.getIcOrgPackage(), castOther.getIcOrgPackage())
        .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        	.append(getIcOrgPackage())
            .toHashCode();
    }
}