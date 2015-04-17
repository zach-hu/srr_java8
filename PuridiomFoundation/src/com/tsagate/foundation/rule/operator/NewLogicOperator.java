/*
 * Created on Sep 18, 2003
 */
package com.tsagate.foundation.rule.operator;

import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.utility.Log;


/**
 * @author renzo
 */
public class NewLogicOperator implements ILogicOperator
{

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.newrule.ILogicOperator#logicValue()
     */
    public boolean logicValue()
    {
        return false;
    }
    public boolean evaluateOneList(List listToEvaluate)
    {
        return this.evaluateOneList(listToEvaluate, true);
    }

    public boolean evaluateOneList(List listToEvaluate, boolean matchAll)
    {
        for(int i = 0; i < listToEvaluate.size(); i++)
        {
            Boolean temp = (Boolean)listToEvaluate.get(i);
            if(!temp.booleanValue())
            {
                if(matchAll)
                {
                    return false;
                }
            }
            else
            {
                if(!matchAll)
                {
                    return true;
                }
            }
        }
        //if no elements were found to be evaluated. And error has ocurred.
        Log.debug(this, " No elements to evaluate?");
        return true;
    }

    /* All logicOperators will loop the lists this way.
     * no logic is being evaluated here
     * <b>This method just gets the results and orders them
     *      List of All clauses
     *      X       X       X ------>   Y
     *      X       X       X ------>   Y
     *      X       X       X ------>   Y
     *                                        |
     *                                        |
     *                                        |
     *                                     result
     * @see com.tsagate.common.rule.newrule.ILogicOperator#evaluate(java.util.List)
     */
    public boolean evaluate(List list, boolean matchAll)
    {
        //validating all or any.
        //will return true if any condition on the list was met.
        if(list.size() == 1)
        {
            return this.evaluateOneList((List)list.get(0), matchAll);
        }
        else
        {
            Object tempObject = list.get(0);
            if (tempObject instanceof List)
            {
                List resultList = new ArrayList();
                List tempList = (List)tempObject;
                for(int i = 0; i < tempList.size(); i++)
                {
                    List tempResultValues = new ArrayList();
                    for(int j = 0; j < list.size(); j++)
                    {
                        List jList = (List)list.get(j);
                        tempResultValues.add(jList.get(i));
                    }
                    boolean tempResult = this.evaluateOneList(tempResultValues);
                    resultList.add(Boolean.valueOf(tempResult));
                }
                return this.evaluateOneList(resultList, matchAll);
            }
            else
            {
                return ((Boolean)tempObject).booleanValue();
                //return this.evaluateOneList((List)tempObject, matchAll);
            }
        }
    }

    public int value()
    {
        return Integer.MAX_VALUE;
    }
    /* (non-Javadoc)
     * @see com.tsagate.foundation.rule.operator.ILogicOperator#evaluate(java.util.List)
     */
    public boolean evaluate(List list)
    {
        return this.evaluate(list, true);
    }
}
