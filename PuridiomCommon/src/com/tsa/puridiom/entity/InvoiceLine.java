package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvoiceLine implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icIvcLine;

    /** persistent field */
    private java.math.BigDecimal icIvcHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icPoLine;

    /** nullable persistent field */
    private String invoiceNumber;

    /** nullable persistent field */
    private java.math.BigDecimal lineNumber;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal lineTotal;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private BigDecimal orderUnitCost;

    /** nullable persistent field */
    private BigDecimal orderUmFactor;

    /** nullable persistent field */
    private BigDecimal qtyOrdered;

    /** nullable persistent field */
    private BigDecimal amountOrdered;

    /** nullable persistent field */
    private BigDecimal qtyReceived;

    /** nullable persistent field */
    private BigDecimal invoiceUnitCost;

    /** nullable persistent field */
    private BigDecimal qtyInvoiced;

    /** nullable persistent field */
    private BigDecimal amountInvoiced;

    /** nullable persistent field */
    private java.math.BigDecimal vchVariance;

    /** nullable persistent field */
    private String vchFailsafe;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

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
    private String commodity;

    /** nullable persistent field */
    private String asset;
    
    /** nullable persistent field */
    private String assetExported;

    /** nullable persistent field */
    private String exportFlag1;

    /** nullable persistent field */
    private String exportFlag2;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private java.math.BigDecimal icRelPoLine;
    
    private List accountList;

    private List budgetInfoList;

    private List receiptLineList;

    private PoLine poLine;


    /** full constructor */
    public InvoiceLine(java.math.BigDecimal icIvcLine, java.math.BigDecimal icIvcHeader, java.math.BigDecimal icPoHeader, java.math.BigDecimal icPoLine, java.lang.String invoiceNumber, java.math.BigDecimal lineNumber, java.lang.String itemNumber, java.lang.String description, java.math.BigDecimal quantity, java.lang.String umCode, java.math.BigDecimal umFactor, java.math.BigDecimal unitPrice, java.lang.String taxable, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal lineTotal, java.lang.String status, java.math.BigDecimal icLineHistory, java.math.BigDecimal shippingCharges, java.lang.String shippingTaxable, java.math.BigDecimal shippingTax, java.math.BigDecimal otherCharges, java.lang.String otherDescription, java.lang.String otherTaxable, java.math.BigDecimal otherTax, java.lang.String commodity, String asset, String assetExported, String exportFlag1, String exportFlag2, java.lang.String udf1Code) {
        this.icIvcLine = icIvcLine;
        this.icIvcHeader = icIvcHeader;
        this.icPoHeader = icPoHeader;
        this.icPoLine = icPoLine;
        this.invoiceNumber = invoiceNumber;
        this.lineNumber = lineNumber;
        this.itemNumber = itemNumber;
        this.description = description;
        this.quantity = quantity;
        this.umCode = umCode;
        this.umFactor = umFactor;
        this.unitPrice = unitPrice;
        this.taxable = taxable;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.lineTotal = lineTotal;
        this.status = status;
        this.icLineHistory = icLineHistory;
        this.shippingCharges = shippingCharges;
        this.shippingTaxable = shippingTaxable;
        this.shippingTax = shippingTax;
        this.otherCharges = otherCharges;
        this.otherDescription = otherDescription;
        this.otherTaxable = otherTaxable;
        this.otherTax = otherTax;
        this.commodity = commodity;
        this.asset = asset;
        this.assetExported = assetExported;
        this.exportFlag1 = exportFlag1;
        this.exportFlag2 = exportFlag2;
        this.udf1Code = udf1Code;
}

    /** default constructor */
    public InvoiceLine() {
    }

    /** minimal constructor */
    public InvoiceLine(java.math.BigDecimal icIvcLine, java.math.BigDecimal icIvcHeader) {
        this.icIvcLine = icIvcLine;
        this.icIvcHeader = icIvcHeader;
    }

    public java.math.BigDecimal getIcIvcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icIvcLine);
    }

    public void setIcIvcLine(java.math.BigDecimal icIvcLine) {
        this.icIvcLine = icIvcLine;
    }

    public java.math.BigDecimal getIcIvcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icIvcHeader);
    }

    public void setIcIvcHeader(java.math.BigDecimal icIvcHeader) {
        this.icIvcHeader = icIvcHeader;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.lang.String getInvoiceNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceNumber).trim();
    }

    public void setInvoiceNumber(java.lang.String invoiceNumber) {
		if (!HiltonUtility.isEmpty(invoiceNumber) && invoiceNumber.length() > 20) {
			invoiceNumber = invoiceNumber.substring(0, 20);
		}
		this.invoiceNumber = invoiceNumber;
    }

    public java.math.BigDecimal getLineNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNumber);
    }

    public void setLineNumber(java.math.BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
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

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
			description = description.substring(0, 2000);
		}
		this.description = description;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
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

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    public java.math.BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public java.math.BigDecimal getLineTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineTotal);
    }

    public void setLineTotal(java.math.BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
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

    public java.math.BigDecimal getOrderUnitCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.orderUnitCost);
    }

    public void setOrderUnitCost(java.math.BigDecimal orderUnitCost) {
        this.orderUnitCost = orderUnitCost;
    }

    public java.math.BigDecimal getOrderUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.orderUmFactor);
    }

    public void setOrderUmFactor(java.math.BigDecimal orderUmFactor) {
        this.orderUmFactor = orderUmFactor;
    }

    public java.math.BigDecimal getQtyOrdered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyOrdered);
    }

    public void setQtyOrdered(java.math.BigDecimal qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public java.math.BigDecimal getAmountOrdered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amountOrdered);
    }

    public void setAmountOrdered(java.math.BigDecimal amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public java.math.BigDecimal getQtyReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyReceived);
    }

    public void setQtyReceived(java.math.BigDecimal qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public java.math.BigDecimal getInvoiceUnitCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceUnitCost);
    }

    public void setInvoiceUnitCost(java.math.BigDecimal invoiceUnitCost) {
        this.invoiceUnitCost = invoiceUnitCost;
    }

    public java.math.BigDecimal getQtyInvoiced() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyInvoiced);
    }

    public void setQtyInvoiced(java.math.BigDecimal qtyInvoiced) {
        this.qtyInvoiced = qtyInvoiced;
    }

    public java.math.BigDecimal getAmountInvoiced() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amountInvoiced);
    }

    public void setAmountInvoiced(java.math.BigDecimal amountInvoiced) {
        this.amountInvoiced = amountInvoiced;
    }

    public java.math.BigDecimal getVchVariance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.vchVariance);
    }

    public void setVchVariance(java.math.BigDecimal vchVariance) {
        this.vchVariance = vchVariance;
    }

    public java.lang.String getVchFailsafe() {
		return (java.lang.String)HiltonUtility.ckNull(this.vchFailsafe).trim();
    }

    public void setVchFailsafe(java.lang.String vchFailsafe) {
        this.vchFailsafe = vchFailsafe;
    }

    public List getAccountList() {
		return this.accountList;
	}

	public void setAccountList(List accountList) {
		this.accountList = accountList;
	}

	/**
	 * Returns the budgetInfoList.
	 * @return List
	 */
	public List getBudgetList() {
		return budgetInfoList;
	}

	/**
	 * Sets the budgetInfoList.
	 * @param budgetInfoList The budgetInfoList to set
	 */
	public void setBudgetInfoList(List budgetInfoList) {
		this.budgetInfoList = budgetInfoList;
	}

	/**
	 * Returns the receiptLineList.
	 * @return List
	 */
	public List getReceiptLineList() {
		return receiptLineList;
	}

	/**
	 * Sets the receiptLineList.
	 * @param receiptLineList The receiptLineList to set
	 */
	public void setReceiptLineList(List receiptLineList) {
		this.receiptLineList = receiptLineList;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icIvcLine", getIcIvcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvoiceLine) ) return false;
        InvoiceLine castOther = (InvoiceLine) other;
        return new EqualsBuilder()
            .append(this.getIcIvcLine(), castOther.getIcIvcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcIvcLine())
            .toHashCode();
    }

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

    public PoLine getPoLine() {
		return poLine;
	}

	public void setPoLine(PoLine poLine) {
		this.poLine = poLine;
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

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
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

    public java.lang.String getAssetExported() {
		return (java.lang.String)HiltonUtility.ckNull(this.assetExported).trim();
    }

    public void setAssetExported(java.lang.String assetExported) {
		if (!HiltonUtility.isEmpty(assetExported) && assetExported.length() > 1) {
			assetExported = assetExported.substring(0, 1);
		}
		this.assetExported = assetExported;
    }

    public java.lang.String getExportFlag1() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportFlag1).trim();
    }

    public void setExportFlag1(java.lang.String exportFlag1) {
		if (!HiltonUtility.isEmpty(exportFlag1) && exportFlag1.length() > 1) {
			exportFlag1 = exportFlag1.substring(0, 1);
		}
		this.exportFlag1 = exportFlag1;
    }

    public java.lang.String getExportFlag2() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportFlag2).trim();
    }

    public void setExportFlag2(java.lang.String exportFlag2) {
		if (!HiltonUtility.isEmpty(exportFlag2) && exportFlag2.length() > 1) {
			exportFlag2 = exportFlag2.substring(0, 1);
		}
		this.exportFlag2 = exportFlag2;
    }

	/**
	 * @return the udf1Code
	 */
    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    /**
	 * @param udf1Code the udf1Code to set
	 */
    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 15) {
			udf1Code = udf1Code.substring(0, 15);
		}
		this.udf1Code = udf1Code;
    }

	/**
	 * @return the icRelPoLine
	 */
	public java.math.BigDecimal getIcRelPoLine()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRelPoLine);
	}

	/**
	 * @param icRelPoLine the icRelPoLine to set
	 */
	public void setIcRelPoLine(java.math.BigDecimal icRelPoLine)
	{
		this.icRelPoLine = icRelPoLine;
	}
}
