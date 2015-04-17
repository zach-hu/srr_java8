/**
 * Created on May 27, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.DisbLineQtySum.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * DisbLineQtySum 
 */
public class DisbLineQtySum extends Task
{
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			List disbSum = (List)incomingRequest.get("disbsum");
			BigDecimal disDemand = new BigDecimal(0);
			for (Iterator iter= disbSum.iterator(); iter.hasNext();)
			{
				BigDecimal qty = (BigDecimal) iter.next();
				disDemand = disDemand.add(qty);
			}
			InvItem item = (InvItem)incomingRequest.get("item");
			BigDecimal yearlyDemand = (BigDecimal)incomingRequest.get("yearlyDemand");
			BigDecimal monthlyAvg = (BigDecimal)incomingRequest.get("monthlyAvg");
			BigDecimal minAvaiQty = (BigDecimal)incomingRequest.get("minAvaiQty");
			BigDecimal totalMonth = (BigDecimal)incomingRequest.get("totalMonth");
			BigDecimal totalEcoOrderQty = (BigDecimal)incomingRequest.get("totalEcoOrderQty");
			
			yearlyDemand = yearlyDemand.add(disDemand);
			BigDecimal monthly = new BigDecimal(0);
			if(disDemand.compareTo(new BigDecimal(0)) > 0)
			{
				monthly = disDemand.divide(new BigDecimal(12), 2, BigDecimal.ROUND_UP);
			}
			monthlyAvg = monthlyAvg.add(monthly);
			
			//Get Miminum on Hand Months from item datawindow
			BigDecimal minOnHand = item.getMohMonths();
			
			//Get Minimum Available Quantity
			minAvaiQty = monthly.multiply(minOnHand);
			
			InvLocation summary = (InvLocation)incomingRequest.get("summary");
			summary.setMinOnHand(minAvaiQty);
			totalMonth = totalMonth.add(minAvaiQty);
			
			//Get Economic Order Quantity Months form item datawindow
			BigDecimal ecoOrderQtyMonthly = item.getEoqMonths();
			
			//calculate economic order quantity
			BigDecimal ecoOrderQty = monthly.multiply(ecoOrderQtyMonthly);
			
			summary.setQtyEoq(ecoOrderQty);
			totalEcoOrderQty = totalEcoOrderQty.add(ecoOrderQty);
			incomingRequest.put("summary", summary);
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