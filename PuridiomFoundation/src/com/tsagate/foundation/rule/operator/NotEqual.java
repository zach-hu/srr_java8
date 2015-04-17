/*
 * Created on Nov 20, 2003
 */
package com.tsagate.foundation.rule.operator;

import java.util.Comparator;

/**
 * @author Kelli
 */
public class NotEqual extends Operator 
{

	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#compare(java.lang.Object, java.lang.Object)
	 */
	public boolean compare(Object objA, Object B)
	{
		Comparator comparator = this.getComparator(objA);
		if (objA == null && B == null) {
			return false;
		} else if (objA == null) {
			return true;
		} else if (B == null) {
			return true;
		}
		return (comparator.compare(objA, B) != 0);
		
	}

	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#value()
	 */
	public int value() 
	{
		return OperatorTypes.equal;
	}
	

}
