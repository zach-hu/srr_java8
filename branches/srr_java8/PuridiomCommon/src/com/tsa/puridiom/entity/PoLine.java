package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;


/** @author Hibernate CodeGenerator */
public class PoLine extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPoLine;

    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private java.math.BigDecimal lineNumber;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

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
    private String commodity;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private String discountSource;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private String shippingTaxable;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTax;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String otherDescription;

    /** nullable persistent field */
    private String otherTaxable;

    /** nullable persistent field */
    private java.math.BigDecimal otherTax;

    /** nullable persistent field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private String splits;

    /** nullable persistent field */
    private String commentFlag;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.math.BigDecimal icRfqLine;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private String catalogId;

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
    private String receiptRequired;

    /** nullable persistent field */
    private java.math.BigDecimal icLineKey;

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
    private String mfgName;

    /** nullable persistent field */
    private String lineRevNo;

    /** nullable persistent field */
    private String actionInd;

    /** nullable persistent field */
    private java.math.BigDecimal icRelKey;

    /** nullable persistent field */
    private java.math.BigDecimal chgPromCnt;

    /** nullable persistent field */
    private java.util.Date chgPromDate;

    /** nullable persistent field */
    private java.util.Date poPromised;

    /** nullable persistent field */
    private String roFlag;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private java.math.BigDecimal qtyReceived;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String requisitionNumber;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String inspectionReqd;

    /** nullable persistent field */
    private String useTaxable;

	/** nullable persistent field */
	private String useTaxCode;

	/** nullable persistent field */
    private java.math.BigDecimal useTaxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal useTaxAmount;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String altItemNumber;

    /** nullable persistent field */
    private String umConv;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyReceived;
    
    private RequisitionHeader msrHeader;
    
    private Account firstAccount;

    private List accountList;

	private List docCommentList;

	private List billToList;

	private List budgetInfoList;

	private List shipToList;

	private List receiptLineList;

	private List inspectionList;

	/* List of documents added to be saved and shown in po_item.jps */
	private List docAttachmentList;

	private java.math.BigDecimal icAccount;

	private java.math.BigDecimal icXls;

	private String imageFile;

	private String appBy;

    private java.util.Date requiredDate;

    private String vendorId;

    private String vendorName;

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
    private java.math.BigDecimal expensed;

    /** nullable persistent field */
    private String shelfLifeRqd;

    private String assetNumber;

    private AltText altText;

    private String chemicalItemNumber;

    /** nullable persistent field */
    private String pyStatus;
    
    /** nullable persistent field */
    private java.math.BigDecimal qtyInvoiced;
    
	/** full constructor */
    public PoLine(java.math.BigDecimal icPoLine, java.math.BigDecimal lineNumber, java.lang.String poNumber, java.math.BigDecimal releaseNumber, java.lang.String itemNumber, java.lang.String itemSource, java.lang.String umCode, java.math.BigDecimal quantity, java.math.BigDecimal unitPrice, java.lang.String commodity, java.lang.String taxable, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.lang.String discountSource, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal shippingCharges, java.lang.String shippingTaxable, java.math.BigDecimal shippingTax, java.math.BigDecimal otherCharges, java.lang.String otherDescription, java.lang.String otherTaxable, java.math.BigDecimal otherTax, java.math.BigDecimal icReqLine, java.lang.String asset, java.lang.String splits, java.lang.String commentFlag, java.lang.String status, java.math.BigDecimal icRfqLine, java.math.BigDecimal umFactor, java.lang.String catalogId, java.math.BigDecimal lineTotal, java.lang.String taxOvr, java.lang.String discOvr, java.lang.String shipOvr, java.lang.String otherOvr, java.lang.String shiptoFlag, java.lang.String receiptRequired, java.math.BigDecimal icLineKey, java.lang.String modelNumber, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String mfgName, java.lang.String lineRevNo, java.lang.String actionInd, java.math.BigDecimal icRelKey, java.math.BigDecimal chgPromCnt, java.util.Date chgPromDate, java.util.Date poPromised, java.lang.String roFlag, java.math.BigDecimal icLineHistory, java.lang.String itemLocation, java.lang.String description, java.lang.String routing, java.lang.String taxCode, java.math.BigDecimal qtyReceived, java.lang.String requisitionerCode,java.math.BigDecimal icAccount, java.math.BigDecimal icXls, java.lang.String useTaxable, String useTaxCode, java.math.BigDecimal useTaxPercent, BigDecimal useTaxAmount, java.lang.String inspectionReqd, java.util.Date lastChgDate, java.lang.String altItemNumber, java.lang.String appBy, java.util.Date requiredDate, String vendorId, String vendorName, String vendContactCode, String umConv, String assetNumber, java.lang.String shelfLifeRqd,
    		String chemicalItemNumber, java.lang.String pyStatus, java.math.BigDecimal qtyInvoiced){
        this.icPoLine = icPoLine;
        this.lineNumber = lineNumber;
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.itemNumber = itemNumber;
        this.itemSource = itemSource;
        this.umCode = umCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.commodity = commodity;
        this.taxable = taxable;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.discountSource = discountSource;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.shippingCharges = shippingCharges;
        this.shippingTaxable = shippingTaxable;
        this.shippingTax = shippingTax;
        this.otherCharges = otherCharges;
        this.otherDescription = otherDescription;
        this.otherTaxable = otherTaxable;
        this.otherTax = otherTax;
        this.icReqLine = icReqLine;
        this.asset = asset;
        this.splits = splits;
        this.commentFlag = commentFlag;
        this.status = status;
        this.icRfqLine = icRfqLine;
        this.umFactor = umFactor;
        this.catalogId = catalogId;
        this.lineTotal = lineTotal;
        this.taxOvr = taxOvr;
        this.discOvr = discOvr;
        this.shipOvr = shipOvr;
        this.otherOvr = otherOvr;
        this.shiptoFlag = shiptoFlag;
        this.receiptRequired = receiptRequired;
        this.icLineKey = icLineKey;
        this.modelNumber = modelNumber;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.mfgName = mfgName;
        this.lineRevNo = lineRevNo;
        this.actionInd = actionInd;
        this.icRelKey = icRelKey;
        this.chgPromCnt = chgPromCnt;
        this.chgPromDate = chgPromDate;
        this.poPromised = poPromised;
        this.roFlag = roFlag;
        this.icLineHistory = icLineHistory;
        this.itemLocation = itemLocation;
        this.description = description;
        this.routing = routing;
        this.taxCode = taxCode;
        this.qtyReceived = qtyReceived;
        this.requisitionerCode = requisitionerCode;
        this.icAccount = icAccount ;
        this.icXls = icXls;
        this.useTaxable = useTaxable;
        this.useTaxCode = useTaxCode;
        this.useTaxAmount = useTaxAmount;
        this.useTaxPercent = useTaxPercent;
        this.inspectionReqd = inspectionReqd;
        this.lastChgDate = lastChgDate;
        this.altItemNumber = altItemNumber;
        this.appBy = appBy;
        this.requiredDate = requiredDate;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendContactCode = vendContactCode;
        this.umConv = umConv;
        this.assetNumber = assetNumber;
        this.shelfLifeRqd = shelfLifeRqd;
        this.chemicalItemNumber = chemicalItemNumber;
        this.pyStatus = pyStatus;
        this.qtyInvoiced = qtyInvoiced;
    }

    /** default constructor */
    public PoLine() {
    }

    /** minimal constructor */
    public PoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.math.BigDecimal getLineNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNumber);
    }

    public void setLineNumber(java.math.BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 20) {
			poNumber = poNumber.substring(0, 20);
		}
		this.poNumber = poNumber;
    }

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
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

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
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

    public java.lang.String getShippingTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.shippingTaxable).trim();
    }

    public void setShippingTaxable(java.lang.String shippingTaxable) {
		if (!HiltonUtility.isEmpty(shippingTaxable) && shippingTaxable.length() > 1) {
			shippingTaxable = shippingTaxable.substring(0, 1);
		}
		this.shippingTaxable = shippingTaxable;
    }

    public java.math.BigDecimal getShippingTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTax);
    }

    public void setShippingTax(java.math.BigDecimal shippingTax) {
        this.shippingTax = shippingTax;
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

    public java.lang.String getOtherTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherTaxable).trim();
    }

    public void setOtherTaxable(java.lang.String otherTaxable) {
		if (!HiltonUtility.isEmpty(otherTaxable) && otherTaxable.length() > 1) {
			otherTaxable = otherTaxable.substring(0, 1);
		}
		this.otherTaxable = otherTaxable;
    }

    public java.math.BigDecimal getOtherTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherTax);
    }

    public void setOtherTax(java.math.BigDecimal otherTax) {
        this.otherTax = otherTax;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
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

    public java.lang.String getSplits() {
		return (java.lang.String)HiltonUtility.ckNull(this.splits).trim();
    }

    public void setSplits(java.lang.String splits) {
		if (!HiltonUtility.isEmpty(splits) && splits.length() > 1) {
			splits = splits.substring(0, 1);
		}
		this.splits = splits;
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

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.math.BigDecimal getIcRfqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqLine);
    }

    public void setIcRfqLine(java.math.BigDecimal icRfqLine) {
        this.icRfqLine = icRfqLine;
    }

    public java.math.BigDecimal getUmFactor() {
		BigDecimal factor = (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
		//if(factor.compareTo(new BigDecimal(0)) == 0)	{		factor = new BigDecimal(1);		}
		return factor;
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
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

    public java.lang.String getReceiptRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptRequired).trim();
    }

    public void setReceiptRequired(java.lang.String receiptRequired) {
		if (!HiltonUtility.isEmpty(receiptRequired) && receiptRequired.length() > 1) {
			receiptRequired = receiptRequired.substring(0, 1);
		}
		this.receiptRequired = receiptRequired;
    }

    public java.math.BigDecimal getIcLineKey() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineKey);
    }

    public void setIcLineKey(java.math.BigDecimal icLineKey) {
        this.icLineKey = icLineKey;
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

    public java.lang.String getMfgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgName).trim();
    }

    public void setMfgName(java.lang.String mfgName) {
		if (!HiltonUtility.isEmpty(mfgName) && mfgName.length() > 60) {
			mfgName = mfgName.substring(0, 60);
		}
		this.mfgName = mfgName;
    }

    public java.lang.String getLineRevNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineRevNo).trim();
    }

    public void setLineRevNo(java.lang.String lineRevNo) {
		if (!HiltonUtility.isEmpty(lineRevNo) && lineRevNo.length() > 20) {
			lineRevNo = lineRevNo.substring(0, 20);
		}
		this.lineRevNo = lineRevNo;
    }

    public java.lang.String getActionInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionInd).trim();
    }

    public void setActionInd(java.lang.String actionInd) {
		if (!HiltonUtility.isEmpty(actionInd) && actionInd.length() > 1) {
			actionInd = actionInd.substring(0, 1);
		}
		this.actionInd = actionInd;
    }

    public java.math.BigDecimal getIcRelKey() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRelKey);
    }

    public void setIcRelKey(java.math.BigDecimal icRelKey) {
        this.icRelKey = icRelKey;
    }

    public java.math.BigDecimal getChgPromCnt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.chgPromCnt);
    }

    public void setChgPromCnt(java.math.BigDecimal chgPromCnt) {
        this.chgPromCnt = chgPromCnt;
    }

    public java.util.Date getChgPromDate() {
		return this.chgPromDate;
    }

    public void setChgPromDate(java.util.Date chgPromDate) {
        this.chgPromDate = chgPromDate;
    }

    public java.util.Date getPoPromised() {
		return this.poPromised;
    }

    public void setPoPromised(java.util.Date poPromised) {
        this.poPromised = poPromised;
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

    public java.lang.String getRouting() {
		return (java.lang.String)HiltonUtility.ckNull(this.routing).trim();
    }

    public void setRouting(java.lang.String routing) {
		if (!HiltonUtility.isEmpty(routing) && routing.length() > 25) {
			routing = routing.substring(0, 25);
		}
		this.routing = routing;
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

    public java.math.BigDecimal getQtyReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyReceived);
    }

    public void setQtyReceived(java.math.BigDecimal qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public java.lang.String getRequisitionerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionerCode).trim();
    }

    public void setRequisitionerCode(java.lang.String requisitionerCode) {
		if (!HiltonUtility.isEmpty(requisitionerCode) && requisitionerCode.length() > 15) {
			requisitionerCode = requisitionerCode.substring(0, 15);
		}
		this.requisitionerCode = requisitionerCode;
    }

	/**
	 * @return Returns the dateEntered.
	 */
	public java.util.Date getDateEntered() {
		return dateEntered;
	}

	/**
	 * @param dateEntered The dateEntered to set.
	 */
	public void setDateEntered(java.util.Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	/**
	 * @return Returns the departmentCode.
	 */
	public String getDepartmentCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
	}

	/**
	 * @param departmentCode The departmentCode to set.
	 */
	public void setDepartmentCode(String departmentCode) {
		if (!HiltonUtility.isEmpty(departmentCode) && departmentCode.length() > 15) {
			departmentCode = departmentCode.substring(0, 15);
		}
		this.departmentCode = departmentCode;
	}

	/**
	 * @return Returns the requisitionNumber.
	 */
	public String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber).trim();
	}

	/**
	 * @param requisitionNumber The requisitionNumber to set.
	 */
	public void setRequisitionNumber(String requisitionNumber) {
		if (!HiltonUtility.isEmpty(requisitionNumber) && requisitionNumber.length() > 20) {
			requisitionNumber = requisitionNumber.substring(0, 20);
		}
		this.requisitionNumber = requisitionNumber;
	}

	/**
	 * @return Returns the icAccount.
	 */
	public java.math.BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
	}

	/**
	 * @param icAccount The icAccount to set.
	 */
	public void setIcAccount(java.math.BigDecimal icAccount) {
		this.icAccount = icAccount;
	}

	/**
	 * @return Returns the inspectionReqd.
	 */
	public String getInspectionReqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionReqd).trim();
	}

	/**
	 * @param inspectionReqd The inspectionReqd to set.
	 */
	public void setInspectionReqd(String inspectionReqd) {
		if (!HiltonUtility.isEmpty(inspectionReqd) && inspectionReqd.length() > 1) {
		    inspectionReqd = inspectionReqd.substring(0, 1);
		}
		this.inspectionReqd = inspectionReqd;
	}

	public boolean isInspectionRequired() {
	    if (this.getInspectionReqd().equalsIgnoreCase("Y")) {
	        return true;
	    }
	    return false;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getIcPoLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoLine) ) return false;
        PoLine castOther = (PoLine) other;
        return new EqualsBuilder()
            .append(this.getIcPoLine(), castOther.getIcPoLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPoLine())
            .toHashCode();
    }

	/**
	 * @return Returns the icPoHeader.
	 */
	public java.math.BigDecimal getIcPoHeader()
	{
		return icPoHeader;
	}

	/**
	 * @param icPoHeader The icPoHeader to set.
	 */
	public void setIcPoHeader(java.math.BigDecimal icPoHeader)
	{
		this.icPoHeader = icPoHeader;
	}

	/**
	 * Returns the accountList.
	 * @return List
	 */
	public List getAccountList() {
		return accountList;
	}
	
	

	public Account getFirstAccount() {
		if(!accountList.isEmpty()){
			return (Account)accountList.get(0);
        } else {
        	return new Account();
        }
	}

	public void setFirstAccount(Account firstAccount) {
		this.firstAccount = firstAccount;
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
	 * Sets the receiptLineList.
	 * @param receiptLineList The receiptLineList to set
	 */
	public void setReceiptLineList(List receiptLineList) {
		this.receiptLineList = receiptLineList;
	}

	public PoLine getPoLine()
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

	public java.lang.String getImageFile()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.imageFile).trim();
    }

    public void setImageFile(java.lang.String imageFile)
    {
		if (!HiltonUtility.isEmpty(imageFile) && imageFile.length() > 40) {
			imageFile = imageFile.substring(0, 40);
		}
		this.imageFile = imageFile;
    }

    public java.math.BigDecimal getLineRevisionNumber()
    {
    	if ( !HiltonUtility.isEmpty(this.getLineRevNo()) )
    	{
    		BigDecimal lineRevisionNumber =  new BigDecimal(this.getLineRevNo());
    		return lineRevisionNumber;
    	}
    	else
    		return new BigDecimal(0);
    }

    public java.util.Date getLastChgDate()
	{
		return lastChgDate;
	}

	public void setLastChgDate(java.util.Date lastChgDate)
	{
		this.lastChgDate = lastChgDate;
	}

	public RequisitionHeader getMsrHeader() {
		return msrHeader;
	}

	public void setMsrHeader(RequisitionHeader msrHeader) {
		this.msrHeader = msrHeader;
	}

	public java.lang.String getAltItemNumber() {
        return (java.lang.String)HiltonUtility.ckNull(this.altItemNumber).trim();
    }

    public void setAltItemNumber(java.lang.String altItemNumber) {
        if (!HiltonUtility.isEmpty(altItemNumber) && altItemNumber.length() > 30) {
            altItemNumber = altItemNumber.substring(0, 30);
        }
        this.altItemNumber = altItemNumber;
    }

	public java.lang.String getAppBy()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.appBy).trim();
	}

	public void setAppBy(java.lang.String appBy)
	{
		if (!HiltonUtility.isEmpty(appBy) && appBy.length() > 15)
		{
			appBy = appBy.substring(0, 15);
		}
		this.appBy = appBy;
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

	public String getVendorId()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
	}

	public void setVendorId(String vendorId)
	{
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
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

    public java.lang.String getUmConv() {
        return (java.lang.String)HiltonUtility.ckNull(this.umConv).trim();
    }

    public void setUmConv(java.lang.String umConv) {
        if (!HiltonUtility.isEmpty(umConv) && umConv.length() > 15) {
            umConv = umConv.substring(0, 15);
        }
        this.umConv = umConv;
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

    public java.lang.String getMemoLine() {
		return (java.lang.String)HiltonUtility.ckNull(this.memoLine).trim();
    }

    public void setMemoLine(java.lang.String memoLine) {
		if (!HiltonUtility.isEmpty(memoLine) && memoLine.length() > 255) {
			memoLine = memoLine.substring(0, 255);
		}
		this.memoLine = memoLine;
    }

	/**
	 * @return the expensed
	 */
	public java.math.BigDecimal getExpensed()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.expensed);
	}

	/**
	 * @param expensed the expensed to set
	 */
	public void setExpensed(java.math.BigDecimal expensed)
	{
		this.expensed = expensed;
	}

	public String getAssetNumber() {
        return (java.lang.String)HiltonUtility.ckNull(this.assetNumber).trim();
    }

    public void setAssetNumber(String assetNumber) {
        if (!HiltonUtility.isEmpty(assetNumber) && assetNumber.length() > 30) {
        	assetNumber = assetNumber.substring(0, 30);
        }
        this.assetNumber = assetNumber;
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

    public AltText getAltText() {
        return this.altText;
    }

    public void setAltText(AltText altText) {
        this.altText = altText;
    }
    public java.math.BigDecimal getDuomQtyReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyReceived);
    }

    public void setDuomQtyReceived(java.math.BigDecimal duomQtyReceived) {
        this.duomQtyReceived = duomQtyReceived;
    }

   public List getDocAttachmentList()
   {
       return docAttachmentList;
   }

   public void setDocAttachmentList(List docAttachmentList)
   {
       this.docAttachmentList = docAttachmentList;
   }

   public List getInspectionList() {
		return inspectionList;
	}

	public void setInspectionList(List inspectionList) {
		this.inspectionList = inspectionList;
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
	public java.lang.String getPyStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.pyStatus).trim();
    }
	
	public void setPyStatus(java.lang.String pyStatus) {
		if (!HiltonUtility.isEmpty(pyStatus) && pyStatus.length() > 4) {
			pyStatus = pyStatus.substring(0, 4);
		}
		this.pyStatus = pyStatus;
    }
	
	public java.math.BigDecimal getQtyInvoiced() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyInvoiced);
    }

    public void setQtyInvoiced(java.math.BigDecimal qtyReceived) {
        this.qtyInvoiced = qtyReceived;
    }
}
