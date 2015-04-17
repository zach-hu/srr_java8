package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.HistoryReject;
import com.tsa.puridiom.entity.HistoryRejectPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ApproverHistoryReject extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			List historyRejectList = (List) incomingRequest.get("historyRejectList");

			ApprovalLog approvalLog = (ApprovalLog)incomingRequest.get("approvalLog");
			HistoryReject historyReject = new HistoryReject();

			String oid = (String)incomingRequest.get("organizationId");
			RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			List routingList = (List)incomingRequest.get("routingList");
			String user = (String)incomingRequest.get("userId");
			BigDecimal maxHistoryReject = (BigDecimal) incomingRequest.get("maxHistoryReject");

			HistoryRejectPK pk = new HistoryRejectPK();
            pk.setIcHeader(header.getIcReqHeader());

            if(!Utility.isObjectEmpty(maxHistoryReject))
			{
				pk.setIcLine( maxHistoryReject.add(new BigDecimal(1)) );
			}
			else
			{
				pk.setIcLine(new BigDecimal(0));
			}

            pk.setUserId(user);
            pk.setSequence(new BigDecimal(routingList.size() + 1)) ;

			UserProfile appRuleUser = UserManager.getInstance().getUser(oid, user);

            historyReject.setComp_id(pk);

            BigDecimal approverAmount = appRuleUser.getApprovalAmount() ;

            historyReject.setRuleSource(approvalLog.getRuleSource());
            historyReject.setAmount(approvalLog.getAmount());
            historyReject.setApproverAmount(approvalLog.getApproverAmount());
            historyReject.setApproverType(approvalLog.getApproverType());
            historyReject.setPoolid(approvalLog.getPoolid());
            historyReject.setApproverName(approvalLog.getApproverName());
            historyReject.setApproved(approvalLog.getApproved());
            historyReject.setAuthorized(approvalLog.getAuthorized());
            historyReject.setRuleAction( approvalLog.getRuleAction() );
            historyReject.setApproverSig(approvalLog.getApproverSig());
            historyReject.setRuleType(approvalLog.getRuleType());
            historyReject.setExcludeLess(approvalLog.getExcludeLess());
            historyReject.setApproverNotes(approvalLog.getApproverNotes());
            historyReject.setUdfValues(approvalLog.getUdfValues());

            ret = historyReject;
            historyRejectList.add(historyReject);
            incomingRequest.put("historyRejectList", historyRejectList);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("HistoryReject could not be Created!", e);
		}
		return ret;
	}
}
