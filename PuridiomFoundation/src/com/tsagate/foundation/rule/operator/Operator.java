/*
 * Created on Sep 17, 2003 
 */
package com.tsagate.foundation.rule.operator;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import com.tsagate.foundation.rule.comparator.*;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo 
 */
public class Operator implements IOperator
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#value()
	 */
	public int value()
	{
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.tsagate.common.rule.newrule.IOperator#compare(java.lang.Object, java.lang.Object)
	 */
	public boolean compare(Object objA, Object B)
	{
		return false;
	}
	
	public Comparator getComparator(Object object)
	{
		Comparator compareTool = null;
		try
		{
			if(object instanceof Double)
			{
				compareTool = new DoubleComparator();
			}
			if(object instanceof Integer)
			{
				compareTool = new IntegerComparator();
			}
			else if(object instanceof String)
			{
				compareTool = new StringComparator();
			}
			else if(object instanceof Date)
			{
				compareTool = new DateComparator();
			}
			else if(object instanceof Boolean)
			{
				compareTool = new BooleanComparator();
			}
			else if(object instanceof BigDecimal)
			{
				compareTool = new BigDecimalComparator();
			}
			else
			{
				compareTool = new ObjectComparator();
			}
		}
		catch(Exception exception)
		{
			Log.error(this, exception.toString());
		}
		return compareTool;
	}
}
