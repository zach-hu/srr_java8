/*
 * Created on Jul 18, 2003 
 */
package com.tsagate.foundation.rule.comparator;

import java.util.Comparator;
import java.util.*;

/**
 * @author Administrator 
 */
public class DateComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object objectOne, Object objectTwo) {
		Date dateOne = (Date)objectOne;
		Date dateTwo = (Date)objectTwo;
		return dateOne.compareTo(dateTwo);
	}

}
