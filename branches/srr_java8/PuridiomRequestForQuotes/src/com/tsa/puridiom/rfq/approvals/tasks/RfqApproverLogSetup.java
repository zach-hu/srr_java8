package com.tsa.puridiom.rfq.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class RfqApproverLogSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String rfqApprovers = (String)incomingRequest.get("ApprovalLog_approvers");
			String rfqPoolids = HiltonUtility.ckNull((String)incomingRequest.get("ApprovalLog_poolids"));
			String rfqApproversArray[] = rfqApprovers.split(";");
			String rfqPoolidsArray[] = rfqPoolids.split(";");
			List appLogList = new ArrayList();
			String organizationId = (String)incomingRequest.get("organizationId");
			String RfqHeader_icRfqHeader = (String)incomingRequest.get("RfqHeader_icRfqHeader");
			incomingRequest.put("RfqLine_icRfqHeader", RfqHeader_icRfqHeader);
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			rfqHeader.setStatus(DocumentStatus.RFQ_APPROVING);

			boolean setPoolid = rfqPoolidsArray != null && rfqApproversArray.length == rfqPoolidsArray.length;

			for (int i = 0; i < rfqApproversArray.length; i++)
			{
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("ApprovalLog_userId", rfqApproversArray[i]);
				if (setPoolid) {
					newIncomingRequest.put("ApprovalLog_poolid", rfqPoolidsArray[i].trim());
				}
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("RfqHeader_icRfqHeader", RfqHeader_icRfqHeader);

				RfqApproverLogAdd approver = new RfqApproverLogAdd();
				Object appLog = approver.executeTask(newIncomingRequest);
				if(appLog != null)
				{
					ApprovalLog temp = (ApprovalLog)appLog;
					temp.getComp_id().setSequence(new BigDecimal(i + 1));
					appLogList.add(appLog);
				}

			}
			if(appLogList.size() > 0)
			{
				ApprovalLog appLog = (ApprovalLog)appLogList.get(0);
				incomingRequest.put("forwardedTo", appLog.getComp_id().getUserId());
				appLog.setApproved("A");
				appLog.setDateAssigned(Dates.getSqlDate(""));
				appLogList.set(0, appLog);
				incomingRequest.put("newStatus", rfqHeader.getStatus());

				String poolIdTemp = appLog.getPoolid();
				if (!HiltonUtility.isEmpty(poolIdTemp))
				{
					AppPool appPool = (AppPool)AppPoolManager.getInstance().getAppPool(organizationId, poolIdTemp);
					if (appPool != null && !appPool.getPoolflag1().equalsIgnoreCase("Y"))
					{
						for (int i = 1; i < appLogList.size(); i++)
						{
							ApprovalLog appLogTemp = (ApprovalLog)appLogList.get(i);
							if (appLogTemp.getPoolid().equalsIgnoreCase(poolIdTemp)) {
								appLogTemp.setApproved("A");
								appLogTemp.setDateAssigned(Dates.getSqlDate(""));
								appLogList.set(i, appLogTemp);
							}
						}
					}
				}
			}
			ret = appLogList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Request for Quotes Approval list could not be built!");
		}
		return ret;
	}
}
