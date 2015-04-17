/*
 * Created on Nov 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule.operator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexOf extends Operator
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.rule.operator.IOperator#value()
     */
    public int value()
    {
        return OperatorTypes.indexOf;
    }

    /* 
     * For strings only
     * if LEFT SIDE is part of RIGHT SIDE
     */
    public boolean compare(Object objA, Object objB)
    {
        if (objA instanceof String && objB instanceof String)
        {
            String stringA = (String)objA;
            String stringB = (String)objB;
            
            return (stringB.indexOf(stringA) > -1);
        }
        return false;
    }

}
