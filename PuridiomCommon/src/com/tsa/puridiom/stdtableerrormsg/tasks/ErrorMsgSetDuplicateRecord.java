package com.tsa.puridiom.stdtableerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

public class ErrorMsgSetDuplicateRecord extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String oid = (String)incomingRequest.get("organizationId");
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			String errorText = "record";
			String tableType = HiltonUtility.ckNull((String) incomingRequest.get("tableType"));
			if (!HiltonUtility.isEmpty(tableType))
			{
				String title = DictionaryManager.getLabel(oid, tableType, "");
				if (!HiltonUtility.isEmpty(title))
				{
					errorText = title;
				}
			}

			message = message + "  This " + errorText + " code already exists.  Your information has not been saved.";

			incomingRequest.put("duplicateRecordErrorMsg", message);
			incomingRequest.put("newStdTable", "Y");

			result = message;
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