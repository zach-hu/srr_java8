/*
 * Created on Nov 24, 2004
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Comparator;

import com.tsa.puridiom.entity.ApprovalLog;

/**
 * @author renzo / Kelli
 */
public class ApprovalLogAmountComparator implements Comparator
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
            
            int temp = 0;
            if ((log1.isAnAdvisor() && log2.isAnAdvisor()) || (log1.isAnAdvisor() && log2.isAnAdvisor()))
            {
                temp = 0;
            }
            else if (log1.isAnFyiApprover())
            {
                temp = 1;  
            }
            else if (log2.isAnFyiApprover())
            {
                temp = -1;  
            }
            else if (log1.isAnAdvisor())
            {
                temp = 1;  
            }
            else if (log2.isAnAdvisor())
            {
                temp = -1;  
            }
            else
            {
                BigDecimal bd1 = log1.getApproverAmount();
                BigDecimal bd2 = log2.getApproverAmount();
                temp = bd1.compareTo(bd2);
            }
            
            if (temp == 0)
            {
                String	name1 = log1.getApproverName();
                String	name2 = log2.getApproverName();
                
                temp = name1.compareTo(name2);
            }
            
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
