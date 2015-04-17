/*
 * Created on Mar 17, 2005
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class AutoReleasedItemsSummary extends ReqEmailItemsSummary
{
    private String poNumber;
    private BigDecimal releaseNumber;
    private PoHeader releaseOrder;

    public AutoReleasedItemsSummary(RequisitionHeader header, List itemsList, PoHeader release)
    {
        super(header, itemsList);
        this.setPoData(release);
        Log.debug(this, "AutoReleasedItems for messages" + release.getDisplayPoNumber());
    }

    private void setPoData(PoHeader release)
    {
        this.setPoNumber(release.getPoNumber());
        this.setReleaseNumber(release.getReleaseNumber());
    }

    public String getPoNumber()
    {
        return poNumber;
    }

    public void setPoNumber(String poNumber)
    {
        this.poNumber = poNumber;
    }

    public BigDecimal getReleaseNumber()
    {
        return releaseNumber;
    }

    public void setReleaseNumber(BigDecimal releaseNumber)
    {
        this.releaseNumber = releaseNumber;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[AutoReleasedItemsSummary:");
        buffer.append(" poNumber: ");
        buffer.append(poNumber);
        buffer.append(" releaseNumber: ");
        buffer.append(releaseNumber);
        buffer.append(" releaseOrder: ");
        buffer.append(releaseOrder);
        buffer.append("]");
        return buffer.toString();
    }
    /**
     * @return Returns the releaseOrder.
     */
    public PoHeader getReleaseOrder()
    {
        return releaseOrder;
    }
    /**
     * @param releaseOrder The releaseOrder to set.
     */
    public void setReleaseOrder(PoHeader releaseOrder)
    {
        this.releaseOrder = releaseOrder;
    }
}
