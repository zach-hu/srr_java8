package com.tsa.puridiom.departmentbuyer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class DepartmentBuyerSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DepartmentBuyerPK comp_id = new DepartmentBuyerPK();
			DepartmentBuyer departmentBuyer = (DepartmentBuyer) incomingRequest.get("departmentBuyer");
			if (departmentBuyer == null)
			{
				departmentBuyer = new DepartmentBuyer();
			}

			if (incomingRequest.containsKey("DepartmentBuyer_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("DepartmentBuyer_departmentCode");
				comp_id.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("DepartmentBuyer_userId"))
			{
				String userId = (String ) incomingRequest.get("DepartmentBuyer_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("DepartmentBuyer_assignAmount"))
			{
				String assignAmountString = (String) incomingRequest.get("DepartmentBuyer_assignAmount");
				if (Utility.isEmpty(assignAmountString))
				{
					assignAmountString = "0";
				}
				BigDecimal assignAmount = new BigDecimal ( assignAmountString );
				departmentBuyer.setAssignAmount(assignAmount);
			}
			departmentBuyer.setComp_id(comp_id);

			result = departmentBuyer;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}