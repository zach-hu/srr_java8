package com.tsa.puridiom.bomtask.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomTaskSetValuesPK
{
	public void setValues(Map incomingRequest, BomTask bomtask ) throws Exception
	{
		try
		{
			String icTaskString = (String) incomingRequest.get("BomTask_icTask");
			BigDecimal icTask = new BigDecimal ( icTaskString );
			bomtask.setIcTask(icTask);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}