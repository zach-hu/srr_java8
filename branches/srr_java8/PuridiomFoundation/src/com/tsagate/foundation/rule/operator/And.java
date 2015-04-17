/*
 * Created on Sep 18, 2003 
 */
package com.tsagate.foundation.rule.operator;


/**
 * @author renzo 
 */
public class And extends LogicOperator
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.ILogicOperator#value()
	 */
	public int value()
	{
		return OperatorTypes.and;
	}
	
	public boolean logicValue()
	{
		return true;
	}

}
