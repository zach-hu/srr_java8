/*
 * Created on Sep 18, 2003
 */
package com.tsagate.foundation.rule.operator;

import java.util.List;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class NewOr extends NewLogicOperator
{
    public boolean evaluateOneList(List listToEvaluate, boolean matchAll)
    {
        for(int i = 0; i < listToEvaluate.size(); i++)
        {
            Boolean temp = (Boolean)listToEvaluate.get(i);
            if(temp.booleanValue())
            {
                return true;
            }
        }

        //if no elements were found to be evaluated. And error has ocurred.
        Log.debug(this, " No elements to evaluate?");
        return false;
    }

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.newrule.ILogicOperator#evaluate(java.util.List)
     */
    public boolean evaluate(List list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if (list.get(i) instanceof List)
            {
                List subList = (List) list.get(i);
                for(int ib = 0; ib < subList.size(); ib++)
                {
                    Boolean temp = (Boolean)subList.get(ib);
                    if (temp.booleanValue())
                    {
                        return true;
                    }
                }
            }
            else
            {
	            Boolean temp = (Boolean)list.get(i);
	            if(temp.booleanValue())
	            {
	                return true;
	            }
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.newrule.ILogicOperator#logicValue()
     */
    public boolean logicValue()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.newrule.ILogicOperator#value()
     */
    public int value()
    {
        return OperatorTypes.or;
    }

}
