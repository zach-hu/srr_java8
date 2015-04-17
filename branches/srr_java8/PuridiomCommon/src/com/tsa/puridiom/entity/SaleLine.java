package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.List;

/** @author Hibernate CodeGenerator */
public class SaleLine implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icSaleLine;

    /** nullable persistent field */
    private java.math.BigDecimal icSaleHeader;

    /** nullable persistent field */
    private String saleNumber;

    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal icSource;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private String catalogId;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private String mfgName;

    /** nullable persistent field */
    private String modelNumber;

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
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String taxCode;

    private List docCommentList;

	private List saleBidList;

	private String highestBuyer;

	private java.math.BigDecimal highestBid;

	private java.math.BigDecimal averageBid;

    /** full constructor */
    public SaleLine(java.math.BigDecimal icSaleLine, java.math.BigDecimal icSaleHeader, java.lang.String saleNumber, java.lang.String source, java.lang.String itemNumber, java.lang.String umCode, java.math.BigDecimal quantity, java.lang.String taxable, java.lang.String status, java.lang.String commodity, java.math.BigDecimal icSource, java.lang.String asset, java.lang.String catalogId, java.math.BigDecimal umFactor, java.lang.String mfgName, java.lang.String modelNumber, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.math.BigDecimal icLineHistory, java.lang.String itemLocation, java.lang.String description, java.lang.String taxCode) {
        this.icSaleLine = icSaleLine;
        this.icSaleHeader = icSaleHeader;
        this.saleNumber = saleNumber;
        this.source = source;
        this.itemNumber = itemNumber;
        this.umCode = umCode;
        this.quantity = quantity;
        this.taxable = taxable;
        this.status = status;
        this.commodity = commodity;
        this.icSource = icSource;
        this.asset = asset;
        this.catalogId = catalogId;
        this.umFactor = umFactor;
        this.mfgName = mfgName;
        this.modelNumber = modelNumber;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.icLineHistory = icLineHistory;
        this.itemLocation = itemLocation;
        this.description = description;
        this.taxCode = taxCode;
    }

    /** default constructor */
    public SaleLine() {
    }

    /** minimal constructor */
    public SaleLine(java.math.BigDecimal icSaleLine) {
        this.icSaleLine = icSaleLine;
    }

    public java.math.BigDecimal getIcSaleLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icSaleLine);
    }

    public void setIcSaleLine(java.math.BigDecimal icSaleLine) {
        this.icSaleLine = icSaleLine;
    }

    public java.math.BigDecimal getIcSaleHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icSaleHeader);
    }

    public void setIcSaleHeader(java.math.BigDecimal icSaleHeader) {
        this.icSaleHeader = icSaleHeader;
    }

    public java.lang.String getSaleNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.saleNumber).trim();
    }

    public void setSaleNumber(java.lang.String saleNumber) {
		if (!HiltonUtility.isEmpty(saleNumber) && saleNumber.length() > 20) {
			saleNumber = saleNumber.substring(0, 20);
		}
		this.saleNumber = saleNumber;
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

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber).trim();
    }

    public void setItemNumber(java.lang.String itemNumber) {
		if (!HiltonUtility.isEmpty(itemNumber) && itemNumber.length() > 30) {
			itemNumber = itemNumber.substring(0, 30);
		}
		this.itemNumber = itemNumber;
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

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
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

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
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

    public java.math.BigDecimal getIcSource() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icSource);
    }

    public void setIcSource(java.math.BigDecimal icSource) {
        this.icSource = icSource;
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

    public java.lang.String getCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogId).trim();
    }

    public void setCatalogId(java.lang.String catalogId) {
		if (!HiltonUtility.isEmpty(catalogId) && catalogId.length() > 15) {
			catalogId = catalogId.substring(0, 15);
		}
		this.catalogId = catalogId;
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    public java.lang.String getMfgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgName).trim();
    }

    public void setMfgName(java.lang.String mfgName) {
		if (!HiltonUtility.isEmpty(mfgName) && mfgName.length() > 60) {
			mfgName = mfgName.substring(0, 60);
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

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

    public java.lang.String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
    }

    public void setItemLocation(java.lang.String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
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

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

	public List getDocCommentList() {
		return this.docCommentList;
	}

	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

	public List getSaleBidList() {
		return this.saleBidList;
	}

	public void setSaleBidList(List saleBidList) {
		this.saleBidList = saleBidList;
	}

	public String getHighestVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.highestBuyer).trim();
	}

	public void setHighestVendorId(String highestBuyer) {
		this.highestBuyer = highestBuyer;
	}

	public java.math.BigDecimal getHighestBid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.highestBid);
	}

	public void setLowestBid(java.math.BigDecimal highestBid) {
		this.highestBid = highestBid;
	}

	public java.math.BigDecimal getAverageBid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.averageBid);
	}

	public void setAverageBid(java.math.BigDecimal averageBid) {
		this.averageBid = averageBid;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icSaleLine", getIcSaleLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SaleLine) ) return false;
        SaleLine castOther = (SaleLine) other;
        return new EqualsBuilder()
            .append(this.getIcSaleLine(), castOther.getIcSaleLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcSaleLine())
            .toHashCode();
    }
}
