package com.tsa.puridiom.bomrouting.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomRoutingSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomRouting_icRouting", "0");
			incomingRequest.put("BomRouting_icMethod", "0");
			incomingRequest.put("BomRouting_parentItem", "");
			incomingRequest.put("BomRouting_componentItem", "");
			incomingRequest.put("BomRouting_stageId", "");
			incomingRequest.put("BomRouting_stageSeq", "0");
			incomingRequest.put("BomRouting_description", "");
			incomingRequest.put("BomRouting_workCenterId", "");
			incomingRequest.put("BomRouting_machineId", "");
			incomingRequest.put("BomRouting_respons", "");
			incomingRequest.put("BomRouting_utilization", "0");
			incomingRequest.put("BomRouting_qtyDays", "0");
			incomingRequest.put("BomRouting_setupHours", "0");
			incomingRequest.put("BomRouting_partsHour", "0");
			incomingRequest.put("BomRouting_timePart", "0");
			incomingRequest.put("BomRouting_vendorName", "");
			incomingRequest.put("BomRouting_leadTime", "0");
			incomingRequest.put("BomRouting_outside", "");
			incomingRequest.put("BomRouting_backflush", "");
			incomingRequest.put("BomRouting_persons", "0");
			incomingRequest.put("BomRouting_ccost", "0");
			incomingRequest.put("BomRouting_unitOfMeasure", "");
			incomingRequest.put("BomRouting_poNotes", "");
			incomingRequest.put("BomRouting_notes", "");
			incomingRequest.put("BomRouting_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomRouting_owner", "");

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