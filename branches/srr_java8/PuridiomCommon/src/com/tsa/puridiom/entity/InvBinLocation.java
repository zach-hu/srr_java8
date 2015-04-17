package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvBinLocation implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRc;

    private String itemNumber;

    private String itemLocation;

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
    private String hdrIc;

    /** nullable persistent field */
    private java.math.BigDecimal icRecLine;

    /** nullable persistent field */
    private java.math.BigDecimal physActual;

    /** nullable persistent field */
    private java.math.BigDecimal physOriginal;

    /** nullable persistent field */
    private java.math.BigDecimal originalAlloc;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.math.BigDecimal tempIc;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal duomPhysActual;

    /** nullable persistent field */
    private java.math.BigDecimal duomPhysOriginal;

    /** nullable persistent field */
    private String source ;

    /** nullable persistent field */
    private String chargeCode ;

    private List invPropertyList;

	/**
	 * getItemLocation
	 * @return
	 */
	public String getItemLocation()
	{
		return itemLocation;
	}

	/**
	 * setItemLocation
	 * @param itemLocation
	 */
	public void setItemLocation(String itemLocation)
	{
		this.itemLocation = itemLocation;
	}

	/**
	 * getItemNumber
	 * @return
	 */
	public String getItemNumber()
	{
		return itemNumber;
	}

	/**
	 * setItemNumber
	 * @param itemNumber
	 */
	public void setItemNumber(String itemNumber)
	{
		this.itemNumber = itemNumber;
	}

    /** full constructor */
    public InvBinLocation(java.math.BigDecimal icRc, java.lang.String vendorId, java.lang.String manufactNo, java.lang.String lot, java.math.BigDecimal cost, java.util.Date itemDate, java.lang.String aisle, java.lang.String locrow, java.lang.String tier, java.lang.String bin, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.math.BigDecimal qtyOnHand, java.math.BigDecimal qtyAlloc, java.lang.String hdrIc, java.math.BigDecimal icRecLine, java.math.BigDecimal physActual, java.math.BigDecimal physOriginal, java.math.BigDecimal originalAlloc) {
        this.icRc = icRc;
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
        this.hdrIc = hdrIc;
        this.icRecLine = icRecLine;
        this.physActual = physActual;
        this.physOriginal = physOriginal;
        this.originalAlloc = originalAlloc;
    }

    /** default constructor */
    public InvBinLocation() {
    }

    /** minimal constructor */
    public InvBinLocation(java.math.BigDecimal icRc) {
        this.icRc = icRc;
    }

    public java.math.BigDecimal getIcRc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRc);
    }

    public void setIcRc(java.math.BigDecimal icRc) {
        this.icRc = icRc;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
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

    public java.lang.String getHdrIc() {
		return (java.lang.String)HiltonUtility.ckNull(this.hdrIc).trim();
    }

    public void setHdrIc(java.lang.String hdrIc) {
		if (!HiltonUtility.isEmpty(hdrIc) && hdrIc.length() > 22) {
			hdrIc = hdrIc.substring(0, 22);
		}
		this.hdrIc = hdrIc;
    }

    public java.math.BigDecimal getIcRecLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecLine);
    }

    public void setIcRecLine(java.math.BigDecimal icRecLine) {
        this.icRecLine = icRecLine;
    }

    public java.math.BigDecimal getPhysActual() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.physActual);
    }

    public void setPhysActual(java.math.BigDecimal physActual) {
        this.physActual = physActual;
    }

    public java.math.BigDecimal getPhysOriginal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.physOriginal);
    }

    public void setPhysOriginal(java.math.BigDecimal physOriginal) {
        this.physOriginal = physOriginal;
    }

    public java.math.BigDecimal getOriginalAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.originalAlloc);
    }

    public void setOriginalAlloc(java.math.BigDecimal originalAlloc) {
        this.originalAlloc = originalAlloc;
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

    public java.math.BigDecimal getTempIc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tempIc);
    }

    public void setTempIc(java.math.BigDecimal tempIc) {
        this.tempIc = tempIc;
    }

    public java.math.BigDecimal getDuomQtyOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyOnHand);
    }

    public void setDuomQtyOnHand(java.math.BigDecimal duomQtyOnHand) {
        this.duomQtyOnHand = duomQtyOnHand;
    }

    public java.math.BigDecimal getDuomQtyAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyAlloc);
    }

    public void setDuomQtyAlloc(java.math.BigDecimal duomQtyAlloc) {
        this.duomQtyAlloc = duomQtyAlloc;
    }

    public java.math.BigDecimal getDuomPhysActual() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomPhysActual);
    }

    public void setDuomPhysActual(java.math.BigDecimal duomPhysActual) {
        this.duomPhysActual = duomPhysActual;
    }

    public java.math.BigDecimal getDuomPhysOriginal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomPhysOriginal);
    }

    public void setDuomPhysOriginal(java.math.BigDecimal duomPhysOriginal) {
        this.duomPhysOriginal = duomPhysOriginal;
    }

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getChargeCode() {
		return HiltonUtility.ckNull(this.chargeCode);
	}

	public void setChargeCode(String chargeCode) {
		if (!HiltonUtility.isEmpty(chargeCode) && chargeCode.length() > 30) {
			status = status.substring(0, 30);
		}
		this.chargeCode = chargeCode;
	}

	public List getInvPropertyList()
    {
        return invPropertyList;
    }

    public void setInvPropertyList(List invPropertyList)
    {
        this.invPropertyList = invPropertyList;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRc", getIcRc())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvBinLocation) ) return false;
        InvBinLocation castOther = (InvBinLocation) other;
        return new EqualsBuilder()
            .append(this.getIcRc(), castOther.getIcRc())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRc())
            .toHashCode();
    }

}
