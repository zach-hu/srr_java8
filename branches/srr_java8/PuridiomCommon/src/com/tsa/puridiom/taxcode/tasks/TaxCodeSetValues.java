package com.tsa.puridiom.taxcode.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class TaxCodeSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			TaxCode taxCode = (TaxCode) incomingRequest.get("taxCode");
			if (taxCode == null)
			{
				taxCode = new TaxCode();
			}

			if (incomingRequest.containsKey("TaxCode_taxCode"))
			{
				String code = (String ) incomingRequest.get("TaxCode_taxCode");
				taxCode.setTaxCode(code);
			}
			if (incomingRequest.containsKey("TaxCode_description"))
			{
				String description = (String ) incomingRequest.get("TaxCode_description");
				taxCode.setDescription(description);
			}
			if (incomingRequest.containsKey("TaxCode_taxRate"))
			{
				String taxRateString = (String) incomingRequest.get("TaxCode_taxRate");
				if (Utility.isEmpty(taxRateString))
				{
					taxRateString = "0";
				}
				BigDecimal taxRate = new BigDecimal ( taxRateString );
				taxCode.setTaxRate(taxRate);
			}
			if (incomingRequest.containsKey("TaxCode_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("TaxCode_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				taxCode.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("TaxCode_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("TaxCode_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				taxCode.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("TaxCode_owner"))
			{
				String owner = (String ) incomingRequest.get("TaxCode_owner");
				taxCode.setOwner(owner);
			}
			if (incomingRequest.containsKey("TaxCode_status"))
			{
				String status = (String ) incomingRequest.get("TaxCode_status");
				taxCode.setStatus(status);
			}

			result = taxCode;
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