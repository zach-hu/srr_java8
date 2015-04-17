package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineResetValuesFromHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		List reqLineList = (List) incomingRequest.get("requisitionLineList");
		String oid = (String) incomingRequest.get("organizationId");
		String receiptRequired = (String) incomingRequest.get("RequisitionHeader_receiptRequired");
		String resetReceipt = (String) incomingRequest.get("resetReceipt");
		String resetUdf2Code = (String) incomingRequest.get("resetUdf2Code");
		String udf2Code = "";

		if (oid.equalsIgnoreCase("vse06p"))
		{
			udf2Code = (String) incomingRequest.get("RequisitionHeader_udf2Code");
		}

		if (Utility.isEmpty(receiptRequired) && Utility.isEmpty(udf2Code))
		{
			throw new Exception("The receipt option and inspection level were not found.");
		}

		if (reqLineList != null)
		{
			for (int i = 0; i < reqLineList.size(); i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-receipt-options.xml");

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("requisitionLine", reqLine);
				if (!Utility.isEmpty(receiptRequired) && resetReceipt.equalsIgnoreCase("true"))
				{
					updateParameters.put("RequisitionLine_receiptRequired", receiptRequired);
				}
				if (!Utility.isEmpty(udf2Code) && resetUdf2Code.equalsIgnoreCase("true"))
				{
					updateParameters.put("RequisitionLine_udf2Code", udf2Code);
				}

				process.executeProcess(updateParameters);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RequisitionLineUpdateValuesFromHeader failed.");
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}