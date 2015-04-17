/*
 * Created on Dec 11, 2003
 * @author renzo
 * project: TSACommon
 * com.tsagate.common.rule.comparatorIntegerComparator.java
 */
 
package com.tsagate.foundation.rule.comparator;

import java.util.Comparator;


public class IntegerComparator implements Comparator
{
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2)
	{
		Integer one = null;
		Integer two = null;
		
		if (o1 instanceof Integer)
		{
			one = (Integer) o1;
		}
		else
		{
			if (o1 == null || (o1.toString().trim().length() == 0))
			{
				one = new Integer(0);
			}
			else
			{
				one = new Integer(o1.toString());
			}
		}
		if (o2 instanceof Integer)
		{
			two = (Integer) o2;
		}
		else
		{
			if (o2 == null || (o2.toString().trim().length() == 0))
			{
				two = new Integer(0);
			}
			else
			{
				two = new Integer(o2.toString());
			}
		}
		int ret = one.compareTo(two);
		return ret;
	}

}
