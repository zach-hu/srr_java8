/*
 * @author ebsGroup mcvz
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ApprovalAbleVerification extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result;
		String approvalAbleVerification = "";

		try
		{
			approvalAbleVerification  = (String)incomingRequest.get("approvalAbleVerification");
			if (HiltonUtility.isEmpty(approvalAbleVerification)){
				approvalAbleVerification = "Y";
			}

			result = approvalAbleVerification;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ApprovalAbleCheck failed with: " + e.getMessage(), e);
		}
		return result ;
    }
}
