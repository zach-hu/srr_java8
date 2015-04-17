/*
 * Created on Sep 7, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoBlanketInfo.java
 *
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.math.BigDecimal;
import java.util.Date;

public class PoBlanketInfo
{
    protected String poNumber = "";
    protected String releaseNumber = "";
    protected BigDecimal releaseCount = new BigDecimal(0);
    protected BigDecimal poBlanket = new BigDecimal(0);
    protected BigDecimal poReleaseLimit = new BigDecimal(0);
    protected BigDecimal releaseTotal = new BigDecimal(0);
    protected BigDecimal orderTotal = new BigDecimal(0);
    protected Date poEffective;
    protected Date poExpires;
    protected BigDecimal availableBlanket = new BigDecimal(0);
    protected BigDecimal availableOrder = new BigDecimal(0);
    protected String boSupercedes = "";
    protected String boSuperceded = "";
    protected Integer lastRelease = new Integer(0);

    public BigDecimal getAvailableBlanket()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.availableBlanket);
    }
    public void setAvailableBlanket(BigDecimal availableBlanket)
    {
        this.availableBlanket = availableBlanket;
    }
    public BigDecimal getAvailableOrder()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.availableOrder);
    }
    public void setAvailableOrder(BigDecimal availableOrder)
    {
        this.availableOrder = availableOrder;
    }
    public String getBoSuperceded()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.boSuperceded);
    }
    public void setBoSuperceded(String boSuperceded)
    {
        this.boSuperceded = boSuperceded;
    }
    public String getBoSupercedes()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.boSuperceded);
    }
    public void setBoSupercedes(String boSupercedes)
    {
        this.boSupercedes = boSupercedes;
    }
    public BigDecimal getPoBlanket()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.poBlanket);
    }
    public void setPoBlanket(BigDecimal poBlanket)
    {
        this.poBlanket = poBlanket;
    }
    public Date getPoEffective()
    {
        return poEffective;
    }
    public void setPoEffective(Date poEffective)
    {
        this.poEffective = poEffective;
    }
    public Date getPoExpires()
    {
        return poExpires;
    }
    public void setPoExpires(Date poExpires)
    {
        this.poExpires = poExpires;
    }
    public String getPoNumber()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.poNumber);
    }
    public void setPoNumber(String poNumber)
    {
        this.poNumber = poNumber;
    }
    public BigDecimal getPoReleaseLimit()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.poReleaseLimit);
    }
    public void setPoReleaseLimit(BigDecimal poReleaseLimit)
    {
        this.poReleaseLimit = poReleaseLimit;
    }
    public BigDecimal getReleaseCount()
    {
        if (this.releaseCount.compareTo(new BigDecimal(0)) < 0) {
            this.releaseCount = new BigDecimal(0);
        }
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseCount);
    }
    public void setReleaseCount(BigDecimal releaseCount)
    {
        this.releaseCount = releaseCount;
    }
    public String getReleaseNumber()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.releaseNumber);
    }
    public void setReleaseNumber(String releaseNumber)
    {
        this.releaseNumber = releaseNumber;
    }
    public BigDecimal getReleaseTotal()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseTotal);
    }
    public void setReleaseTotal(BigDecimal releaseTotal)
    {
        this.releaseTotal = releaseTotal;
    }
    public BigDecimal getOrderTotal()
    {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.orderTotal);
    }
    public void setOrderTotal(BigDecimal orderTotal)
    {
        this.orderTotal = orderTotal;
    }
	public void setLastRelease(Integer lastRelease)
	{
		this.lastRelease = lastRelease;
	}
	public Integer getLastRelease()
	{
		return lastRelease;
	}
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[PoBlanketInfo:");
        buffer.append(" poNumber: ");
        buffer.append(poNumber);
        buffer.append(" releaseNumber: ");
        buffer.append(releaseNumber);
        buffer.append(" releaseCount: ");
        buffer.append("\r\n");
        buffer.append(releaseCount);
        buffer.append(" poBlanket: ");
        buffer.append(poBlanket);
        buffer.append(" poReleaseLimit: ");
        buffer.append(poReleaseLimit);
        buffer.append(" releaseTotal: ");
        buffer.append("\r\n");
        buffer.append(releaseTotal);
        buffer.append(" orderTotal: ");
        buffer.append(orderTotal);
        buffer.append(" poEffective: ");
        buffer.append(poEffective);
        buffer.append(" poExpires: ");
        buffer.append(poExpires);
        buffer.append("\r\n");
        buffer.append(" availableBlanket: ");
        buffer.append(availableBlanket);
        buffer.append(" availableOrder: ");
        buffer.append(availableOrder);
        buffer.append(" boSupercedes: ");
        buffer.append(boSupercedes);
        buffer.append("\r\n");
        buffer.append(" boSuperceded: ");
        buffer.append(boSuperceded);
        buffer.append("]");
        return buffer.toString();
    }
}
