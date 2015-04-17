package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PackagePricing implements Serializable {
    /** identifier field */
    private java.math.BigDecimal icPackage;

    /** nullable persistent field */
    private String packageName;

    /** nullable persistent field */
    private String packageDescription;

    /** nullable persistent field */
    private BigDecimal packagePrice;

    /** nullable persistent field */
    private BigDecimal packageSavings;

    /** nullable persistent field */
    private BigDecimal packageDiscount;

    /** nullable persistent field */
    private BigDecimal buyerCount;

    /** nullable persistent field */
    private BigDecimal requisitionerCount;
    
    /** nullable persistent field */
    private String packageType;
    
    /** full constructor */
    public PackagePricing(java.math.BigDecimal icPackage, java.lang.String packageName, java.lang.String packageDescription, java.math.BigDecimal packagePrice, java.math.BigDecimal packageSavings, java.math.BigDecimal packageDiscount, java.math.BigDecimal buyerCount, java.math.BigDecimal requisitionerCount, java.lang.String packageType) {
        this.icPackage = icPackage;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.packagePrice = packageSavings;
        this.packageSavings = packageSavings;
        this.packageDiscount = packageDiscount;
        this.buyerCount = buyerCount;
        this.requisitionerCount = requisitionerCount;
        this.packageType = packageType;
    }

    /** default constructor */
    public PackagePricing() {
    }

    /** minimal constructor */
    public PackagePricing(java.math.BigDecimal icPackage) {
        this.icPackage = icPackage;
    }

    public java.math.BigDecimal getIcPackage() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPackage);
    }

    public void setIcPackage(java.math.BigDecimal icPackage) {
        this.icPackage = icPackage;
    }

    public java.lang.String getPackageName() {
		return (java.lang.String)HiltonUtility.ckNull(this.packageName).trim();
    }
    
    public void setPackageName(java.lang.String packageName) {
		if (!HiltonUtility.isEmpty(packageName) && packageName.length() > 30) {
			packageName = packageName.substring(0, 30);
		}
		this.packageName = packageName;
    }

    public java.lang.String getPackageDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.packageDescription).trim();
    }

    public void setPackageDescription(java.lang.String packageDescription) {
		if (!HiltonUtility.isEmpty(packageDescription) && packageDescription.length() > 250) {
			packageDescription = packageDescription.substring(0, 250);
		}
		this.packageDescription = packageDescription;
    }

	public BigDecimal getPackagePrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.packagePrice);
	}

	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}

	public BigDecimal getPackageSavings() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.packageSavings);
	}

	public void setPackageSavings(BigDecimal packageSavings) {
		this.packageSavings = packageSavings;
	}

	public BigDecimal getPackageDiscount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.packageDiscount);
	}

	public void setPackageDiscount(BigDecimal packageDiscount) {
		this.packageDiscount = packageDiscount;
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

    public java.lang.String getPackageType() {
		return (java.lang.String)HiltonUtility.ckNull(this.packageType).trim();
    }
    
    public void setPackageType(java.lang.String packageType) {
		if (!HiltonUtility.isEmpty(packageType) && packageType.length() > 1) {
			packageType = packageType.substring(0, 1);
		}
		this.packageType = packageType;
    }
    
    public BigDecimal getProratedPrice(BigDecimal subscriptionDays, BigDecimal previouslyPaidAmount) {
    	BigDecimal proratedPrice = new BigDecimal(0);
    	
    	try {
    		BigDecimal dailyRate = new BigDecimal(0);
    		
    		if (this.getPackageType().equals("G") && previouslyPaidAmount.compareTo(this.getPackagePrice()) < 0) {
				BigDecimal upgradeAmount = this.getPackagePrice().subtract(previouslyPaidAmount);
				dailyRate = upgradeAmount.divide(new BigDecimal(365));				
			} else {
    			// Previously Paid Amount only applies to group package being upgraded
    			dailyRate = this.getPackagePrice().divide(new BigDecimal(365));
    		}

    		proratedPrice = dailyRate.multiply(subscriptionDays);

    	} catch (Exception e) {
    		Log.error(this, e.getMessage());
    	}
    	
    	return proratedPrice;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("icPackage: " + getIcPackage() + "; packageName: " + getPackageName())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PackagePricing) ) return false;
        PackagePricing castOther = (PackagePricing) other;
        return new EqualsBuilder()
            .append(this.getIcPackage(), castOther.getIcPackage())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPackage())
            .toHashCode();
    }
}