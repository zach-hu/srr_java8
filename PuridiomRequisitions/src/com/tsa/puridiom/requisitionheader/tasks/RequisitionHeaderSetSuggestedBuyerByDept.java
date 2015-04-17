/*
 * Created on January 17, 2007
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
/**
 * @author Kathleen
 */
public class RequisitionHeaderSetSuggestedBuyerByDept extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try
		{
			String userid = (String) incomingRequest.get("UserProfile_userId");

			if ( !HiltonUtility.isEmpty(userid) )
			{
				incomingRequest.put("RequisitionHeader_buyer", userid);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
