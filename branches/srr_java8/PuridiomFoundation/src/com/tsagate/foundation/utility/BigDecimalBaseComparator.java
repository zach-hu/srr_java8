/*
 * Created on Nov 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.utility;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BigDecimalBaseComparator implements Comparator
{
    private int order = 1;
        

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int baseCompare(Object o1, Object o2)
    {
        if (o1 instanceof BigDecimal && o2 instanceof BigDecimal)
        {
            BigDecimal bd1 = (BigDecimal) o1;
            BigDecimal bd2 = (BigDecimal) o2;
            
            int temp = bd1.compareTo(bd2) * this.getOrder(); 
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
    public int compare(Object o1, Object o2)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
