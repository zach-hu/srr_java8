/*
 * Created on Sep 18, 2003 
 */
package com.tsagate.foundation.rule.operator;

import java.util.List;

/**
 * @author renzo 
 */
public class Or extends LogicOperator
{
	
	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.ILogicOperator#evaluate(java.util.List)
	 */
	public boolean evaluate(List list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			Boolean temp = (Boolean)list.get(i);
			if(temp.booleanValue())
			{
				return true;
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
