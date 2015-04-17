package com.tsa.puridiom.po.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class PoApproverLogSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String poApprovers = (String)incomingRequest.get("ApprovalLog_approvers");
			String poApproversArray[] = poApprovers.split(";");
			List appLogList = new ArrayList();
			String organizationId = (String)incomingRequest.get("organizationId");
			String PoHeader_icPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
			incomingRequest.put("PoLine_icPoHeader", PoHeader_icPoHeader);
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

            if (poHeader.getStatus().equals(DocumentStatus.CT_INPROGRESS)) {
                poHeader.setStatus(DocumentStatus.CT_APPROVING);
            } else {
                poHeader.setStatus(DocumentStatus.PO_APPROVING);
            }

			for (int i = 0; i < poApproversArray.length; i++)
			{
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("ApprovalLog_userId", poApproversArray[i]);
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("PoHeader_icPoHeader", PoHeader_icPoHeader);

				PoApproverLogAdd approver = new PoApproverLogAdd();
				Object appLog = approver.executeTask(newIncomingRequest);
				if (appLog != null)
				{
					ApprovalLog temp = (ApprovalLog)appLog;
					temp.getComp_id().setSequence(new BigDecimal(i + 1));
					appLogList.add(appLog);
				}

			}
			if (appLogList.size() > 0)
			{
				ApprovalLog appLog = (ApprovalLog)appLogList.get(0);
				incomingRequest.put("forwardedTo", appLog.getComp_id().getUserId());
				appLog.setApproved("A");
				appLog.setDateAssigned(Dates.getSqlDate(""));
				appLogList.set(0, appLog);
				incomingRequest.put("newStatus", poHeader.getStatus());
			}
			ret = appLogList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Order Approval list could not be built!");
		}
		return ret;
	}
}
