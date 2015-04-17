/**
 *
 */
package com.tsa.puridiom.common.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class GeneralStatusListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String organizationId = (String) incomingRequest.get("organizationId");
		String startCode = "0";
		String lastCode = "100000";

		try
		{

			Map specificStatus = GeneralStatus.getPropertyMap(organizationId, startCode, lastCode);
			Map extraStatus = GeneralStatus.getPropertyMap(organizationId, "9000", "9020");
			specificStatus.putAll(extraStatus);

			result = specificStatus;

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
