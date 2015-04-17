/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule;

import java.math.BigDecimal;

import com.tsagate.foundation.utility.BigDecimalBaseComparator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidationRuleOrderComparator extends BigDecimalBaseComparator
{

    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof ValidationRule && o2 instanceof ValidationRule)
        {
            ValidationRule v1 = (ValidationRule)o1;
            ValidationRule v2 = (ValidationRule)o2;
            BigDecimal bd1 = new BigDecimal(v1.getOrder());
            BigDecimal bd2 = new BigDecimal(v2.getOrder());
            
            return super.baseCompare(bd1, bd2);
 
        }
        else
        {
            return -1;
        }
    }
}
