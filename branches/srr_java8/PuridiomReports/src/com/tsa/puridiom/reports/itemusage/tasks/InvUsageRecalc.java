/**
 * Created on May 26, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.InvUsageRecalc.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvUsageRecalc 
 */
public class InvUsageRecalc extends Task
{
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			List locations = (List)incomingRequest.get("inventorySummary");
			BigDecimal yearlyDemand = new BigDecimal(0);
			BigDecimal monthlyAvg = new BigDecimal(0);
			BigDecimal minAvaiQty = new BigDecimal(0);
			BigDecimal totalMonth = new BigDecimal(0);
			BigDecimal totalEcoOrderQty = new BigDecimal(0);
			
			//	this are global variables.
			incomingRequest.put("yearlyDemand", yearlyDemand);
			incomingRequest.put("monthlyAvg", monthlyAvg);
			incomingRequest.put("minAvaiQty", minAvaiQty);
			incomingRequest.put("totalMonth", totalMonth);
			incomingRequest.put("totalEcoOrderQty", totalEcoOrderQty);
			
			for (Iterator iter= locations.iterator(); iter.hasNext();)
			{
				InvLocation summary = (InvLocation) iter.next();
				incomingRequest.put("summary", summary);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("item-recalc.xml");
				process.executeProcess(incomingRequest);
				if(process.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(process.getStatus());
					throw new TsaException("An error ouccred calculating Item Usage!");
				}
			}
			yearlyDemand = (BigDecimal)incomingRequest.get("yearlyDemand");
			monthlyAvg = (BigDecimal)incomingRequest.get("monthlyAvg");
			minAvaiQty = (BigDecimal)incomingRequest.get("minAvaiQty");
			totalMonth = (BigDecimal)incomingRequest.get("totalMonth");
			totalEcoOrderQty = (BigDecimal)incomingRequest.get("totalEcoOrderQty");
			ItemUsage usage = (ItemUsage)incomingRequest.get("itemUsage");
			
			usage.setUsageYear(yearlyDemand);
			usage.setUsageMonth(monthlyAvg);
			usage.setMohCalc(totalMonth);
			usage.setEoqCalc(totalEcoOrderQty);
			InvItem item =(InvItem)incomingRequest.get("item");
			item.setMohTot(totalMonth);
			item.setEoqTot(totalEcoOrderQty);
			ItemDetailUsage detailUsage = (ItemDetailUsage)incomingRequest.get("itemDetailUsage");
			detailUsage.setCalcMoh(totalMonth);
			detailUsage.setCalcEoq(totalEcoOrderQty);
			detailUsage.setReorderPoint(totalEcoOrderQty);
			if(monthlyAvg.compareTo(new BigDecimal(0)) > 0)
			{
				BigDecimal bdTemp = new BigDecimal(0);
				bdTemp = usage.getQoh().divide(monthlyAvg, 2, BigDecimal.ROUND_UP);
				usage.setQohMonths(bdTemp);
				bdTemp = usage.getAlloc().divide(monthlyAvg, 2, BigDecimal.ROUND_UP);
				usage.setAllocMonths(bdTemp);
				bdTemp = usage.getAvailable().divide(monthlyAvg, 2, BigDecimal.ROUND_UP);
				usage.setAvailableMonths(bdTemp);
			}
			else
			{
				usage.setQohMonths(new BigDecimal(0));
				usage.setAllocMonths(new BigDecimal(0));
				usage.setAvailableMonths(new BigDecimal(0));
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
