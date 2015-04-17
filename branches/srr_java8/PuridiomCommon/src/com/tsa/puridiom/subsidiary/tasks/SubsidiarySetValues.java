package com.tsa.puridiom.subsidiary.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.Subsidiary;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class SubsidiarySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Subsidiary subsidiary = (Subsidiary) incomingRequest.get("subsidiary");
			if (subsidiary == null)
			{
				subsidiary = new Subsidiary();
			}

			if (incomingRequest.containsKey("Subsidiary_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("Subsidiary_icHeader");
				if (Utility.isEmpty( icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				subsidiary.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("Subsidiary_countryCode"))
			{
				String countryCode = (String) incomingRequest.get("Subsidiary_countryCode");
				subsidiary.setCountryCode(countryCode);
			}
			if (incomingRequest.containsKey("Subsidiary_subsidiaryLanguaje"))
			{
				String subsidiaryLanguaje = (String) incomingRequest.get("Subsidiary_subsidiaryLanguaje");
				subsidiary.setSubsidiaryName(subsidiaryLanguaje);
			}
			if (incomingRequest.containsKey("Subsidiary_subsidiaryName"))
			{
				String subsidiaryName = (String) incomingRequest.get("Subsidiary_subsidiaryName");
				subsidiary.setSubsidiaryName(subsidiaryName);
			}
			if (incomingRequest.containsKey("Subsidiary_status"))
			{
				String status = (String ) incomingRequest.get("Subsidiary_status");
				subsidiary.setStatus(status);
			}

			result = subsidiary;
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