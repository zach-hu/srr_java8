/*
 * Created on Sep 16, 2003
 */
package com.tsagate.foundation.rule.operator;

import java.util.Comparator;

/**
 * @author renzo
 */
public class Greater extends Operator
{
    public boolean compare(Object objA, Object B)
    {
        Comparator comparator = this.getComparator(objA);
        boolean compareResult =  (comparator.compare(objA, B) > 0);
        return compareResult;

    }

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.newrule.IOperator#type()
     */
    public int value()
    {
        return OperatorTypes.greater;
    }

}
