/*
 * Created on Jun 24, 2004
 *
 * @author renzo
 * com.tsa.puridiom.common.documents.InventoryItemLookup.java
 * 
 */
package com.tsa.puridiom.common.documents;

import java.math.BigDecimal;

public class InventoryItemLookup extends ItemLookup
{
	protected String locIc = "";
	protected String aisle = "";
	protected String locrow = "";
	protected String tier = "";
	protected String bin = "";
	protected String vendorId = "";
	protected BigDecimal icRc = new BigDecimal(0);
	protected BigDecimal qtyAvail = new BigDecimal(0);

	/**
	 * @return Returns the aisle.
	 */
	public String getAisle()
	{
		return aisle;
	}
	/**
	 * @param aisle The aisle to set.
	 */
	public void setAisle(String aisle)
	{
		this.aisle = aisle;
	}
	/**
	 * @return Returns the bin.
	 */
	public String getBin()
	{
		return bin;
	}
	/**
	 * @param bin The bin to set.
	 */
	public void setBin(String bin)
	{
		this.bin = bin;
	}
	/**
	 * @return Returns the icRc.
	 */
	public BigDecimal getIcRc()
	{
		return icRc;
	}
	/**
	 * @param icRc The icRc to set.
	 */
	public void setIcRc(BigDecimal icRc)
	{
		this.icRc = icRc;
	}
	/**
	 * @return Returns the locIc.
	 */
	public String getLocIc()
	{
		return locIc;
	}
	/**
	 * @param locIc The locIc to set.
	 */
	public void setLocIc(String locIc)
	{
		this.locIc = locIc;
	}
	/**
	 * @return Returns the locrow.
	 */
	public String getLocrow()
	{
		return locrow;
	}
	/**
	 * @param locrow The locrow to set.
	 */
	public void setLocrow(String locrow)
	{
		this.locrow = locrow;
	}
	/**
	 * @return Returns the tier.
	 */
	public String getTier()
	{
		return tier;
	}
	/**
	 * @param tier The tier to set.
	 */
	public void setTier(String tier)
	{
		this.tier = tier;
	}
	/**
	 * @return Returns the vendorId.
	 */
	public String getVendorId()
	{
		return vendorId;
	}
	/**
	 * @param vendorId The vendorId to set.
	 */
	public void setVendorId(String vendorId)
	{
		this.vendorId = vendorId;
	}
	/**
	 * @param decimal
	 */
	public void setQtyAvail(BigDecimal available)
	{
		this.qtyAvail = available;
	}
	/**
	 * @return Returns the qtyAvail.
	 */
	public BigDecimal getQtyAvail()
	{
		return qtyAvail;
	}
}
