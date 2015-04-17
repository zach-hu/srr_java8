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
public class StartsWith extends Operator
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.rule.operator.IOperator#compare(java.lang.Object, java.lang.Object)
     */
    public boolean compare(Object objA, Object objB)
    {
        if (objA instanceof String)
        {
            String stringA = (String)objA;
            String stringB = (String)objB;
             return (stringA.startsWith(stringB));
            
        }
        return false;
    }
    
    public int value()
    {
        return OperatorTypes.startsWith;
    }
}
