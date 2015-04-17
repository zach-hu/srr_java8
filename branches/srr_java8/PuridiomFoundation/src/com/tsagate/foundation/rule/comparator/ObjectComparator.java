/*
 * Created on Nov 19, 2003
 */
package com.tsagate.foundation.rule.comparator;

import java.util.Comparator;

/**
 * @author Kelli
 */
public class ObjectComparator implements Comparator 
{
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object objectOne, Object objectTwo) 
	{
		if (objectOne == null && objectTwo == null)
		{
			return 0;
		}
		else if (objectOne != null && objectTwo != null && objectOne.equals(objectTwo))
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}

}
