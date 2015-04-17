/*
 * Created on Aug 26, 2003
 */
package com.tsa.puridiom.common.documents;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
public class ItemLookup
{
    private String source ;
    private String catalogId ;
    private String itemNumber = "Empty";
    private String commodity ;
    private BigDecimal icText = new BigDecimal(0);
    private BigDecimal icAccount = new BigDecimal(0);
    private String unitOfOrder ;
    private String taxable ;
    private BigDecimal orderCost ;
    private String unitOfIssue ;
    private String duomUmCode ;
    private BigDecimal issueCost ;
    private String umConv ;
    private BigDecimal umFactor = new BigDecimal(1);
    private BigDecimal unitOfIssueFactor = new BigDecimal(1);
    private String udf01 ;
    private String udf02 ;
    private String udf03 ;
    private String udf04 ;
    private String udf05 ;
    private String udf06 ;
    private String udf07 ;
    private String udf08 ;
    private String udf09 ;
    private String udf10 ;
    private String memoLine ;

	private String chargeable ;
	private String kit ;
	private String restricted ;
	private String owner ;
	private String description ;
	private String location ;
	private String receiptRequired ;
	private String shelfLifeRqd;

	private String model ;
	private String mfgName ;

	private BigDecimal avgCost ;
	private BigDecimal stockEoq ;
	private BigDecimal quantity ;

	private BigDecimal icLine ;
	private BigDecimal icHeader ;

	private boolean autoRelease ;
	private String	asset;
	private String	actionCode;
    private String  vendorId;
    private String  vendorName;

	private BigDecimal icHeaderComment = new BigDecimal(0);
	private BigDecimal icLineComment   = new BigDecimal(0);

	private String	blanketOrder ;

	/**
	 * Returns the catalogId.
	 * @return String
	 */
	public String getCatalogId() {
		return catalogId;
	}

	/**
	 * Returns the chargeable.
	 * @return String
	 */
	public String getChargeable() {
		return chargeable;
	}

	/**
	 * Returns the commodity.
	 * @return String
	 */
	public String getCommodity() {
		return commodity;
	}

	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the icText.
	 * @return BigDecimal
	 */
	public BigDecimal getIcText() {
		return icText;
	}

	/**
	 * Returns the icAccount.
	 * @return BigDecimal
	 */
	public BigDecimal getIcAccount() {
		return icAccount;
	}

	/**
	 * Returns the issueCost.
	 * @return BigDecimal
	 */
	public BigDecimal getIssueCost() {
		return issueCost;
	}

	/**
	 * Returns the umConv.
	 * @return String
	 */
	public String getUmConv() {
		return umConv;
	}

	/**
	 * Returns the umFactor.
	 * @return BigDecimal
	 */
	public BigDecimal getUmFactor() {
		return umFactor;
	}

	/**
	 * Returns the unitOfIssueFactor.
	 * @return BigDecimal
	 */
	public BigDecimal getUnitOfIssueFactor() {
		return unitOfIssueFactor;
	}

	/**
	 * Returns the itemNumber.
	 * @return String
	 */
	public String getItemNumber() {
		return itemNumber;
	}

	/**
	 * Returns the kit.
	 * @return String
	 */
	public String getKit() {
		return kit;
	}

	/**
	 * Returns the location.
	 * @return String
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns the orderCost.
	 * @return BigDecimal
	 */
	public BigDecimal getOrderCost() {
		return orderCost;
	}

	/**
	 * Returns the owner.
	 * @return String
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Returns the receiptRequired.
	 * @return String
	 */
	public String getReceiptRequired() {
		return receiptRequired;
	}

	/**
	 * Returns the restricted.
	 * @return String
	 */
	public String getRestricted() {
		return restricted;
	}

	/**
	 * Returns the source.
	 * @return String
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Returns the taxable.
	 * @return String
	 */
	public String getTaxable() {
		return taxable;
	}

	/**
	 * Returns the udf01.
	 * @return String
	 */
	public String getUdf01() {
		return udf01;
	}

	/**
	 * Returns the udf02.
	 * @return String
	 */
	public String getUdf02() {
		return udf02;
	}

	/**
	 * Returns the udf03.
	 * @return String
	 */
	public String getUdf03() {
		return udf03;
	}

	/**
	 * Returns the udf04.
	 * @return String
	 */
	public String getUdf04() {
		return udf04;
	}

	/**
	 * Returns the udf05.
	 * @return String
	 */
	public String getUdf05() {
		return udf05;
	}

	/**
	 * Returns the unitOfIssue.
	 * @return String
	 */
	public String getUnitOfIssue() {
		return unitOfIssue;
	}

	/**
	 * Returns the unitOfOrder.
	 * @return String
	 */
	public String getUnitOfOrder() {
		return unitOfOrder;
	}

	/**
	 * Sets the catalogId.
	 * @param catalogId The catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	/**
	 * Sets the chargeable.
	 * @param chargeable The chargeable to set
	 */
	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}

	/**
	 * Sets the commodity.
	 * @param commodity The commodity to set
	 */
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the icText.
	 * @param icText The icText to set
	 */
	public void setIcText(BigDecimal icText) {
		this.icText = icText;
	}

	/**
	 * Sets the icAccount.
	 * @param icAccount The icAccount to set
	 */
	public void setIcAccount(BigDecimal icAccount) {
		this.icAccount = icAccount;
	}

	/**
	 * Sets the issueCost.
	 * @param issueCost The issueCost to set
	 */
	public void setIssueCost(BigDecimal issueCost) {
		this.issueCost = issueCost;
	}

	/**
	 * Sets the umConv.
	 * @param umConv The umConv to set
	 */
	public void setUmConv(String umConv) {
		this.umConv = umConv;
	}

	/**
	 * Sets the umFactor.
	 * @param umFactor The umFactor to set
	 */
	public void setUmFactor(BigDecimal umFactor) {
		this.umFactor = umFactor;
	}

	/**
	 * Sets the unitOfIssueFactor.
	 * @param unitOfIssueFactor The unitOfIssueFactor to set
	 */
	public void setUnitOfIssueFactor(BigDecimal unitOfIssueFactor) {
		this.unitOfIssueFactor = unitOfIssueFactor;
	}

	/**
	 * Sets the itemNumber.
	 * @param itemNumber The itemNumber to set
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * Sets the kit.
	 * @param kit The kit to set
	 */
	public void setKit(String kit) {
		this.kit = kit;
	}

	/**
	 * Sets the location.
	 * @param location The location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Sets the orderCost.
	 * @param orderCost The orderCost to set
	 */
	public void setOrderCost(BigDecimal orderCost) {
		this.orderCost = orderCost;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Sets the receiptRequired.
	 * @param receiptRequired The receiptRequired to set
	 */
	public void setReceiptRequired(String receiptRequired) {
		this.receiptRequired = receiptRequired;
	}

	/**
	 * Sets the restricted.
	 * @param restricted The restricted to set
	 */
	public void setRestricted(String restricted) {
		this.restricted = restricted;
	}

	/**
	 * Sets the source.
	 * @param source The source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Sets the taxable.
	 * @param taxable The taxable to set
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	/**
	 * Sets the udf01.
	 * @param udf01 The udf01 to set
	 */
	public void setUdf01(String udf01) {
		this.udf01 = udf01;
	}

	/**
	 * Sets the udf02.
	 * @param udf02 The udf02 to set
	 */
	public void setUdf02(String udf02) {
		this.udf02 = udf02;
	}

	/**
	 * Sets the udf03.
	 * @param udf03 The udf03 to set
	 */
	public void setUdf03(String udf03) {
		this.udf03 = udf03;
	}

	/**
	 * Sets the udf04.
	 * @param udf04 The udf04 to set
	 */
	public void setUdf04(String udf04) {
		this.udf04 = udf04;
	}

	/**
	 * Sets the udf05.
	 * @param udf05 The udf05 to set
	 */
	public void setUdf05(String udf05) {
		this.udf05 = udf05;
	}

	/**
	 * Sets the unitOfIssue.
	 * @param unitOfIssue The unitOfIssue to set
	 */
	public void setUnitOfIssue(String unitOfIssue) {
		this.unitOfIssue = unitOfIssue;
	}

	/**
	 * Sets the unitOfOrder.
	 * @param unitOfOrder The unitOfOrder to set
	 */
	public void setUnitOfOrder(String unitOfOrder) {
		this.unitOfOrder = unitOfOrder;
	}

	public String toString()
	{
		return "Lookup-" + this.getItemNumber() ;
	}

	/**
	 * Returns the autoRelease.
	 * @return boolean
	 */
	public boolean isAutoRelease() {
		return autoRelease;
	}

	/**
	 * Returns the avgCost.
	 * @return BigDecimal
	 */
	public BigDecimal getAvgCost() {
		return avgCost;
	}

	/**
	 * Returns the icHeader.
	 * @return BigDecimal
	 */
	public BigDecimal getIcHeader() {
		return icHeader;
	}

	/**
	 * Returns the icLine.
	 * @return BigDecimal
	 */
	public BigDecimal getIcLine() {
		return icLine;
	}

	/**
	 * Returns the mfgName.
	 * @return String
	 */
	public String getMfgName() {
		return mfgName;
	}

	/**
	 * Returns the model.
	 * @return String
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Returns the quantity.
	 * @return BigDecimal
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * Returns the stockEoq.
	 * @return BigDecimal
	 */
	public BigDecimal getStockEoq() {
		return stockEoq;
	}

	/**
	 * Sets the autoRelease.
	 * @param autoRelease The autoRelease to set
	 */
	public void setAutoRelease(boolean autoRelease) {
		this.autoRelease = autoRelease;
	}

	/**
	 * Sets the avgCost.
	 * @param avgCost The avgCost to set
	 */
	public void setAvgCost(BigDecimal avgCost) {
		this.avgCost = avgCost;
	}

	/**
	 * Sets the icHeader.
	 * @param icHeader The icHeader to set
	 */
	public void setIcHeader(BigDecimal icHeader) {
		this.icHeader = icHeader;
	}

	/**
	 * Sets the icLine.
	 * @param icLine The icLine to set
	 */
	public void setIcLine(BigDecimal icLine) {
		this.icLine = icLine;
	}

	/**
	 * Sets the mfgName.
	 * @param mfgName The mfgName to set
	 */
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}

	/**
	 * Sets the model.
	 * @param model The model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Sets the quantity.
	 * @param quantity The quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the stockEoq.
	 * @param stockEoq The stockEoq to set
	 */
	public void setStockEoq(BigDecimal stockEoq) {
		this.stockEoq = stockEoq;
	}

	/**
	 * Returns the udf06.
	 * @return String
	 */
	public String getUdf06() {
		return udf06;
	}

	/**
	 * Returns the udf07.
	 * @return String
	 */
	public String getUdf07() {
		return udf07;
	}

	/**
	 * Returns the udf08.
	 * @return String
	 */
	public String getUdf08() {
		return udf08;
	}

	/**
	 * Returns the udf09.
	 * @return String
	 */
	public String getUdf09() {
		return udf09;
	}

	/**
	 * Returns the udf10.
	 * @return String
	 */
	public String getUdf10() {
		return udf10;
	}	

	/**
	 * Sets the udf06.
	 * @param udf06 The udf06 to set
	 */
	public void setUdf06(String udf06) {
		this.udf06 = udf06;
	}

	/**
	 * Sets the udf07.
	 * @param udf07 The udf07 to set
	 */
	public void setUdf07(String udf07) {
		this.udf07 = udf07;
	}

	/**
	 * Sets the udf08.
	 * @param udf08 The udf08 to set
	 */
	public void setUdf08(String udf08) {
		this.udf08 = udf08;
	}

	/**
	 * Sets the udf09.
	 * @param udf09 The udf09 to set
	 */
	public void setUdf09(String udf09) {
		this.udf09 = udf09;
	}

	/**
	 * Sets the udf10.
	 * @param udf10 The udf10 to set
	 */
	public void setUdf10(String udf10) {
		this.udf10 = udf10;
	}
	
	public String getMemoLine() {
		return memoLine;
	}
	
	public void setMemoLine(String memoLine) {
		this.memoLine = memoLine;
	}

	/**
	 * Returns the asset flag.
	 */
	public String getAsset() {
		return this.asset;
	}
	/**
	 * Sets the asset flag.
	 * @param asset The asset to set
	 */
	public void setAsset(String asset) {
		this.asset = asset;
	}
    /**
     * @return Returns the actionCode.
     */
    public String getActionCode() {
        return actionCode;
    }
    /**
     * @param actionType The actionCode to set.
     */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

	/**
	 * @return the icHeaderComment
	 */
	public BigDecimal getIcHeaderComment()
	{
		return icHeaderComment;
	}

	/**
	 * @param icHeaderComment the icHeaderComment to set
	 */
	public void setIcHeaderComment(BigDecimal icHeaderComment)
	{
		this.icHeaderComment = icHeaderComment;
	}

	/**
	 * @return the icLineComment
	 */
	public BigDecimal getIcLineComment()
	{
		return icLineComment;
	}

	/**
	 * @param icLineComment the icLineComment to set
	 */
	public void setIcLineComment(BigDecimal icLineComment)
	{
		this.icLineComment = icLineComment;
	}

    /**
     * @return Returns the vendorId.
     */
    public String getVendorId() {
        return vendorId;
    }
    /**
     * @param vendorId The vendorId to set.
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

	public String getDuomUmCode() {
		return duomUmCode;
	}

	public void setDuomUmCode(String duomUmCode) {
		this.duomUmCode = duomUmCode;
	}

	public String getBlanketOrder() {
		return blanketOrder;
	}

	public void setBlanketOrder(String blanketOrder) {
		this.blanketOrder = blanketOrder;
	}

	public String getShelfLifeRqd() {
		return shelfLifeRqd;
	}

	public void setShelfLifeRqd(String shelfLifeRqd) {
		this.shelfLifeRqd = shelfLifeRqd;
	}



}
