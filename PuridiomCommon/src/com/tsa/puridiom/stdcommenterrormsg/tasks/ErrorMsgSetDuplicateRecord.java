package com.tsa.puridiom.stdcommenterrormsg.tasks;

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
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			String commentId = HiltonUtility.ckNull((String ) incomingRequest.get("StdComment_commentId"));

			message = message + "  This " + commentId + " code already exists.  Your information has not been saved.";

			incomingRequest.put("duplicateRecordErrorMsg", message);
			incomingRequest.put("newStdComment", "Y");

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