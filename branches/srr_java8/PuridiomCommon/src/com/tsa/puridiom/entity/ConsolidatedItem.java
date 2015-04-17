package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ConsolidatedItem implements Serializable {

    /** identifier field */
    private ConsolidatedItemPK comp_id;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal cost;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.math.BigDecimal icText;
    
    /** nullable persistent field */
    private String mfgName;

    /** nullable persistent field */
    private String modelNumber;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String imageFile;

    /** nullable persistent field */
    private String kit;

    /** nullable persistent field */
    private String priceBrk;

    /** nullable persistent field */
    private String source;

    /** full constructor */
    public ConsolidatedItem(java.lang.String umCode, java.lang.String commodity, java.math.BigDecimal cost, java.lang.String currencyCode, java.lang.String status, java.math.BigDecimal icText, java.lang.String mfgName, java.lang.String modelNumber, java.lang.String description, java.lang.String imageFile, java.lang.String kit, java.lang.String priceBrk, java.lang.String source) {
        this.umCode = umCode;
        this.commodity = commodity;
        this.cost = cost;
        this.currencyCode = currencyCode;
        this.status = status;
        this.icText = icText;
        this.mfgName = mfgName;
        this.modelNumber = modelNumber;
        this.description = description;
        this.imageFile = imageFile;
        this.kit = kit;
        this.priceBrk = priceBrk;
        this.source = source;
    }

    /** default constructor */
    public ConsolidatedItem() {
    }

    public ConsolidatedItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(ConsolidatedItemPK comp_id) {
        this.comp_id = comp_id;
    }

	public java.lang.String getUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.umCode).trim();
	}
	
	public void setUmCode(java.lang.String umCode) {
		if (!HiltonUtility.isEmpty(umCode) && umCode.length() > 15) {
			umCode = umCode.substring(0, 15);
		}
		this.umCode = umCode;
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
	
	public java.math.BigDecimal getCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.cost);
	}
	
	public void setCost(java.math.BigDecimal cost) {
	    this.cost = cost;
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
	
	public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
	}
	
	public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
	}
	
	public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
	}
	
	public void setIcText(java.math.BigDecimal icText) {
	    this.icText = icText;
	}
	
	
	public java.lang.String getMfgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgName).trim();
	}
	
	public void setMfgName(java.lang.String mfgName) {
		if (!HiltonUtility.isEmpty(mfgName) && mfgName.length() > 25) {
			mfgName = mfgName.substring(0, 25);
		}
		this.mfgName = mfgName;
	}
	
	public java.lang.String getModelNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.modelNumber).trim();
	}
	
	public void setModelNumber(java.lang.String modelNumber) {
		if (!HiltonUtility.isEmpty(modelNumber) && modelNumber.length() > 20) {
			modelNumber = modelNumber.substring(0, 20);
		}
		this.modelNumber = modelNumber;
	}
	
	public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
	}
	
	public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
			description = description.substring(0, 2000);
		}
		this.description = description;
	}
	public java.lang.String getImageFile() {
		return (java.lang.String)HiltonUtility.ckNull(this.imageFile).trim();
	}
	
	public void setImageFile(java.lang.String imageFile) {
		if (!HiltonUtility.isEmpty(imageFile) && imageFile.length() > 255) {
			imageFile = imageFile.substring(0, 255);
		}
		this.imageFile = imageFile;
	}
	
	public java.lang.String getKit() {
		return (java.lang.String)HiltonUtility.ckNull(this.kit).trim();
	}
	
	public void setKit(java.lang.String kit) {
		if (!HiltonUtility.isEmpty(kit) && kit.length() > 1) {
			kit = kit.substring(0, 1);
		}
		this.kit = kit;
	}
	
	public java.lang.String getPriceBrk() {
		return (java.lang.String)HiltonUtility.ckNull(this.priceBrk).trim();
	}
	
	public void setPriceBrk(java.lang.String priceBrk) {
		if (!HiltonUtility.isEmpty(priceBrk) && priceBrk.length() > 1) {
			priceBrk = priceBrk.substring(0, 1);
		}
		this.priceBrk = priceBrk;
	}
	
	public java.lang.String getSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.source).trim();
	}
	
	public void setSource(java.lang.String source) {
		if (!HiltonUtility.isEmpty(source) && source.length() > 3) {
			source = source.substring(0, 3);
		}
		this.source = source;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogItem) ) return false;
        CatalogItem castOther = (CatalogItem) other;
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
