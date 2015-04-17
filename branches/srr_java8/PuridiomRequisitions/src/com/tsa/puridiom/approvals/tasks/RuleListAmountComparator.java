/*
 * Created on Jul 18, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Comparator;

import com.tsa.puridiom.entity.AppRule;

/**
 * @author Administrator
 */
public class RuleListAmountComparator implements Comparator
{
	public int compare(Object objectOne, Object objectTwo)
	{
		AppRule appRule1 = (AppRule) objectOne ;
		AppRule appRule2 = (AppRule) objectTwo ;

		if (appRule1 == null && appRule2 == null)
		{
			return 0;
		}
		else if (appRule1 == null || appRule2 == null)
		{
			return -1;
		}
		else
		{
			BigDecimal bd1 = appRule1.getUserAmount();
			BigDecimal bd2 = appRule2.getUserAmount();

			return bd1.compareTo(bd2);
		}
	}
}
