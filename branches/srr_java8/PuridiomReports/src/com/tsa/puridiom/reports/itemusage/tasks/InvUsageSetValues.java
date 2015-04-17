package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvUsage;
import com.tsa.puridiom.entity.InvUsagePK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvUsageSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvUsagePK comp_id = new InvUsagePK();
			InvUsage invUsage = (InvUsage) incomingRequest.get("invUsage");
			if (invUsage == null)
			{
				invUsage = new InvUsage();
			}

			if (incomingRequest.containsKey("InvUsage_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvUsage_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvUsage_usageYear"))
			{
				String yearString = (String) incomingRequest.get("InvUsage_usageYear");
				if (Utility.isEmpty(yearString))
				{
					yearString = "0";
				}
				BigDecimal year = new BigDecimal ( yearString );
				comp_id.setUsageYear(year);
			}
			if (incomingRequest.containsKey("InvUsage_usageMonth"))
			{
				String monthString = (String) incomingRequest.get("InvUsage_usageMonth");
				if (Utility.isEmpty(monthString))
				{
					monthString = "0";
				}
				BigDecimal month = new BigDecimal ( monthString );
				comp_id.setUsageMonth(month);
			}
			if (incomingRequest.containsKey("InvUsage_quantity"))
			{
				String qtyString = (String) incomingRequest.get("InvUsage_quantity");
				if (Utility.isEmpty(qtyString))
				{
					qtyString = "0";
				}
				BigDecimal qty = new BigDecimal ( qtyString );
				invUsage.setQuantity(qty);
			}
			invUsage.setComp_id(comp_id);

			result = invUsage;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}