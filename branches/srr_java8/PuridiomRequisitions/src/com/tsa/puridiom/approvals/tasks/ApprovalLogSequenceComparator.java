/*
 * Created on June 21, 2006
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Comparator;

import com.tsa.puridiom.entity.ApprovalLog;

/**
 * @author Kelli
 */
public class ApprovalLogSequenceComparator implements Comparator
{
    private int order = 1;
        

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof ApprovalLog && o2 instanceof ApprovalLog)
        {
            ApprovalLog log1 = (ApprovalLog) o1;
            ApprovalLog log2 = (ApprovalLog) o2;
            BigDecimal bd1 = log1.getComp_id().getSequence();
            BigDecimal bd2 = log2.getComp_id().getSequence();
            
            int temp = bd1.compareTo(bd2);
                       
            temp = temp * this.getOrder();
            return temp;
        }
        return 0;
    }

    /**
     * @return Returns the order.
     */
    public int getOrder()
    {
        return this.order;
    }
    /**
     * @param order The order to set.
     */
    public void setOrder(String orderType)
    {
        if(orderType.equalsIgnoreCase("D"))
        {
            this.order = -1;
        }
    }
}
