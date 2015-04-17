package com.tsagate.foundation.rule;

import com.tsagate.foundation.rule.operator.OperatorFactory;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapRuleLoader
{
    public void loadRule(Map rules, Rule rule, Map mapValues)
    {
        try
        {
            List clauses = (List)rules.get("clauses");

            String newRules = (String)mapValues.get("newRules");
            if (newRules == null) {
                newRules = "false";
            }
            
            if (clauses != null)
            {
	            for (int i = 0; i < clauses.size(); i++)
	            {
	                Clause clause = new Clause();

	                JSONObject clauseElement = (JSONObject)clauses.get(i);

	                String clauseType = clauseElement.has("type") ? (String)clauseElement.get("type") : null;
	                String foreignDatabase = clauseElement.has("foreignDatabase") ? (String)clauseElement.get("foreignDatabase") : null;
	                boolean subRuleNew =Utility.ckNull(newRules).equalsIgnoreCase("true");

	                if (clauseType.equalsIgnoreCase("Rule"))
	                {
	                    /*Element ruleElement = clauseElement.getChild("rule-filename");
	                    String	ruleName = ruleElement.getTextTrim();
	                    Element subRuleNewElement = clauseElement.getChild("new");

	                    if (subRuleNewElement != null) {
	                        String	subRuleNewString = subRuleNewElement.getTextTrim();
	                        if (!Utility.isEmpty(subRuleNewString)) {
	                            if (Utility.ckNull(subRuleNewString).equalsIgnoreCase("true")) {
	                                subRuleNew = true;
	                            } else if (Utility.ckNull(subRuleNewString).equalsIgnoreCase("false")) {
	                                subRuleNew = false;
	                            } else {
	                                // use newRules from original Rule
	                            }
	                        }
	                    }

	                    clause.setRuleName(ruleName);
	                    clause.setRuleNew(subRuleNew);

	                    Attribute checkFalse = ruleElement.getAttribute("check-for-false");
	                    if(checkFalse != null)
	                    {
	                        try
	                        {
	                            clause.setCheckForFalse(checkFalse.getBooleanValue());
	                        }
	                        catch (Exception e)
	                        {
	                            clause.setCheckForFalse(false);
	                        }
	                    }*/
	                }
	                else if(clauseType.equalsIgnoreCase("lookup"))
	                {
	                    clause = this.getLookupClause(clauseElement);
	                }
	                else if(clauseType.equalsIgnoreCase("ElementRule") || clauseType.equalsIgnoreCase("Empty") || clauseType.equalsIgnoreCase("NotEmpty"))
	                {
	                    clause = this.getEmptyClause(clauseElement, clauseType);
	                }
	                /*else if(clauseType.indexOf("AccountRule") > -1)
	                {
	                    if(clauseType.indexOf("lookup") > 0)
	                    {
	                        clause = this.getLookupClause(clauseElement, clauseType);
	                    }
	                    else
	                    {
	                        clause = this.getEmptyClause(clauseElement, clauseType);
	                    }
	                }*/
	                else
	                {	//Compare 2 Objects
	                    clause = this.getObjectCompareClause(clauseElement, clauseType);
	                }

	                String matchAllAttrib = clauseElement.has("matchAll") ? (String)clauseElement.get("matchAll") : null;
	                if (matchAllAttrib != null)
	                {
	                    clause.setMatchAll(Boolean.parseBoolean(matchAllAttrib));
	                }
	                else
	                {
	                    clause.setMatchAll(false);
	                }

	                clause.setClauseType(clauseType);
	                clause.setForeignDatabase(foreignDatabase);
	                rule.addEvaluation(clause);
	            }
            }

			List logicOperators = (List)rules.get("operators");

			if (logicOperators != null)
			{
	            for (int i = 0; i < logicOperators.size(); i++)
	            {
	            	String logicOperator = (String)logicOperators.get(i);

	                if (newRules.equals("false"))
	                {
	                    rule.addLogicOp(OperatorFactory.getLogicOperator(logicOperator));
	                }
	                else
	                {
	                    rule.addLogicOp(OperatorFactory.getNewLogicOperator(logicOperator));
	                }
	            }
			}
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }

    /**
     * @param clauseElement
     * @return
     */
    private Clause getEmptyClause(JSONObject clauseElement, String type)
    {
        Clause clause = new Clause();

        ClauseElement leftClauseElement;
        try
        {
        	JSONObject leftSide = clauseElement.has("leftSide") ? clauseElement.getJSONObject("leftSide") : null;

            leftClauseElement = this.getClauseElement(leftSide, type);
            clause.setLeftExpression(leftClauseElement);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return clause;
    }

    /**
     * @param clauseElement
     * @param clauseType
     * @return
     */
    private Clause getLookupClause(JSONObject clauseElement) throws Exception
    {
        LookupClause lookup = new LookupClause();
        String uniqueAttribute = clauseElement.has("unique") ? (String)clauseElement.get("unique") : null;
        String elementSourceAttribute = clauseElement.has("elementSource") ? (String)clauseElement.get("elementSource") : null;

        if (uniqueAttribute != null)
        {
        	lookup.setUnique(Boolean.parseBoolean(uniqueAttribute));
        }
        if (elementSourceAttribute != null)
        {
        	lookup.setElementSource(Boolean.parseBoolean(elementSourceAttribute));
        }

        String sql = clauseElement.has("sql") ? (String)clauseElement.get("sql") : null;
        if (sql != null)
        {
        	sql = sql.replaceAll("@E@" , "=");
        }

        lookup.setSql(sql);
        lookup.setSource(clauseElement.has("source") ? (String)clauseElement.get("source") : null);
        lookup.setFromObject(clauseElement.has("object") ? (String)clauseElement.get("object") : null);

        JSONArray whereList = clauseElement.has("arguments") ? clauseElement.getJSONArray("arguments") : null;

        if (whereList != null && whereList.length() > 0)
        {
        	for (int whereIndex = 0; whereIndex < whereList.length(); whereIndex++)
            {
                JSONObject where = (JSONObject)whereList.getJSONObject(whereIndex);
                String name = where.has("name") ? (String)where.get("name") : null;
                String source = where.has("source") ? (String)where.get("source") : null;
                String colName = where.has("colName") ? (String)where.get("colName") : null;
                lookup.setArgument(name, source, colName);
            }
        }

        return lookup;
    }

    private Clause getObjectCompareClause(JSONObject clauseElement, String objectType) throws Exception
    {
        Clause clause = new Clause();

        try
        {
        	JSONObject leftSide = clauseElement.has("leftSide") ? clauseElement.getJSONObject("leftSide") : null;
        	JSONObject rightSide = clauseElement.has("rightSide") ? clauseElement.getJSONObject("rightSide") : null;

            ClauseElement leftClauseElement = this.getClauseElement(leftSide, objectType);
            ClauseElement rightClauseElement = this.getClauseElement(rightSide, objectType);
            String operatorType = clauseElement.has("operator") ? (String)clauseElement.get("operator") : null;

            clause.setOperator(OperatorFactory.getOperator(operatorType));
            clause.setLeftExpression(leftClauseElement);
            clause.setRightExpression(rightClauseElement);
        }
        catch (Exception e)
        {
            throw e;
        }

        return clause;
    }

    public ClauseElement getClauseElement(JSONObject element, String type) throws Exception
    {
        ClauseElement clauseElement = new ClauseElement();
        
        if (element != null)
        {
            try
            {
            	/*Element ruleElement = element.getChild("rule-filename");
            	Attribute elementSourceAttrib = element.getAttribute("elementSource");

            	if (ruleElement != null)
            	{
            		clauseElement.setRuleName(ruleElement.getTextTrim());
            	}

                if(elementSourceAttrib != null)
                {
                	clauseElement.setElementSource(elementSourceAttrib.getBooleanValue());
                }*/

                clauseElement.setType(type);
                clauseElement.setObject(element.has("object") ? (String)element.get("object") : null);
                clauseElement.setName(element.has("name") ? (String)element.get("name") : null);
                clauseElement.setSource(element.has("source") ? (String)element.get("source") : null);
                clauseElement.setValue(element.has("value") ? element.get("value") : null);

                List args = element.has("argumentValue") ? (List)element.get("argumentValue") : null;
                if (args != null)
                {
	                for (int iArgs = 0; iArgs < args.size(); iArgs++)
	                {
	                    JSONObject elArg = (JSONObject)args.get(iArgs);
	                    String argSource = (String)elArg.get("source");
	                    ArgumentElement argElement = new ArgumentElement();
	                    if (argSource == null || argSource.toString().trim().length() < 1)
	                    {
	                        String arg = elArg.toString();
	                        argElement.setSource("constant");
	                        argElement.setValue(arg);
	                    }
	                    else
	                    {
	                        String arg = elArg.toString();
	                        argElement.setSource(argSource);
	                        argElement.setName(arg);
	                    }
	                    clauseElement.addArgument(argElement);
	                }
                }
            }
            catch (Exception e)
            {
                throw e;
            }
        }
        return clauseElement;
    }
}
