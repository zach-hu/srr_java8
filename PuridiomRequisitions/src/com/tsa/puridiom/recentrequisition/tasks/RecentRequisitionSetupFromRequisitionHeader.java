package com.tsa.puridiom.recentrequisition.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RecentRequisitionSetupFromRequisitionHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    if (requisitionHeader == null) {
		        throw new Exception("RecentRequisition cannot be setup.  RequisitionHeader was not found.");
		    }
		    incomingRequest.put("RecentRequisition_requisitionerCode", requisitionHeader.getRequisitionerCode());
		    incomingRequest.put("RecentRequisition_icReqHeader", requisitionHeader.getIcReqHeader().toString());
		    incomingRequest.put("RecentRequisition_dateEntered", Dates.today("", ""));
		    // Use date in default system time zone to ensure proper sorting

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