/**
 *
 */
package com.tsa.puridiom.inspectionstd.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InspectionStdFormatNumberSetup extends Task
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String stdCode = (String) incomingRequest.get("InspectionStd_standardCode");
			String fiscalYear = (String) incomingRequest.get("AutoGen_fiscalYear");

			incomingRequest.put("AutoGen_documentType", "CRT");
			incomingRequest.put("AutoGen_genYear", fiscalYear);
			incomingRequest.put("documentNumber", stdCode);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			Log.error(this, e.toString());

			this.setStatus(Status.FAILED);

			throw e;
		}

		return result;
	}
}