package com.tsa.puridiom.requisition.rules;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RequisitionValidEngineerId extends Task
{
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			boolean functionalClass = false;
			boolean validEngineerId = true;
			String funcClassHeader = "";
			String msrType ="";
			boolean requestCat = false;
			requestCat = requestCatValid(header);

			funcClassHeader = HiltonUtility.ckNull(header.getUdf7Code());
			if (funcClassHeader.equalsIgnoreCase("SS") || funcClassHeader.equalsIgnoreCase("SC") || funcClassHeader.equalsIgnoreCase("ER"))
			{
				functionalClass = true;
			}

			msrType = HiltonUtility.ckNull(header.getUdf13Code());
			if (requestCat && msrType.indexOf("C") != 0 && functionalClass && Utility.isEmpty(header.getBuyer()))
			{
				validEngineerId = false;
			}

			incomingRequest.put("validEngineerId", String.valueOf(validEngineerId));
		}
		catch(Exception e)
		{
			Log.error(this, "RequisitionValidEngineerId error " + e.getMessage());

			throw e;
		}
		return result;
	}

	public boolean requestCatValid(RequisitionHeader header)
	{
		String requestCat = "";
		boolean validate = false;
		requestCat = (HiltonUtility.ckNull(header.getRequestCat()));

		if (requestCat.equalsIgnoreCase("SPA") || requestCat.equalsIgnoreCase("SA") || requestCat.equalsIgnoreCase("SL12") || requestCat.equalsIgnoreCase("SL3"))
		{
			return validate;
		}
		else
		{
			validate = true;
			return validate;
		}
	}
}
