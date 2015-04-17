/**
 * Created on May 25, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.CheckMonthUsage.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvUsage;
import com.tsa.puridiom.entity.InvUsagePK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * CheckMonthUsage 
 */
public class CheckMonthUsage extends Task
{
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			Calendar cal = (Calendar)incomingRequest.get("date");
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			List usage = (List)incomingRequest.get("usageMonths");
			BigDecimal bdMonth = new BigDecimal(0);
			BigDecimal bd12Month = new BigDecimal(0);
			BigDecimal bd24Month = new BigDecimal(0);
			List dwUsage = (List)incomingRequest.get("usageMonths");
			
			if(usage.size() < 24)
			{
				if(month == 0)
				{
					//month = 12;
					month = 11;
					year = cal.get(Calendar.YEAR) - 1;
				}
				else
				{
					month = cal.get(Calendar.MONTH);
				}
				List altUsage = new ArrayList();
				for(int u = 0; u < 24; u++)
				{
					InvUsage tempUsage = new InvUsage();
					InvUsagePK pk = new InvUsagePK();
					pk.setItemNumber((String)incomingRequest.get("InvUsage_itemNumber"));
					pk.setUsageYear(new BigDecimal(year));
					pk.setUsageMonth(new BigDecimal(month));
					tempUsage.setComp_id(pk);
					tempUsage.setQuantity(new BigDecimal(0));
					altUsage.add(tempUsage);
					if(month == 0)
					{
						//month = 12;
						month = 11;
						year = year - 1;
					}
					else
					{
						month = month -1;
					}
				}
				
				
				for(int u = 0; u < 24; u++)
				{
					InvUsage tempUsage = (InvUsage) altUsage.get(u);
					
					month = tempUsage.getUsageMonth().intValue();
					year = tempUsage.getUsageYear().intValue();
					boolean found = false;
					for (Iterator iter= dwUsage.iterator(); iter.hasNext();)
					{
						InvUsage dwInvUsage = (InvUsage) iter.next();
						if(dwInvUsage.getUsageMonth().intValue() == month && dwInvUsage.getUsageYear().intValue() == year)
						{
							found = true;
						}
					}
					if(!found)
					{
						dwUsage.add(tempUsage);
					}
				}
			}
			
			InvUsageDescending comp = new InvUsageDescending();
			Collections.sort(dwUsage, comp);
			ItemDetailUsage itemDetailUsage = (ItemDetailUsage)incomingRequest.get("itemDetailUsage");
			int u = 1;
			for ( Iterator iter= dwUsage.iterator(); iter.hasNext();)
			{
				InvUsage dwInvUsage = (InvUsage) iter.next();
				itemDetailUsage.addMonthUsage(dwInvUsage);
				if(u == 1)
				{
					bdMonth = dwInvUsage.getQuantity();
					bd12Month = bdMonth;
					bd24Month = bdMonth;
				}
				else if (u < 13)
				{
					bd12Month = bd12Month.add(dwInvUsage.getQuantity());
					bd24Month = bd24Month.add(dwInvUsage.getQuantity());
				}
				else
				{
					bd24Month = bd24Month.add(dwInvUsage.getQuantity());
				}
				u++;
			}
			itemDetailUsage.setQtyLastMonth(bdMonth);
			itemDetailUsage.setQtyLast12Months(bd12Month);
			itemDetailUsage.setQtyLast24Months(bd24Month);
			BigDecimal bdAvg = new BigDecimal(0);
			if(dwUsage.size() > 0)
			{
				bdAvg = bd12Month.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
			}
			itemDetailUsage.setAvgMonthUsage(bdAvg);
			InvItem item = (InvItem)incomingRequest.get("item");
			
			itemDetailUsage.setReorderPoint(bdAvg.multiply(item.getEoqMonths()).setScale(0, BigDecimal.ROUND_HALF_UP));
			ret = itemDetailUsage;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

}
