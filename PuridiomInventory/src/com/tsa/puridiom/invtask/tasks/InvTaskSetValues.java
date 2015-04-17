package com.tsa.puridiom.invtask.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvTaskSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvTask invTask = (InvTask) incomingRequest.get("invTask");
			if (invTask == null)
			{
				invTask = new InvTask();
			}

			if (incomingRequest.containsKey("InvTask_taskId"))
			{
				String taskId = (String) incomingRequest.get("InvTask_taskId");
				invTask.setTaskId(taskId);
			}
			if (incomingRequest.containsKey("InvTask_refNo"))
			{
				String refNoString = (String) incomingRequest.get("InvTask_refNo");
				if (Utility.isEmpty(refNoString))
				{
					refNoString = "0";
				}
				BigDecimal refNo = new BigDecimal ( refNoString );
				invTask.setRefNo(refNo);
			}
			if (incomingRequest.containsKey("InvTask_stageId"))
			{
				String stageId = (String) incomingRequest.get("InvTask_stageId");
				invTask.setStageId(stageId);
			}
			if (incomingRequest.containsKey("InvTask_taskType"))
			{
				String taskType = (String) incomingRequest.get("InvTask_taskType");
				invTask.setTaskType(taskType);
			}
			if (incomingRequest.containsKey("InvTask_description"))
			{
				String description = (String) incomingRequest.get("InvTask_description");
				invTask.setDescription(description);
			}
			if (incomingRequest.containsKey("InvTask_notes"))
			{
				String notes = (String) incomingRequest.get("InvTask_notes");
				invTask.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvTask_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvTask_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invTask.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvTask_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvTask_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invTask.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvTask_owner"))
			{
				String owner = (String) incomingRequest.get("InvTask_owner");
				invTask.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvTask_status"))
			{
				String status = (String) incomingRequest.get("InvTask_status");
				invTask.setStatus(status);
			}

			result = invTask;
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