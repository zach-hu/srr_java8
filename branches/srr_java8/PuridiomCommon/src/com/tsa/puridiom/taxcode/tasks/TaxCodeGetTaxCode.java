package com.tsa.puridiom.taxcode.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class TaxCodeGetTaxCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    TaxCode taxCodeEntity = (TaxCode) incomingRequest.get("taxCode");
		    String	taxCode = "";
		    
		    if (taxCodeEntity != null) {
		        taxCode = taxCodeEntity.getTaxCode();
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