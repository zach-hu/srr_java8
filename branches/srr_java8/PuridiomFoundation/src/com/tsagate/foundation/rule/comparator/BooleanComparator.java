/*
 * Created on Nov 5, 2003 
 */
package com.tsagate.foundation.rule.comparator;

import java.util.Comparator;

/**
 * @author renzo 
 */
public class BooleanComparator implements Comparator
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2)
	{
		Boolean b1 = (Boolean)o1;
		Boolean b2 = (Boolean)o2;
		int iret = 0;
		if(b1.equals(b2))
		{
			iret = 0;
		}
		else
		{
			iret = 1;
		}
		
		return iret;
	}
}
