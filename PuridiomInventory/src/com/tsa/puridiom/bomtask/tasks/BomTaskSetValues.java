package com.tsa.puridiom.bomtask.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomTaskSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomTask bomTask = (BomTask) incomingRequest.get("bomTask");
			if (bomTask == null)
			{
				bomTask = new BomTask();
			}

			if (incomingRequest.containsKey("BomTask_icTask"))
			{
				String icTaskString = (String) incomingRequest.get("BomTask_icTask");
				if (Utility.isEmpty(icTaskString))
				{
					icTaskString = "0";
				}
				BigDecimal icTask = new BigDecimal ( icTaskString );
				bomTask.setIcTask(icTask);
			}
			if (incomingRequest.containsKey("BomTask_taskId"))
			{
				String taskId = (String) incomingRequest.get("BomTask_taskId");
				bomTask.setTaskId(taskId);
			}
			if (incomingRequest.containsKey("BomTask_refNo"))
			{
				String refNoString = (String) incomingRequest.get("BomTask_refNo");
				if (Utility.isEmpty(refNoString))
				{
					refNoString = "0";
				}
				BigDecimal refNo = new BigDecimal ( refNoString );
				bomTask.setRefNo(refNo);
			}
			if (incomingRequest.containsKey("BomTask_stageId"))
			{
				String stageId = (String) incomingRequest.get("BomTask_stageId");
				bomTask.setStageId(stageId);
			}
			if (incomingRequest.containsKey("BomTask_icRouting"))
			{
				String icRoutingString = (String) incomingRequest.get("BomTask_icRouting");
				if (Utility.isEmpty(icRoutingString))
				{
					icRoutingString = "0";
				}
				BigDecimal icRouting = new BigDecimal ( icRoutingString );
				bomTask.setIcRouting(icRouting);
			}
			if (incomingRequest.containsKey("BomTask_taskType"))
			{
				String taskType = (String) incomingRequest.get("BomTask_taskType");
				bomTask.setTaskType(taskType);
			}
			if (incomingRequest.containsKey("BomTask_description"))
			{
				String description = (String) incomingRequest.get("BomTask_description");
				bomTask.setDescription(description);
			}
			if (incomingRequest.containsKey("BomTask_notes"))
			{
				String notes = (String) incomingRequest.get("BomTask_notes");
				bomTask.setNotes(notes);
			}
			if (incomingRequest.containsKey("BomTask_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomTask_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomTask.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomTask_owner"))
			{
				String owner = (String) incomingRequest.get("BomTask_owner");
				bomTask.setOwner(owner);
			}

			result = bomTask;
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