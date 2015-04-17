/*
 * Created on Sep 16, 2003 
 */
package com.tsagate.foundation.rule.operator;

import java.util.Comparator;

/**
 * @author renzo 
 */
public class Less extends Operator
{
	public boolean compare(Object objA, Object B)
	{
		Comparator comparator = this.getComparator(objA);
		return (comparator.compare(objA, B) <= -1);
	
	}

	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#value()
	 */
	public int value() 
	{
		return OperatorTypes.less;
	}

}
