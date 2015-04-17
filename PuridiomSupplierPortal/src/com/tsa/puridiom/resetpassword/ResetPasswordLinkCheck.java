package com.tsa.puridiom.resetpassword;

import java.util.Map;

import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ResetPasswordLinkCheck extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String resetPasswordLinkActive = "N";

			ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");
			if (resetPasswordLink != null)
			{
				resetPasswordLinkActive = "Y";
				incomingRequest.put("ResetPasswordLink_used", "Y");
			}

            Log.debug(this, "resetPasswordLinkActive: " + resetPasswordLinkActive);

			result = resetPasswordLinkActive;
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			Log.error(this, "ResetPasswordLinkCheck failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}