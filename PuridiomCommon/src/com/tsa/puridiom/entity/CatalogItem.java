package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogItem implements Serializable {

    /** identifier field */
    private CatalogItemPK comp_id;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal cost;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private String imageFile;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private String taxable;

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
    private String fld1;

    /** nullable persistent field */
    private String fld2;

    /** nullable persistent field */
    private String fld3;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String kit;

    /** nullable persistent field */
    private String priceBrk;

    /** nullable persistent field */
    private String mfgName;

    /** nullable persistent field */
    private String modelNumber;

    /** nullable persistent field */
    private java.math.BigDecimal leadtime;

    /** nullable persistent field */
    private java.math.BigDecimal dscQty1;

    /** nullable persistent field */
    private java.math.BigDecimal dscCost1;

    /** nullable persistent field */
    private java.math.BigDecimal dscQty2;

    /** nullable persistent field */
    private java.math.BigDecimal dscCost2;

    /** nullable persistent field */
    private java.math.BigDecimal dscQty3;

    /** nullable persistent field */
    private java.math.BigDecimal dscCost3;

    /** nullable persistent field */
    private String umConv;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal stockEoq;

    /** nullable persistent field */
    private String itemRestricted;

    /** nullable persistent field */
    private java.math.BigDecimal minReqQty;

    /** nullable persistent field */
    private java.math.BigDecimal maxReqQty;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private java.math.BigDecimal stdCost;

    /** nullable persistent field */
    private String description;

    /** persistent field */
    private Catalog catalog;

    /** persistent field */
    private Set catalogComponents;

    /** persistent field */
    private Set catalogPriceBrks;

    /** nullable persistent field */
    private java.math.BigDecimal icAccount;

    /** nullable persistent field */
    private java.math.BigDecimal icLineComment;

    /** nullabel persistent field */
    private String shelfLifeRqd;

    /** full constructor */
    public CatalogItem(CatalogItemPK comp_id, java.lang.String umCode, java.lang.String commodity, java.math.BigDecimal cost, java.math.BigDecimal icText, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String source, java.lang.String imageFile, java.lang.String asset, java.lang.String taxable, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String type, java.lang.String kit, String priceBrk, java.lang.String mfgName, java.lang.String modelNumber, java.math.BigDecimal leadtime, java.math.BigDecimal dscQty1, java.math.BigDecimal dscCost1, java.math.BigDecimal dscQty2, java.math.BigDecimal dscCost2, java.math.BigDecimal dscQty3, java.math.BigDecimal dscCost3, java.lang.String umConv, java.math.BigDecimal umFactor, java.math.BigDecimal stockEoq, java.lang.String itemRestricted, java.math.BigDecimal minReqQty, java.math.BigDecimal maxReqQty, java.lang.String receiptRequired, java.math.BigDecimal stdCost, java.lang.String description, Catalog catalog, Set catalogComponents, Set catalogPriceBrks, java.math.BigDecimal icAccount, String fld1, String fld2, String fld3) {
        this.comp_id = comp_id;
        this.umCode = umCode;
        this.commodity = commodity;
        this.cost = cost;
        this.icText = icText;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.source = source;
        this.imageFile = imageFile;
        this.asset = asset;
        this.taxable = taxable;
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
        this.type = type;
        this.kit = kit;
        this.priceBrk = priceBrk;
        this.mfgName = mfgName;
        this.modelNumber = modelNumber;
        this.leadtime = leadtime;
        this.dscQty1 = dscQty1;
        this.dscCost1 = dscCost1;
        this.dscQty2 = dscQty2;
        this.dscCost2 = dscCost2;
        this.dscQty3 = dscQty3;
        this.dscCost3 = dscCost3;
        this.umConv = umConv;
        this.umFactor = umFactor;
        this.stockEoq = stockEoq;
        this.itemRestricted = itemRestricted;
        this.minReqQty = minReqQty;
        this.maxReqQty = maxReqQty;
        this.receiptRequired = receiptRequired;
        this.stdCost = stdCost;
        this.description = description;
        this.catalog = catalog;
        this.catalogComponents = catalogComponents;
        this.catalogPriceBrks = catalogPriceBrks;
        this.icAccount = icAccount;
        this.fld1 = fld1;
        this.fld2 = fld2;
        this.fld3 = fld3;
    }

    /** default constructor */
    public CatalogItem() {
    }

    public CatalogItem(CatalogItemPK comp_id)
    {
    	this.comp_id = comp_id;
    }

    /** minimal constructor */
    public CatalogItem(CatalogItemPK comp_id, Catalog catalog, Set catalogComponents, Set catalogPriceBrks) {
        this.comp_id = comp_id;
        this.catalog = catalog;
        this.catalogComponents = catalogComponents;
        this.catalogPriceBrks = catalogPriceBrks;
    }

    public CatalogItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CatalogItemPK comp_id) {
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

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.source).trim();
    }

    public void setSource(java.lang.String source) {
		if (!HiltonUtility.isEmpty(source) && source.length() > 20) {
			source = source.substring(0, 20);
		}
		this.source = source;
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

    public java.lang.String getAsset() {
		return (java.lang.String)HiltonUtility.ckNull(this.asset).trim();
    }

    public void setAsset(java.lang.String asset) {
		if (!HiltonUtility.isEmpty(asset) && asset.length() > 1) {
			asset = asset.substring(0, 1);
		}
		this.asset = asset;
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

    public java.lang.String getType() {
		return (java.lang.String)HiltonUtility.ckNull(this.type).trim();
    }

    public void setType(java.lang.String type) {
		if (!HiltonUtility.isEmpty(type) && type.length() > 3) {
			type = type.substring(0, 3);
		}
		this.type = type;
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

    public java.math.BigDecimal getLeadtime() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.leadtime);
    }

    public void setLeadtime(java.math.BigDecimal leadtime) {
        this.leadtime = leadtime;
    }

    public java.math.BigDecimal getDscQty1() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscQty1);
    }

    public void setDscQty1(java.math.BigDecimal dscQty1) {
        this.dscQty1 = dscQty1;
    }

    public java.math.BigDecimal getDscCost1() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscCost1);
    }

    public void setDscCost1(java.math.BigDecimal dscCost1) {
        this.dscCost1 = dscCost1;
    }

    public java.math.BigDecimal getDscQty2() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscQty2);
    }

    public void setDscQty2(java.math.BigDecimal dscQty2) {
        this.dscQty2 = dscQty2;
    }

    public java.math.BigDecimal getDscCost2() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscCost2);
    }

    public void setDscCost2(java.math.BigDecimal dscCost2) {
        this.dscCost2 = dscCost2;
    }

    public java.math.BigDecimal getDscQty3() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscQty3);
    }

    public void setDscQty3(java.math.BigDecimal dscQty3) {
        this.dscQty3 = dscQty3;
    }

    public java.math.BigDecimal getDscCost3() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dscCost3);
    }

    public void setDscCost3(java.math.BigDecimal dscCost3) {
        this.dscCost3 = dscCost3;
    }

    public java.lang.String getUmConv() {
		return (java.lang.String)HiltonUtility.ckNull(this.umConv).trim();
    }

    public void setUmConv(java.lang.String umConv) {
		if (!HiltonUtility.isEmpty(umConv) && umConv.length() > 15) {
			umConv = umConv.substring(0, 15);
		}
		this.umConv = umConv;
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    public java.math.BigDecimal getStockEoq() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.stockEoq);
    }

    public void setStockEoq(java.math.BigDecimal stockEoq) {
        this.stockEoq = stockEoq;
    }

    public java.lang.String getItemRestricted() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemRestricted).trim();
    }

    public void setItemRestricted(java.lang.String itemRestricted) {
		if (!HiltonUtility.isEmpty(itemRestricted) && itemRestricted.length() > 1) {
			itemRestricted = itemRestricted.substring(0, 1);
		}
		this.itemRestricted = itemRestricted;
    }

    public java.math.BigDecimal getMinReqQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.minReqQty);
    }

    public void setMinReqQty(java.math.BigDecimal minReqQty) {
        this.minReqQty = minReqQty;
    }

    public java.math.BigDecimal getMaxReqQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxReqQty);
    }

    public void setMaxReqQty(java.math.BigDecimal maxReqQty) {
        this.maxReqQty = maxReqQty;
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

    public java.math.BigDecimal getStdCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.stdCost);
    }

    public void setStdCost(java.math.BigDecimal stdCost) {
        this.stdCost = stdCost;
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

    public Catalog getCatalog() {
        return this.catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public java.util.Set getCatalogComponents() {
        return this.catalogComponents;
    }

    public void setCatalogComponents(java.util.Set catalogComponents) {
        this.catalogComponents = catalogComponents;
    }

    public java.util.Set getCatalogPriceBrks() {
        return this.catalogPriceBrks;
    }

    public void setCatalogPriceBrks(java.util.Set catalogPriceBrks) {
        this.catalogPriceBrks = catalogPriceBrks;
    }

    public java.math.BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
    }

    public void setIcAccount(java.math.BigDecimal icAccount) {
        this.icAccount = icAccount;
    }

    /**
	 * @return the icLineComment
	 */
	public java.math.BigDecimal getIcLineComment()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icLineComment);
	}

	/**
	 * @param icLineComment the icLineComment to set
	 */
	public void setIcLineComment(java.math.BigDecimal icLineComment)
	{
		this.icLineComment = icLineComment;
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

	public String getFld1() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld1).trim();
	}

	public void setFld1(String fld1) {
		if (!HiltonUtility.isEmpty(fld1) && fld1.length() > 15) {
            fld1 = fld1.substring(0, 15);
        }
        this.fld1 = fld1;
	}

	public String getFld2() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld2).trim();
	}

	public void setFld2(String fld2) {
		if (!HiltonUtility.isEmpty(fld2) && fld2.length() > 15) {
            fld2 = fld2.substring(0, 15);
        }
        this.fld2 = fld2;
	}

	public String getFld3() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld3).trim();
	}

	public void setFld3(String fld3) {
		if (!HiltonUtility.isEmpty(fld3) && fld3.length() > 15) {
            fld3 = fld3.substring(0, 15);
        }
        this.fld3 = fld3;
	}

	public String getShelfLifeRqd() {
		return (String) HiltonUtility.ckNull(this.shelfLifeRqd).trim();
	}

	public void setShelfLifeRqd(String shelfLifeRqd) {
		if (!HiltonUtility.isEmpty(shelfLifeRqd) && shelfLifeRqd.length() > 1) {
			shelfLifeRqd = shelfLifeRqd.substring(0, 1);
		}
		this.shelfLifeRqd = shelfLifeRqd;
	}

}
