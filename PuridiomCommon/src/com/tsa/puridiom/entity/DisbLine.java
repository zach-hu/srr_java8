package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DisbLine implements Serializable
{
	private java.math.BigDecimal icRc;

    /** identifier field */
    private java.math.BigDecimal icDsbLine;

	private java.math.BigDecimal icDsbHeader;

    /** nullable persistent field */
    private java.math.BigDecimal lineNumber;

    /** nullable persistent field */
    private String disbNumber;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String itemSource;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private String commodityCode;

    /** nullable persistent field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String commentFlag;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbAccount;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal lineTotal;

    /** nullable persistent field */
    private String shiptoFlag;

    /** nullable persistent field */
    private String aisle;

    /** nullable persistent field */
    private String locrow;

    /** nullable persistent field */
    private String tier;

    /** nullable persistent field */
    private String bin;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String manufactNo;

    /** nullable persistent field */
    private String lot;

    /** nullable persistent field */
    private String locIc;

    /** nullable persistent field */
    private java.math.BigDecimal qtyBkord;

    /** nullable persistent field */
    private java.util.Date disbDate;

    /** nullable persistent field */
    private String assetCode;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private java.math.BigDecimal duomQuantity;

    /** nullable persistent field */
    private String duomUmCode;

    /** nullable persistent field */
    private String timeZone;

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

    private java.math.BigDecimal icAccount ;

    private List accountList;

    private List docCommentList;

    private List shipToList;

    /** nullable persistent field */
    private String departmentCode;

    private RequisitionLine reqLine;
    
    private RequisitionLine msrLine;
	/**
	 * getIcRc
	 * @return
	 */
	public java.math.BigDecimal getIcRc()
	{
		return icRc;
	}

	/**
	 * setIcRc
	 * @param icRc
	 */
	public void setIcRc(java.math.BigDecimal icRc)
	{
		this.icRc = icRc;
	}

	/**
	 * getIcDsbHeader
	 * @return
	 */
	public java.math.BigDecimal getIcDsbHeader()
	{
		return icDsbHeader;
	}

	/**
	 * setIcDsbHeader
	 * @param icDsbHeader
	 */
	public void setIcDsbHeader(java.math.BigDecimal icDsbHeader)
	{
		this.icDsbHeader = icDsbHeader;
	}

    /** full constructor */
    public DisbLine(java.math.BigDecimal icDsbLine, java.math.BigDecimal lineNumber, java.lang.String disbNumber, java.lang.String itemNumber, java.lang.String itemSource, java.lang.String umCode, java.math.BigDecimal quantity, java.math.BigDecimal unitPrice, java.lang.String commodityCode, java.math.BigDecimal icReqLine, java.lang.String status, java.lang.String commentFlag, java.lang.String itemLocation, java.lang.String description, java.math.BigDecimal icDsbAccount, java.math.BigDecimal umFactor, java.math.BigDecimal lineTotal, java.lang.String shiptoFlag, java.lang.String aisle, java.lang.String locrow, java.lang.String tier, java.lang.String bin, java.lang.String vendorId, java.lang.String manufactNo, java.lang.String lot, java.lang.String locIc, java.math.BigDecimal qtyBkord, java.util.Date disbDate, java.lang.String assetCode, java.math.BigDecimal icLineHistory, java.lang.String timeZone, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code) {
        this.icDsbLine = icDsbLine;
        this.lineNumber = lineNumber;
        this.disbNumber = disbNumber;
        this.itemNumber = itemNumber;
        this.itemSource = itemSource;
        this.umCode = umCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.commodityCode = commodityCode;
        this.icReqLine = icReqLine;
        this.status = status;
        this.commentFlag = commentFlag;
        this.itemLocation = itemLocation;
        this.description = description;
        this.icDsbAccount = icDsbAccount;
        this.umFactor = umFactor;
        this.lineTotal = lineTotal;
        this.shiptoFlag = shiptoFlag;
        this.aisle = aisle;
        this.locrow = locrow;
        this.tier = tier;
        this.bin = bin;
        this.vendorId = vendorId;
        this.manufactNo = manufactNo;
        this.lot = lot;
        this.locIc = locIc;
        this.qtyBkord = qtyBkord;
        this.disbDate = disbDate;
        this.assetCode = assetCode;
        this.icLineHistory = icLineHistory;
        this.timeZone = timeZone;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
    }

    /** default constructor */
    public DisbLine() {
    }

    /** minimal constructor */
    public DisbLine(java.math.BigDecimal icDsbLine) {
        this.icDsbLine = icDsbLine;
    }

    public java.math.BigDecimal getIcDsbLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbLine);
    }

    public void setIcDsbLine(java.math.BigDecimal icDsbLine) {
        this.icDsbLine = icDsbLine;
    }

    public java.math.BigDecimal getLineNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNumber);
    }

    public void setLineNumber(java.math.BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public java.lang.String getDisbNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.disbNumber).trim();
    }

    public void setDisbNumber(java.lang.String disbNumber) {
		if (!HiltonUtility.isEmpty(disbNumber) && disbNumber.length() > 20) {
			disbNumber = disbNumber.substring(0, 20);
		}
		this.disbNumber = disbNumber;
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

    public java.lang.String getItemSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemSource).trim();
    }

    public void setItemSource(java.lang.String itemSource) {
		if (!HiltonUtility.isEmpty(itemSource) && itemSource.length() > 3) {
			itemSource = itemSource.substring(0, 3);
		}
		this.itemSource = itemSource;
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

    public java.math.BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.lang.String getCommodityCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodityCode).trim();
    }

    public void setCommodityCode(java.lang.String commodityCode) {
		if (!HiltonUtility.isEmpty(commodityCode) && commodityCode.length() > 15) {
			commodityCode = commodityCode.substring(0, 15);
		}
		this.commodityCode = commodityCode;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
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

    public java.lang.String getCommentFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentFlag).trim();
    }

    public void setCommentFlag(java.lang.String commentFlag) {
		if (!HiltonUtility.isEmpty(commentFlag) && commentFlag.length() > 1) {
			commentFlag = commentFlag.substring(0, 1);
		}
		this.commentFlag = commentFlag;
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

    public java.math.BigDecimal getIcDsbAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbAccount);
    }

    public void setIcDsbAccount(java.math.BigDecimal icDsbAccount) {
        this.icDsbAccount = icDsbAccount;
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    public java.math.BigDecimal getLineTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineTotal);
    }

    public void setLineTotal(java.math.BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public java.lang.String getShiptoFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.shiptoFlag).trim();
    }

    public void setShiptoFlag(java.lang.String shiptoFlag) {
		if (!HiltonUtility.isEmpty(shiptoFlag) && shiptoFlag.length() > 1) {
			shiptoFlag = shiptoFlag.substring(0, 1);
		}
		this.shiptoFlag = shiptoFlag;
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

    public java.lang.String getLocIc() {
		return (java.lang.String)HiltonUtility.ckNull(this.locIc).trim();
    }

    public void setLocIc(java.lang.String locIc) {
		if (!HiltonUtility.isEmpty(locIc) && locIc.length() > 8) {
			locIc = locIc.substring(0, 8);
		}
		this.locIc = locIc;
    }

    public java.math.BigDecimal getQtyBkord() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyBkord);
    }

    public void setQtyBkord(java.math.BigDecimal qtyBkord) {
        this.qtyBkord = qtyBkord;
    }

    public java.util.Date getDisbDate() {
		return this.disbDate;
//		return HiltonUtility.ckNull(this.disbDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.disbDate);
    }

    public void setDisbDate(java.util.Date disbDate) {
        this.disbDate = disbDate;
    }

    public java.lang.String getAssetCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetCode).trim();
    }

    public void setAssetCode(java.lang.String assetCode) {
		if (!HiltonUtility.isEmpty(assetCode) && assetCode.length() > 1) {
			assetCode = assetCode.substring(0, 1);
		}
		this.assetCode = assetCode;
    }

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

    public String getTimeZone()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone)
    {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("icDsbLine", getIcDsbLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DisbLine) ) return false;
        DisbLine castOther = (DisbLine) other;
        return new EqualsBuilder()
            .append(this.getIcDsbLine(), castOther.getIcDsbLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcDsbLine())
            .toHashCode();
    }

    /**
	 * Returns the accountList.
	 * @return List
	 */
	public List getAccountList() {
		return accountList;
	}

	/**
	 * Returns the docCommentList.
	 * @return List
	 */
	public List getDocCommentList() {
		return docCommentList;
	}

	/**
	 * Sets the accountList.
	 * @param accountList The accountList to set
	 */
	public void setAccountList(List accountList) {
		this.accountList = accountList;
	}

	/**
	 * Sets the docCommentList.
	 * @param docCommentList The docCommentList to set
	 */
	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

    public String getDepartmentCode()
    {
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }

    public RequisitionLine getReqLine()
    {
    	return reqLine;
    }

    public void setRequisitionLine(RequisitionLine reqLine)
    {
        this.reqLine = reqLine;
    }
    
    public RequisitionLine getMsrLine()
    {
    	return msrLine;
    }

    public void setMsrLine(RequisitionLine msrLine)
    {
        this.msrLine = msrLine;
    }

	/**
	 * @return Returns the icAccount.
	 */
	public java.math.BigDecimal getIcAccount() {
		return icAccount;
	}

	/**
	 * @param icAccount The icAccount to set.
	 */
	public void setIcAccount(java.math.BigDecimal icAccount) {
		this.icAccount = icAccount;
	}

	public List getShipToList()
	{
		return shipToList;
	}

	public void setShipToList(List shipToList)
	{
		this.shipToList = shipToList;
	}

    public java.math.BigDecimal getDuomQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQuantity);
    }

    public void setDuomQuantity(java.math.BigDecimal duomQuantity) {
        this.duomQuantity = duomQuantity;
    }

    public java.lang.String getDuomUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.duomUmCode).trim();
    }

    public void setDuomUmCode(java.lang.String duomUmCode) {
		if (!HiltonUtility.isEmpty(duomUmCode) && duomUmCode.length() > 15) {
			duomUmCode = duomUmCode.substring(0, 15);
		}
		this.duomUmCode = duomUmCode;
    }


}
