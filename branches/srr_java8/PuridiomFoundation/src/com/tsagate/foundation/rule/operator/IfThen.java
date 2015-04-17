/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule.operator;

import java.util.List;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IfThen extends LogicOperator
{
    public boolean logicValue()
    {
        return super.logicValue();
    }
    
    public int value()
    {
        return OperatorTypes.ifthen;
    }
    
    /* Returns true if :
     * A		B		result
     * T		T		T
     * T		F		F
     * F		T		T
     * F		F		T
     * (non-Javadoc)
     * @see com.tsagate.foundation.rule.operator.ILogicOperator#evaluate(java.util.List)
     */
    public boolean evaluate(List list)
    {
        if(list.size() > 2)
        {
            return false;
        }
        else
        {
            Boolean tempA = (Boolean)list.get(0);
            Boolean tempB = (Boolean)list.get(1);
            
            if(tempA.booleanValue() && !tempB.booleanValue())
            {
                return false;
            }
        }
        return true;
    }
}
