package com.tsa.puridiom.resetpasswordlink.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class ResetPasswordLinkAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("ResetPasswordLink_icLink",ukg.getUniqueKey().toString());						
			incomingRequest.put("ResetPasswordLink_linkDate",  new java.util.Date());
			incomingRequest.put("ResetPasswordLink_used", "N");

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