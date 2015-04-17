package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoLineUpdateValuesFromHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		List poLineList = (List) incomingRequest.get("poLineList");
		String oid = (String) incomingRequest.get("organizationId");
		String receiptRequired = (String) incomingRequest.get("PoHeader_receiptRequired");
		String resetReceipt = (String) incomingRequest.get("resetReceipt");
		String resetUdf2Code = (String) incomingRequest.get("resetUdf2Code");// not applicable for SRR
		String udf2Code = "";
		
		udf2Code = (String) incomingRequest.get("PoHeader_udf2Code");

//		if (Utility.isEmpty(udf2Code))
//		{
//			throw new Exception("The receipt option and inspection level were not found.");
//		}

		if (poLineList != null)
		{
			for (int i = 0; i < poLineList.size(); i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poline-update-receipt-options.xml");

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("poLine", poLine);
				if (resetReceipt.equalsIgnoreCase("true"))
				{
					updateParameters.put("PoLine_receiptRequired", receiptRequired);
				}
				/*if (!Utility.isEmpty(udf2Code) )//&& resetUdf2Code.equalsIgnoreCase("true"))
				{
					updateParameters.put("PoLine_udf2Code", udf2Code);
				}*/

				process.executeProcess(updateParameters);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("PoLineUpdateValuesFromHeader failed.");
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}