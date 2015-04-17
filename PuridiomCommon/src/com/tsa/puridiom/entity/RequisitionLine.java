package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

/** @author Hibernate CodeGenerator */
public class RequisitionLine extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private java.math.BigDecimal lineNumber;

    /** nullable persistent field */
	private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private String requisitionNumber;

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
    private String taxable;

	/** nullable persistent field */
	private String taxCode;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private String discountSource;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private String taxShipping;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String otherDescription;

    /** nullable persistent field */
    private String taxOther;

    /** nullable persistent field */
    private java.math.BigDecimal icPoLine;

    /** nullable persistent field */
    private java.math.BigDecimal icRevisedPoLine;

    /** nullable persistent field */
    private String splits;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String commentFlag;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTaxAmt;

    /** nullable persistent field */
    private java.math.BigDecimal otherTaxAmount;

    /** nullable persistent field */
    private String reqType;

    /** nullable persistent field */
    private String catalogId;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal lineTotal;

    /** nullable persistent field */
    private String taxOvr;

    /** nullable persistent field */
    private String discOvr;

    /** nullable persistent field */
    private String shipOvr;

    /** nullable persistent field */
    private String otherOvr;

    /** nullable persistent field */
    private String shiptoFlag;

    /** nullable persistent field */
    private java.math.BigDecimal autoRelease;

    /** nullable persistent field */
    private java.math.BigDecimal lastQtyEntered;

    /** nullable persistent field */
    private String assignedBuyer;

    /** nullable persistent field */
    private java.util.Date assignedDate;

	/** nullable persistent field */
	private java.math.BigDecimal allocated;

    /** nullable persistent field */
    private java.math.BigDecimal backordered;

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
    private String rqLineKey;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private String roFlag;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */

    private com.tsa.puridiom.entity.RequisitionHeader requisitionHeader;

    private List accountList;

    private List billToList;

    private List budgetInfoList;

    private List docCommentList;

	/* List of documents added to be saved and shown in po_item.jps */
	private List docAttachmentList;

    private List shipToList;

    private List rfqInfoList;

    private List poInfoList;

    private List receiptLineList;

    private List inspectionList ;
    
    private Map reqSourcedMapList ;

	/** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private BigDecimal	icAccount ;

    private String blanketOrder;

    private java.math.BigDecimal icXls;

    /** nullable persistent field */
    private String useTaxable;

	/** nullable persistent field */
	private String useTaxCode;

	/** nullable persistent field */
    private java.math.BigDecimal useTaxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal useTaxAmount;

    /** nullable persistent field */
    private java.util.Date requiredDate;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String vendContactCode;

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
    private String memoLine ;

    /** nullable persistent field */
    private java.math.BigDecimal duomQuantity;

    /** nullable persistent field */
    private String duomUmCode;

    /** nullable persistent field */
    private String shelfLifeRqd;

    /** nullable persistent field */
    private AltText altText;

    /** nullable persistent field */
    private boolean assigned;

    /** nullable persistent field */
    private String msrNumber;

    private String chemicalItemNumber;

    /** full constructor */
    public RequisitionLine(java.math.BigDecimal icReqLine, java.math.BigDecimal lineNumber, java.lang.String requisitionNumber, java.lang.String itemNumber, java.lang.String itemSource, java.lang.String umCode, java.math.BigDecimal quantity, java.math.BigDecimal unitPrice, java.lang.String commodityCode, java.lang.String taxable, java.lang.String taxCode, java.lang.String asset, java.lang.String discountSource, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal shippingCharges, java.lang.String taxShipping, java.math.BigDecimal otherCharges, java.lang.String otherDescription, java.lang.String taxOther, java.math.BigDecimal icPoLine, java.lang.String splits, java.lang.String status, java.lang.String commentFlag, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal shippingTaxAmt, java.math.BigDecimal otherTaxAmount, java.lang.String reqType, java.lang.String catalogId, java.math.BigDecimal umFactor, java.math.BigDecimal lineTotal, java.lang.String taxOvr, java.lang.String discOvr, java.lang.String shipOvr, java.lang.String otherOvr, java.lang.String shiptoFlag, java.math.BigDecimal autoRelease, java.math.BigDecimal lastQtyEntered, java.lang.String assignedBuyer, java.util.Date assignedDate, java.math.BigDecimal allocated, java.math.BigDecimal backordered, java.lang.String mfgName, java.lang.String modelNumber, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String rqLineKey, java.lang.String receiptRequired, java.lang.String roFlag, java.lang.String routing, java.lang.String itemLocation, java.lang.String description, java.math.BigDecimal icLineHistory, com.tsa.puridiom.entity.RequisitionHeader requisitionHeader,java.math.BigDecimal icAccount, java.lang.String blanketOrder, java.math.BigDecimal icXls, java.lang.String useTaxable, String useTaxCode, java.math.BigDecimal useTaxPercent, BigDecimal useTaxAmount, java.util.Date dateEntered, java.util.Date lastChgDate, java.util.Date requiredDate, String vendorName, String vendContactCode, java.lang.String shelfLifeRqd,
    		String chemicalItemNumber){
        this.icReqLine = icReqLine;
        this.lineNumber = lineNumber;
        this.requisitionNumber = requisitionNumber;
        this.itemNumber = itemNumber;
        this.itemSource = itemSource;
        this.umCode = umCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.commodityCode = commodityCode;
        this.taxable = taxable;
        this.taxCode = taxCode;
        this.asset = asset;
        this.discountSource = discountSource;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.shippingCharges = shippingCharges;
        this.taxShipping = taxShipping;
        this.otherCharges = otherCharges;
        this.otherDescription = otherDescription;
        this.taxOther = taxOther;
        this.icPoLine = icPoLine;
        this.splits = splits;
        this.status = status;
        this.commentFlag = commentFlag;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.shippingTaxAmt = shippingTaxAmt;
        this.otherTaxAmount = otherTaxAmount;
        this.reqType = reqType;
        this.catalogId = catalogId;
        this.umFactor = umFactor;
        this.lineTotal = lineTotal;
        this.taxOvr = taxOvr;
        this.discOvr = discOvr;
        this.shipOvr = shipOvr;
        this.otherOvr = otherOvr;
        this.shiptoFlag = shiptoFlag;
        this.autoRelease = autoRelease;
        this.lastQtyEntered = lastQtyEntered;
        this.assignedBuyer = assignedBuyer;
        this.assignedDate = assignedDate;
        this.allocated = allocated;
        this.backordered = backordered;
        this.mfgName = mfgName;
        this.modelNumber = modelNumber;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.rqLineKey = rqLineKey;
        this.receiptRequired = receiptRequired;
        this.roFlag = roFlag;
        this.routing = routing;
        this.itemLocation = itemLocation;
        this.description = description;
        this.icLineHistory = icLineHistory;
        this.icAccount = icAccount ;
        this.requisitionHeader = requisitionHeader;
        this.blanketOrder = blanketOrder;
        this.icXls = icXls;
        this.useTaxable = useTaxable;
        this.useTaxCode = useTaxCode;
        this.useTaxAmount = useTaxAmount;
        this.useTaxPercent = useTaxPercent;
        this.dateEntered = dateEntered;
        this.lastChgDate = lastChgDate;
        this.requiredDate = requiredDate;
        this.vendorName = vendorName;
        this.vendContactCode = vendContactCode;
        this.shelfLifeRqd = shelfLifeRqd;
        this.chemicalItemNumber = chemicalItemNumber;
    }

    /** default constructor */
    public RequisitionLine() {
    }

    /** minimal constructor */
    public RequisitionLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
    }

    public java.math.BigDecimal getLineNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNumber);
    }

    public void setLineNumber(java.math.BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public java.lang.String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber).trim();
    }

    public void setRequisitionNumber(java.lang.String requisitionNumber) {
		if (!HiltonUtility.isEmpty(requisitionNumber) && requisitionNumber.length() > 20) {
			requisitionNumber = requisitionNumber.substring(0, 20);
		}
		this.requisitionNumber = requisitionNumber;
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

    public java.lang.String getTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxable).trim();
    }

    public void setTaxable(java.lang.String taxable) {
		if (!HiltonUtility.isEmpty(taxable) && taxable.length() > 1) {
			taxable = taxable.substring(0, 1);
		}
		this.taxable = taxable;
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

    public java.lang.String getAsset() {
		return (java.lang.String)HiltonUtility.ckNull(this.asset).trim();
    }

    public void setAsset(java.lang.String asset) {
		if (!HiltonUtility.isEmpty(asset) && asset.length() > 1) {
			asset = asset.substring(0, 1);
		}
		this.asset = asset;
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

    public java.lang.String getTaxShipping() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxShipping).trim();
    }

    public void setTaxShipping(java.lang.String taxShipping) {
		if (!HiltonUtility.isEmpty(taxShipping) && taxShipping.length() > 1) {
			taxShipping = taxShipping.substring(0, 1);
		}
		this.taxShipping = taxShipping;
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

    public java.lang.String getTaxOther() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxOther).trim();
    }

    public void setTaxOther(java.lang.String taxOther) {
		if (!HiltonUtility.isEmpty(taxOther) && taxOther.length() > 1) {
			taxOther = taxOther.substring(0, 1);
		}
		this.taxOther = taxOther;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    /**
	 * @return the icRevisedPoLine
	 */
	public java.math.BigDecimal getIcRevisedPoLine()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRevisedPoLine);
	}

	/**
	 * @param icRevisedPoLine the icRevisedPoLine to set
	 */
	public void setIcRevisedPoLine(java.math.BigDecimal icRevisedPoLine)
	{
		this.icRevisedPoLine = icRevisedPoLine;
	}

	public java.lang.String getSplits() {
		return (java.lang.String)HiltonUtility.ckNull(this.splits).trim();
    }

    public void setSplits(java.lang.String splits) {
		if (!HiltonUtility.isEmpty(splits) && splits.length() > 1) {
			splits = splits.substring(0, 1);
		}
		this.splits = splits;
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

    public java.lang.String getReqType() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqType).trim();
    }

    public void setReqType(java.lang.String reqType) {
		if (!HiltonUtility.isEmpty(reqType) && reqType.length() > 1) {
			reqType = reqType.substring(0, 1);
		}
		this.reqType = reqType;
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
    	BigDecimal factor = (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    	//if (factor.compareTo(new BigDecimal(0)) == 0) {	factor = new BigDecimal(1);	}
    	return factor;
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

    public java.lang.String getTaxOvr() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxOvr).trim();
    }

    public void setTaxOvr(java.lang.String taxOvr) {
		if (!HiltonUtility.isEmpty(taxOvr) && taxOvr.length() > 1) {
			taxOvr = taxOvr.substring(0, 1);
		}
		this.taxOvr = taxOvr;
    }

    public java.lang.String getDiscOvr() {
		return (java.lang.String)HiltonUtility.ckNull(this.discOvr).trim();
    }

    public void setDiscOvr(java.lang.String discOvr) {
		if (!HiltonUtility.isEmpty(discOvr) && discOvr.length() > 1) {
			discOvr = discOvr.substring(0, 1);
		}
		this.discOvr = discOvr;
    }

    public java.lang.String getShipOvr() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipOvr).trim();
    }

    public void setShipOvr(java.lang.String shipOvr) {
		if (!HiltonUtility.isEmpty(shipOvr) && shipOvr.length() > 1) {
			shipOvr = shipOvr.substring(0, 1);
		}
		this.shipOvr = shipOvr;
    }

    public java.lang.String getOtherOvr() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherOvr).trim();
    }

    public void setOtherOvr(java.lang.String otherOvr) {
		if (!HiltonUtility.isEmpty(otherOvr) && otherOvr.length() > 1) {
			otherOvr = otherOvr.substring(0, 1);
		}
		this.otherOvr = otherOvr;
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

    public java.math.BigDecimal getAutoRelease() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.autoRelease);
    }

    public void setAutoRelease(java.math.BigDecimal autoRelease) {
        this.autoRelease = autoRelease;
    }

    public java.math.BigDecimal getLastQtyEntered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastQtyEntered);
    }

    public void setLastQtyEntered(java.math.BigDecimal lastQtyEntered) {
        this.lastQtyEntered = lastQtyEntered;
    }

    public java.lang.String getAssignedBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.assignedBuyer).trim();
    }

    public void setAssignedBuyer(java.lang.String assignedBuyer) {
		if (!HiltonUtility.isEmpty(assignedBuyer) && assignedBuyer.length() > 15) {
			assignedBuyer = assignedBuyer.substring(0, 15);
		}
		this.assignedBuyer = assignedBuyer;
    }

    public java.util.Date getAssignedDate() {
		return this.assignedDate;
//		return HiltonUtility.ckNull(this.assignedDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.assignedDate);
    }

    public void setAssignedDate(java.util.Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public java.math.BigDecimal getAllocated() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocated);
    }

    public void setAllocated(java.math.BigDecimal allocated) {
        this.allocated = allocated;
    }

    public java.math.BigDecimal getBackordered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.backordered);
    }

    public void setBackordered(java.math.BigDecimal backordered) {
        this.backordered = backordered;
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
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 30) {
			udf1Code = udf1Code.substring(0, 30);
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

    public java.lang.String getRqLineKey() {
		return (java.lang.String)HiltonUtility.ckNull(this.rqLineKey).trim();
    }

    public void setRqLineKey(java.lang.String rqLineKey) {
		if (!HiltonUtility.isEmpty(rqLineKey) && rqLineKey.length() > 6) {
			rqLineKey = rqLineKey.substring(0, 6);
		}
		this.rqLineKey = rqLineKey;
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

    public java.lang.String getRoFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.roFlag).trim();
    }

    public void setRoFlag(java.lang.String roFlag) {
		if (!HiltonUtility.isEmpty(roFlag) && roFlag.length() > 1) {
			roFlag = roFlag.substring(0, 1);
		}
		this.roFlag = roFlag;
    }

    public java.lang.String getRouting() {
		return (java.lang.String)HiltonUtility.ckNull(this.routing).trim();
    }

    public void setRouting(java.lang.String routing) {
		if (!HiltonUtility.isEmpty(routing) && routing.length() > 25) {
			routing = routing.substring(0, 25);
		}
		this.routing = routing;
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

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

	public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
//		return icReqHeader;
	}

	public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
		this.icReqHeader = icReqHeader;
	}

    public com.tsa.puridiom.entity.RequisitionHeader getRequisitionHeader() {



        return this.requisitionHeader;
    }

    public void setRequisitionHeader(com.tsa.puridiom.entity.RequisitionHeader requisitionHeader) {
        this.requisitionHeader = requisitionHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icReqLine", getIcReqLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RequisitionLine) ) return false;
        RequisitionLine castOther = (RequisitionLine) other;
        return new EqualsBuilder()
            .append(this.getIcReqLine(), castOther.getIcReqLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReqLine())
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
	 * Returns the billToList.
	 * @return List
	 */
	public List getBillToList() {
		return billToList;
	}

	/**
	 * Returns the budgetInfoList.
	 * @return List
	 */
	public List getBudgetList() {
		return budgetInfoList;
	}

	/**
	 * Returns the docCommentList.
	 * @return List
	 */
	public List getDocCommentList() {
		return docCommentList;
	}

	/**
	 * Returns the shipToList.
	 * @return List
	 */
	public List getShipToList() {
		return shipToList;
	}

	/**
	 * Returns the rfqInfoList.
	 * @return List
	 */
	public List getRfqInfoList() {
		return rfqInfoList;
	}

	/**
	 * Returns the poInfoList.
	 * @return List
	 */
	public List getPoInfoList() {
		return poInfoList;
	}

	/**
	 * Returns the receiptLineList.
	 * @return List
	 */
	public List getReceiptLineList() {
		return receiptLineList;
	}

	/**
	 * Sets the accountList.
	 * @param accountList The accountList to set
	 */
	public void setAccountList(List accountList) {
		this.accountList = accountList;
	}

	/**
	 * Sets the billToList.
	 * @param billToList The billToList to set
	 */
	public void setBillToList(List billToList) {
		this.billToList = billToList;
	}

	/**
	 * Sets the budgetInfoList.
	 * @param budgetInfoList The budgetInfoList to set
	 */
	public void setBudgetInfoList(List budgetInfoList) {
		this.budgetInfoList = budgetInfoList;
	}

	/**
	 * Sets the docCommentList.
	 * @param docCommentList The docCommentList to set
	 */
	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

	/**
	 * Sets the shipToList.
	 * @param shipToList The shipToList to set
	 */
	public void setShipToList(List shipToList) {
		this.shipToList = shipToList;
	}

	/**
	 * Sets the rfqInfoList.
	 * @param rfqInfoList The rfqInfoList to set
	 */
	public void setRfqInfoList(List rfqInfoList) {
		this.rfqInfoList = rfqInfoList;
	}

	/**
	 * Sets the poInfoList.
	 * @param poInfoList The poInfoList to set
	 */
	public void setPoInfoList(List poInfoList) {
		this.poInfoList = poInfoList;
	}

	/**
	 * Sets the receiptLineList.
	 * @param receiptLineList The receiptLineList to set
	 */
	public void setReceiptLineList(List receiptLineList) {
		this.receiptLineList = receiptLineList;
	}

    /**
     * @return Returns the vendorId.
     */
    public String getVendorId()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }
    /**
     * @param vendorId The vendorId to set.
     */
    public void setVendorId(String vendorId)
    {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }
    /**
     * @return Returns the requisitionerCode.
     */
    public String getRequisitionerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionerCode).trim();
    }
    /**
     * @param requisitionerCode The requisitionerCode to set.
     */
    public void setRequisitionerCode(String requisitionerCode) {
		if (!HiltonUtility.isEmpty(requisitionerCode) && requisitionerCode.length() > 15) {
			requisitionerCode = requisitionerCode.substring(0, 15);
		}
		this.requisitionerCode = requisitionerCode;
    }
    public String getDepartmentCode()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
    }
    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }

	public BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
	}

	public void setIcAccount(BigDecimal icAccount) {
		this.icAccount = icAccount;
	}

	public java.lang.String getBlanketOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.blanketOrder).trim();
    }

    public void setBlanketOrder(java.lang.String blanketOrder) {
		if (!HiltonUtility.isEmpty(blanketOrder) && blanketOrder.length() > 25) {
		    blanketOrder = blanketOrder.substring(0, 25);
		}
		this.blanketOrder = blanketOrder;
    }
    public RequisitionLine getRequisitionLine()
	{
		return this;
	}

    public java.math.BigDecimal getIcXls() {
		return this.icXls;
    }

    public void setIcXls(java.math.BigDecimal icXls) {
        this.icXls = icXls;
    }
    public String getUseTaxable()
	{
    	return (java.lang.String)HiltonUtility.ckNull(this.useTaxable).trim();
	}

	public void setUseTaxable(String useTaxable)
	{
		this.useTaxable = useTaxable;
	}

	public java.math.BigDecimal getUseTaxAmount()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.useTaxAmount);
	}

	public void setUseTaxAmount(java.math.BigDecimal useTaxAmount)
	{
		this.useTaxAmount = useTaxAmount;
	}

	public String getUseTaxCode()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.useTaxCode).trim();
	}

	public void setUseTaxCode(String useTaxCode)
	{
		this.useTaxCode = useTaxCode;
	}

	public java.math.BigDecimal getUseTaxPercent()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.useTaxPercent);
	}

	public void setUseTaxPercent(java.math.BigDecimal useTaxPercent)
	{
		this.useTaxPercent = useTaxPercent;
	}

	public java.util.Date getDateEntered()
	{
		return dateEntered;
	}

	public void setDateEntered(java.util.Date dateEntered)
	{
		this.dateEntered = dateEntered;
	}

	public java.util.Date getLastChgDate()
	{
		return lastChgDate;
	}

	public void setLastChgDate(java.util.Date lastChgDate)
	{
		this.lastChgDate = lastChgDate;
	}

	public java.util.Date getRequiredDate()
	{
		return requiredDate;
	}

	public void setRequiredDate(java.util.Date requiredDate)
	{
		this.requiredDate = requiredDate;
	}

	public String getVendContactCode()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.vendContactCode).trim();
	}

	public void setVendContactCode(String vendContactCode)
	{
		if (!HiltonUtility.isEmpty(vendContactCode) && vendContactCode.length() > 15) {
			vendContactCode = vendContactCode.substring(0, 15);
		}
		this.vendContactCode = vendContactCode;
	}

	public String getVendorName()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
	}

	public void setVendorName(String vendorName)
	{
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
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

    public java.lang.String getShelfLifeRqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.shelfLifeRqd).trim();
    }

    public void setShelfLifeRqd(java.lang.String shelfLifeRqd) {
		if (!HiltonUtility.isEmpty(shelfLifeRqd) && shelfLifeRqd.length() > 1) {
			shelfLifeRqd = shelfLifeRqd.substring(0, 1);
		}
		this.shelfLifeRqd = shelfLifeRqd;
    }

    public java.lang.String getMemoLine() {
		return (java.lang.String)HiltonUtility.ckNull(this.memoLine).trim();
    }

    public void setMemoLine(java.lang.String memoLine) {
		if (!HiltonUtility.isEmpty(memoLine) && memoLine.length() > 255) {
			memoLine = memoLine.substring(0, 255);
		}
		this.memoLine = memoLine;
    }

    public AltText getAltText() {
        return this.altText;
    }

    public void setAltText(AltText altText) {
        this.altText = altText;
    }

    public void setAssigned(boolean assigned) {
	    this.assigned = assigned;
	}

    public boolean getAssigned() {
	    return this.assigned;
	}

	public List getInspectionList() {
		return inspectionList;
	}

	public void setInspectionList(List inspectionList) {
		this.inspectionList = inspectionList;
	}

	public Map getReqSourcedMapList() {
		return reqSourcedMapList;
	}

	public void setReqSourcedMapList(Map reqSourcedMapList) {
		this.reqSourcedMapList = reqSourcedMapList;
	}
	
	public List getDocAttachmentList() {
		return docAttachmentList;
	}

	public void setDocAttachmentList(List docAttachmentList) {
		this.docAttachmentList = docAttachmentList;
	}

	public String getMsrNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.msrNumber).trim();
	}

	public void setMsrNumber(String msrNumber) {
		if (!HiltonUtility.isEmpty(msrNumber) && msrNumber.length() > 20) {
			msrNumber = msrNumber.substring(0, 20);
		}
		this.msrNumber = msrNumber;
	}

    public String getChemicalItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.chemicalItemNumber).trim();
	}

	public void setChemicalItemNumber(String chemicalItemNumber) {
		if (!HiltonUtility.isEmpty(chemicalItemNumber) && chemicalItemNumber.length() > 30) {
			chemicalItemNumber = chemicalItemNumber.substring(0, 30);
		}
		this.chemicalItemNumber = chemicalItemNumber;
	}
}