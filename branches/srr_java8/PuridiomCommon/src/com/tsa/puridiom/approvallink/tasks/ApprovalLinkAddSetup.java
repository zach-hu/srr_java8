/**
 *
 */
package com.tsa.puridiom.approvallink.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Johnny Zapana
 */
public class ApprovalLinkAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("ApprovalLink_icApprovalLink", ukg.getUniqueKey().toString());
			incomingRequest.put("ApprovalLink_logDate", Dates.today("yyyy/MM/dd", userTimeZone));
	        incomingRequest.put("ApprovalLink_logTime", Dates.getNow(null, userTimeZone));

			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			Log.error(this, "Error executing ApprovalLinkAddSetup \r\n" + e.getMessage());

			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
