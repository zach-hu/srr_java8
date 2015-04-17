package com.tsa.puridiom.bomtask.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomTaskSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomTask */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomTask	originalBomTask = (BomTask) incomingRequest.get("bomTask");
			BomTask	bomTask = new BomTask();

			bomTask.setIcTask(originalBomTask.getIcTask());
			bomTask.setTaskId(originalBomTask.getTaskId());
			bomTask.setRefNo(originalBomTask.getRefNo());
			bomTask.setStageId(originalBomTask.getStageId());
			bomTask.setIcRouting(originalBomTask.getIcRouting());
			bomTask.setTaskType(originalBomTask.getTaskType());
			bomTask.setDescription(originalBomTask.getDescription());
			bomTask.setNotes(originalBomTask.getNotes());
			bomTask.setDateEntered(originalBomTask.getDateEntered());
			bomTask.setOwner(originalBomTask.getOwner());

			incomingRequest.put("bomTask", bomTask);

			BomTaskAdd addTask = new BomTaskAdd();
			bomTask = (BomTask) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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