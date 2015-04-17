package com.tsa.puridiom.invmachine.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvMachineSetValuesPK
{
	public void setValues(Map incomingRequest, InvMachine invmachine ) throws Exception
	{
		try
		{
			String machineId = (String ) incomingRequest.get("InvMachine_machineId");
			invmachine.setMachineId(machineId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}