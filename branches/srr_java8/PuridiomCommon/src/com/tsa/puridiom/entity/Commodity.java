package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Commodity implements Serializable {

    /** identifier field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal variance;

    /** nullable persistent field */
    private String failsafe;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String buyerCode;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private java.math.BigDecimal vchVariance;

    /** nullable persistent field */
    private String vchFailsafe;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private java.math.BigDecimal depreciationTerm;

    /** nullable persistent field */
    private java.math.BigDecimal icAccount;

    /** nullable persistent field */
    private String nigplevel;

    /** nullable persistent field */
    private String level1;

    /** nullable persistent field */
    private String level2;

    /** nullable persistent field */
    private String level3;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private String commodityType;

    /** nullable persistent field */
    private String duomRequired;

    /** nullable persistent field */
    private java.math.BigDecimal iclLevel;

    /** nullable persistent field */
    private String serialNumbersRequired;
    
    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private String taxable;
    
    /** full constructor */
    public Commodity(java.lang.String commodity, java.math.BigDecimal variance, java.lang.String failsafe, java.lang.String description, java.lang.String buyerCode, java.lang.String status, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String lastChgBy, java.util.Date lastChgDate, java.math.BigDecimal vchVariance, java.lang.String vchFailsafe, java.lang.String asset, java.math.BigDecimal depreciationTerm, java.math.BigDecimal icAccount, java.lang.String nigplevel, java.lang.String level1, java.lang.String level2, java.lang.String level3, java.lang.String taxCode, java.lang.String commodityType, java.math.BigDecimal iclLevel, String receiptRequired, String taxable) {
        this.commodity = commodity;
        this.variance = variance;
        this.failsafe = failsafe;
        this.description = description;
        this.buyerCode = buyerCode;
        this.status = status;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.lastChgBy = lastChgBy;
        this.lastChgDate = lastChgDate;
        this.vchVariance = vchVariance;
        this.vchFailsafe = vchFailsafe;
        this.asset = asset;
        this.depreciationTerm = depreciationTerm;
        this.icAccount = icAccount;
        this.nigplevel = nigplevel;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.taxCode = taxCode;
        this.commodityType = commodityType;
        this.iclLevel = iclLevel;
        this.receiptRequired = receiptRequired;
        this.taxable = taxable;
    }

    /** default constructor */
    public Commodity() {
    }

    /** minimal constructor */
    public Commodity(java.lang.String commodity) {
        this.commodity = commodity;
    }

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
    }

    public java.math.BigDecimal getVariance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.variance);
    }

    public void setVariance(java.math.BigDecimal variance) {
        this.variance = variance;
    }

    public java.lang.String getFailsafe() {
		return (java.lang.String)HiltonUtility.ckNull(this.failsafe).trim();
    }

    public void setFailsafe(java.lang.String failsafe) {
		if (!HiltonUtility.isEmpty(failsafe) && failsafe.length() > 1) {
			failsafe = failsafe.substring(0, 1);
		}
		this.failsafe = failsafe;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode).trim();
    }

    public void setBuyerCode(java.lang.String buyerCode) {
		if (!HiltonUtility.isEmpty(buyerCode) && buyerCode.length() > 15) {
			buyerCode = buyerCode.substring(0, 15);
		}
		this.buyerCode = buyerCode;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 15) {
			lastChgBy = lastChgBy.substring(0, 15);
		}
		this.lastChgBy = lastChgBy;
    }

    public java.util.Date getLastChgDate() {
		return this.lastChgDate;
//		return HiltonUtility.ckNull(this.lastChgDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastChgDate);
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.math.BigDecimal getVchVariance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.vchVariance);
    }

    public void setVchVariance(java.math.BigDecimal vchVariance) {
        this.vchVariance = vchVariance;
    }

    public java.lang.String getVchFailsafe() {
		return (java.lang.String)HiltonUtility.ckNull(this.vchFailsafe).trim();
    }

    public void setVchFailsafe(java.lang.String vchFailsafe) {
		if (!HiltonUtility.isEmpty(vchFailsafe) && vchFailsafe.length() > 1) {
			vchFailsafe = vchFailsafe.substring(0, 1);
		}
		this.vchFailsafe = vchFailsafe;
    }

    public java.lang.String getAsset() {
		return (java.lang.String)HiltonUtility.ckNull(this.asset).trim();
    }

    public void setAsset(java.lang.String asset) {
		if (!HiltonUtility.isEmpty(asset) && asset.length() > 1) {
			asset = asset.substring(0, 1);
		}
		this.asset = asset;
    }

    public java.math.BigDecimal getDepreciationTerm() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.depreciationTerm);
    }

    public void setDepreciationTerm(java.math.BigDecimal depreciationTerm) {
        this.depreciationTerm = depreciationTerm;
    }

    public java.math.BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
    }

    public void setIcAccount(java.math.BigDecimal icAccount) {
        this.icAccount = icAccount;
    }

    public java.lang.String getNigplevel() {
		return (java.lang.String)HiltonUtility.ckNull(this.nigplevel).trim();
    }

    public void setNigplevel(java.lang.String nigplevel) {
		if (!HiltonUtility.isEmpty(nigplevel) && nigplevel.length() > 1) {
		    nigplevel = nigplevel.substring(0, 1);
		}
		this.nigplevel = nigplevel;
    }

    public java.lang.String getLevel1() {
		return (java.lang.String)HiltonUtility.ckNull(this.level1).trim();
    }

    public void setLevel1(java.lang.String level1) {
		if (!HiltonUtility.isEmpty(level1) && level1.length() > 15) {
		    level1 = level1.substring(0, 15);
		}
		this.level1 = level1;
    }

    public java.lang.String getLevel2() {
		return (java.lang.String)HiltonUtility.ckNull(this.level2).trim();
    }

    public void setLevel2(java.lang.String level2) {
		if (!HiltonUtility.isEmpty(level2) && level2.length() > 15) {
		    level2 = level2.substring(0, 15);
		}
		this.level2 = level2;
    }

    public java.lang.String getLevel3() {
		return (java.lang.String)HiltonUtility.ckNull(this.level3).trim();
    }

    public void setLevel3(java.lang.String level3) {
		if (!HiltonUtility.isEmpty(level3) && level3.length() > 15) {
		    level3 = level3.substring(0, 15);
		}
		this.level3 = level3;
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

    public java.lang.String getCommodityType() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodityType).trim();
    }

    public void setCommodityType(java.lang.String commodityType) {
		if (!HiltonUtility.isEmpty(commodityType) && commodityType.length() > 2) {
			commodityType = commodityType.substring(0, 2);
		}
		this.commodityType = commodityType;
    }

    public String getDuomRequired() {
    	return (java.lang.String)HiltonUtility.ckNull(this.duomRequired).trim();
	}

    public void setDuomRequired(String duomRequired) {
    	if (!HiltonUtility.isEmpty(duomRequired) && duomRequired.length() > 1) {
    		duomRequired = duomRequired.substring(0, 1);
		}
    	this.duomRequired = duomRequired;
	}

    public java.math.BigDecimal getIclLevel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclLevel);
    }

    public void setIclLevel(java.math.BigDecimal iclLevel) {
        this.iclLevel = iclLevel;
    }
    
    public java.lang.String getReceiptRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptRequired).trim();
    }

    public void setReceiptRequired(java.lang.String receiptRequired) {
		if (!HiltonUtility.isEmpty(receiptRequired) && receiptRequired.length() > 1) {
			receiptRequired = receiptRequired.substring(0, 1);
		}
		this.receiptRequired = receiptRequired;
    }

    public String getSerialNumbersRequired() {
    	return (java.lang.String)HiltonUtility.ckNull(this.serialNumbersRequired).trim();
	}

	public void setSerialNumbersRequired(String serialNumbersRequired) {
		if (!HiltonUtility.isEmpty(serialNumbersRequired) && serialNumbersRequired.length() > 1) {
			serialNumbersRequired = serialNumbersRequired.substring(0, 1);
		}
    	this.serialNumbersRequired = serialNumbersRequired;
	}
	
    public java.lang.String getTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxable).trim();
    }

    public void setTaxable(java.lang.String taxable) {
		if (!HiltonUtility.isEmpty(taxable) && taxable.length() > 1) {
			taxable = taxable.substring(0, 1);
		}
		this.taxable = taxable;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("commodity", getCommodity())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Commodity) ) return false;
        Commodity castOther = (Commodity) other;
        return new EqualsBuilder()
            .append(this.getCommodity(), castOther.getCommodity())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCommodity())
            .toHashCode();
    }
}
