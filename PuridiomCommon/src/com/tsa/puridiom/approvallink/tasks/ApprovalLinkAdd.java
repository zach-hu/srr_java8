/**
 *
 */
package com.tsa.puridiom.approvallink.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLink;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 */
public class ApprovalLinkAdd extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ApprovalLink approvalLink = (ApprovalLink) incomingRequest.get("approvalLink");

			if (approvalLink == null)
			{
				throw new Exception("ApprovalLink was not found.");
			}

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			dbs.add(approvalLink);

			result = approvalLink;
			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			Log.error(this, "Error adding an ApprovalLink record \r\n" + e.getMessage());

			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
