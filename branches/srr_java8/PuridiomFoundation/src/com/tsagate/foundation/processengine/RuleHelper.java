/**
 * Created on Feb 25, 2004
 * @author renzo
 * project: Foundation
 * com.tsagate.foundation.processengine.RuleHelper.java
 *
 */
package com.tsagate.foundation.processengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.rule.Clause;
import com.tsagate.foundation.rule.Rule;
import com.tsagate.foundation.rule.RuleManager;
import org.jdom.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class RuleHelper
{
	
	/**
	 * rule file name.
	 */
	private String name = "";
	private String applicationName = "";
	/**
	 * check weather to return true when a rule should fail.
	 */
	private boolean checkForFalse = false;
	/**
	 * status of rule execution
	 */
	private int status;
	private boolean trueValue = false;
	private boolean evaluate = false;
	
	/**
	 * returns the evaluation value of the rule.
	 */
	public boolean executeRule(Map request, String applicationName) throws Exception 
	{
		this.setApplicationName(applicationName);
		return executeRule(request);
	}
	
	public boolean validateRule(Map request)
	{
	    boolean evaluation = false;
	    return evaluation;
	}
	
	public Object[] executeRuleWithMsg(Map request) throws Exception
	{
		boolean evaluation = false;
		String rules = this.getName();
		Object ret[] = new Object[2];
		List wrongValues = new ArrayList();
		if(this.evaluate)
		{	
			String	ruleFilename = "";
			try 
			{
				if (rules == null || rules.trim().length() == 0) 
				{
					evaluation = true;
				}
				else 
				{
					rules = rules.trim();
	
					boolean continueEvaluation = true;
					int	ind = rules.lastIndexOf(";");
	
					if (ind < (rules.length() - 1)) 
					{
						rules = rules + ";";
					}
					ind = rules.indexOf(";");
	
					while (ind > 0 && continueEvaluation) 
					{
						ruleFilename = rules.substring(0, rules.indexOf(";"));
						rules = rules.substring(ind + 1);
						
						if (ruleFilename.indexOf(".xml") < 0) 
						{
							ruleFilename = ruleFilename + ".xml";
						}
						RuleManager ruleManager = RuleManager.getInstance();
						Rule rule = ruleManager.getRule(ruleFilename, request, this.applicationName);
						if(this.isCheckForFalse())
						{
							continueEvaluation = !rule.evaluate(request);
						}
						else
						{
							continueEvaluation = rule.evaluate(request);
						}
						ind = rules.indexOf(";");
						
						if(!continueEvaluation)
						{
						    for(int i = 0; i < rule.evaluations.size(); i++)
						    {
						        Clause clause = (Clause)rule.evaluations.get(i);
						        wrongValues.add(clause.getValues());
						    }
						}
					}
					
					evaluation = continueEvaluation;
				}
			}
			catch (Exception e) 
			{
				this.setStatus(Status.FAILED);
				//system.out.println("rule name(s): " + ruleFilename);
				throw e;
			}
		}
		else
		{
			//return true;
		    ret[0] = new Boolean(true);
		    return ret;
		}
		ret[0] = new Boolean(evaluation);
		ret[1] = wrongValues;
		return ret; 
	
	}

	public Object[] executeRulesWithMsg(String alias, String ruleExpression, Map request) throws Exception
	{
		boolean evaluation = false;

		Object ret[] = new Object[2];
		List wrongValues = new ArrayList();

		if (this.evaluate)
		{
			try 
			{
				boolean continueEvaluation = true;

				Map rules = this.populateMapFromRuleExpression(alias, ruleExpression);
				
				if (rules != null && rules.size() > 0)
				{
					Rule rule = new Rule(rules, request);

					if (this.isCheckForFalse())
					{
						continueEvaluation = !rule.evaluate(request);
					}
					else
					{
						continueEvaluation = rule.evaluate(request);
					}

					if (!continueEvaluation)
					{
					    for (int i = 0; i < rule.evaluations.size(); i++)
					    {
					        Clause clause = (Clause)rule.evaluations.get(i);
					        wrongValues.add(clause.getValues());
					    }
					}
				}

				evaluation = continueEvaluation;
			}
			catch (Exception e) 
			{
				this.setStatus(Status.FAILED);
				//system.out.println("rule name(s): " + ruleFilename);
				throw e;
			}
		}
		else
		{
			//return true;
		    ret[0] = new Boolean(true);
		    return ret;
		}
		ret[0] = new Boolean(evaluation);
		ret[1] = wrongValues;
		return ret; 
	
	}

	/**
	 * returns the evaluation value of the rule.
	 */
	public boolean executeRule(Map request) throws Exception 
	{
		boolean evaluation = false;
		String rules = this.getName();
		
		if(this.evaluate)
		{	
			String	ruleFilename = "";
			try 
			{
				if (rules == null || rules.trim().length() == 0) 
				{
					evaluation = true;
				}
				else 
				{
					rules = rules.trim();
	
					boolean continueEvaluation = true;
					int	ind = rules.lastIndexOf(";");
	
					if (ind < (rules.length() - 1)) 
					{
						rules = rules + ";";
					}
					ind = rules.indexOf(";");
	
					while (ind > 0 && continueEvaluation) 
					{
						ruleFilename = rules.substring(0, rules.indexOf(";"));
						rules = rules.substring(ind + 1);
						
						if (ruleFilename.indexOf(".xml") < 0) 
						{
							ruleFilename = ruleFilename + ".xml";
						}
						RuleManager ruleManager = RuleManager.getInstance();
						Rule rule = ruleManager.getRule(ruleFilename, request, this.applicationName);
						if(this.isCheckForFalse())
						{
							continueEvaluation = !rule.evaluate(request);
						}
						else
						{
							continueEvaluation = rule.evaluate(request);
						}
						ind = rules.indexOf(";");
					}
					
					evaluation = continueEvaluation;
				}
			}
			catch (Exception e) 
			{
				this.setStatus(Status.FAILED);
				//system.out.println("rule name(s): " + ruleFilename);
				throw e;
			}
		}
		else
		{
			return true;
		}
/*
		if(this.isCheckForFalse())
		{
			if(!evaluation)
			{
				evaluation = true;
			}
			else
			{
				evaluation = false;
			}
		}
*/
		return evaluation; 
	}
	
	/**
	 * @return Returns the checkForFalse.
	 */
	public boolean isCheckForFalse()
	{
		return checkForFalse;
	}

	/**
	 * @param checkForFalse The checkForFalse to set.
	 */
	public void setCheckForFalse(boolean checkForFalse)
	{
		this.checkForFalse = checkForFalse;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return Returns the applicationName.
	 */
	public String getApplicationName()
	{
		return applicationName;
	}
	/**
	 * @param name The applicationName to set.
	 */
	public void setApplicationName(String name)
	{
		this.applicationName = name;
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[RuleHelper:");
		buffer.append(" name: ");
		buffer.append(name);
		buffer.append(" checkForFalse: ");
		buffer.append(checkForFalse);
		buffer.append("]");
		return buffer.toString();
	}
	/**
	 * @return Returns the status.
	 */
	public int getStatus()
	{
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status)
	{
		this.status = status;
	}

	/**
	 * @return Returns the trueValue.
	 */
	public boolean isTrueValue()
	{
		return trueValue;
	}

	/**
	 * @param trueValue The trueValue to set.
	 */
	public void setTrueValue(boolean trueValue)
	{
		this.trueValue = trueValue;
	}
	
	public RuleHelper()
	{
		this.evaluate = false;
		this.setName("");
	}
	
	public RuleHelper(String fileName)
	{
	    this.evaluate = true;
		this.setName(fileName);
	}
	public RuleHelper(String fileName, String applicationName)
	{
	    this(fileName);
	    this.applicationName = applicationName;
	}
	
	public RuleHelper(String fileName, boolean checkForFalse)
	{
	    this(fileName);
		this.setCheckForFalse(checkForFalse);
	}
	
	public RuleHelper(String fileName, String applicationName, boolean checkForFalse)
	{
	    this(fileName, applicationName);
		this.setCheckForFalse(checkForFalse);
	}
	
	public RuleHelper(Element ruleElement)
	{
		this();
		if(ruleElement != null)
		{
			String fileName = ruleElement.getText();
			if(fileName != null)
			{	
				this.evaluate = true;
				this.setName(fileName);
				Attribute checkFalse = ruleElement.getAttribute("check-for-false");
				if(checkFalse != null)
				{
					try
					{
						this.setCheckForFalse(checkFalse.getBooleanValue());
					}
					catch (Exception e)
					{
						this.setCheckForFalse(false);
					}
				}
			}
			else
			{
				this.evaluate = false;
			}
		}
	}
	
	public RuleHelper(Element ruleElement, String applicationName)
	{
	    this(ruleElement);
	    this.applicationName = applicationName;
	}

	private Map populateMapFromRuleExpression(String alias, String ruleExpression)
	{
		Map rules = new HashMap();
		
		try
		{
			List<JSONObject> clauses = new ArrayList<JSONObject>();
			List<String> operators = new ArrayList<String>();

			JSONObject jsonRuleExpression = new JSONObject(ruleExpression);

			JSONArray jsonClauses = jsonRuleExpression.has("clauses") ? jsonRuleExpression.getJSONArray("clauses") : null;
			JSONArray jsonOperators = jsonRuleExpression.has("logicalOperators") ? jsonRuleExpression.getJSONArray("logicalOperators") : null;

			if (jsonClauses != null && jsonClauses.length() > 0)
			{
				for (int i = 0; i < jsonClauses.length(); i++)
				{
					JSONObject clause = jsonClauses.getJSONObject(i);
					clauses.add(clause);
				}
			}

			if (jsonOperators != null && jsonOperators.length() > 0)
			{
				for (int i = 0; i < jsonOperators.length(); i++)
				{
					String operator = (String)jsonOperators.get(i);
					operators.add(operator);
				}
			}

			if (clauses.size() > 0 || operators.size() > 0)
			{
				rules.put("name", alias);
				rules.put("clauses", clauses);
				rules.put("operators", operators);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return rules;
	}
}
