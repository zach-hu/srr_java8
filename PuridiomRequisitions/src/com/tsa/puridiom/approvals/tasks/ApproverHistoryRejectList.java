package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.HistoryReject;
import com.tsa.puridiom.entity.HistoryRejectPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ApproverHistoryRejectList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			List routingList = (List) incomingRequest.get("routingList");
			BigDecimal maxHistoryReject = (BigDecimal) incomingRequest.get("maxHistoryReject");
			List historyRejectList = new ArrayList();

			for( int i =0; i < routingList.size(); i++)
			{
				HistoryReject historyReject = new HistoryReject();
				HistoryRejectPK historyRejectPK = new HistoryRejectPK();
				ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
				ApprovalLogPK approvalLogPK = (ApprovalLogPK) approvalLog.getComp_id();

				historyRejectPK.setIcHeader(approvalLogPK.getIcHeader());
				if(!Utility.isObjectEmpty(maxHistoryReject))
				{
					historyRejectPK.setIcLine( maxHistoryReject.add(new BigDecimal(1)) );
				}
				else
				{
					historyRejectPK.setIcLine( approvalLog.getComp_id().getIcLine() );
				}

				historyRejectPK.setSequence(approvalLogPK.getSequence());
				historyRejectPK.setUserId(approvalLogPK.getUserId());

				historyReject.setComp_id(historyRejectPK);

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

	            historyReject.setAlternateUserid(approvalLog.getAlternateUserid());
	            historyReject.setDateAssigned(approvalLog.getDateAssigned());
	            historyReject.setDateApproved(approvalLog.getDateApproved());
	            historyReject.setCallForward(approvalLog.getCallForward());
	            historyReject.setRequiredApprover(approvalLog.getRequiredApprover());
	            historyReject.setFyiApprover(approvalLog.getFyiApprover());
	            historyReject.setPooldesc(approvalLog.getPooldesc());
	            historyReject.setBackupApprover(approvalLog.getBackupApprover());
	            historyReject.setRecommendation(approvalLog.getRecommendation());
	            historyReject.setAdvisor(approvalLog.getAdvisor());

	            historyRejectList.add(historyReject);
			}
			ret = historyRejectList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("HistoryRejectList could not be Created!", e);
		}
		return ret;
	}
}
