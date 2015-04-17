/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule.operator;

import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NewIfThen extends NewLogicOperator
{
    public boolean logicValue()
    {
        return super.logicValue();
    }

    public int value()
    {
        return OperatorTypes.ifthen;
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
        List resultList = new ArrayList();
        List tempResults = new ArrayList();
        //validating all or any.
        //will return true if any condition on the list was met.
        if(list.size() == 1)
        {
            return this.evaluateRow((List)list.get(0));
        }
        else
        {
            Object tempObjectA = list.get(0);
            Object tempObjectB = list.get(1);

            if (tempObjectA instanceof List)
            {                                                                                       //first object as list
                //cast it
                List tempListA = (List)tempObjectA;
                if(tempObjectB instanceof List)
                {                                                                   //list vs list
                    //cast it
                    List tempListB = (List)tempObjectB;
                    //compare as 2 lists.
                    if(tempListB.size() == tempListA.size() || tempListB.size() > tempListA.size())
                    {                    //both lists same size or B > A
                        for(int i =0; i < tempListA.size(); i++)
                        {
                            List subList = new ArrayList();
                            Object subObjA = tempListA.get(i);
                            Object subObjB = tempListB.get(i);
                            subList.add(subObjA);
                            subList.add(subObjB);
                            resultList.add(subList);
                            boolean tempResult = this.evaluate(subList, matchAll);
                            tempResults.add(Boolean.valueOf(tempResult));
                        }
                        return this.evaluateOneList(tempResults, matchAll);
                    }
                    else if (tempListA.size() > tempListB.size())
                    {
                        for(int i =0; i < tempListB.size(); i++)
                        {
                            List subList = new ArrayList();
                            Object subObjA = tempListA.get(i);
                            Object subObjB = tempListB.get(i);
                            subList.add(subObjA);
                            subList.add(subObjB);
                            resultList.add(subList);
                            boolean tempResult = this.evaluate(subList, matchAll);
                            tempResults.add(Boolean.valueOf(tempResult));
                        }
                        return this.evaluateOneList(tempResults, matchAll);
                    }
                }
                else
                {                                                                  //list vs object
                    for(int i = 0; i < tempListA.size(); i++)
                    {
                        List subList = new ArrayList();
                        subList.add(tempListA.get(i));
                        subList.add(tempObjectB);
                        resultList.add(subList);
                    }
                }
            }
            else
            {                                                                                       //first object as object
                if(tempObjectB instanceof List)
                {                                                                       //object vs list
                    List tempListB = (List)tempObjectB;
                    for(int i = 0; i < tempListB.size(); i++)
                    {
                        List subList = new ArrayList();
                        subList.add(tempObjectA);
                        subList.add(tempListB.get(i));
                        resultList.add(subList);
                    }
                }
                else
                {                                                                       //object vs object
                    List subList = new ArrayList();
                    subList.add(tempObjectA);
                    subList.add(tempObjectB);
                    resultList.add(subList);
                }
            }

            //compare partial results

            for(int i = 0; i < resultList.size(); i++)
            {
                List tempValues = (List)resultList.get(i);
                boolean tempResult = this.evaluateRow(tempValues);
                tempResults.add(Boolean.valueOf(tempResult));
            }
            return this.evaluateOneList(tempResults, matchAll);
        }
    }

    /* Returns true if :
     * A        B       result
     * T        T       T
     * T        F       F
     * F        T       T
     * F        F       T
     */
    public boolean evaluateRow(List list)
    {
        if(list.size() > 2)
        {
            return false;
        }
        else
        {
            Boolean tempA = (Boolean)list.get(0);
            Log.debug(this, " evaluateRow valueA\t" + tempA);
            if(tempA.booleanValue())
            {
                Object objB = list.get(1);
                Boolean tempB = (Boolean)list.get(1);
                Log.debug(this, " evaluateRow valueB\t" + tempB);

                if(tempA.booleanValue() && !tempB.booleanValue())
                {
                    Log.debug(this, " evaluateRow returns\t" + "false");
                    return false;
                }
            }
            else
            {
                return true;
            }
        }
        Log.debug(this, " evaluateRow returns\t" + "true");
        return true;
    }
}