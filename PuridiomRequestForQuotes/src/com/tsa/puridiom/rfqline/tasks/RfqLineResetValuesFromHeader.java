package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineResetValuesFromHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		List rfqLineList = (List) incomingRequest.get("rfqLineList");
		String oid = (String) incomingRequest.get("organizationId");
		String receiptRequired = (String) incomingRequest.get("RfqHeader_receiptRequired");
		String resetReceipt = (String) incomingRequest.get("resetReceipt");
		String resetUdf2Code = (String) incomingRequest.get("resetUdf2Code");
		String udf2Code = "";

		if (oid.equalsIgnoreCase("vse06p"))
		{
			udf2Code = (String) incomingRequest.get("RfqHeader_udf2Code");
		}

		if (Utility.isEmpty(receiptRequired) && Utility.isEmpty(udf2Code))
		{
			throw new Exception("The receipt option and inspection level were not found.");
		}

		if (rfqLineList != null)
		{
			for (int i = 0; i < rfqLineList.size(); i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqline-update-receipt-options.xml");

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("rfqLine", rfqLine);
				if (!Utility.isEmpty(receiptRequired) && resetReceipt.equalsIgnoreCase("true"))
				{
					updateParameters.put("RfqLine_receiptRequired", receiptRequired);
				}
				if (!Utility.isEmpty(udf2Code) && resetUdf2Code.equalsIgnoreCase("true"))
				{
					updateParameters.put("RfqLine_udf2Code", udf2Code);
				}

				process.executeProcess(updateParameters);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RfqLineUpdateValuesFromHeader failed.");
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}