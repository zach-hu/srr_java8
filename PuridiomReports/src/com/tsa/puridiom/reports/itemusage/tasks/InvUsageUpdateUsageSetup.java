/*
 * Created on Jun 2, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvUsageUpdateUsageSetup extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest =(Map)object;
			Calendar cal = Calendar.getInstance();
			BigDecimal InvUsage_usageYear = new BigDecimal(cal.get(Calendar.YEAR));
			incomingRequest.put("InvUsage_usageYear", InvUsage_usageYear.toString());
			BigDecimal InvUsage_usageMonth = new BigDecimal(cal.get(Calendar.MONTH));
			incomingRequest.put("InvUsage_usageMonth", InvUsage_usageMonth.toString());
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			String InvUsage_itemNumber = disbLine.getItemNumber();
			incomingRequest.put("InvUsage_itemNumber", InvUsage_itemNumber);
			BigDecimal InvUsage_quantity = disbLine.getQuantity();
			incomingRequest.put("InvUsage_quantity", InvUsage_quantity.toString());
			
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
