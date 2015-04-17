package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvBinLocHistory implements Serializable {

    /** nullable persistent field */
    private String itemNumber ;

    /** nullable persistent field */
    private String itemLocation ;

    /** identifier field */
    private java.math.BigDecimal icCode;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String manufactNo;

    /** nullable persistent field */
    private String lot;

    /** nullable persistent field */
    private java.math.BigDecimal cost;

    /** nullable persistent field */
    private java.util.Date itemDate;

    /** nullable persistent field */
    private String aisle;

    /** nullable persistent field */
    private String locrow;

    /** nullable persistent field */
    private String tier;

    /** nullable persistent field */
    private String bin;

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
    private java.math.BigDecimal qtyOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal qtyAlloc;

    /** nullable persistent field */
    private String actionCode;

    /** nullable persistent field */
    private java.math.BigDecimal qtyMoved;

    /** nullable persistent field */
    private String histText;

    /** nullable persistent field */
    private java.util.Date transactionDate;

    /** nullable persistent field */
    private String transactionTime;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String reasonCode;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbLine;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal icRc;

    /** full constructor */
    public InvBinLocHistory(java.lang.String itemNumber, java.lang.String itemLocation, java.math.BigDecimal icCode, java.lang.String vendorId, java.lang.String manufactNo, java.lang.String lot, java.math.BigDecimal cost, java.util.Date itemDate, java.lang.String aisle, java.lang.String locrow, java.lang.String tier, java.lang.String bin, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.math.BigDecimal qtyOnHand, java.math.BigDecimal qtyAlloc, java.lang.String actionCode, java.math.BigDecimal qtyMoved, java.lang.String histText, java.util.Date transactionDate, java.lang.String transactionTime, java.lang.String userId, java.lang.String reasonCode, java.math.BigDecimal icPoHeader, java.math.BigDecimal icDsbHeader, java.math.BigDecimal icDsbLine, java.math.BigDecimal duomQtyOnHand, java.math.BigDecimal duomQtyAlloc, java.math.BigDecimal icRc) {
    	this.itemNumber = itemNumber ;
    	this.itemLocation = itemLocation ;
        this.icCode = icCode;
        this.vendorId = vendorId;
        this.manufactNo = manufactNo;
        this.lot = lot;
        this.cost = cost;
        this.itemDate = itemDate;
        this.aisle = aisle;
        this.locrow = locrow;
        this.tier = tier;
        this.bin = bin;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.qtyOnHand = qtyOnHand;
        this.qtyAlloc = qtyAlloc;
        this.actionCode = actionCode;
        this.qtyMoved = qtyMoved;
        this.histText = histText;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.userId = userId;
        this.reasonCode = reasonCode;
        this.icPoHeader = icPoHeader;
        this.icDsbHeader = icDsbHeader;
        this.icDsbLine = icDsbLine;
        this.duomQtyOnHand = duomQtyOnHand;
        this.duomQtyAlloc = duomQtyAlloc;
        this.icRc = icRc ;
    }

    /** default constructor */
    public InvBinLocHistory() {
    }

    public String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(itemNumber);
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(itemLocation);
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	/** minimal constructor */
    public InvBinLocHistory(java.math.BigDecimal icCode) {
        this.icCode = icCode;
    }

    public java.math.BigDecimal getIcCode() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icCode);
    }

    public void setIcCode(java.math.BigDecimal icCode) {
        this.icCode = icCode;
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

    public java.lang.String getManufactNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.manufactNo).trim();
    }

    public void setManufactNo(java.lang.String manufactNo) {
		if (!HiltonUtility.isEmpty(manufactNo) && manufactNo.length() > 20) {
			manufactNo = manufactNo.substring(0, 20);
		}
		this.manufactNo = manufactNo;
    }

    public java.lang.String getLot() {
		return (java.lang.String)HiltonUtility.ckNull(this.lot).trim();
    }

    public void setLot(java.lang.String lot) {
		if (!HiltonUtility.isEmpty(lot) && lot.length() > 15) {
			lot = lot.substring(0, 15);
		}
		this.lot = lot;
    }

    public java.math.BigDecimal getCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.cost);
    }

    public void setCost(java.math.BigDecimal cost) {
        this.cost = cost;
    }

    public java.util.Date getItemDate() {
		return this.itemDate;
//		return HiltonUtility.ckNull(this.itemDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.itemDate);
    }

    public void setItemDate(java.util.Date itemDate) {
        this.itemDate = itemDate;
    }

    public java.lang.String getAisle() {
		return (java.lang.String)HiltonUtility.ckNull(this.aisle).trim();
    }

    public void setAisle(java.lang.String aisle) {
		if (!HiltonUtility.isEmpty(aisle) && aisle.length() > 15) {
			aisle = aisle.substring(0, 15);
		}
		this.aisle = aisle;
    }

    public java.lang.String getLocrow() {
		return (java.lang.String)HiltonUtility.ckNull(this.locrow).trim();
    }

    public void setLocrow(java.lang.String locrow) {
		if (!HiltonUtility.isEmpty(locrow) && locrow.length() > 15) {
			locrow = locrow.substring(0, 15);
		}
		this.locrow = locrow;
    }

    public java.lang.String getTier() {
		return (java.lang.String)HiltonUtility.ckNull(this.tier).trim();
    }

    public void setTier(java.lang.String tier) {
		if (!HiltonUtility.isEmpty(tier) && tier.length() > 15) {
			tier = tier.substring(0, 15);
		}
		this.tier = tier;
    }

    public java.lang.String getBin() {
		return (java.lang.String)HiltonUtility.ckNull(this.bin).trim();
    }

    public void setBin(java.lang.String bin) {
		if (!HiltonUtility.isEmpty(bin) && bin.length() > 15) {
			bin = bin.substring(0, 15);
		}
		this.bin = bin;
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

    public java.math.BigDecimal getQtyOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyOnHand);
    }

    public void setQtyOnHand(java.math.BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public java.math.BigDecimal getQtyAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyAlloc);
    }

    public void setQtyAlloc(java.math.BigDecimal qtyAlloc) {
        this.qtyAlloc = qtyAlloc;
    }

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode).trim();
    }

    public void setActionCode(java.lang.String actionCode) {
		if (!HiltonUtility.isEmpty(actionCode) && actionCode.length() > 15) {
			actionCode = actionCode.substring(0, 15);
		}
		this.actionCode = actionCode;
    }

    public java.math.BigDecimal getQtyMoved() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyMoved);
    }

    public void setQtyMoved(java.math.BigDecimal qtyMoved) {
        this.qtyMoved = qtyMoved;
    }

    public java.lang.String getHistText() {
		return (java.lang.String)HiltonUtility.ckNull(this.histText).trim();
    }

    public void setHistText(java.lang.String histText) {
		if (!HiltonUtility.isEmpty(histText) && histText.length() > 60) {
			histText = histText.substring(0, 60);
		}
		this.histText = histText;
    }

    public java.util.Date getTransactionDate() {
		return this.transactionDate;
//		return HiltonUtility.ckNull(this.transactionDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.transactionDate);
    }

    public void setTransactionDate(java.util.Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public java.lang.String getTransactionTime() {
		return (java.lang.String)HiltonUtility.ckNull(this.transactionTime).trim();
    }

    public void setTransactionTime(java.lang.String transactionTime) {
		if (!HiltonUtility.isEmpty(transactionTime) && transactionTime.length() > 12) {
			transactionTime = transactionTime.substring(0, 12);
		}
		this.transactionTime = transactionTime;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
		if (!HiltonUtility.isEmpty(userId) && userId.length() > 15) {
			userId = userId.substring(0, 15);
		}
		this.userId = userId;
    }

    public java.lang.String getReasonCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.reasonCode).trim();
    }

    public void setReasonCode(java.lang.String reasonCode) {
		if (!HiltonUtility.isEmpty(reasonCode) && reasonCode.length() > 15) {
			reasonCode = reasonCode.substring(0, 15);
		}
		this.reasonCode = reasonCode;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcDsbHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbHeader);
    }

    public void setIcDsbHeader(java.math.BigDecimal icDsbHeader) {
        this.icDsbHeader = icDsbHeader;
    }

    public java.math.BigDecimal getIcDsbLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbLine);
    }

    public void setIcDsbLine(java.math.BigDecimal icDsbLine) {
        this.icDsbLine = icDsbLine;
    }

    public java.math.BigDecimal getDuomQtyOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(duomQtyOnHand);
	}

	public void setDuomQtyOnHand(java.math.BigDecimal duomQtyOnHand) {
		this.duomQtyOnHand = duomQtyOnHand;
	}

	public java.math.BigDecimal getDuomQtyAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(duomQtyAlloc);
	}

	public void setDuomQtyAlloc(java.math.BigDecimal duomQtyAlloc) {
		this.duomQtyAlloc = duomQtyAlloc;
	}

	public java.math.BigDecimal getIcRc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(icRc);
	}

	public void setIcRc(java.math.BigDecimal icRc) {
		this.icRc = icRc;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("icCode", getIcCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvBinLocHistory) ) return false;
        InvBinLocHistory castOther = (InvBinLocHistory) other;
        return new EqualsBuilder()
            .append(this.getIcCode(), castOther.getIcCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcCode())
            .toHashCode();
    }

}
