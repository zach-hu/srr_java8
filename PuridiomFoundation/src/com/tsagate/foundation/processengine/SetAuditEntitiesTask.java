/*
 * Created on Nov 5, 2003
 */
package com.tsagate.foundation.processengine;

import java.util.Map;

import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Renzo
 */
public class SetAuditEntitiesTask extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
				incomingRequest.put("auditTransactionId", UniqueKeyGenerator.getInstance().getUniqueKey().toString());
				this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return object;
	}
}

