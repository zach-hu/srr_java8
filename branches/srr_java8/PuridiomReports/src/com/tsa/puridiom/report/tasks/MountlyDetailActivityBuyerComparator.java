package com.tsa.puridiom.report.tasks;

import java.util.Comparator;

import com.tsa.puridiom.entity.MonthlyDetailActivityBuyer;

public class MountlyDetailActivityBuyerComparator implements Comparator {

	public int compare(Object o1, Object o2) { 
		MonthlyDetailActivityBuyer object1 = (MonthlyDetailActivityBuyer)o1; 
		MonthlyDetailActivityBuyer object2 = (MonthlyDetailActivityBuyer)o2; 
        return object1.getPeriod1(). 
                compareTo(object2.getPeriod1()); 

    } 

}
