/*
 * Created on Jan 25, 2007
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.Comparator;

import com.tsa.puridiom.entity.ApprovalLog;

/**
 * @author Kelli
 */
public class ApprovalLogRuleTypeComparator implements Comparator
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

            return log1.getRuleType().compareTo(log2.getRuleType());
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
