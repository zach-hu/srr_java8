/*
 * Created on Nov 26, 2003
 */
package com.tsagate.foundation.rule;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.exceptions.DatabaseConnectionException;
import com.tsagate.foundation.rule.operator.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author renzo
 *
 * This is an updated / enhanced version of the Rule
 */
public class RuleNew extends Rule
{
    public boolean evaluate(Map map)
    {
        boolean bret = false;
        Log.debug(this, "evaluating rule: " + this.name);
        Object oRet = null;
        try
        {
            List values = new ArrayList();
            int subrules = 0;

            for (int i = 0; i < this.evaluations.size(); i++)
            {
                Clause clause = (Clause) this.evaluations.get(i);

                if(clause.getClauseType().equalsIgnoreCase("Rule"))
                {
                    Rule subRule = null;;
                    if(!clause.isRuleNew())
                    {
                        subRule = new Rule(clause.getRuleName(), map, this.getApplicationName());
                    }
                    else
                    {
                        subRule = new RuleNew(clause.getRuleName(), map, this.getApplicationName());
                    }
                    if(clause.isCheckForFalse())
                    {
                        bret = !subRule.evaluate(map);
                    }
                    else
                    {
                        bret = subRule.evaluate(map);
                    }
                    oRet = Boolean.valueOf(bret);
                }
                else if(clause.getClauseType().equalsIgnoreCase("lookup"))
                {
                    oRet = this.evaluateLookup((LookupClause)clause, map);
                }
                else if(clause.getClauseType().equalsIgnoreCase("empty"))
                {
                    Log.debug(this, "empty rule " + clause.getLeftExpression());
                    Object object = this.getObjectFromClauseElement(clause.getLeftExpression(), map);
                    oRet = this.isEmpty(object, clause.isMatchAll());
                    Log.debug(this, "empty returns: " + bret);
                }
                else if(clause.getClauseType().equalsIgnoreCase("NotEmpty"))
                {
                    Object object = this.getObjectFromClauseElement(clause.getLeftExpression(), map);
                    oRet = this.isNotEmpty(object, clause.isMatchAll());
                }
                else if(clause.getClauseType().indexOf("AccountRule-Line") > -1)
                {
                    List accountLines = (List)this.getValueFromIncomingRequest("accountLineList", map);
                    List accountHeader = (List)this.getValueFromIncomingRequest("accounts", map);

                    oRet = this.accountRule(accountLines, accountHeader, clause, map);
                    //oRet = Boolean.valueOf(bret);
                }
                else if(clause.getClauseType().indexOf("AccountRule-Header") > -1)
                {
                    List accountHeader = (List)this.getValueFromIncomingRequest("accounts", map);

                    bret = this.accountRule(accountHeader, clause, map);
                    oRet = Boolean.valueOf(bret);
                }
                else
                {
                    ClauseElement ceA = clause.getLeftExpression();
                    ClauseElement ceB = clause.getRightExpression();
                    Object objectA = this.getObjectFromClauseElement(ceA, map);
                    Object objectB = this.getObjectFromClauseElement(ceB, map);
                    oRet = this.isTrue(objectA, objectB, clause.getOperator(), clause.isMatchAll());
                }

                clause.setEvaluation(bret);
                clause.setReturnedResults(oRet);
                clause.setEvaluationComplete(true);

                this.evaluations.set(i, clause);
            }

            List resultValues = new ArrayList();
            for (int i=0; i < this.evaluations.size(); i++)
            {
                Clause clause = (Clause) this.evaluations.get(i);
                resultValues.add(clause.getReturnedResults());
            }
            this.result = this.logicValue(resultValues);
        }
        catch (Exception e)
        {
            Log.error(this, "Error processing - " + this.name);
            e.printStackTrace();
            this.result = false;
        }
        Log.debug(this, this.name + ": " + this.result);
        return this.result;
    }

    /**
     * @param object
     * @param accountHeader
     * @param clauseElement
     * @return
     * @throws Exception
     */
    private boolean accountRule(List accountHeader, Clause clause, Map incomingRequest) throws Exception
    {
        ClauseElement clauseElement = clause.getLeftExpression();
        boolean ret = false;
        boolean headerEval = false;
        List tempResults = new ArrayList();
        String ruleType = clause.getClauseType();

        if(accountHeader.size() > 0)
        {
            if(ruleType.indexOf("lookup") > 0 && !headerEval)
            {
                this.accountLookup(clause, accountHeader, tempResults, incomingRequest);
            }
        }
        else
        {
            tempResults.add(new Boolean(true));
        }
        And result = new And();
        ret = result.evaluate(tempResults);

        return ret;
    }
    /**
     * @param object
     * @param accountHeader
     * @param clauseElement
     * @return
     * @throws Exception
     */
    private Object accountRule(List accountLines, List accountHeader, Clause clause, Map incomingRequest) throws Exception
    {
        ClauseElement clauseElement = clause.getLeftExpression();
        boolean ret = false;
        boolean headerEval = false;
        List tempResults = new ArrayList();
        String ruleType = clause.getClauseType();
        for(int i = 0; i < accountLines.size(); i++)
        {
            List tempList = (List)accountLines.get(i);
            if(tempList.size() < 1)
            {
                tempResults.add(new Boolean(true));
            }
            else
            {
                //evaluate line account
                if(ruleType.indexOf("lookup") > 0)
                {
                    //for(int j = 0; j < tempList.size(); j++)
                    //{
                        //Object account = tempList.get(j);
                        this.accountLookup(clause, tempList, tempResults, incomingRequest);
                    //}
                }
            }
        }
        return tempResults;
    }

    private void accountLookup(Clause clause, List accountList, List resultsList, Map incomingRequest)
    {
        boolean ret = false;

        LookupClause clauseLookup = (LookupClause)clause;
        List valuesList = new ArrayList();
        List sqlList = new ArrayList();
        Object returnValues = null;

        for(int j = 0; j < accountList.size(); j++)
        {
            Object account = accountList.get(j);
            String newSql = this.getSqlList(clauseLookup, account, clauseLookup.getArguments(), clauseLookup.getSql(), valuesList);
            sqlList.add(newSql);
        }
        try
        {
            returnValues = this.executeLookupSql(incomingRequest, clauseLookup, sqlList, valuesList);
        }
        catch (Exception e)
        {
            ret = false;
            returnValues = Boolean.FALSE;
            e.printStackTrace();
        }
        if (returnValues instanceof List) {
            List returnValuesList = (List) returnValues;
            resultsList.addAll(returnValuesList);
        } else {
            resultsList.add(returnValues);
        }
    }

    private Object isNotEmpty(Object object, boolean matchAll)
    {
        boolean bret = true;
        List results = new ArrayList();
        if (object instanceof List)
        {
            List listObj = (List) object;
            for(int i = 0; i < listObj.size(); i++)
            {
                Object resultObj = listObj.get(i);
                if (resultObj instanceof List)
                {
                    List resultList = (List) resultObj;
                    List tempResults = new ArrayList();
                    for (int il = 0; il < resultList.size(); il++)
                    {
                        boolean truth = !Utility.isObjectEmpty(resultList.get(il));
                        tempResults.add(Boolean.valueOf(truth));
                    }
                    results.add(tempResults);
                }
                else
                {
	                boolean truth = !Utility.isObjectEmpty(listObj.get(i));
	                /*if((matchAll && truth) || (!matchAll && !truth))
	                {
	                    bret = !truth;
	                    i = listObj.size();
	                }
	                */
	                results.add(Boolean.valueOf(truth));
                }
            }
        }
        else
        {
            if(object != null)
            {
                bret = !Utility.isObjectEmpty(object);
            }
            else
            {
                bret = false;
            }
            results.add(Boolean.valueOf(bret));
        }
        return results;
    }
    private Object isEmpty(Object object, boolean matchAll)
    {
        boolean bret = true;
        List results = new ArrayList();
        if (object instanceof List)
        {
            List listObj = (List) object;
            for(int i = 0; i < listObj.size(); i++)
            {
                boolean truth = Utility.isObjectEmpty(listObj.get(i));
                /*if((matchAll && !truth) || (!matchAll && truth))
                {
                    bret = truth;
                    i = listObj.size();
                }
                */
                results.add(Boolean.valueOf(truth));
            }
        }
        else
        {
            if(object != null)
            {
                bret = Utility.isObjectEmpty(object);
            }
            else
            {
                bret = true;
            }
            results.add(Boolean.valueOf(bret));
        }
        return results;

    }
    /**
     * @param object
     * @return
     */
    private Object isEmpty(Object object)
    {
        return this.isEmpty(object, true);
    }



    /**
     * @param clause
     * @return
     */
    private Object evaluateLookup(LookupClause clause, Object object)
    {
        Map incomingRequest = (Map)object;
        boolean ret = false;
        List valuesList = new ArrayList();
        Object returnValues = null;
        try
        {
            List arguments = clause.getArguments();
            List sqlList = new ArrayList();
            String originalSql = clause.getSql();

            String source = clause.getSource();

            if(source.equalsIgnoreCase("header"))
            {
                Object objSource = incomingRequest.get(source);
                if(objSource instanceof List)
                {
                    objSource = ((List)objSource).get(0);
                }
                String newSql = this.getSqlList(clause, objSource, arguments, originalSql, valuesList);
                sqlList.add(newSql);
            }
            else
            {
                List sourceList = (List)incomingRequest.get(source);
                List newSourceList = new ArrayList();
                if(!Utility.isEmpty(clause.getFromObject()))
                {
                    for(int i = 0; i < sourceList.size(); i++)
                    {
                        Object listElement = sourceList.get(i);
                        Object tempObject = this.getValueFromMethod(listElement, clause.getFromObject());
                        List tempList = new ArrayList();
                        if (tempObject != null)
                        {
                            tempList = (List) tempObject;
                        }
                        newSourceList.add(tempList);
                    }
                    sourceList = newSourceList;
                }

                if (sourceList != null)
                {
	                for (int i = 0; i < sourceList.size(); i++)
	                {
	                    Object sourceObject = sourceList.get(i);
	                    if (sourceObject instanceof List)
	                    {
	                        List sourceObjectList = (List) sourceObject;
	                        List tempSqlList = new ArrayList();
	                        for (int il = 0; il < sourceObjectList.size(); il++)
	                        {
	    	                    Object sourceElement = sourceObjectList.get(il);
	    	                    //insert code to keep track of line # or/and account secuence
	    	                    String newSql = this.getSqlList(clause, sourceElement, arguments, originalSql, valuesList);
	    	                    tempSqlList.add(newSql);
	                        }
	                        sqlList.add(tempSqlList);
	                    }
	                    else
	                    {
		                    Object sourceElement = sourceObject;
		                    //insert code to keep track of line # or/and account secuence
		                    String newSql = this.getSqlList(clause, sourceElement, arguments, originalSql, valuesList);
		                    sqlList.add(newSql);
	                    }
	                }
                }
            }

            returnValues =  this.executeLookupSql(incomingRequest, clause, sqlList, valuesList);
        }
        catch (Exception e)
        {
            Log.error(this.getName(), e.toString());
        }

        clause.setValues(valuesList);
        return returnValues;
    }



    /**
     * @return
     * @throws Exception
     */
    private Object executeLookupSql(Map incomingRequest, LookupClause clause, List sqlList, List valuesList) throws Exception
    {
        boolean ret = false;
        DBSession dbs = null;
        String	foreignDatabase = Utility.ckNull(clause.getForeignDatabase());
        String	organizationId = Utility.ckNull((String) incomingRequest.get("organizationId"));
        boolean newConnection = false;
        boolean matchAll = clause.isMatchAll();

        if (!Utility.isEmpty(foreignDatabase) && !organizationId.equals(foreignDatabase)) {
            try {
                dbs = new DBSession(foreignDatabase);
                dbs.noUpdate();

                newConnection = true;
            }
    		catch(DatabaseConnectionException e) {
    		    incomingRequest.put("errorMsg", e.getMessage());
    		    throw e;
    		}
    		catch(Exception e) {
    		    e.printStackTrace();
    			Log.error(this, "Error occured starting database!");
    		}
        } else {
            dbs = (DBSession)incomingRequest.get("dbsession");
        }
        List clauseResults = new ArrayList();
        for(int i = 0; i < sqlList.size(); i++)
        {
            Object sqlObject = sqlList.get(i);
            if (sqlObject instanceof List)
            {
                List	sqlQueryList = (List) sqlObject;
                List resultList = new ArrayList();
                for (int il = 0; il < sqlQueryList.size(); il++)
                {
                    String	sqlQuery = (String) sqlQueryList.get(il);
                    ret = executeSingleSqlLookup(sqlQuery, dbs, matchAll, (String) incomingRequest.get("timeZone"));

                    resultList.add(new Boolean(ret));
                }
	            clauseResults.add(resultList);
            }
            else
            {
                String	sqlQuery = (String) sqlObject;
                ret = executeSingleSqlLookup(sqlQuery, dbs, matchAll, (String) incomingRequest.get("timeZone"));

	            clauseResults.add(new Boolean(ret));
            }
        }

        if (!Utility.isEmpty(foreignDatabase)) {
            if (dbs != null) {
                dbs.close() ;
            }
        }

        return clauseResults;
    }

    public boolean executeSingleSqlLookup(String sqlQuery, DBSession dbs, boolean matchAll, String timeZone) throws Exception
    {
        List dateArguments = new ArrayList();
        boolean ret = false;

        while (sqlQuery.indexOf("@today@") > 0)
        {
            dateArguments.add(Dates.getDate(Dates.today("", timeZone)));
            sqlQuery = sqlQuery.replaceFirst("@today@", "?");
        }

        Object arguments[] = new Object[dateArguments.size()];
        for (int ia = 0; ia < dateArguments.size(); ia++)
        {
            arguments[ia] = dateArguments.get(ia);
        }

        List found = dbs.query(sqlQuery, arguments);
        if(found != null)
        {
            if(found.size() == 1 && !matchAll)
            {
                ret = true;
                //i = sqlList.size();
            }
            else if(found.size() == 1 && matchAll)
            {
                ret = true;
            }
            else if(found.size() < 1 && matchAll)
            {
                ret = false;
                //i = sqlList.size();
            }
            else if(found.size() < 1 && !matchAll)
            {
                ret = false;
            }
        }
        else
        {
            if(matchAll)
            {
                //i = sqlList.size();
                ret = false;
            }
            else
            {
                ret = false;
            }
        }
        return ret;
    }

    public String getSqlList(LookupClause clause, Object source, List arguments, String newSql, List valuesList)
    {
        try
        {
            Class c = source.getClass();
            for (int arg = 0; arg < arguments.size(); arg++)
            {
                String key = (String)arguments.get(arg);
                String toReplace = "@" + key + "@";
                String tempValue = "";

                String methodName = clause.getArgColName(key);

                Method method = c.getMethod(methodName, null);
                tempValue = method.invoke(source, null).toString();

                newSql = newSql.replaceAll(toReplace, tempValue);
                valuesList.add(tempValue);
            }
        }
        catch (Exception e)
        {
            Log.error(e, "getSqlList for: " + arguments);
        }
        return newSql;
    }

    /**
     * getValueFromIncomingRequest
     * @param fieldName object to get
     * @param map incomingRequest
     * @return
     */
    private Object getValueFromIncomingRequest(String fieldName, Map map)
    {
        return map.get(fieldName);
    }
    /**
     * logicValue
     * @param truthValues
     * @return
     */
    private boolean logicValue(List truthValues)
    {
        LogicOperator logOp = new LogicOperator();

        if(this.logicOps.size() < 1)
        {
            if (truthValues.size() > 0)
            {
                Object tempObject = truthValues.get(0);
                if (tempObject instanceof List)
                {
                    List tempList = (List) tempObject;
                    NewAnd and = new NewAnd();
                    List results = new ArrayList();
                    results.add(tempList);
                    return and.evaluate(results);
                }
                else
                {
                    return ((Boolean)tempObject).booleanValue();
                }
            }
            return false;
        }

        /*if(((LogicOperator)this.logicOps.get(0)).logicValue())
        {
            logOp = new And();
        }
        else
        {
            logOp = new Or();
        }*/

        if (this.logicOps.get(0) instanceof NewLogicOperator) {
            NewLogicOperator newLogOp = (NewLogicOperator) this.logicOps.get(0);
            return (newLogOp).evaluate(truthValues);
        } else {
            LogicOperator logicOperator = (LogicOperator) this.logicOps.get(0);
            return (logicOperator).evaluate(truthValues);
        }
    }

    public RuleNew()
    {
        this.evaluations = new ArrayList();
        this.logicOps = new ArrayList();
    }
    public RuleNew(String name, Map mapValues)
    {
        this(name, mapValues, null);
    }

    public RuleNew(String name, Map mapValues, String applicationName)
    {
        this();
        this.name = name;

        if (applicationName == null || applicationName.trim().length() <= 0)
        {
            applicationName = this.getApplicationName();
        }
        if (applicationName != null && applicationName.trim().length() > 0)
        {
            this.applicationName = applicationName;
            applicationName = applicationName + "-";
        }

        XMLRuleLoader loader = new XMLRuleLoader();
        String organizationId = "";
        String isValidationRule = "false";
        String isAppExtRules = "false";

        if(mapValues != null)
        {
            organizationId = (String)mapValues.get("organizationId");
            if(organizationId == null)
            {
                organizationId = "";
            }
            isValidationRule = (String)mapValues.get("isValidationRule");
            if(isValidationRule == null) {	isValidationRule = "false";	}
            isAppExtRules = (String)mapValues.get("isAppExtRules");
            if(isAppExtRules == null) {	isAppExtRules = "false";	}
        }

        String fromPath = "rule-xml-path";

        if(isValidationRule.equalsIgnoreCase("true"))
        {
            fromPath = "validation-rule-xml-path";
        }
        
        String filename = DictionaryManager.getInstance("host", organizationId).getProperty(applicationName + fromPath, "");
        filename += name;
        loader.loadRule(filename, this, mapValues);
    }

    public RuleNew(String name)
    {
        this(name, null);
    }

    public void addLogicOp(ILogicOperator logicOp)
    {
        this.logicOps.add(logicOp);
    }

    public void addEvaluation(Object object)
    {
        evaluations.add(object);
    }

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public void loadRule(String name)
    {
        this.setName(name);
    }

    private Object isTrue(Object clauseObject, Object comparisonObject, IOperator condition, boolean matchAll)
    {
        boolean truth = false;
        List objectListA = null;
        List objectListB = null;
        List results = new ArrayList();

        if(clauseObject != null)
        {
            Log.debug(this, "objectA = " + clauseObject.toString());
        }
        if(comparisonObject != null)
        {
            Log.debug(this, "objectB = " + comparisonObject.toString());
        }
        if (clauseObject instanceof List)
        {
            objectListA = (List) clauseObject;
        }

        if (comparisonObject instanceof List)
        {
            objectListB = (List) clauseObject;
        }

        if(objectListA != null && objectListB != null)
        {
            if(objectListA.size() == objectListB.size())
            {
                for(int i = 0; i < objectListA.size(); i++)
                {
                    Object objA = objectListA.get(i);
                    Object objB = objectListB.get(i);
                    truth = condition.compare(objA, objB);
                    /*if((matchAll && !truth) || (!matchAll && truth))
                    {
                        i = objectListA.size();
                    }*/
                    results.add(Boolean.valueOf(truth));
                }
            }
            else
            {
                Log.error(this.getName(), "List are of different size could not compare them");
            }
        }
        else if(objectListA != null || objectListB != null)
        {
            List templist = null;
            Object objB = null;

            if(objectListA != null)
            {
                templist = objectListA;
                objB = comparisonObject;
            }
            else if(objectListB != null)
            {
                templist = objectListB;
                objB = clauseObject;
            }
            for(int i = 0; i < templist.size(); i++)
            {
                Object objA = templist.get(i);
                truth = condition.compare(objA, objB);
                //looping through a list if matchall false evaluation will return true if just one element is true
                /*if((matchAll && !truth) || (!matchAll && truth))
                {
                    i = templist.size();
                }*/
                results.add(Boolean.valueOf(truth));
            }
        }
        else
        {
            truth = condition.compare(clauseObject, comparisonObject);
            results.add(Boolean.valueOf(truth));
        }
        return results;
    }

    /**
     * @param clauseObject
     * @param comparisonObject
     * @param condition
     * if one or both objects are lists will return true only if all elements of the list
     * meet the criteria and the lists are of the same size.
     * should move object out to its own class!
     * @return
     */
    /*public Boolean isTrue(Object clauseObject, Object comparisonObject, IOperator condition)
    {
        return this.isTrue(clauseObject, comparisonObject, condition, true);
    }*/

    public Object getValueFromSingleton(String object, String methodName, List args, Map incomingRequest)
    {
        Object result = null;
        try
        {
            Class singeltonClass = Class.forName(object);
            Method singeltonMethod = null;
            Object singelton = null;

            try
            {
                singeltonMethod = singeltonClass.getMethod("getInstance", new Class[]{String.class});
                String temp = (String)incomingRequest.get("organizationId");
                if(temp == null)
                {
                    temp = "PURIDIOM";
                }
                Object arguments[] = {temp};
                singelton = singeltonMethod.invoke(null,  arguments);
            }
            catch (NoSuchMethodException nsme)
            {
                singeltonMethod = singeltonClass.getMethod("getInstance", new Class[]{});
                singelton = singeltonMethod.invoke(null, null);
            }

            Class staticClass = singelton.getClass();
            Method methods[] = staticClass.getMethods();
            for(int i = 0; i < methods.length; i++)
            {
                String name = methods[i].getName();

                if(name.equals(methodName))
                {
                    Object argsObj[] = args.toArray();
                    result = methods[i].invoke(singelton, argsObj);
                }
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromSingleton");
            exception.printStackTrace();
        }

        return result;
    }

    public Object getValueFromIncomingRequest(String key, String methodName, Map incomingRequest, List arguments)
    {
        Object obj = this.getValueFromIncomingRequest(key, methodName, incomingRequest);
        String fromObjMethod = this.getFromObjectMethodName(arguments);
        return this.getValueFromMethod(obj, fromObjMethod);
    }

    public Object getValueFromIncomingRequest(String key, String methodName, Map incomingRequest)
    {
        Object obj = incomingRequest.get(key);
        return this.getValueFromMethod(obj, methodName);
    }

    public Object getValueFromMethod(Object obj, String methodName)
    {
        Object result = null;
        try
        {
            if (obj != null)
            {
            	if (!(obj instanceof List))
				{
            		Class c = obj.getClass();
                    Method method = c.getMethod(methodName, null);
                    result = method.invoke(obj, null);
                    Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
				}
            	else
            	{
            		List tempList = (List)obj;
            		List valueList = new ArrayList();
            		for(int i =0; i < tempList.size(); i++)
            		{
            			Object tempObject = tempList.get(i);
            			Class c = tempObject.getClass();
                        Method method = c.getMethod(methodName, null);
                        valueList.add(method.invoke(tempObject, null));
                        Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
            		}
            		result = valueList;
            	}
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromMethod for " + this.applicationName + " - " + this.name);
            exception.printStackTrace();
        }
        return result;
    }

    public Object getValueFromMethodWithArguments(String object, String methodName, List args)
    {
        Object result = null;
        try
        {
            Class classRuntime = Class.forName(object);

            Method methods[] = classRuntime.getMethods();
            for(int i = 0; i < methods.length; i++)
            {
                String name = methods[i].getName();
                if(name.equals(methodName))
                {
                    Object argsObj[] = args.toArray();
                    result = methods[i].invoke(classRuntime, argsObj);
                }
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromMethodWithArguments");
            exception.printStackTrace();
        }

        return result;

    }

    private Object getObjectFromClauseElement(ClauseElement ce, Map map) throws Exception
    {
        Object o = null;

        try
        {
            o = this.getValue(ce, map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new TsaException(this.name, e);
        }

        return o;
    }

    /**
     * @param ce
     * @return
     */
    public Object getValue(ClauseElement ce, Map map)
    {

        Object value = null;
        String source = ce.getSource();

        if (source.equalsIgnoreCase("constant"))
        {
            value = ce.getValue();
            Log.debug(this, "constant- " + value );
        }
        else if (source.equalsIgnoreCase("singleton"))
        {
            List arguments = ce.getArguments();
            List	stringArgs = new ArrayList();
            if (arguments != null && arguments.size() > 0)
            {
                for (int i = 0; i < arguments.size(); i++ )
                {
                    ArgumentElement argElement = (ArgumentElement) arguments.get(i);
                    try
                    {
                        stringArgs.add(i, argElement.getValue(map));
                    }
                    catch (Exception e)
                    {
                        Log.error(this, "Error occured finding arguments");
                        e.printStackTrace();
                    }
                }
            }
            value = this.getValueFromSingleton(ce.getObject(), ce.getName(), stringArgs, map);
        }
        else if (source.equalsIgnoreCase("incomingRequest"))
        {
            value = this.getValueFromIncomingRequest(ce.getName(), map);
        }
        else if(source.equalsIgnoreCase("incomingRequestList"))
        {
            if(this.isFromObject(ce.getArguments()))
            {
                value = this.getValueFromList(ce, map, true);
            }
            else
            {
                value = this.getValueFromList(ce, map);
            }
        }
        else
        {
            if (ce.getArguments().size() > 0)
            {
                if(this.isFromObject(ce.getArguments()))
                {
                    value = this.getValueFromIncomingRequest(ce.getObject(), ce.getName(), map, ce.getArguments());
                }
                else
                {
                    value = this.getValueFromIncomingRequest(ce.getObject(), ce.getName(), map);
                }
            }
            else
            {
                value = this.getValueFromIncomingRequest(ce.getObject(), ce.getName(), map);
            }
        }

        return this.formatValue(ce, value);
    }

    private boolean isFromObject(List argsList)
    {
        boolean isFrom = false;
        if(argsList != null)
        {
            for(int i = 0; i < argsList.size(); i++)
            {
                ArgumentElement argu = (ArgumentElement)argsList.get(i);

                if(argu.getValue().equalsIgnoreCase("fromObject"))
                {
                    isFrom = true;
                    i = argsList.size();
                }
            }
        }
        return isFrom;
    }
    private Object getValueFromList(ClauseElement ce, Map incomingRequest)
    {
        return this.getValueFromList(ce, incomingRequest, false);
    }
    /**
     * @param ce
     * @param map
     * this method can be extended to retrieve values from methods with arguments!
     * @return
     */
    private Object getValueFromList(ClauseElement ce, Map incomingRequest, boolean fromObject)
    {
        Log.debug(this, "getValueFromList.. start");
        Object key = ce.getObject();
        Object obj = incomingRequest.get(key);
        List values = new ArrayList();

        if (obj instanceof List)
        {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++)
            {
                Object listObject = list.get(i);

                if (listObject instanceof List)
                {
                    List objectList = (List) listObject;
                    List resultList = new ArrayList();
                    for (int il = 0; il < objectList.size(); il++)
                    {
                        Object listElement = objectList.get(il);
                        Object partialValue = this.getValueFromMethod(listElement, ce.getName());

    	                if(fromObject)
    	                {
    	                    partialValue = this.getValueFromMethod(partialValue, ce.getArguments());
    	                }
    	                if (partialValue instanceof List) {
    	                    List tempie = (List) partialValue;
    	                    if(tempie != null)
    	                    {
    	                        if(tempie.size() < 1)
    	                        {
    	                            resultList.add(null);
    	                        }
    	                        else
    	                        {
    	                            resultList.addAll(tempie);
    	                        }
    	                    }
    	                    else
    	                    {
    	                        resultList.add(null);
    	                    }

    	                }
    	                else
    	                {
    	                    resultList.add(partialValue);
    	                }
                    }
                    values.add(resultList);
                }
                else
                {
                    Object listElement = list.get(i);
	                Object partialValue = this.getValueFromMethod(listElement, ce.getName());

	                if(fromObject)
	                {
	                    partialValue = this.getValueFromMethod(partialValue, ce.getArguments());
	                }
	                if (partialValue instanceof List) {
	                    List tempie = (List) partialValue;
	                    if(tempie != null)
	                    {
	                        if(tempie.size() < 1)
	                        {
	                            values.add(null);
	                        }
	                        else
	                        {
	                            values.addAll(tempie);
	                        }
	                    }
	                    else
	                    {
	                        values.add(null);
	                    }

	                }
	                else
	                {
	                    values.add(partialValue);
	                }
                }
            }
        }
        else
        {

        }

        Log.debug(this, "getValueFromList.. returns: " + values.toString());

        return values;
    }

    private String getFromObjectMethodName(List arguments)
    {
        String ret = "";
        for(int i = 0; i < arguments.size(); i++)
        {
            ArgumentElement argu = (ArgumentElement)arguments.get(i);
            if(!(argu.getValue().equalsIgnoreCase("fromobject")))
            {
                ret = argu.getValue();
                i = arguments.size();
            }
        }
        return ret;
    }
    /**
     * @param partialValue
     * @param arguments
     * @return
     */
    private Object getValueFromMethod(Object partialValue, List arguments)
    {
        String ret = this.getFromObjectMethodName(arguments);
        Object retObject = null;

        if(ret.indexOf("size") < 0)
        {
            if (partialValue instanceof List)
            {
                List tempList = (List) partialValue;
                List tempValues = new ArrayList();
                for(int i = 0; i < tempList.size(); i++)
                {
                    partialValue = tempList.get(i);
                    tempValues.add(this.getValueFromMethod(partialValue, ret));
                }
                retObject = tempValues;
            }
        }
        else
        {
            return this.getValueFromMethod(partialValue, ret);
        }
        return retObject;

    }
    /**
     * @param ce
     * @return
     */
    private Object formatValue(ClauseElement ce, Object value)
    {
        Object o = null;
        if (value != null)
        {
            if(value instanceof List && (ce.getSource().equalsIgnoreCase("incomingRequestList") || ce.getSource().equalsIgnoreCase("incomingRequestObjectMethod")))
            {
                List templist  = (List)value;
                for(int i = 0; i < templist.size(); i++)
                {
                    Object obj  = this.formatValue(ce, templist.get(i));
                    templist.set(i, obj);
                }
                o = templist;
            }
            else
            {
                if (ce.getType().equalsIgnoreCase("DateCompare"))
                {
                    o = Dates.getDate(String.valueOf(value));
                }
                else if (ce.getType().equalsIgnoreCase("DateTimeCompare"))
                {
                    if (value instanceof Date) {
                        o = value;
                    } else {
                        o = Dates.getDateTime(String.valueOf(value));
                    }
                }
                else if (ce.getType().equalsIgnoreCase("DoubleCompare") || ce.getType().equalsIgnoreCase("NumberCompare"))
                {
                    try
                    {
                        o = new Double(String.valueOf(value));
                    }
                    catch(NumberFormatException nfe)
                    {
                        o = new Double(0);
                    }
                }
                else if (ce.getType().equalsIgnoreCase("IntegerCompare"))
                {
                    try
                    {
                        o = new Integer(String.valueOf(value));
                    }
                    catch(NumberFormatException nfe)
                    {
                        o = new Integer(0);
                    }
                }
                else if (ce.getType().equalsIgnoreCase("BooleanCompare"))
                {
                    o = new Boolean(String.valueOf(value));
                }
                else if (ce.getType().equalsIgnoreCase("BigDecimalCompare"))
                {
                    try
                    {
                        o = new BigDecimal(String.valueOf(value)) ;
                    }
                    catch(NumberFormatException nfe)
                    {
                        o = new BigDecimal(0);
                    }
                }
                else
                {//default case a string
                    o = String.valueOf(value);
                }
            }
        }

        return o;
    }
    public String getApplicationName() {
        return this.applicationName;
    }
    public void setApplicationName(String value) {
        this.applicationName = value;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[Rule:");
        buffer.append(" name: ");
        buffer.append(name);
        buffer.append(" applicationName: ");
        buffer.append(applicationName);
        buffer.append(" evaluations: ");
        buffer.append(evaluations);
        buffer.append(" logicOps: ");
        buffer.append(logicOps);
        buffer.append(" type: ");
        buffer.append(type);
        buffer.append(" result: ");
        buffer.append(result);
        buffer.append("]");
        return buffer.toString();
    }
}
