/**
 * Created on May 19, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.reports.itemusage.taks.ItemDetialUsage.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tsa.puridiom.entity.InvUsage;

/*
 * ItemDetialUsage 
 */
public class ItemDetailUsage
{
	protected String itemNumber = "";
	protected String formDate ;
	protected String description ;
	protected Date dateSent;
	protected Date returnDate;
	protected String formAnalyst;
	protected String primUser;
	protected String mailCode;
	protected String lastPrinted;
	protected BigDecimal qtyLastPrinted;
	protected BigDecimal qtyOnHand;
	protected BigDecimal qtyAlloc;
	protected BigDecimal qtyAvail;
	protected BigDecimal reorderPoint;
	protected BigDecimal qtyLast24Months;
	protected BigDecimal qtyLast12Months;
	protected BigDecimal qtyLastMonth;
	protected BigDecimal avgMonthUsage;
	protected String autoReprint;
	protected String deptCode;
	protected String docCopy;
	protected String lastPoNum;
	protected String lastVend;
	protected Date lastRecDate;
	protected BigDecimal lastQtyRec;
	protected Date lastAdjDate;
	protected BigDecimal lastQtyAdj;
	protected BigDecimal calcMoh;
	protected BigDecimal calcEoq;
	protected List usage = new ArrayList();
	
	public List getUsage()
	{
		return this.usage;
	}
	
	public void addMonthUsage(InvUsage invUsage)
	{
		this.usage.add(invUsage);
	}
	
	public InvUsage getUsage(int year, int month)
	{
		for (Iterator iter= this.usage.iterator(); iter.hasNext();)
		{
			InvUsage invUsage = (InvUsage) iter.next();
			if(year == invUsage.getUsageYear().intValue() && month == invUsage.getUsageMonth().intValue())
			{
				return invUsage;
			}
		}
		return null;
	}
	
	/**
	 * @return Returns the autoReprint.
	 */
	public String getAutoReprint()
	{
		return autoReprint;
	}

	/**
	 * @param autoReprint The autoReprint to set.
	 */
	public void setAutoReprint(String autoReprint)
	{
		this.autoReprint= autoReprint;
	}

	/**
	 * @return Returns the avgMonthUsage.
	 */
	public BigDecimal getAvgMonthUsage()
	{
		return avgMonthUsage;
	}

	/**
	 * @param avgMonthUsage The avgMonthUsage to set.
	 */
	public void setAvgMonthUsage(BigDecimal avgMonthUsage)
	{
		this.avgMonthUsage= avgMonthUsage;
	}

	/**
	 * @return Returns the calcEoq.
	 */
	public BigDecimal getCalcEoq()
	{
		return calcEoq;
	}

	/**
	 * @param calcEoq The calcEoq to set.
	 */
	public void setCalcEoq(BigDecimal calcEoq)
	{
		this.calcEoq= calcEoq;
	}

	/**
	 * @return Returns the calcMoh.
	 */
	public BigDecimal getCalcMoh()
	{
		return calcMoh;
	}

	/**
	 * @param calcMoh The calcMoh to set.
	 */
	public void setCalcMoh(BigDecimal calcMoh)
	{
		this.calcMoh= calcMoh;
	}

	/**
	 * @return Returns the dateSent.
	 */
	public Date getDateSent()
	{
		return dateSent;
	}

	/**
	 * @param dateSent The dateSent to set.
	 */
	public void setDateSent(Date dateSent)
	{
		this.dateSent= dateSent;
	}

	/**
	 * @return Returns the deptCode.
	 */
	public String getDeptCode()
	{
		return deptCode;
	}

	/**
	 * @param deptCode The deptCode to set.
	 */
	public void setDeptCode(String deptCode)
	{
		this.deptCode= deptCode;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		this.description= description;
	}

	/**
	 * @return Returns the docCopy.
	 */
	public String getDocCopy()
	{
		return docCopy;
	}

	/**
	 * @param docCopy The docCopy to set.
	 */
	public void setDocCopy(String docCopy)
	{
		this.docCopy= docCopy;
	}

	/**
	 * @return Returns the formAnalyst.
	 */
	public String getFormAnalyst()
	{
		return formAnalyst;
	}

	/**
	 * @param formAnalyst The formAnalyst to set.
	 */
	public void setFormAnalyst(String formAnalyst)
	{
		this.formAnalyst= formAnalyst;
	}

	/**
	 * @return Returns the formDate.
	 */
	public String getFormDate()
	{
		return formDate;
	}

	/**
	 * @param formDate The formDate to set.
	 */
	public void setFormDate(String formDate)
	{
		this.formDate= formDate;
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
	 * @return Returns the lastAdjDate.
	 */
	public Date getLastAdjDate()
	{
		return lastAdjDate;
	}

	/**
	 * @param lastAdjDate The lastAdjDate to set.
	 */
	public void setLastAdjDate(Date lastAdjDate)
	{
		this.lastAdjDate= lastAdjDate;
	}

	/**
	 * @return Returns the lastPoNum.
	 */
	public String getLastPoNum()
	{
		return lastPoNum;
	}

	/**
	 * @param lastPoNum The lastPoNum to set.
	 */
	public void setLastPoNum(String lastPoNum)
	{
		this.lastPoNum= lastPoNum;
	}

	/**
	 * @return Returns the lastPrinted.
	 */
	public String getLastPrinted()
	{
		return lastPrinted;
	}

	/**
	 * @param lastPrinted The lastPrinted to set.
	 */
	public void setLastPrinted(String lastPrinted)
	{
		this.lastPrinted= lastPrinted;
	}

	/**
	 * @return Returns the lastQtyAdj.
	 */
	public BigDecimal getLastQtyAdj()
	{
		return lastQtyAdj;
	}

	/**
	 * @param lastQtyAdj The lastQtyAdj to set.
	 */
	public void setLastQtyAdj(BigDecimal lastQtyAdj)
	{
		this.lastQtyAdj= lastQtyAdj;
	}

	/**
	 * @return Returns the lastQtyRec.
	 */
	public BigDecimal getLastQtyRec()
	{
		return lastQtyRec;
	}

	/**
	 * @param lastQtyRec The lastQtyRec to set.
	 */
	public void setLastQtyRec(BigDecimal lastQtyRec)
	{
		this.lastQtyRec= lastQtyRec;
	}

	/**
	 * @return Returns the lastRecDate.
	 */
	public Date getLastRecDate()
	{
		return lastRecDate;
	}

	/**
	 * @param lastRecDate The lastRecDate to set.
	 */
	public void setLastRecDate(Date lastRecDate)
	{
		this.lastRecDate= lastRecDate;
	}

	/**
	 * @return Returns the lastVend.
	 */
	public String getLastVend()
	{
		return lastVend;
	}

	/**
	 * @param lastVend The lastVend to set.
	 */
	public void setLastVend(String lastVend)
	{
		this.lastVend= lastVend;
	}

	/**
	 * @return Returns the mailCode.
	 */
	public String getMailCode()
	{
		return mailCode;
	}

	/**
	 * @param mailCode The mailCode to set.
	 */
	public void setMailCode(String mailCode)
	{
		this.mailCode= mailCode;
	}

	/**
	 * @return Returns the primUser.
	 */
	public String getPrimUser()
	{
		return primUser;
	}

	/**
	 * @param primUser The primUser to set.
	 */
	public void setPrimUser(String primUser)
	{
		this.primUser= primUser;
	}

	/**
	 * @return Returns the qtyAlloc.
	 */
	public BigDecimal getQtyAlloc()
	{
		return qtyAlloc;
	}

	/**
	 * @param qtyAlloc The qtyAlloc to set.
	 */
	public void setQtyAlloc(BigDecimal qtyAlloc)
	{
		this.qtyAlloc= qtyAlloc;
	}

	/**
	 * @return Returns the qtyAvail.
	 */
	public BigDecimal getQtyAvail()
	{
		return qtyAvail;
	}

	/**
	 * @param qtyAvail The qtyAvail to set.
	 */
	public void setQtyAvail(BigDecimal qtyAvail)
	{
		this.qtyAvail= qtyAvail;
	}

	/**
	 * @return Returns the qtyLast12Months.
	 */
	public BigDecimal getQtyLast12Months()
	{
		return qtyLast12Months;
	}

	/**
	 * @param qtyLast12Months The qtyLast12Months to set.
	 */
	public void setQtyLast12Months(BigDecimal qtyLast12Months)
	{
		this.qtyLast12Months= qtyLast12Months;
	}

	/**
	 * @return Returns the qtyLast24Months.
	 */
	public BigDecimal getQtyLast24Months()
	{
		return qtyLast24Months;
	}

	/**
	 * @param qtyLast24Months The qtyLast24Months to set.
	 */
	public void setQtyLast24Months(BigDecimal qtyLast24Months)
	{
		this.qtyLast24Months= qtyLast24Months;
	}

	/**
	 * @return Returns the qtyLastMonth.
	 */
	public BigDecimal getQtyLastMonth()
	{
		return qtyLastMonth;
	}

	/**
	 * @param qtyLastMonth The qtyLastMonth to set.
	 */
	public void setQtyLastMonth(BigDecimal qtyLastMonth)
	{
		this.qtyLastMonth= qtyLastMonth;
	}

	/**
	 * @return Returns the qtyLastPrinted.
	 */
	public BigDecimal getQtyLastPrinted()
	{
		return qtyLastPrinted;
	}

	/**
	 * @param qtyLastPrinted The qtyLastPrinted to set.
	 */
	public void setQtyLastPrinted(BigDecimal qtyLastPrinted)
	{
		this.qtyLastPrinted= qtyLastPrinted;
	}

	/**
	 * @return Returns the qtyOnHand.
	 */
	public BigDecimal getQtyOnHand()
	{
		return qtyOnHand;
	}

	/**
	 * @param qtyOnHand The qtyOnHand to set.
	 */
	public void setQtyOnHand(BigDecimal qtyOnHand)
	{
		this.qtyOnHand= qtyOnHand;
	}

	/**
	 * @return Returns the reorderPoint.
	 */
	public BigDecimal getReorderPoint()
	{
		return reorderPoint;
	}

	/**
	 * @param reorderPoint The reorderPoint to set.
	 */
	public void setReorderPoint(BigDecimal reorderPoint)
	{
		this.reorderPoint= reorderPoint;
	}

	/**
	 * @return Returns the returnDate.
	 */
	public Date getReturnDate()
	{
		return returnDate;
	}

	/**
	 * @param returnDate The returnDate to set.
	 */
	public void setReturnDate(Date returnDate)
	{
		this.returnDate= returnDate;
	}
}