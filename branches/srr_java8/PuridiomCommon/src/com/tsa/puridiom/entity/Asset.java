package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Asset implements Serializable {

    /** identifier field */
    private String tagNumber;

    private String trackingNumber;

    /** nullable persistent field */
    private String assetClass;

    /** nullable persistent field */
    private String securityCode;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private String manufacturer;

    /** nullable persistent field */
    private String model;

    /** nullable persistent field */
    private String serialNumber;

    /** nullable persistent field */
    private java.math.BigDecimal assetCost;

    /** nullable persistent field */
    private java.util.Date dateInService;

    /** nullable persistent field */
    private String deprecMethod;

    /** nullable persistent field */
    private java.util.Date warrantyStart;

    /** nullable persistent field */
    private java.util.Date warrantyEnd;

    /** nullable persistent field */
    private String upgradeReqs;

    /** nullable persistent field */
    private String contractService;

    /** nullable persistent field */
    private String purchaseOrder;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String assetStatus;

    /** nullable persistent field */
    private String assetUdf1;

    /** nullable persistent field */
    private String assetUdf2;

    /** nullable persistent field */
    private String assetUdf3;

    /** nullable persistent field */
    private String assetUdf4;

    /** nullable persistent field */
    private String assetUdf5;

    /** nullable persistent field */
    private java.math.BigDecimal totalCost;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private String imageFile;

    /** nullable persistent field */
    private String poVendor;

    /** nullable persistent field */
    private String contractorName;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.math.BigDecimal deprecTerm;

    /** nullable persistent field */
    private java.util.Date dateReceived;

    /** nullable persistent field */
    private java.math.BigDecimal icReceipt;

    /** nullable persistent field */
    private String assetUdf6;

    /** nullable persistent field */
    private String assetUdf7;

    /** nullable persistent field */
    private String assetUdf8;

    /** nullable persistent field */
    private String assetUdf9;

    /** nullable persistent field */
    private String assetUdf10;

    /** nullable persistent field */
    private java.math.BigDecimal icLineKey;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private java.math.BigDecimal icAccount;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbLine;

    /** nullable persistent field */
    private java.math.BigDecimal localCurrencyPrice;

    /** nullable persistent field */
    private java.math.BigDecimal originalCost;

    /** nullable persistent field */
    private java.math.BigDecimal exitValue;

    /** nullable persistent field */
    private java.util.Date dateInactive;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private java.lang.String description;

    private List docAttachmentList;

    /** nullable persistent field */
    private String lease;

    /** nullable persistent field */
    private String leaseType;

    /** nullable persistent field */
    private String leaseTerm;

    /** nullable persistent field */
    private java.util.Date monthlyPayment;

    /** nullable persistent field */
    private java.math.BigDecimal leasingCompany;

    /** nullable persistent field */
    private java.util.Date renewalDate;

    /** nullable persistent field */
    private java.math.BigDecimal financing;

    /** nullable persistent field */
    private String printed;

    private String fiscalYear;

    /** full constructor */
    public Asset(java.lang.String assetClass, java.lang.String securityCode, java.lang.String commodity, java.lang.String manufacturer, java.lang.String model, java.lang.String serialNumber, java.math.BigDecimal assetCost, java.util.Date dateInService, java.lang.String deprecMethod, java.util.Date warrantyStart, java.util.Date warrantyEnd, java.lang.String upgradeReqs, java.lang.String contractService, java.lang.String purchaseOrder, java.util.Date dateEntered, java.lang.String assetStatus, java.lang.String assetUdf1, java.lang.String assetUdf2, java.lang.String assetUdf3, java.lang.String assetUdf4, java.lang.String assetUdf5, java.math.BigDecimal totalCost, java.math.BigDecimal icText, java.lang.String imageFile, java.lang.String poVendor, java.lang.String contractorName, java.lang.String owner, java.math.BigDecimal deprecTerm, java.util.Date dateReceived, java.math.BigDecimal icReceipt, java.lang.String assetUdf6, java.lang.String assetUdf7, java.lang.String assetUdf8, java.lang.String assetUdf9, java.lang.String assetUdf10, java.math.BigDecimal icLineKey, java.lang.String itemNumber, java.lang.String itemLocation, java.math.BigDecimal icAccount, java.math.BigDecimal icDsbHeader, java.math.BigDecimal icDsbLine, java.math.BigDecimal localCurrencyPrice, java.math.BigDecimal originalCost, java.math.BigDecimal exitValue, java.util.Date dateInactive, java.lang.String lastChgBy, java.util.Date dateChanged, java.lang.String description, java.lang.String lease,  java.lang.String leaseType, java.lang.String leaseTerm, java.util.Date monthlyPayment, java.math.BigDecimal leasingCompany, java.util.Date renewalDate, java.math.BigDecimal financing, java.lang.String printed, java.lang.String trackingNumber, String fiscalYear) {
        this.assetClass = assetClass;
        this.securityCode = securityCode;
        this.commodity = commodity;
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.assetCost = assetCost;
        this.dateInService = dateInService;
        this.deprecMethod = deprecMethod;
        this.warrantyStart = warrantyStart;
        this.warrantyEnd = warrantyEnd;
        this.upgradeReqs = upgradeReqs;
        this.contractService = contractService;
        this.purchaseOrder = purchaseOrder;
        this.dateEntered = dateEntered;
        this.assetStatus = assetStatus;
        this.assetUdf1 = assetUdf1;
        this.assetUdf2 = assetUdf2;
        this.assetUdf3 = assetUdf3;
        this.assetUdf4 = assetUdf4;
        this.assetUdf5 = assetUdf5;
        this.totalCost = totalCost;
        this.icText = icText;
        this.imageFile = imageFile;
        this.poVendor = poVendor;
        this.contractorName = contractorName;
        this.owner = owner;
        this.deprecTerm = deprecTerm;
        this.dateReceived = dateReceived;
        this.icReceipt = icReceipt;
        this.assetUdf6 = assetUdf6;
        this.assetUdf7 = assetUdf7;
        this.assetUdf8 = assetUdf8;
        this.assetUdf9 = assetUdf9;
        this.assetUdf10 = assetUdf10;
        this.icLineKey = icLineKey;
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
        this.icAccount = icAccount;
        this.icDsbHeader = icDsbHeader;
        this.icDsbLine = icDsbLine;
        this.localCurrencyPrice = localCurrencyPrice;
        this.originalCost = originalCost;
        this.exitValue = exitValue;
        this.dateInactive = dateInactive;
        this.lastChgBy = lastChgBy;
        this.dateChanged = dateChanged;
        this.description = description;
        this.lease = lease;
        this.leaseType = leaseType;
        this.leaseTerm = leaseTerm;
        this.monthlyPayment = monthlyPayment;
        this.leasingCompany = leasingCompany;
        this.renewalDate = renewalDate;
        this.financing = financing;
        this.printed = printed;
        this.trackingNumber = trackingNumber;
        this.fiscalYear = fiscalYear;
    }

    /** default constructor */
    public Asset() {
    }

    public java.lang.String getTagNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.tagNumber).trim();
    }

    public void setTagNumber(java.lang.String tagNumber) {
		if (!HiltonUtility.isEmpty(tagNumber) && tagNumber.length() > 20) {
			tagNumber = tagNumber.substring(0, 20);
		}
		this.tagNumber = tagNumber;
    }

    public java.lang.String getAssetClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetClass).trim();
    }

    public void setAssetClass(java.lang.String assetClass) {
		if (!HiltonUtility.isEmpty(assetClass) && assetClass.length() > 15) {
			assetClass = assetClass.substring(0, 15);
		}
		this.assetClass = assetClass;
    }

    public java.lang.String getSecurityCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.securityCode).trim();
    }

    public void setSecurityCode(java.lang.String securityCode) {
		if (!HiltonUtility.isEmpty(securityCode) && securityCode.length() > 15) {
			securityCode = securityCode.substring(0, 15);
		}
		this.securityCode = securityCode;
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

    public java.lang.String getManufacturer() {
		return (java.lang.String)HiltonUtility.ckNull(this.manufacturer).trim();
    }

    public void setManufacturer(java.lang.String manufacturer) {
		if (!HiltonUtility.isEmpty(manufacturer) && manufacturer.length() > 20) {
			manufacturer = manufacturer.substring(0, 20);
		}
		this.manufacturer = manufacturer;
    }

    public java.lang.String getModel() {
		return (java.lang.String)HiltonUtility.ckNull(this.model).trim();
    }

    public void setModel(java.lang.String model) {
		if (!HiltonUtility.isEmpty(model) && model.length() > 20) {
			model = model.substring(0, 20);
		}
		this.model = model;
    }

    public java.lang.String getSerialNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.serialNumber).trim();
    }

    public void setSerialNumber(java.lang.String serialNumber) {
		if (!HiltonUtility.isEmpty(serialNumber) && serialNumber.length() > 25) {
			serialNumber = serialNumber.substring(0, 25);
		}
		this.serialNumber = serialNumber;
    }

    public java.math.BigDecimal getAssetCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.assetCost);
    }

    public void setAssetCost(java.math.BigDecimal assetCost) {
        this.assetCost = assetCost;
    }

    public java.util.Date getDateInService() {
        return this.dateInService;
    }

    public void setDateInService(java.util.Date dateInService) {
        this.dateInService = dateInService;
    }

    public java.lang.String getDeprecMethod() {
		return (java.lang.String)HiltonUtility.ckNull(this.deprecMethod).trim();
    }

    public void setDeprecMethod(java.lang.String deprecMethod) {
		if (!HiltonUtility.isEmpty(deprecMethod) && deprecMethod.length() > 15) {
			deprecMethod = deprecMethod.substring(0, 15);
		}
		this.deprecMethod = deprecMethod;
    }

    public java.util.Date getWarrantyStart() {
        return this.warrantyStart;
    }

    public void setWarrantyStart(java.util.Date warrantyStart) {
        this.warrantyStart = warrantyStart;
    }

    public java.util.Date getWarrantyEnd() {
        return this.warrantyEnd;
    }

    public void setWarrantyEnd(java.util.Date warrantyEnd) {
        this.warrantyEnd = warrantyEnd;
    }

    public java.lang.String getUpgradeReqs() {
		return (java.lang.String)HiltonUtility.ckNull(this.upgradeReqs).trim();
    }

    public void setUpgradeReqs(java.lang.String upgradeReqs) {
		if (!HiltonUtility.isEmpty(upgradeReqs) && upgradeReqs.length() > 15) {
			upgradeReqs = upgradeReqs.substring(0, 15);
		}
		this.upgradeReqs = upgradeReqs;
    }

    public java.lang.String getContractService() {
		return (java.lang.String)HiltonUtility.ckNull(this.contractService).trim();
    }

    public void setContractService(java.lang.String contractService) {
		if (!HiltonUtility.isEmpty(contractService) && contractService.length() > 20) {
			contractService = contractService.substring(0, 20);
		}
		this.contractService = contractService;
    }

    public java.lang.String getPurchaseOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.purchaseOrder).trim();
    }

    public void setPurchaseOrder(java.lang.String purchaseOrder) {
		if (!HiltonUtility.isEmpty(purchaseOrder) && purchaseOrder.length() > 30) {
			purchaseOrder = purchaseOrder.substring(0, 30);
		}
		this.purchaseOrder = purchaseOrder;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getAssetStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetStatus).trim();
    }

    public void setAssetStatus(java.lang.String assetStatus) {
		if (!HiltonUtility.isEmpty(assetStatus) && assetStatus.length() > 15) {
			assetStatus = assetStatus.substring(0, 15);
		}
		this.assetStatus = assetStatus;
    }

    public java.lang.String getAssetUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf1).trim();
    }

    public void setAssetUdf1(java.lang.String assetUdf1) {
		if (!HiltonUtility.isEmpty(assetUdf1) && assetUdf1.length() > 15) {
			assetUdf1 = assetUdf1.substring(0, 15);
		}
		this.assetUdf1 = assetUdf1;
    }

    public java.lang.String getAssetUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf2).trim();
    }

    public void setAssetUdf2(java.lang.String assetUdf2) {
		if (!HiltonUtility.isEmpty(assetUdf2) && assetUdf2.length() > 15) {
			assetUdf2 = assetUdf2.substring(0, 15);
		}
		this.assetUdf2 = assetUdf2;
    }

    public java.lang.String getAssetUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf3).trim();
    }

    public void setAssetUdf3(java.lang.String assetUdf3) {
		if (!HiltonUtility.isEmpty(assetUdf3) && assetUdf3.length() > 15) {
			assetUdf3 = assetUdf3.substring(0, 15);
		}
		this.assetUdf3 = assetUdf3;
    }

    public java.lang.String getAssetUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf4).trim();
    }

    public void setAssetUdf4(java.lang.String assetUdf4) {
		if (!HiltonUtility.isEmpty(assetUdf4) && assetUdf4.length() > 15) {
			assetUdf4 = assetUdf4.substring(0, 15);
		}
		this.assetUdf4 = assetUdf4;
    }

    public java.lang.String getAssetUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf5).trim();
    }

    public void setAssetUdf5(java.lang.String assetUdf5) {
		if (!HiltonUtility.isEmpty(assetUdf5) && assetUdf5.length() > 15) {
			assetUdf5 = assetUdf5.substring(0, 15);
		}
		this.assetUdf5 = assetUdf5;
    }

    public java.math.BigDecimal getTotalCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.totalCost);
    }

    public void setTotalCost(java.math.BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
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

    public java.lang.String getPoVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.poVendor).trim();
    }

    public void setPoVendor(java.lang.String poVendor) {
		if (!HiltonUtility.isEmpty(poVendor) && poVendor.length() > 40) {
			poVendor = poVendor.substring(0, 40);
		}
		this.poVendor = poVendor;
    }

    public java.lang.String getContractorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.contractorName).trim();
    }

    public void setContractorName(java.lang.String contractorName) {
		if (!HiltonUtility.isEmpty(contractorName) && contractorName.length() > 40) {
			contractorName = contractorName.substring(0, 40);
		}
		this.contractorName = contractorName;
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

    public java.math.BigDecimal getDeprecTerm() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.deprecTerm);
    }

    public void setDeprecTerm(java.math.BigDecimal deprecTerm) {
        this.deprecTerm = deprecTerm;
    }

    public java.util.Date getDateReceived() {
        return this.dateReceived;
    }

    public void setDateReceived(java.util.Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public java.math.BigDecimal getIcReceipt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReceipt);
    }

    public void setIcReceipt(java.math.BigDecimal icReceipt) {
        this.icReceipt = icReceipt;
    }

    public java.lang.String getAssetUdf6() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf6).trim();
    }

    public void setAssetUdf6(java.lang.String assetUdf6) {
		if (!HiltonUtility.isEmpty(assetUdf6) && assetUdf6.length() > 15) {
			assetUdf6 = assetUdf6.substring(0, 15);
		}
		this.assetUdf6 = assetUdf6;
    }

    public java.lang.String getAssetUdf7() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf7).trim();
    }

    public void setAssetUdf7(java.lang.String assetUdf7) {
		if (!HiltonUtility.isEmpty(assetUdf7) && assetUdf7.length() > 15) {
			assetUdf7 = assetUdf7.substring(0, 15);
		}
		this.assetUdf7 = assetUdf7;
    }

    public java.lang.String getAssetUdf8() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf8).trim();
    }

    public void setAssetUdf8(java.lang.String assetUdf8) {
		if (!HiltonUtility.isEmpty(assetUdf8) && assetUdf8.length() > 15) {
			assetUdf8 = assetUdf8.substring(0, 15);
		}
		this.assetUdf8 = assetUdf8;
    }

    public java.lang.String getAssetUdf9() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf9).trim();
    }

    public void setAssetUdf9(java.lang.String assetUdf9) {
		if (!HiltonUtility.isEmpty(assetUdf9) && assetUdf9.length() > 15) {
			assetUdf9 = assetUdf9.substring(0, 15);
		}
		this.assetUdf9 = assetUdf9;
    }

    public java.lang.String getAssetUdf10() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetUdf10).trim();
    }

    public void setAssetUdf10(java.lang.String assetUdf10) {
		if (!HiltonUtility.isEmpty(assetUdf10) && assetUdf10.length() > 15) {
			assetUdf10 = assetUdf10.substring(0, 15);
		}
		this.assetUdf10 = assetUdf10;
    }

    public java.math.BigDecimal getIcLineKey() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineKey);
    }

    public void setIcLineKey(java.math.BigDecimal icLineKey) {
        this.icLineKey = icLineKey;
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

    public java.lang.String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
    }

    public void setItemLocation(java.lang.String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
    }

    public java.math.BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
    }

    public void setIcAccount(java.math.BigDecimal icAccount) {
        this.icAccount = icAccount;
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

    public java.math.BigDecimal getLocalCurrencyPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.localCurrencyPrice);
    }

    public void setLocalCurrencyPrice(java.math.BigDecimal localCurrencyPrice) {
        this.localCurrencyPrice = localCurrencyPrice;
    }

    public java.math.BigDecimal getOriginalCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.originalCost);
    }

    public void setOriginalCost(java.math.BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    public java.math.BigDecimal getExitValue() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.exitValue);
    }

    public void setExitValue(java.math.BigDecimal exitValue) {
        this.exitValue = exitValue;
    }

    public java.util.Date getDateInactive() {
        return this.dateInactive;
    }

    public void setDateInactive(java.util.Date dateInactive) {
        this.dateInactive = dateInactive;
    }

    public java.util.Date getDateChanged() {
        return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 25) {
			lastChgBy = lastChgBy.substring(0, 25);
		}
		this.lastChgBy = lastChgBy;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public List getDocAttachmentList() {
			return docAttachmentList;
	}


    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.lang.String getLease() {
		return (java.lang.String)HiltonUtility.ckNull(this.lease).trim();
    }

    public void setLease(java.lang.String lease) {
		if (!HiltonUtility.isEmpty(lease) && lease.length() > 1) {
			lease = lease.substring(0, 1);
		}
		this.lease = lease;
    }

    public java.lang.String getLeaseType() {
		return (java.lang.String)HiltonUtility.ckNull(this.leaseType).trim();
    }

    public void setLeaseType(java.lang.String leaseType) {
		if (!HiltonUtility.isEmpty(leaseType) && leaseType.length() > 25) {
			leaseType = leaseType.substring(0, 1);
		}
		this.leaseType = leaseType;
    }

    public java.lang.String getLeaseTerm() {
		return (java.lang.String)HiltonUtility.ckNull(this.leaseTerm).trim();
    }

    public void setLeaseTerm(java.lang.String leaseTerm) {
		if (!HiltonUtility.isEmpty(leaseTerm) && leaseTerm.length() > 25) {
			leaseTerm = leaseTerm.substring(0, 1);
		}
		this.leaseTerm = leaseTerm;
    }

    public java.util.Date getMonthlyPayment() {
        return this.monthlyPayment;
    }

    public void setMonthlyPayment(java.util.Date monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public java.math.BigDecimal getLeasingCompany() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.leasingCompany);
    }

    public void setLeasingCompany(java.math.BigDecimal leasingCompany) {
        this.leasingCompany = leasingCompany;
    }

    public java.util.Date getRenewalDate() {
        return this.renewalDate;
    }

    public void setRenewalDate(java.util.Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public java.math.BigDecimal getFinancing() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.financing);
    }

    public void setFinancing(java.math.BigDecimal financing) {
        this.financing = financing;
    }

    public java.lang.String getPrinted() {
		return (java.lang.String)HiltonUtility.ckNull(this.printed).trim();
    }

    public void setPrinted(java.lang.String printed) {
		if (!HiltonUtility.isEmpty(printed) && printed.length() > 1) {
			printed = printed.substring(0, 1);
		}
		this.printed = printed;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("tagNumber", getTagNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Asset) ) return false;
        Asset castOther = (Asset) other;
        return new EqualsBuilder()
            .append(this.getTagNumber(), castOther.getTagNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTagNumber())
            .toHashCode();
    }

	public String getTrackingNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.trackingNumber).trim();
	}

	public void setTrackingNumber(String trackingNumber) {
		if (!HiltonUtility.isEmpty(trackingNumber) && trackingNumber.length() > 25) {
			trackingNumber = trackingNumber.substring(0, 25);
		}
		this.trackingNumber = trackingNumber;
	}

	public String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
	}

	public void setFiscalYear(String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
	}

}
