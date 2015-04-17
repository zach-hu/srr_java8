package com.tsa.puridiom.resetpasswordlink.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ResetPasswordLinkUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");			
			if (resetPasswordLink == null)
			{
                this.setStatus(Status.FAILED);
				throw new Exception ("ResetPasswordLink was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
            Log.debug(this, "Updating ResetPasswordLink: " + resetPasswordLink.getIcLink() +
                    " with used : " + resetPasswordLink.getUsed());

			dbs.update(resetPasswordLink);
			
			result = resetPasswordLink;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}