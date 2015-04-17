package com.tsa.puridiom.invtask.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvTaskSetValuesPK
{
	public void setValues(Map incomingRequest, InvTask invtask ) throws Exception
	{
		try
		{
			String taskId = (String ) incomingRequest.get("InvTask_taskId");
			invtask.setTaskId(taskId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}