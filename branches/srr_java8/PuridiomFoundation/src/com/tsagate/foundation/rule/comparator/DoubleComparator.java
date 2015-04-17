/*
 * Created on Jul 18, 2003 
 */
package com.tsagate.foundation.rule.comparator;

import java.util.Comparator;

/**
 * @author Administrator 
 */
public class DoubleComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1) {
		Double one = ((Double)arg0);
		Double two = ((Double)arg1);
		int ret = one.compareTo(two);
		return ret;
	}

}
