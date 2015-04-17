/*
 * Created on Nov 26, 2003
 */
package com.tsagate.foundation.rule;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.exceptions.DatabaseConnectionException;
import com.tsagate.foundation.rule.operator.And;
import com.tsagate.foundation.rule.operator.ILogicOperator;
import com.tsagate.foundation.rule.operator.IOperator;
import com.tsagate.foundation.rule.operator.LogicOperator;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo / Kelli
 *
 * This is an updated / enhanced version of the Rule
 */
public class Rule
{
    protected String name = ""; //file name
    protected String applicationName = ""; //application name - used in finding path to xml files
    public List evaluations;
    public List logicOps;
    //variable to hold values that were checked.
    public List values = new ArrayList();
    public String type;
    public boolean result = true;

    public boolean evaluate(Map map)
    {
    	return this.evaluate(map, null);
    }

    public boolean evaluate(Map map, Object defaultObject)
    {
        boolean bret = false;
        Log.debug(this, "evaluating rule: " + this.name);
        try
        {
            //List values = new ArrayList();
            //int subrules = 0;

            for (int i = 0; i < this.evaluations.size(); i++)
            {
                Clause clause = (Clause) this.evaluations.get(i);

                if (defaultObject != null)
                {
                	if (this.evaluations.get(i) instanceof LookupClause)
                	{
                		LookupClause lookupClause = (LookupClause) this.evaluations.get(i);
                		if (lookupClause.isElementSource())
                		{
                			this.setNewRequestObject(defaultObject, lookupClause, map);
                		}
                	}
                	else
                	{
                		ClauseElement clauseLeftElement = clause.getLeftExpression();
                		ClauseElement clauseRightElement = clause.getRightExpression();

                		if (clauseLeftElement != null && clauseLeftElement.isElementSource())
                		{
                			this.setNewRequestObject(defaultObject, clauseLeftElement, map);
                		}

                		if (clauseRightElement != null && clauseRightElement.isElementSource())
                		{
                			this.setNewRequestObject(defaultObject, clauseRightElement, map);
                		}
                	}
                }

                if(clause.getClauseType().equalsIgnoreCase("Rule"))
                {
                    Rule subRule = null;
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
                }
                else if(clause.getClauseType().equalsIgnoreCase("ElementRule"))
                {
                    Log.debug(this, "Element rule " + clause.getLeftExpression());
                    Object object = this.getObjectFromClauseElement(clause.getLeftExpression(), map);

                    bret = this.evaluateElementRule(object, clause.isMatchAll(), clause.getLeftExpression().getRuleName(), map);
                    Log.debug(this, "Element rule returns: " + bret);
                }
                else if(clause.getClauseType().equalsIgnoreCase("lookup"))
                {
                	bret = this.evaluateLookup((LookupClause)clause, map);
                }
                else if(clause.getClauseType().equalsIgnoreCase("empty"))
                {
                    Log.debug(this, "empty rule " + clause.getLeftExpression());
                    Object object = this.getObjectFromClauseElement(clause.getLeftExpression(), map);
                    bret = this.isEmpty(object, clause.isMatchAll());
                    Log.debug(this, "empty returns: " + bret);
                }
                else if(clause.getClauseType().equalsIgnoreCase("NotEmpty"))
                {
                    Log.debug(this, "not empty rule " + clause.getLeftExpression());
                    Object object = this.getObjectFromClauseElement(clause.getLeftExpression(), map);
                    bret = this.isNotEmpty(object, clause.isMatchAll());
                    Log.debug(this, "not empty rule returns: " + String.valueOf(bret));
                }
                else if(clause.getClauseType().indexOf("AccountRule-Line") > -1)
                {
                    Log.debug(this, "AccountRule-Line ");
                    List accountLines = (List)this.getValueFromIncomingRequest("accountLineList", map);
                    List accountHeader = (List)this.getValueFromIncomingRequest("accounts", map);

                    bret = this.accountRule(accountLines, accountHeader, clause, map);
                    Log.debug(this, "AccountRule-Line Returns: " + String.valueOf(bret));
                }
                else if(clause.getClauseType().indexOf("AccountRule-Header") > -1)
                {
                    List accountHeader = (List)this.getValueFromIncomingRequest("accounts", map);

                    bret = this.accountRule(accountHeader, clause, map);
                }
                else
                {
                    ClauseElement ceA = clause.getLeftExpression();
                    ClauseElement ceB = clause.getRightExpression();
                    Object objectA = this.getObjectFromClauseElement(ceA, map);
                    Object objectB = this.getObjectFromClauseElement(ceB, map);
                    bret = this.isTrue(objectA, objectB, clause.getOperator(), clause.isMatchAll()).booleanValue();
                }

                clause.setEvaluation(bret);
                clause.setEvaluationComplete(true);

                this.evaluations.set(i, clause);
            }

            List resultValues = new ArrayList();
            for (int i=0; i < this.evaluations.size(); i++)
            {
                Clause clause = (Clause) this.evaluations.get(i);
                resultValues.add(Boolean.valueOf(clause.getEvaluation()));
            }
            this.result = this.logicValue(resultValues);
        }
        catch (Exception e)
        {
            Log.error(this, "ERROR caused by " + this.name);
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

        if(accountHeader != null && accountHeader.size() > 0)
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
    private boolean accountRule(List accountLines, List accountHeader, Clause clause, Map incomingRequest) throws Exception
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
                /*evaluate header accounts
                if(accountHeader.size() > 0)
                {
                    if(ruleType.indexOf("lookup") > 0 && !headerEval)
                    {
                        this.accountLookup(clause, accountHeader, tempResults, incomingRequest);
                        headerEval = true;
                    }
                }
                else
                {
                    tempResults.add(new Boolean(false));
                }
                */
                tempResults.add(new Boolean(true));
            }
            else
            {
                //evaluate line account
                if(ruleType.indexOf("lookup") > 0)
                {
                    for(int j = 0; j < tempList.size(); j++)
                    {
                        Object account = tempList.get(j);
                        this.accountLookup(clause, tempList, tempResults, incomingRequest);
                    }
                }
            }
        }

        /*
        if (accountHeader.size() > 0 && !headerEval)
        {
            if(ruleType.indexOf("lookup") > 0)
            {
                this.accountLookup(clause, accountHeader, tempResults, incomingRequest);
                headerEval = true;
            }
        }
        */
        And result = new And();
        ret = result.evaluate(tempResults);

        return ret;
    }

    private void accountLookup(Clause clause, List accountList, List resultsList, Map incomingRequest)
    {
        boolean ret = false;

        LookupClause clauseLookup = (LookupClause)clause;
        List valuesList = new ArrayList();
        List sqlList = new ArrayList();
        for(int j = 0; j < accountList.size(); j++)
        {
            Object account = accountList.get(j);
            String newSql = this.getSqlList(clauseLookup, account, clauseLookup.getArguments(), clauseLookup.getSql(), valuesList);
            sqlList.add(newSql);
        }
        try
        {
            ret = this.executeLookupSql(incomingRequest, clauseLookup, sqlList, valuesList);
        }
        catch (Exception e)
        {
            ret = false;
            e.printStackTrace();
        }
        resultsList.add(Boolean.valueOf(ret));

    }

    private boolean evaluateElementRule(Object object, boolean matchAll, String ruleName, Map incomingRequest)
    {
    	Rule elementRule = new Rule(ruleName, incomingRequest, this.getApplicationName());
    	boolean bret = false;

    	if (object instanceof List)
    	{
    		List listObj = (List) object;

    		for (int i = 0; i < listObj.size(); i++)
    		{
    			bret = elementRule.evaluate(incomingRequest, listObj.get(i));

    			if ((matchAll && !bret) || (!matchAll && bret))
    			{
    				break;
    			}
    		}
    	} else
    	{
    		bret = elementRule.evaluate(incomingRequest, object);
    	}

    	return bret;
    }

    private void setNewRequestObject(Object object, ClauseElement clauseElement, Map incomingRequest)
    {
    	if (clauseElement.getObject() != null)
    	{
    		incomingRequest.put(clauseElement.getObject(), object);

    	} else
    	{
    		incomingRequest.put(clauseElement.getName(), object);
    	}
    }

    private void setNewRequestObject(Object object, LookupClause lookupClause, Map incomingRequest)
    {
    	incomingRequest.put(lookupClause.getSource(), object);
    }

    private boolean isNotEmpty(Object object, boolean matchAll)
    {
        boolean bret = true;
        if (object instanceof List)
        {
            List listObj = (List) object;
            for(int i = 0; i < listObj.size(); i++)
            {
                boolean truth = Utility.isObjectEmpty(listObj.get(i));
                if((matchAll && truth) || (!matchAll && !truth))
                {
                    bret = !truth;
                    i = listObj.size();
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
        }
        return bret;
    }

    private boolean isEmpty(Object object, boolean matchAll)
    {
        boolean bret = true;
        if (object instanceof List)
        {
            List listObj = (List) object;
            for(int i = 0; i < listObj.size(); i++)
            {
                boolean truth = Utility.isObjectEmpty(listObj.get(i));
                if((matchAll && !truth) || (!matchAll && truth))
                {
                    bret = truth;
                    i = listObj.size();
                }
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
        }
        return bret;

    }
    /**
     * @param object
     * @return
     */
    private boolean isEmpty(Object object)
    {
        return this.isEmpty(object, true);
    }
    /**
     * @param clause
     * @return
     */
    private boolean evaluateLookup(LookupClause clause, Object object)
    {
        Map incomingRequest = (Map)object;
        boolean ret = false;
        List valuesList = new ArrayList();
        try
        {
            List arguments = clause.getArguments();
            List sqlList = new ArrayList();
            String originalSql = clause.getSql();

            String source = clause.getSource();
            Object objSource = incomingRequest.get(source);

            if(source.equalsIgnoreCase("header") || !(objSource instanceof List))
            {
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
                        Object tempList = this.getValueFromMethod(listElement, clause.getFromObject());
                        newSourceList.addAll((List)tempList);
                    }
                    sourceList = newSourceList;
                }

                for (int i = 0; i < sourceList.size(); i++)
                {
                    Object sourceElement = sourceList.get(i);
                    //insert code to keep track of line # or/and account secuence
                    String newSql = this.getSqlList(clause, sourceElement, arguments, originalSql, valuesList);
                    sqlList.add(newSql);
                }
            }

            ret = this.executeLookupSql(incomingRequest, clause, sqlList, valuesList);
        }
        catch (Exception e)
        {
            Log.error(this.getName(), e.toString());
        }

        clause.setValues(valuesList);
        return ret;
    }

    /**
     * @return
     * @throws Exception
     */
    private boolean executeLookupSql(Map incomingRequest, LookupClause clause, List sqlList, List valuesList) throws Exception
    {
        boolean ret = false;
        DBSession dbs = null;
        String	foreignDatabase = Utility.ckNull(clause.getForeignDatabase());
        String	organizationId = Utility.ckNull((String) incomingRequest.get("organizationId"));
        boolean newConnection = false;
        boolean matchAll = clause.isMatchAll();
        boolean unique = clause.isUnique();

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

        if (dbs == null) {
            throw new Exception("A database connection was not established.");
        }

        for(int i = 0; i < sqlList.size(); i++)
        {
            String	sqlQuery = (String) sqlList.get(i);
            List dateArguments = new ArrayList();

            while (sqlQuery.indexOf("@today@") > 0)
            {
                dateArguments.add(Dates.getDate(Dates.today("", (String) incomingRequest.get("timeZone"))));
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
                boolean existResults = false;

                if (((found.size() == 1) && unique) || ((found.size() >= 1) && (!unique)))
				{
                	existResults = true;
				}

            	if(existResults && !matchAll)
                {
                    ret = true;
                    i = sqlList.size();
                }
                else if(existResults && matchAll)
                {
                    ret = true;
                }
                else if(found.size() < 1 && matchAll)
                {
                    ret = false;
                    i = sqlList.size();
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
                    i = sqlList.size();
                    ret = false;
                }
                else
                {
                    ret = false;
                }
            }
        }

		if (!Utility.isEmpty(foreignDatabase)) {
            if (dbs != null) {
				dbs.close() ;
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

                if (!key.equalsIgnoreCase("today"))
                {
                	if (source instanceof String)
					{
						tempValue = (String) source;

					} else
					{

						String methodName = clause.getArgColName(key);

						Method method = c.getMethod(methodName, null);
						tempValue = method.invoke(source, null).toString();
					}

	                newSql = newSql.replaceAll(toReplace, tempValue);
	                valuesList.add(tempValue);
                }
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
                return ((Boolean)truthValues.get(0)).booleanValue();
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
        return ((LogicOperator)this.logicOps.get(0)).evaluate(truthValues);
    }

    public Rule()
    {
        this.evaluations = new ArrayList();
        this.logicOps = new ArrayList();
    }
    public Rule(String name, Map mapValues)
    {
        this(name, mapValues, null);
    }

    public Rule(String name, Map mapValues, String applicationName)
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

        String fromPath = applicationName + "rule-xml-path";

        if(isValidationRule.equalsIgnoreCase("true"))
        {
            fromPath = applicationName + "validation-rule-xml-path";
        }
        else if(isAppExtRules.equalsIgnoreCase("true"))
        {
            fromPath = applicationName + "app-rules-ext-xml-path";
        }
        String filename = DictionaryManager.getInstance("host", organizationId).getProperty(fromPath, "");
        filename += name;
        loader.loadRule(filename, this, mapValues);
    }

    public Rule(Map rules, Map mapValues)
    {
        this();
        this.name = (String)rules.get("name");

        MapRuleLoader loader = new MapRuleLoader();

        loader.loadRule(rules, this, mapValues);
    }

    public Rule(String name)
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

    private Boolean isTrue(Object clauseObject, Object comparisonObject, IOperator condition, boolean matchAll)
    {
        boolean truth = false;
        List objectListA = null;
        List objectListB = null;
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
                    if((matchAll && !truth) || (!matchAll && truth))
                    {
                        i = objectListA.size();
                    }
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
                if((matchAll && !truth) || (!matchAll && truth))
                {
                    i = templist.size();
                }
            }
        }
        else
        {
            truth = condition.compare(clauseObject, comparisonObject);
            if(truth)
            {
                int i =0;
            }
        }
        return new Boolean(truth);
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
    public Boolean isTrue(Object clauseObject, Object comparisonObject, IOperator condition)
    {
        return this.isTrue(clauseObject, comparisonObject, condition, true);
    }

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
                if(temp == null || temp.length() == 0)
                {
                    //temp = "PURIDIOM";
                	temp = DictionaryManager.getInstance("host", "puridiom").getProperty("default-database-configuration", "PURIDIOM").toUpperCase();
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
                Class c = obj.getClass();
                Method method = c.getMethod(methodName, null);
                result = method.invoke(obj, null);
                Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromMethod");
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
    protected Object getValue(ClauseElement ce, Map map)
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
                Object listElement = list.get(i);

                Object partialValue = listElement;

                if (ce.getName() != null && ce.getName().length() > 0)
				{
					partialValue = this.getValueFromMethod(listElement, ce.getName());
				}

                if(fromObject)
                {
                    partialValue = this.getValueFromMethod(partialValue, ce.getArguments());
                }
                if (partialValue instanceof List) {
                    List tempie = (List) partialValue;
                    values.addAll(tempie);
                }
                else
                {
                    values.add(partialValue);
                }
            }
        }

        Log.debug(this, "getValueFromList.. returns: " + values.toString());
int i = 0;
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
            if(value instanceof List && ce.getSource().equalsIgnoreCase("incomingRequestList"))
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
            	if (ce.getType().equalsIgnoreCase("ElementRule"))
            	{
            		o = value;
            	}
            	else if (ce.getType().equalsIgnoreCase("DateCompare"))
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
