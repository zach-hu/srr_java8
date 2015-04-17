/**
 * Created on May 18, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.reports.itemusage.taks.ItemUsage.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;


public class ItemUsage
{
	protected String itemNumber = "";
	protected BigDecimal usageYear = new BigDecimal(0);
	protected BigDecimal usageMonth = new BigDecimal(0);
	protected BigDecimal mohCalc = new BigDecimal(0);
	protected BigDecimal eoqCalc = new BigDecimal(0);
	protected BigDecimal qoh = new BigDecimal(0);
	protected BigDecimal alloc = new BigDecimal(0);
	protected BigDecimal available = new BigDecimal(0);
	protected BigDecimal qohMonths = new BigDecimal(0);
	protected BigDecimal allocMonths = new BigDecimal(0);
	protected BigDecimal availableMonths = new BigDecimal(0);
	
	/**
	 * @return Returns the alloc.
	 */
	public BigDecimal getAlloc()
	{
		return alloc;
	}

	/**
	 * @param alloc The alloc to set.
	 */
	public void setAlloc(BigDecimal alloc)
	{
		this.alloc= alloc;
	}

	/**
	 * @return Returns the allocMonths.
	 */
	public BigDecimal getAllocMonths()
	{
		return allocMonths;
	}

	/**
	 * @param allocMonths The allocMonths to set.
	 */
	public void setAllocMonths(BigDecimal allocMonths)
	{
		this.allocMonths= allocMonths;
	}

	/**
	 * @return Returns the available.
	 */
	public BigDecimal getAvailable()
	{
		return available;
	}

	/**
	 * @param available The available to set.
	 */
	public void setAvailable(BigDecimal available)
	{
		this.available= available;
	}

	/**
	 * @return Returns the availableMonths.
	 */
	public BigDecimal getAvailableMonths()
	{
		return availableMonths;
	}

	/**
	 * @param availableMonths The availableMonths to set.
	 */
	public void setAvailableMonths(BigDecimal availableMonths)
	{
		this.availableMonths= availableMonths;
	}

	/**
	 * @return Returns the eoqCalc.
	 */
	public BigDecimal getEoqCalc()
	{
		return eoqCalc;
	}

	/**
	 * @param eoqCalc The eoqCalc to set.
	 */
	public void setEoqCalc(BigDecimal eoqCalc)
	{
		this.eoqCalc= eoqCalc;
	}

	/**
	 * @return Returns the itemNumber.
	 */
	public String getItemNumber()
	{
		return itemNumber;
	}

	/**
	 * @param itemNumber The itemNumber to set.
	 */
	public void setItemNumber(String itemNumber)
	{
		this.itemNumber= itemNumber;
	}

	/**
	 * @return Returns the mohCalc.
	 */
	public BigDecimal getMohCalc()
	{
		return mohCalc;
	}

	/**
	 * @param mohCalc The mohCalc to set.
	 */
	public void setMohCalc(BigDecimal mohCalc)
	{
		this.mohCalc= mohCalc;
	}

	/**
	 * @return Returns the qoh.
	 */
	public BigDecimal getQoh()
	{
		return qoh;
	}

	/**
	 * @param qoh The qoh to set.
	 */
	public void setQoh(BigDecimal qoh)
	{
		this.qoh= qoh;
	}

	/**
	 * @return Returns the qohMonths.
	 */
	public BigDecimal getQohMonths()
	{
		return qohMonths;
	}

	/**
	 * @param qohMonths The qohMonths to set.
	 */
	public void setQohMonths(BigDecimal qohMonths)
	{
		this.qohMonths= qohMonths;
	}

	/**
	 * @return Returns the usageMonth.
	 */
	public BigDecimal getUsageMonth()
	{
		return usageMonth;
	}

	/**
	 * @param usageMonth The usageMonth to set.
	 */
	public void setUsageMonth(BigDecimal usageMonth)
	{
		this.usageMonth= usageMonth;
	}

	/**
	 * @return Returns the usageYear.
	 */
	public BigDecimal getUsageYear()
	{
		return usageYear;
	}

	/**
	 * @param usageYear The usageYear to set.
	 */
	public void setUsageYear(BigDecimal usageYear)
	{
		this.usageYear= usageYear;
	}

}
