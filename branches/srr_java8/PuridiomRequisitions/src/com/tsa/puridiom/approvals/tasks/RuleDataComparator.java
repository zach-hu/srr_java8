/*
 * Created on Jul 18, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.Comparator;

import com.tsa.puridiom.entity.AppRule;

/**
 * @author Administrator
 */
public class RuleDataComparator implements Comparator
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
			String ruleData1 = appRule1.getComp_id().getUdf1Code();
			String ruleData2 = appRule2.getComp_id().getUdf1Code();

			return ruleData1.compareTo(ruleData2);
		}
	}
}
