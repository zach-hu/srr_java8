package com.tsa.puridiom.receiptheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.receiptheader.history.ReceiptHistorySetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptHeaderGetHistory extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		HistoryLog history = null;
		try
		{
			Map incomingRequest = (Map)object;
			ReceiptHeader newHeader = (ReceiptHeader)incomingRequest.get("newHistoryReceiptHeader");

			if(newHeader == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("New Receipt Header was not found!");
			}

			ReceiptHistorySetupValues historyBuild = new ReceiptHistorySetupValues();
			historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
			historyBuild.setUserId((String)incomingRequest.get("userId"));
			historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));
			historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
			historyBuild.setExtraIc(incomingRequest.get("BuyerRemarks_icLine"));
			historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));

			String forwardedTo = (String)incomingRequest.get("forwardedTo");
			if (forwardedTo == null)
			{
				forwardedTo = "";
			}
			else
			{
				forwardedTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getDisplayName();
			}
			historyBuild.setForwardedTo(forwardedTo);

			String rejectedBy = (String)incomingRequest.get("rejectedBy");
			if (rejectedBy == null)
			{
				rejectedBy = "";
			}
			else
			{
				rejectedBy = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
			}
			historyBuild.setRejectedBy(rejectedBy);

			history = historyBuild.getHeaderHistoryLog(newHeader);

			if (incomingRequest.containsKey("ReceiptHeader_noteCancel") && ( DocumentStatus.CANCELLED.compareTo(history.getStatus()) == 0 || DocumentStatus.CLOSED.compareTo(history.getStatus()) == 0 )) {
				history.setDescription(history.getDescription() + " Reason:" + incomingRequest.get("ReceiptHeader_noteCancel"));
			}

			if (incomingRequest.containsKey("ApprovalLog_approverNotes") && history != null ) {
				String approvalNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
				if (!HiltonUtility.isEmpty(approvalNotes)) {
					history.setDescription(history.getDescription() + " Approval Notes: " + approvalNotes);
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return history;
	}
}
