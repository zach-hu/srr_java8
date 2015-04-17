/*
 * Created on Dec 10, 2003
 */
package com.tsagate.foundation.rule.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * @author Kelli
 */
public class BigDecimalComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object objectOne, Object objectTwo) {
		if (objectOne == null && objectTwo == null)
		{
			return 0;
		}
		else if (objectOne == null || objectTwo == null)
		{
			return -1;
		}
		else
		{
			BigDecimal one = new BigDecimal("0");
			BigDecimal two = new BigDecimal("0");
			
			if (objectOne instanceof BigDecimal)
			{
				one = (BigDecimal) objectOne;
			}
			else if (objectOne instanceof String)
			{
				one = new BigDecimal((String) objectOne);
			}
			if (objectTwo instanceof BigDecimal)
			{
				two = (BigDecimal) objectTwo;
			}
			else if (objectTwo instanceof String)
			{
				two = new BigDecimal((String) objectTwo);
			}
			
			return one.compareTo(two);
		}
	}

}
