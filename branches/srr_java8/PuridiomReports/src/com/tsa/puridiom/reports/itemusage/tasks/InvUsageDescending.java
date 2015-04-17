/**
 * Created on May 26, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.InvUsageDescending.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Comparator;

import com.tsa.puridiom.entity.InvUsage;

/*
 * InvUsageDescending 
 */
public class InvUsageDescending implements Comparator
{
	/* 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2)
	{
		InvUsage usage1 = null;
		InvUsage usage2 = null;
		
		if(o1 instanceof InvUsage)
		{	
			usage1 = (InvUsage)o1;
		}
		if(o2 instanceof InvUsage)
		{	
			usage2 = (InvUsage)o2;
		}
		int year = usage1.getUsageYear().compareTo(usage2.getUsageYear());
		
		if(year == 0)
		{
			int month = usage1.getUsageMonth().compareTo(usage2.getUsageMonth());
			return (month * -1);
		}
		else
		{
			return (year * -1);
		}
	}
}