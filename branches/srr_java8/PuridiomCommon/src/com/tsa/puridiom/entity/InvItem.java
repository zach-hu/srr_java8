package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvItem implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** nullable persistent field */
    private String itemNumberSuperceded;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private java.math.BigDecimal icInvAccount;

    /** nullable persistent field */
    private String unitOfOrder;

    /** nullable persistent field */
    private java.math.BigDecimal cost;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private String abcCode;

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
    private String unitOfIssue;

    /** nullable persistent field */
    private java.math.BigDecimal factor;

    /** nullable persistent field */
    private java.math.BigDecimal averageCost;

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
    private String udf11Code;

    /** nullable persistent field */
    private String udf12Code;

    /** nullable persistent field */
    private String udf13Code;

    /** nullable persistent field */
    private String udf14Code;

    /** nullable persistent field */
    private String udf15Code;

    /** nullable persistent field */
    private java.math.BigDecimal variance;

    /** nullable persistent field */
    private String imageFile;

    /** nullable persistent field */
    private java.math.BigDecimal issueCost;

    /** nullable persistent field */
    private java.math.BigDecimal lastCost;

    /** nullable persistent field */
    private java.math.BigDecimal icNotes;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String kit;

    /** nullable persistent field */
    private java.math.BigDecimal mohMonths;

    /** nullable persistent field */
    private java.math.BigDecimal eoqMonths;

    /** nullable persistent field */
    private java.math.BigDecimal mohTot;

    /** nullable persistent field */
    private java.math.BigDecimal eoqTot;

    /** nullable persistent field */
    private String chargable;

    /** nullable persistent field */
    private java.math.BigDecimal maxReqQty;

    /** nullable persistent field */
    private String actionCode;

    /** nullable persistent field */
    private String restrictedItem;

    /** nullable persistent field */
    private java.math.BigDecimal sumQtyOh;

    /** nullable persistent field */
    private java.math.BigDecimal sumBackorder;

    /** nullable persistent field */
    private java.math.BigDecimal sumUsage;

    /** nullable persistent field */
    private java.math.BigDecimal sumEoq;

    /** nullable persistent field */
    private java.math.BigDecimal sumMinOh;

    /** nullable persistent field */
    private java.math.BigDecimal sumQtyOrder;

    /** nullable persistent field */
    private String lastPo;

    /** nullable persistent field */
    private java.util.Date lastPoDate;

    /** nullable persistent field */
    private String assetCode;

    /** nullable persistent field */
    private java.math.BigDecimal minReqQty;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private java.math.BigDecimal pullIncrement;

    /** nullable persistent field */
    private String usageRecalc;

    /** nullable persistent field */
    private String buyerCode;

    /** nullable persistent field */
    private String aribaExport;

    /** nullable persistent field */
    private String appointedFlag;

    /** nullable persistent field */
    private String ssInterface;

    /** nullable persistent field */
    private String managedBy;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String mfgName;

    /** nullable persistent field */
    private String modelNumber ;

    /** nullable persistent field */
    private String nsnNumber ;

    private String gfpProperty ;
    private String fgpProperty ;
    private String capProperty ;
    private String duomUmCode ;
    private String critSparePart;
    private String approveStatus;
    private String approved;
    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    private String itemType;
    private String barcodeData;
    private String drawingNo;
    private String shelfLifeRqd;

    /** field to calculate qtyOnHand of InvBinLocation related */
    private java.math.BigDecimal qtyOnHandInvBinLocation;

    /** nullable persistent field */
    private com.tsa.puridiom.entity.InvFormData invFormData;

    /** persistent field */
    private Set invAllocs;

    /** persistent field */
    private Set invBinLocations;

    /** persistent field */
    private Set invBinLocHistories;

    /** persistent field */
    private Set invFormCatalogs;

    /** persistent field */
    private Set invFormParts;

    /** persistent field */
    private Set invFormProducts;

    /** persistent field */
    private Set invFormStates;

    /** persistent field */
    private Set invHistories;

    /** persistent field */
    private Set invLocations;

    /** persistent field */
    private Set invVendors;

    /** full constructor */
    public InvItem(java.lang.String itemNumber, java.lang.String commodity, java.math.BigDecimal icText, java.math.BigDecimal icInvAccount, java.lang.String unitOfOrder, java.math.BigDecimal cost, java.lang.String taxable, java.lang.String abcCode, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String source, java.lang.String unitOfIssue, java.math.BigDecimal factor, java.math.BigDecimal averageCost, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String udf11Code, java.lang.String udf12Code, java.lang.String udf13Code, java.lang.String udf14Code, java.lang.String udf15Code, java.math.BigDecimal variance, java.lang.String imageFile, java.math.BigDecimal issueCost, java.math.BigDecimal lastCost, java.math.BigDecimal icNotes, java.lang.String poNumber, java.lang.String kit, java.math.BigDecimal mohMonths, java.math.BigDecimal eoqMonths, java.math.BigDecimal mohTot, java.math.BigDecimal eoqTot, java.lang.String chargable, java.math.BigDecimal maxReqQty, java.lang.String actionCode, java.lang.String restrictedItem, java.math.BigDecimal sumQtyOh, java.math.BigDecimal sumBackorder, java.math.BigDecimal sumUsage, java.math.BigDecimal sumEoq, java.math.BigDecimal sumMinOh, java.math.BigDecimal sumQtyOrder, java.lang.String lastPo, java.util.Date lastPoDate, java.lang.String assetCode, java.math.BigDecimal minReqQty, java.lang.String receiptRequired, java.math.BigDecimal pullIncrement, java.lang.String usageRecalc, java.lang.String buyerCode, java.lang.String aribaExport, java.lang.String appointedFlag, java.lang.String ssInterface, java.lang.String managedBy, java.lang.String description, com.tsa.puridiom.entity.InvFormData invFormData, Set invAllocs, Set invBinLocations, Set invBinLocHistories, Set invFormCatalogs, Set invFormParts, Set invFormProducts, Set invFormStates, Set invHistories, Set invLocations, Set invVendors, java.lang.String itemType,  java.lang.String barcodeData, java.lang.String drawingNo, String itemNumberSuperceded, String approveStatus, String approved, java.math.BigDecimal icHeaderHistory) {
        this.itemNumber = itemNumber;
        this.commodity = commodity;
        this.icText = icText;
        this.icInvAccount = icInvAccount;
        this.unitOfOrder = unitOfOrder;
        this.cost = cost;
        this.taxable = taxable;
        this.abcCode = abcCode;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.source = source;
        this.unitOfIssue = unitOfIssue;
        this.factor = factor;
        this.averageCost = averageCost;
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
        this.udf11Code = udf11Code;
        this.udf12Code = udf12Code;
        this.udf13Code = udf13Code;
        this.udf14Code = udf14Code;
        this.udf15Code = udf15Code;
        this.variance = variance;
        this.imageFile = imageFile;
        this.issueCost = issueCost;
        this.lastCost = lastCost;
        this.icNotes = icNotes;
        this.poNumber = poNumber;
        this.kit = kit;
        this.mohMonths = mohMonths;
        this.eoqMonths = eoqMonths;
        this.mohTot = mohTot;
        this.eoqTot = eoqTot;
        this.chargable = chargable;
        this.maxReqQty = maxReqQty;
        this.actionCode = actionCode;
        this.restrictedItem = restrictedItem;
        this.sumQtyOh = sumQtyOh;
        this.sumBackorder = sumBackorder;
        this.sumUsage = sumUsage;
        this.sumEoq = sumEoq;
        this.sumMinOh = sumMinOh;
        this.sumQtyOrder = sumQtyOrder;
        this.lastPo = lastPo;
        this.lastPoDate = lastPoDate;
        this.assetCode = assetCode;
        this.minReqQty = minReqQty;
        this.receiptRequired = receiptRequired;
        this.pullIncrement = pullIncrement;
        this.usageRecalc = usageRecalc;
        this.buyerCode = buyerCode;
        this.aribaExport = aribaExport;
        this.appointedFlag = appointedFlag;
        this.ssInterface = ssInterface;
        this.managedBy = managedBy;
        this.description = description;
        this.invFormData = invFormData;
        this.invAllocs = invAllocs;
        this.invBinLocations = invBinLocations;
        this.invBinLocHistories = invBinLocHistories;
        this.invFormCatalogs = invFormCatalogs;
        this.invFormParts = invFormParts;
        this.invFormProducts = invFormProducts;
        this.invFormStates = invFormStates;
        this.invHistories = invHistories;
        this.invLocations = invLocations;
        this.invVendors = invVendors;
        this.itemType = itemType;
        this.barcodeData = barcodeData;
        this.drawingNo = drawingNo;
        this.itemNumberSuperceded = itemNumberSuperceded;
        this.approveStatus = approveStatus;
        this.approved = approved;
        this.icHeaderHistory = icHeaderHistory;
    }

    /** default constructor */
    public InvItem() {
    }

    /** minimal constructor */
    public InvItem(java.lang.String itemNumber, Set invAllocs, Set invBinLocations, Set invBinLocHistories, Set invFormCatalogs, Set invFormParts, Set invFormProducts, Set invFormStates, Set invHistories, Set invLocations, Set invVendors) {
        this.itemNumber = itemNumber;
        this.invAllocs = invAllocs;
        this.invBinLocations = invBinLocations;
        this.invBinLocHistories = invBinLocHistories;
        this.invFormCatalogs = invFormCatalogs;
        this.invFormParts = invFormParts;
        this.invFormProducts = invFormProducts;
        this.invFormStates = invFormStates;
        this.invHistories = invHistories;
        this.invLocations = invLocations;
        this.invVendors = invVendors;
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

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
    }

    
    
    public java.math.BigDecimal getQtyOnHandInvBinLocation() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyOnHandInvBinLocation);
	}

	public void setQtyOnHandInvBinLocation(java.math.BigDecimal qtyOnHandInvBinLocation) {
		this.qtyOnHandInvBinLocation = qtyOnHandInvBinLocation;
	}

	public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public java.math.BigDecimal getIcInvAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icInvAccount);
    }

    public void setIcInvAccount(java.math.BigDecimal icInvAccount) {
        this.icInvAccount = icInvAccount;
    }

    public java.lang.String getUnitOfOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.unitOfOrder).trim();
    }

    public void setUnitOfOrder(java.lang.String unitOfOrder) {
		if (!HiltonUtility.isEmpty(unitOfOrder) && unitOfOrder.length() > 15) {
			unitOfOrder = unitOfOrder.substring(0, 15);
		}
		this.unitOfOrder = unitOfOrder;
    }

    public java.math.BigDecimal getCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.cost);
    }

    public void setCost(java.math.BigDecimal cost) {
        this.cost = cost;
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

    public java.lang.String getAbcCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.abcCode).trim();
    }

    public void setAbcCode(java.lang.String abcCode) {
		if (!HiltonUtility.isEmpty(abcCode) && abcCode.length() > 1) {
			abcCode = abcCode.substring(0, 1);
		}
		this.abcCode = abcCode;
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

    public java.lang.String getUnitOfIssue() {
		return (java.lang.String)HiltonUtility.ckNull(this.unitOfIssue).trim();
    }

    public void setUnitOfIssue(java.lang.String unitOfIssue) {
		if (!HiltonUtility.isEmpty(unitOfIssue) && unitOfIssue.length() > 15) {
			unitOfIssue = unitOfIssue.substring(0, 15);
		}
		this.unitOfIssue = unitOfIssue;
    }

    public java.math.BigDecimal getFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.factor);
    }

    public void setFactor(java.math.BigDecimal factor) {
        this.factor = factor;
    }

    public java.math.BigDecimal getAverageCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.averageCost);
    }

    public void setAverageCost(java.math.BigDecimal averageCost) {
        this.averageCost = averageCost;
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

    public java.lang.String getUdf11Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf11Code).trim();
    }

    public void setUdf11Code(java.lang.String udf11Code) {
		if (!HiltonUtility.isEmpty(udf11Code) && udf11Code.length() > 15) {
			udf11Code = udf11Code.substring(0, 15);
		}
		this.udf11Code = udf11Code;
    }

    public java.lang.String getUdf12Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf12Code).trim();
    }

    public void setUdf12Code(java.lang.String udf12Code) {
		if (!HiltonUtility.isEmpty(udf12Code) && udf12Code.length() > 15) {
			udf12Code = udf12Code.substring(0, 15);
		}
		this.udf12Code = udf12Code;
    }

    public java.lang.String getUdf13Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf13Code).trim();
    }

    public void setUdf13Code(java.lang.String udf13Code) {
		if (!HiltonUtility.isEmpty(udf13Code) && udf13Code.length() > 15) {
			udf13Code = udf13Code.substring(0, 15);
		}
		this.udf13Code = udf13Code;
    }

    public java.lang.String getUdf14Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf14Code).trim();
    }

    public void setUdf14Code(java.lang.String udf14Code) {
		if (!HiltonUtility.isEmpty(udf14Code) && udf14Code.length() > 15) {
			udf14Code = udf14Code.substring(0, 15);
		}
		this.udf14Code = udf14Code;
    }

    public java.lang.String getUdf15Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf15Code).trim();
    }

    public void setUdf15Code(java.lang.String udf15Code) {
		if (!HiltonUtility.isEmpty(udf15Code) && udf15Code.length() > 15) {
			udf15Code = udf15Code.substring(0, 15);
		}
		this.udf15Code = udf15Code;
    }

    public java.math.BigDecimal getVariance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.variance);
    }

    public void setVariance(java.math.BigDecimal variance) {
        this.variance = variance;
    }

    public java.lang.String getImageFile() {
		return (java.lang.String)HiltonUtility.ckNull(this.imageFile).trim();
    }

    public void setImageFile(java.lang.String imageFile) {
		if (!HiltonUtility.isEmpty(imageFile) && imageFile.length() > 40) {
			imageFile = imageFile.substring(0, 40);
		}
		this.imageFile = imageFile;
    }

    public java.math.BigDecimal getIssueCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.issueCost);
    }

    public void setIssueCost(java.math.BigDecimal issueCost) {
        this.issueCost = issueCost;
    }

    public java.math.BigDecimal getLastCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastCost);
    }

    public void setLastCost(java.math.BigDecimal lastCost) {
        this.lastCost = lastCost;
    }

    public java.math.BigDecimal getIcNotes() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNotes);
    }

    public void setIcNotes(java.math.BigDecimal icNotes) {
        this.icNotes = icNotes;
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

    public java.lang.String getKit() {
		return (java.lang.String)HiltonUtility.ckNull(this.kit).trim();
    }

    public void setKit(java.lang.String kit) {
		if (!HiltonUtility.isEmpty(kit) && kit.length() > 1) {
			kit = kit.substring(0, 1);
		}
		this.kit = kit;
    }

    public java.math.BigDecimal getMohMonths() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.mohMonths);
    }

    public void setMohMonths(java.math.BigDecimal mohMonths) {
        this.mohMonths = mohMonths;
    }

    public java.math.BigDecimal getEoqMonths() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.eoqMonths);
    }

    public void setEoqMonths(java.math.BigDecimal eoqMonths) {
        this.eoqMonths = eoqMonths;
    }

    public java.math.BigDecimal getMohTot() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.mohTot);
    }

    public void setMohTot(java.math.BigDecimal mohTot) {
        this.mohTot = mohTot;
    }

    public java.math.BigDecimal getEoqTot() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.eoqTot);
    }

    public void setEoqTot(java.math.BigDecimal eoqTot) {
        this.eoqTot = eoqTot;
    }

    public java.lang.String getChargable() {
		return (java.lang.String)HiltonUtility.ckNull(this.chargable).trim();
    }

    public void setChargable(java.lang.String chargable) {
		if (!HiltonUtility.isEmpty(chargable) && chargable.length() > 1) {
			chargable = chargable.substring(0, 1);
		}
		this.chargable = chargable;
    }

    public java.math.BigDecimal getMaxReqQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxReqQty);
    }

    public void setMaxReqQty(java.math.BigDecimal maxReqQty) {
        this.maxReqQty = maxReqQty;
    }

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode).trim();
    }

    public void setActionCode(java.lang.String actionCode) {
		if (!HiltonUtility.isEmpty(actionCode) && actionCode.length() > 1) {
			actionCode = actionCode.substring(0, 1);
		}
		this.actionCode = actionCode;
    }

    public java.lang.String getRestrictedItem() {
		return (java.lang.String)HiltonUtility.ckNull(this.restrictedItem).trim();
    }

    public void setRestrictedItem(java.lang.String restrictedItem) {
		if (!HiltonUtility.isEmpty(restrictedItem) && restrictedItem.length() > 1) {
			restrictedItem = restrictedItem.substring(0, 1);
		}
		this.restrictedItem = restrictedItem;
    }

    public java.math.BigDecimal getSumQtyOh() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumQtyOh);
    }

    public void setSumQtyOh(java.math.BigDecimal sumQtyOh) {
        this.sumQtyOh = sumQtyOh;
    }

    public java.math.BigDecimal getSumBackorder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumBackorder);
    }

    public void setSumBackorder(java.math.BigDecimal sumBackorder) {
        this.sumBackorder = sumBackorder;
    }

    public java.math.BigDecimal getSumUsage() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumUsage);
    }

    public void setSumUsage(java.math.BigDecimal sumUsage) {
        this.sumUsage = sumUsage;
    }

    public java.math.BigDecimal getSumEoq() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumEoq);
    }

    public void setSumEoq(java.math.BigDecimal sumEoq) {
        this.sumEoq = sumEoq;
    }

    public java.math.BigDecimal getSumMinOh() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumMinOh);
    }

    public void setSumMinOh(java.math.BigDecimal sumMinOh) {
        this.sumMinOh = sumMinOh;
    }

    public java.math.BigDecimal getSumQtyOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sumQtyOrder);
    }

    public void setSumQtyOrder(java.math.BigDecimal sumQtyOrder) {
        this.sumQtyOrder = sumQtyOrder;
    }

    public java.lang.String getLastPo() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastPo).trim();
    }

    public void setLastPo(java.lang.String lastPo) {
		if (!HiltonUtility.isEmpty(lastPo) && lastPo.length() > 20) {
			lastPo = lastPo.substring(0, 20);
		}
		this.lastPo = lastPo;
    }

    public java.util.Date getLastPoDate() {
		return this.lastPoDate;
//		return HiltonUtility.ckNull(this.lastPoDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastPoDate);
    }

    public void setLastPoDate(java.util.Date lastPoDate) {
        this.lastPoDate = lastPoDate;
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

    public java.math.BigDecimal getMinReqQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.minReqQty);
    }

    public void setMinReqQty(java.math.BigDecimal minReqQty) {
        this.minReqQty = minReqQty;
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

    public java.math.BigDecimal getPullIncrement() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.pullIncrement);
    }

    public void setPullIncrement(java.math.BigDecimal pullIncrement) {
        this.pullIncrement = pullIncrement;
    }

    public java.lang.String getUsageRecalc() {
		return (java.lang.String)HiltonUtility.ckNull(this.usageRecalc).trim();
    }

    public void setUsageRecalc(java.lang.String usageRecalc) {
		if (!HiltonUtility.isEmpty(usageRecalc) && usageRecalc.length() > 1) {
			usageRecalc = usageRecalc.substring(0, 1);
		}
		this.usageRecalc = usageRecalc;
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

    public java.lang.String getAribaExport() {
		return (java.lang.String)HiltonUtility.ckNull(this.aribaExport).trim();
    }

    public void setAribaExport(java.lang.String aribaExport) {
		if (!HiltonUtility.isEmpty(aribaExport) && aribaExport.length() > 1) {
			aribaExport = aribaExport.substring(0, 1);
		}
		this.aribaExport = aribaExport;
    }

    public java.lang.String getAppointedFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.appointedFlag).trim();
    }

    public void setAppointedFlag(java.lang.String appointedFlag) {
		if (!HiltonUtility.isEmpty(appointedFlag) && appointedFlag.length() > 1) {
			appointedFlag = appointedFlag.substring(0, 1);
		}
		this.appointedFlag = appointedFlag;
    }

    public java.lang.String getSsInterface() {
		return (java.lang.String)HiltonUtility.ckNull(this.ssInterface).trim();
    }

    public void setSsInterface(java.lang.String ssInterface) {
		if (!HiltonUtility.isEmpty(ssInterface) && ssInterface.length() > 1) {
			ssInterface = ssInterface.substring(0, 1);
		}
		this.ssInterface = ssInterface;
    }

    public java.lang.String getManagedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.managedBy).trim();
    }

    public void setManagedBy(java.lang.String managedBy) {
		if (!HiltonUtility.isEmpty(managedBy) && managedBy.length() > 15) {
			managedBy = managedBy.substring(0, 15);
		}
		this.managedBy = managedBy;
    }

    public java.lang.String getDrawingNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.drawingNo).trim();
    }

    public void setDrawingNo(java.lang.String drawingNo) {
		if (!HiltonUtility.isEmpty(drawingNo) && drawingNo.length() > 15) {
			drawingNo = drawingNo.substring(0, 15);
		}
		this.drawingNo = drawingNo ;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)Utility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

	public String getMfgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgName).trim();
	}

	public void setMfgName(String mfgName) {
		if (!HiltonUtility.isEmpty(mfgName) && mfgName.length() > 30) {
			mfgName = mfgName.substring(0, 30);
		}
		this.mfgName = mfgName;
	}

	public String getModelNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.modelNumber).trim();
	}

	public void setModelNumber(String modelNumber) {
		if (!HiltonUtility.isEmpty(modelNumber) && modelNumber.length() > 30) {
			modelNumber = modelNumber.substring(0, 30);
		}
		this.modelNumber = modelNumber;
	}

	public String getNsnNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.nsnNumber).trim();
	}

	public void setNsnNumber(String nsnNumber) {
		if (!HiltonUtility.isEmpty(nsnNumber) && nsnNumber.length() > 20) {
			nsnNumber = nsnNumber.substring(0, 20);
		}
		this.nsnNumber = nsnNumber;
	}

	public String getGfpProperty() {
		return (java.lang.String)HiltonUtility.ckNull(this.gfpProperty).trim();
	}

	public void setGfpProperty(String gfpProperty) {
		if (!HiltonUtility.isEmpty(gfpProperty) && gfpProperty.length() > 1 ) {
			gfpProperty = gfpProperty.substring(0, 1);
		}
		this.gfpProperty = gfpProperty;
	}

	public String getFgpProperty() {
		return (java.lang.String)HiltonUtility.ckNull(this.fgpProperty).trim();
	}

	public void setFgpProperty(String fgpProperty) {
		if (!HiltonUtility.isEmpty(fgpProperty) && fgpProperty.length() > 1) {
			fgpProperty = fgpProperty.substring(0, 1);
		}
		this.fgpProperty = fgpProperty;
	}

	public String getCapProperty() {
		return (java.lang.String)HiltonUtility.ckNull(this.capProperty).trim();
	}

	public void setCapProperty(String capProperty) {
		if (!HiltonUtility.isEmpty(capProperty) && capProperty.length() > 1 ) {
			capProperty = capProperty.substring(0, 1);
		}
		this.capProperty = capProperty;
	}

	public String getDuomUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.duomUmCode).trim();
	}

	public void setDuomUmCode(String duomUmCode) {
		if (!HiltonUtility.isEmpty(duomUmCode) && duomUmCode.length() > 15 ) {
			duomUmCode = duomUmCode.substring(0, 15);
		}
		this.duomUmCode = duomUmCode;
	}

	public String getCritSparePart(){
		return (java.lang.String)HiltonUtility.ckNull(this.critSparePart).trim();
	}

	public void setCritSparePart(String critSparePart){
		if (!HiltonUtility.isEmpty(critSparePart) && critSparePart.length() > 1){
			this.critSparePart = critSparePart.substring(0, 1);
		}
		this.critSparePart = critSparePart;
	}

	public String getItemType() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemType).trim();
	}

	public String getShelfLifeRqd() {
		return (String)HiltonUtility.ckNull(this.shelfLifeRqd).trim();
	}

	public void setShelfLifeRqd(String shelfLifeRqd) {
		if (!HiltonUtility.isEmpty(shelfLifeRqd) && shelfLifeRqd.length() > 1){
			this.shelfLifeRqd = shelfLifeRqd.substring(0, 1);
		}
		this.shelfLifeRqd = shelfLifeRqd;
	}

	public void setItemType(String itemType) {
		if (!HiltonUtility.isEmpty(itemType) && itemType.length() > 3 ) {
			itemType = itemType.substring(0, 3);
		}
		this.itemType = itemType;
	}


	public String getBarcodeData() {
		return (java.lang.String)HiltonUtility.ckNull(this.barcodeData).trim();
	}

	public void setBarcodeData(String barcodeData) {
		if (!HiltonUtility.isEmpty(barcodeData) && barcodeData.length() > 70 ) {
			barcodeData = barcodeData.substring(0, 70);
		}
		this.barcodeData = barcodeData;
	}


	public com.tsa.puridiom.entity.InvFormData getInvFormData() {
        return this.invFormData;
    }

    public void setInvFormData(com.tsa.puridiom.entity.InvFormData invFormData) {
        this.invFormData = invFormData;
    }

    public java.util.Set getInvAllocs() {



        return this.invAllocs;
    }

    public void setInvAllocs(java.util.Set invAllocs) {
        this.invAllocs = invAllocs;
    }

    public java.util.Set getInvBinLocations() {



        return this.invBinLocations;
    }

    public void setInvBinLocations(java.util.Set invBinLocations) {
        this.invBinLocations = invBinLocations;
    }

    public java.util.Set getInvBinLocHistories() {



        return this.invBinLocHistories;
    }

    public void setInvBinLocHistories(java.util.Set invBinLocHistories) {
        this.invBinLocHistories = invBinLocHistories;
    }

    public java.util.Set getInvFormCatalogs() {



        return this.invFormCatalogs;
    }

    public void setInvFormCatalogs(java.util.Set invFormCatalogs) {
        this.invFormCatalogs = invFormCatalogs;
    }

    public java.util.Set getInvFormParts() {



        return this.invFormParts;
    }

    public void setInvFormParts(java.util.Set invFormParts) {
        this.invFormParts = invFormParts;
    }

    public java.util.Set getInvFormProducts() {



        return this.invFormProducts;
    }

    public void setInvFormProducts(java.util.Set invFormProducts) {
        this.invFormProducts = invFormProducts;
    }

    public java.util.Set getInvFormStates() {



        return this.invFormStates;
    }

    public void setInvFormStates(java.util.Set invFormStates) {
        this.invFormStates = invFormStates;
    }

    public java.util.Set getInvHistories() {



        return this.invHistories;
    }

    public void setInvHistories(java.util.Set invHistories) {
        this.invHistories = invHistories;
    }

    public java.util.Set getInvLocations() {



        return this.invLocations;
    }

    public void setInvLocations(java.util.Set invLocations) {
        this.invLocations = invLocations;
    }

    public java.util.Set getInvVendors() {



        return this.invVendors;
    }

    public void setInvVendors(java.util.Set invVendors) {
        this.invVendors = invVendors;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvItem) ) return false;
        InvItem castOther = (InvItem) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .toHashCode();
    }

	public java.lang.String getItemNumberSuperceded() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumberSuperceded).trim();
	}

	public void setItemNumberSuperceded(java.lang.String itemNumberSuperceded) {
		if (!HiltonUtility.isEmpty(itemNumberSuperceded) && itemNumberSuperceded.length() > 30) {
			itemNumberSuperceded = itemNumberSuperceded.substring(0, 30);
		}
		this.itemNumberSuperceded = itemNumberSuperceded;
	}

	public java.lang.String getApproveStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.approveStatus).trim();
    }

    public void setApproveStatus(java.lang.String approveStatus) {
		if (!HiltonUtility.isEmpty(approveStatus) && approveStatus.length() > 4) {
			approveStatus = approveStatus.substring(0, 4);
		}
		this.approveStatus = approveStatus;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

}
