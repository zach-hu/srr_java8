package com.tsa.puridiom.invtask.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvTaskSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invTask */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvTask	originalInvTask = (InvTask) incomingRequest.get("invTask");
			InvTask	invTask = new InvTask();

			invTask.setTaskId(originalInvTask.getTaskId());
			invTask.setRefNo(originalInvTask.getRefNo());
			invTask.setStageId(originalInvTask.getStageId());
			invTask.setTaskType(originalInvTask.getTaskType());
			invTask.setDescription(originalInvTask.getDescription());
			invTask.setNotes(originalInvTask.getNotes());
			invTask.setDateEntered(originalInvTask.getDateEntered());
			invTask.setDateExpires(originalInvTask.getDateExpires());
			invTask.setOwner(originalInvTask.getOwner());
			invTask.setStatus(originalInvTask.getStatus());

			incomingRequest.put("invTask", invTask);

			InvTaskAdd addTask = new InvTaskAdd();
			invTask = (InvTask) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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