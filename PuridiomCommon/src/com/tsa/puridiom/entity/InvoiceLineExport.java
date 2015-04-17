package com.tsa.puridiom.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.tsa.puridiom.common.utility.HiltonUtility;

public class InvoiceLineExport
{
	private String fld1;
	private String fld2;
	private String fld3;
	private BigDecimal lineTotal;
	private BigDecimal checkAmount;
	private BigDecimal discountAmount;
	private String vendorId;
	private String vendorName;
	private String invoiceNumber;
	private Date invoiceDate = null;
	private String description;
	private String state;
	private String city;
	private String county;
	private String shipToCode;
	private String majorCategory;
	private String subCategory;

	/**
	 * @return the fld1
	 */
	public String getFld1()
	{
		return fld1;
	}
	/**
	 * @param fld1 the fld1 to set
	 */
	public void setFld1(String fld1)
	{
		this.fld1 = fld1;
	}
	/**
	 * @return the fld2
	 */
	public String getFld2()
	{
		return fld2;
	}
	/**
	 * @param fld2 the fld2 to set
	 */
	public void setFld2(String fld2)
	{
		this.fld2 = fld2;
	}
	/**
	 * @return the fld3
	 */
	public String getFld3()
	{
		return fld3;
	}
	/**
	 * @param fld3 the fld3 to set
	 */
	public void setFld3(String fld3)
	{
		this.fld3 = fld3;
	}
	/**
	 * @return the lineTotal
	 */
	public BigDecimal getLineTotal()
	{
		return HiltonUtility.ckNull(lineTotal);
	}
	/**
	 * @param lineTotal the lineTotal to set
	 */
	public void setLineTotal(BigDecimal lineTotal)
	{
		this.lineTotal = lineTotal;
	}
	/**
	 * @return the checkAmount
	 */
	public BigDecimal getCheckAmount()
	{
		return HiltonUtility.ckNull(checkAmount);
	}
	/**
	 * @param checkAmount the checkAmount to set
	 */
	public void setCheckAmount(BigDecimal checkAmount)
	{
		this.checkAmount = checkAmount;
	}
	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount()
	{
		return HiltonUtility.ckNull(discountAmount);
	}
	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(BigDecimal discountAmount)
	{
		this.discountAmount = discountAmount;
	}
	/**
	 * @return the vendorId
	 */
	public String getVendorId()
	{
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(String vendorId)
	{
		this.vendorId = vendorId;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName()
	{
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName)
	{
		this.vendorName = vendorName;
	}
	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber()
	{
		return invoiceNumber;
	}
	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber)
	{
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate()
	{
		return invoiceDate;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate)
	{
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	/**
	 * @return the county
	 */
	public String getCounty()
	{
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county)
	{
		this.county = county;
	}
	/**
	 * @return the shipToCode
	 */
	public String getShipToCode()
	{
		return shipToCode;
	}
	/**
	 * @param shipToCode the shipToCode to set
	 */
	public void setShipToCode(String shipToCode)
	{
		this.shipToCode = shipToCode;
	}
	/**
	 * @return the majorCategory
	 */
	public String getMajorCategory()
	{
		return majorCategory;
	}
	/**
	 * @param majorCategory the majorCategory to set
	 */
	public void setMajorCategory(String majorCategory)
	{
		this.majorCategory = majorCategory;
	}
	/**
	 * @return the subCategory
	 */
	public String getSubCategory()
	{
		return subCategory;
	}
	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory)
	{
		this.subCategory = subCategory;
	}
}
