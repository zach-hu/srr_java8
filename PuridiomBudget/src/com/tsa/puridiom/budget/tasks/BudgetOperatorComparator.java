/**
 * Created on May 26, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.InvUsageDescending.java
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Comparator;

import com.tsa.puridiom.entity.BudgetAudit;

/*
 * BugetOperatorComparator
 */
public class BudgetOperatorComparator implements Comparator
{
	/*
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2)
	{
		BudgetAudit	ba1 = null ;
		BudgetAudit  ba2 = null ;

		if(o1 instanceof BudgetAudit)
		{
			ba1 = (BudgetAudit)o1;
		}
		if(o2 instanceof BudgetAudit)
		{
			ba2 = (BudgetAudit)o2;
		}

		return ba1.getBOperator().compareTo(ba2.getBOperator()) ;
	}
}