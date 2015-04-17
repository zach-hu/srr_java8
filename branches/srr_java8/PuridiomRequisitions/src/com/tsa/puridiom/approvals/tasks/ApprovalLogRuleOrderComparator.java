/*
 * Created on Jan 25, 2007
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

/**
 * @author Kelli
 */
public class ApprovalLogRuleOrderComparator implements Comparator
{
    private int order = 1;
        

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof  Map && o2 instanceof Map)
        {
            Map	map1 = (Map) o1;
            Map	map2 = (Map) o2;
            BigDecimal bd1 = (BigDecimal) map1.get("ruleOrder");
            BigDecimal bd2 = (BigDecimal) map2.get("ruleOrder");
            String	ruleType1 = (String) map1.get("ruleType");
            String	ruleType2 = (String) map2.get("ruleType");
            
            
            int temp = bd1.compareTo(bd2);
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
