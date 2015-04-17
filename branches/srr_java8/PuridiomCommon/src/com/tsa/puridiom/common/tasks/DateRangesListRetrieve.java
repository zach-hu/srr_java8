/**
 *
 */
package com.tsa.puridiom.common.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DateRanges;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class DateRangesListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String organizationId = (String) incomingRequest.get("organizationId");
		try
		{
			result = DateRanges.getPropertyMap(organizationId);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
