package com.tsa.puridiom.noncapitalentitydepartment;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class NonCapitalEntityDepartmentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			NonCapitalEntityDepartment nonCapitalEntityDepartment = (NonCapitalEntityDepartment) incomingRequest.get("nonCapitalEntityDepartment");
			if (nonCapitalEntityDepartment == null)
			{
				nonCapitalEntityDepartment = new NonCapitalEntityDepartment();
			}

			if (incomingRequest.containsKey("NonCapitalEntityDepartment_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("NonCapitalEntityDepartment_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				nonCapitalEntityDepartment.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("NonCapitalEntityDepartment_division"))
			{
				String division = (String) incomingRequest.get("NonCapitalEntityDepartment_division");
				nonCapitalEntityDepartment.setDivision(division);
			}
			if (incomingRequest.containsKey("NonCapitalEntityDepartment_entity"))
			{
				String entity = (String) incomingRequest.get("NonCapitalEntityDepartment_entity");
				nonCapitalEntityDepartment.setEntity(entity);
			}
			if (incomingRequest.containsKey("NonCapitalEntityDepartment_department"))
			{
				String department = (String) incomingRequest.get("NonCapitalEntityDepartment_department");
				nonCapitalEntityDepartment.setDepartment(department);
			}
			if (incomingRequest.containsKey("NonCapitalEntityDepartment_status"))
			{
				String status = (String) incomingRequest.get("NonCapitalEntityDepartment_status");
				nonCapitalEntityDepartment.setStatus(status);
			}
			if (incomingRequest.containsKey("NonCapitalEntityDepartment_description"))
			{
				String description = (String) incomingRequest.get("NonCapitalEntityDepartment_description");
				nonCapitalEntityDepartment.setDescription(description);
			}

			result = nonCapitalEntityDepartment;
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