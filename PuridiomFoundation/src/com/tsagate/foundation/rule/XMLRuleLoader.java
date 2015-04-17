/*
 * Created on Nov 26, 2003
 */
package com.tsagate.foundation.rule;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsagate.foundation.rule.operator.OperatorFactory;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Kelli
 */
public class XMLRuleLoader implements RuleLoading
{
    /* (non-Javadoc)
     * @see com.tsagate.common.rule.RuleLoading#loadRule(java.lang.String)
     */
    public void loadRule(String ruleName, Rule rule, Map mapValues)
    {
        try
        {
            Log.debug(this, ruleName);
            //File f = new File(ruleName);
            File f = Utility.getOidFile(ruleName, (String)mapValues.get("organizationId"));
            if (f.exists())
            {
                DOMBuilder docBuilder = new DOMBuilder();
                Document document = docBuilder.build(f);
                populateRuleFromXML(document, rule, mapValues);
            }
            else
            {
                String isValidationRule = (String)mapValues.get("isValidationRule");
                if(isValidationRule == null) {
                    isValidationRule = "false";
                }
            	int is = ruleName.lastIndexOf(File.separatorChar);
                if (isValidationRule.equals("true") && is > 0) {
                	String ruleFileName = ruleName.substring(is + 1);
                	if (ruleFileName.indexOf("-") > 0) {
                		// Check for basic rule (non module specific)
                		ruleFileName = ruleFileName.substring(ruleFileName.indexOf("-") + 1);
                		if (!ruleFileName.equals("v.xml") && !ruleFileName.equals("mv.xml") && !ruleFileName.equals("m.xml")) {
                		
	                    	ruleName = ruleName.substring(0, ruleName.lastIndexOf(File.separatorChar) + 1) + ruleFileName;
	                    	
	                    	f = Utility.getOidFile(ruleName, (String)mapValues.get("organizationId"));
	                        if (f.exists())
	                        {
	                            DOMBuilder docBuilder = new DOMBuilder();
	                            Document document = docBuilder.build(f);
	                            populateRuleFromXML(document, rule, mapValues);
	                        } else {
	                            throw new Exception("Rule file not found for: " + ruleName);
	                        }
                		}
                	}
                }
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            //system.out.println(exception.toString());
        }
    }

    /**
     * @param document
     * @param rule
     */
    public void populateRuleFromXML(Document document, Rule rule, Map mapValues)
    {
        try
        {
            List clauses =  document.getRootElement().getChildren("clause");
            String newRules = (String)mapValues.get("newRules");
            if(newRules == null)
            {
                newRules = "false";
            }
            for(int i = 0; i < clauses.size(); i++)
            {
                Clause clause = new Clause();
                Element clauseElement = (Element)clauses.get(i);
                String clauseType = clauseElement.getAttributeValue("type");
                String foreignDatabase = clauseElement.getAttributeValue("foreign-database");
                boolean subRuleNew =Utility.ckNull(newRules).equalsIgnoreCase("true");

                if (clauseType.equalsIgnoreCase("Rule"))
                {//sub rule
                    Element ruleElement = clauseElement.getChild("rule-filename");
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
                    }
                }
                else if(clauseType.equalsIgnoreCase("lookup"))
                {
                    clause = this.getLookupClause(clauseElement, clauseType);
                }
                else if(clauseType.equalsIgnoreCase("ElementRule") || clauseType.equalsIgnoreCase("empty") || clauseType.equalsIgnoreCase("NotEmpty"))
                {
                    clause = this.getEmptyClause(clauseElement, clauseType);
                }
                else if(clauseType.indexOf("AccountRule") > -1)
                {
                    if(clauseType.indexOf("lookup") > 0)
                    {
                        clause = this.getLookupClause(clauseElement, clauseType);
                    }
                    else
                    {
                        clause = this.getEmptyClause(clauseElement, clauseType);
                    }
                }
                else
                {	//Compare 2 Objects
                    clause = this.getObjectCompareClause(clauseElement, clauseType);
                }

                Attribute matchAllAttrib = clauseElement.getAttribute("matchAll");
                if(matchAllAttrib != null)
                {
                    clause.setMatchAll(matchAllAttrib.getBooleanValue());
                }
                else
                {
                    clause.setMatchAll(false);
                }

                clause.setClauseType(clauseType);
                clause.setForeignDatabase(foreignDatabase);
                rule.addEvaluation(clause);
            }
			List logicOperators = document.getRootElement().getChildren("logical-operator");
            for (int i = 0; i < logicOperators.size(); i++)
            {
                Element logicOperator = (Element)logicOperators.get(i);
                if(newRules.equals("false"))
                {
                    rule.addLogicOp(OperatorFactory.getLogicOperator(logicOperator.getText()));
                }
                else
                {
                    rule.addLogicOp(OperatorFactory.getNewLogicOperator(logicOperator.getText()));
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
    private Clause getEmptyClause(Element clauseElement, String type)
    {
        Clause clause = new Clause();

        ClauseElement leftClauseElement;
        try
        {
            leftClauseElement = this.getClauseElement(clauseElement.getChild("left-side"), type);
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
    private Clause getLookupClause(Element clauseElement, String clauseType) throws Exception
    {
        LookupClause lookup = new LookupClause();
        Attribute uniqueAttribute = clauseElement.getAttribute("unique");
        Attribute elementSourceAttribute = clauseElement.getAttribute("elementSource");

        if (uniqueAttribute != null)
        {
        	lookup.setUnique(uniqueAttribute.getBooleanValue());
        }
        if (elementSourceAttribute != null)
        {
        	lookup.setElementSource(elementSourceAttribute.getBooleanValue());
        }

        lookup.setSql(clauseElement.getChildText("sql"));
        lookup.setSource(clauseElement.getChildText("source"));
        lookup.setFromObject(clauseElement.getChildText("object"));

        List whereList = clauseElement.getChildren("arguments");

        for(int whereIndex = 0; whereIndex < whereList.size(); whereIndex++)
        {
            Element where = (Element)whereList.get(whereIndex);
            lookup.setArgument(where.getChildText("name"), where.getChildText("source"), where.getChildText("colName"));
        }

        return lookup;
    }

    /* (non-Javadoc)
     * @see com.tsagate.common.rule.RuleLoading#loadRule(java.lang.String, com.tsagate.common.rule.Rule)
     */
    public void loadRule(String ruleName, Rule rule) throws Exception
    {
        this.loadRule(ruleName, rule, null);
    }

    private Clause getObjectCompareClause(Element clauseElement, String objectType) throws Exception
    {
        Clause clause = new Clause();

        try
        {
            ClauseElement leftClauseElement = this.getClauseElement(clauseElement.getChild("left-side"), objectType);
            ClauseElement rightClauseElement = this.getClauseElement(clauseElement.getChild("right-side"), objectType);
            String operatorType = clauseElement.getChildText("operator");

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

    public ClauseElement getClauseElement(Element element, String type) throws Exception
    {
        ClauseElement clauseElement = new ClauseElement();
        if(element != null)
        {
            try
            {
            	Element ruleElement = element.getChild("rule-filename");
            	Attribute elementSourceAttrib = element.getAttribute("elementSource");

            	if (ruleElement != null)
            	{
            		clauseElement.setRuleName(ruleElement.getTextTrim());
            	}

                if(elementSourceAttrib != null)
                {
                	clauseElement.setElementSource(elementSourceAttrib.getBooleanValue());
                }

                clauseElement.setType(type);
                clauseElement.setObject(element.getChildTextTrim("object"));
                clauseElement.setName(element.getChildTextTrim("name"));
                clauseElement.setSource(element.getChildTextTrim("source"));
                clauseElement.setValue(element.getChildTextTrim("value"));

                List args = element.getChildren("argument-value");
                for(int iArgs = 0; iArgs < args.size(); iArgs++)
                {
                    Element elArg = (Element)args.get(iArgs);
                    Attribute	argSource = elArg.getAttribute("source");
                    ArgumentElement argElement = new ArgumentElement();
                    if (argSource == null || argSource.toString().trim().length() < 1)
                    {
                        String arg = elArg.getText();
                        argElement.setSource("constant");
                        argElement.setValue(arg);
                    }
                    else
                    {
                        String arg = elArg.getText();
                        argElement.setSource(argSource.getValue());
                        argElement.setName(arg);
                    }
                    clauseElement.addArgument(argElement);
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
