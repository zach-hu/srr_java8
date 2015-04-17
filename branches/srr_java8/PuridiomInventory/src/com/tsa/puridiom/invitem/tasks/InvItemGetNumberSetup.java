/**
 *
 */
package com.tsa.puridiom.invitem.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvItemGetNumberSetup extends Task
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
			String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
			String fiscalYear = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			if (HiltonUtility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}

			incomingRequest.put("AutoGen_documentType", "INV");
			incomingRequest.put("AutoGen_genYear", fiscalYear);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, e.toString());

			throw e;
		}

		return result;
	}
}