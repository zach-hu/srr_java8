/*
 * Created on Feb 16, 2007
 */
package com.tsagate.foundation.rule.operator;

/**
 * @author Kelli
 */
public class DoesNotStartWith extends Operator
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
             return !(stringA.startsWith(stringB));
            
        }
        return false;
    }
    
    public int value()
    {
        return OperatorTypes.startsWith;
    }
}
