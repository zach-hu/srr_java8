package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.Map;

public class CheckBuyerWarrantAmount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			ret = "false";

			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String organizationId = (String)incomingRequest.get("organizationId");
			
			if (poHeader != null)
			{
				String buyerId = poHeader.getBuyerCode();

				BigDecimal warrantAmount = UserManager.getInstance().getUser(organizationId, buyerId).getWarrantAmount();
				BigDecimal poTotal = poHeader.getTotal();

				if (warrantAmount.compareTo(poTotal) >= 0) {
					ret = "true";
				} else {
					ret = "false";
				}
			}
		}
		catch (Exception e)
		{
			ret = "false";
		}
		this.setStatus(Status.SUCCEEDED);
		return ret;
	}
}
