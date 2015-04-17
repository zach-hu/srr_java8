package com.tsa.puridiom.invstage.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvStageSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvStage_stageId", "");
			incomingRequest.put("InvStage_description", "");
			incomingRequest.put("InvStage_respons", "");
			incomingRequest.put("InvStage_notes", "");
			incomingRequest.put("InvStage_workCenterId", "");
			incomingRequest.put("InvStage_utilization", "0");
			incomingRequest.put("InvStage_qtyDays", "0");
			incomingRequest.put("InvStage_setupHours", "0");
			incomingRequest.put("InvStage_partsHour", "0");
			incomingRequest.put("InvStage_timePart", "0");
			incomingRequest.put("InvStage_vendorName", "");
			incomingRequest.put("InvStage_leadTime", "0");
			incomingRequest.put("InvStage_outside", "");
			incomingRequest.put("InvStage_descriptor", "");
			incomingRequest.put("InvStage_machineId", "");
			incomingRequest.put("InvStage_backflush", "");
			incomingRequest.put("InvStage_persons","0") ;
			incomingRequest.put("InvStage_ccost","0") ;
			incomingRequest.put("InvStage_unitOfMeasure","") ;
			incomingRequest.put("InvStage_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvStage_dateExpires", Dates.today("", ""));
			incomingRequest.put("InvStage_owner", "");
			incomingRequest.put("InvStage_status", "");

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