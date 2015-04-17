package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class CapitalProjectCarSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CapitalProjectCar capitalProjectCar = (CapitalProjectCar) incomingRequest.get("capitalProjectCar");
			if (capitalProjectCar == null)
			{
				capitalProjectCar = new CapitalProjectCar();
			}

			if (incomingRequest.containsKey("CapitalProjectCar_icProjectCar"))
			{
				String icProjectCarString = (String) incomingRequest.get("CapitalProjectCar_icProjectCar");
				if (Utility.isEmpty(icProjectCarString))
				{
					icProjectCarString = "0";
				}
				BigDecimal icProjectCar = new BigDecimal ( icProjectCarString );
				capitalProjectCar.setIcProjectCar(icProjectCar);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_division"))
			{
				String division = (String) incomingRequest.get("CapitalProjectCar_division");
				capitalProjectCar.setDivision(division);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_project"))
			{
				String project = (String) incomingRequest.get("CapitalProjectCar_project");
				capitalProjectCar.setProject(project);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_car"))
			{
				String car = (String) incomingRequest.get("CapitalProjectCar_car");
				capitalProjectCar.setCar(car);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_description"))
			{
				String description = (String) incomingRequest.get("CapitalProjectCar_description");
				capitalProjectCar.setDescription(description);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_program"))
			{
				String program = (String) incomingRequest.get("CapitalProjectCar_program");
				capitalProjectCar.setProgram(program);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_amount"))
			{
				String amountString = (String) incomingRequest.get("CapitalProjectCar_amount");
				if (Utility.isEmpty(amountString))
				{
					amountString = "0";
				}
				BigDecimal amount = new BigDecimal ( amountString );
				capitalProjectCar.setAmount(amount);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_status"))
			{
				String status = (String) incomingRequest.get("CapitalProjectCar_status");
				capitalProjectCar.setStatus(status);
			}
			if (incomingRequest.containsKey("CapitalProjectCar_year"))
			{
				String year = (String) incomingRequest.get("CapitalProjectCar_year");
				capitalProjectCar.setYear(year);
			}

			result = capitalProjectCar;
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