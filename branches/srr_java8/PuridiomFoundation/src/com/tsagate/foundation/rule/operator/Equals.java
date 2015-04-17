/*
 * Created on Sep 16, 2003 
 */
package com.tsagate.foundation.rule.operator;

import java.util.Comparator;

/**
 * @author renzo 
 */
public class Equals extends Operator 
{

	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#compare(java.lang.Object, java.lang.Object)
	 */
	public boolean compare(Object objA, Object B)
	{
		Comparator comparator = this.getComparator(objA);
		return (comparator.compare(objA, B) == 0);
		
	}

	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#value()
	 */
	public int value() 
	{
		return OperatorTypes.equal;
	}
	

}
