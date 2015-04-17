package com.tsa.puridiom.invworkcenter.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvWorkCenterSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvWorkCenter_workCenterId", "");
			incomingRequest.put("InvWorkCenter_description", "");
			incomingRequest.put("InvWorkCenter_departmentCode", "");
			incomingRequest.put("InvWorkCenter_subcontract", "");
			incomingRequest.put("InvWorkCenter_laborRate", "0");
			incomingRequest.put("InvWorkCenter_setupRate", "0");
			incomingRequest.put("InvWorkCenter_fixOverHead", "0");
			incomingRequest.put("InvWorkCenter_varOverHead", "0");
			incomingRequest.put("InvWorkCenter_setuphours", "0");
			incomingRequest.put("InvWorkCenter_perDayHours", "0");
			incomingRequest.put("InvWorkCenter_perJobHours", "0");
			incomingRequest.put("InvWorkCenter_processTime", "0");
			incomingRequest.put("InvWorkCenter_processItems", "0");
			incomingRequest.put("InvWorkCenter_bufferDays", "0");
			incomingRequest.put("InvWorkCenter_vendorName", "");
			incomingRequest.put("InvWorkCenter_vendorId", "");
			incomingRequest.put("InvWorkCenter_cost", "0");
			incomingRequest.put("InvWorkCenter_unitOfMeasure", "");
			incomingRequest.put("InvWorkCenter_leadTime", "0");
			incomingRequest.put("InvWorkCenter_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvWorkCenter_dateExpires", Dates.today("", ""));
			incomingRequest.put("InvWorkCenter_owner", "");
			incomingRequest.put("InvWorkCenter_status", "");

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