package com.tsagate.foundation.rule;

import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.rule.operator.*;
/**
 * @author Kelli
 *
 */
public class Clause 
{
	private boolean evaluation = true;
	private boolean evaluationComplete = false;
	private boolean checkForFalse = false;
	private String clauseType = null;
	private String ruleName = null;
	private String foreignDatabase = null;
	private IOperator operator = null;
	private ClauseElement leftExpression = null;
	private ClauseElement rightExpression = null;
	private boolean matchAll = true;
	private boolean ruleNew = false;
	private List values = new ArrayList();
    private Object returnedResults = null;

	public void setClauseType(String type)
	{
		this.clauseType = type;
	}
	public String getClauseType()
	{
		return this.clauseType;
	}
	public void setRuleName(String name)
	{
		this.ruleName = name;
	}
	public String getRuleName()
	{
		return this.ruleName;
	}
	public void setEvaluationComplete(boolean booleanValue)
	{
		this.evaluationComplete = booleanValue;
	}
	public boolean getEvaluationComplete()
	{
		return this.evaluationComplete;
	}
	public void setEvaluation(boolean booleanValue)
	{
		this.evaluation = booleanValue;
	}
	public boolean getEvaluation()
	{
		return this.evaluation;
	}
	public void setOperator(IOperator operatorValue)
	{
		this.operator = operatorValue;
	}
	public IOperator getOperator()
	{
		return this.operator;
	}
	public void setLeftExpression(ClauseElement clauseElement)
	{
		this.leftExpression = clauseElement;
	}
	public ClauseElement getLeftExpression()
	{
		return this.leftExpression;
	}
	public void setRightExpression(ClauseElement clauseElement)
	{
		this.rightExpression = clauseElement;
	}
	public ClauseElement getRightExpression()
	{
		return this.rightExpression;
	}
	public void setCheckForFalse(boolean booleanValue)
	{
		this.checkForFalse =booleanValue;
	}
	public boolean isCheckForFalse()
	{
		return this.checkForFalse;
	}
	/**
     * @return Returns the matchAll.
     */
    public boolean isMatchAll()
    {
        return matchAll;
    }
    /**
     * @param matchAll The matchAll to set.
     */
    public void setMatchAll(boolean matchAll)
    {
        this.matchAll = matchAll;
    }
	/**
     * @return Returns the ruleNew.
     */
    public boolean isRuleNew()
    {
        return ruleNew;
    }
    /**
     * @param ruleNew The ruleNew to set.
     */
    public void setRuleNew(boolean ruleNew)
    {
        this.ruleNew = ruleNew;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[Clause:");
        buffer.append(" evaluation: ");
        buffer.append(evaluation);
        buffer.append(" evaluationComplete: ");
        buffer.append(evaluationComplete);
        buffer.append(" checkForFalse: ");
        buffer.append(checkForFalse);
        buffer.append(" clauseType: ");
        buffer.append(clauseType);
        buffer.append(" ruleName: ");
        buffer.append(ruleName);
        buffer.append(" operator: ");
        buffer.append(operator);
        buffer.append(" leftExpression: ");
        buffer.append(leftExpression);
        buffer.append(" rightExpression: ");
        buffer.append(rightExpression);
        buffer.append(" matchAll: ");
        buffer.append(matchAll);
        buffer.append("]");
        return buffer.toString();
    }
    public List getValues()
    {
        return values;
    }
    public void setValues(List values)
    {
        this.values = values;
    }
    public Object getReturnedResults()
    {
        return returnedResults;
    }
    public void setReturnedResults(Object returnedResults)
    {
        this.returnedResults = returnedResults;
    }
    public String getForeignDatabase() 
    {
        return foreignDatabase;
    }
    public void setForeignDatabase(String foreignDatabase) 
    {
        this.foreignDatabase = foreignDatabase;
    }
}
