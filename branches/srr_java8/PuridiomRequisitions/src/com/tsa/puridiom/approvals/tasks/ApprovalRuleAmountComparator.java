/*
 * Created on Nov 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import com.tsa.puridiom.entity.AppRule;
import com.tsagate.foundation.utility.BigDecimalBaseComparator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApprovalRuleAmountComparator extends BigDecimalBaseComparator
{

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof AppRule && o2 instanceof AppRule)
        {
            AppRule log1 = (AppRule) o1;
            AppRule log2 = (AppRule) o2;
            
            BigDecimal bd1 = log1.getUserAmount() ;
            BigDecimal bd2 = log2.getUserAmount();
            int temp = bd1.compareTo(bd2) * this.getOrder(); 
            return temp;
        }
        return 0;
    }
}