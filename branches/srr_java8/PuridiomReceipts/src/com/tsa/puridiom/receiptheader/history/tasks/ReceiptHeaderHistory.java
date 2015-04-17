package com.tsa.puridiom.receiptheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ReceiptHeaderHistory extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			ReceiptHeader newHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			if(!Utility.isEmpty(newHeader.getReceiptNumber()))
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("receipt-history.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("ReceiptHeader_oldReceiptStatus", incomingRequest.get("ReceiptHeader_receiptStatus"));
				newIncomingRequest.put("newHistoryReceiptHeader", newHeader);
				newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
				newIncomingRequest.put("BuyerRemarks_icLine", incomingRequest.get("BuyerRemarks_icLine"));

				if (incomingRequest.containsKey("ReceiptHeader_noteCancel")) {
					newIncomingRequest.put("ReceiptHeader_noteCancel", incomingRequest.get("ReceiptHeader_noteCancel"));
				}
				if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
					newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
				}

				String forwardTo = (String)incomingRequest.get("forwardedTo");
				if (forwardTo == null)
				{
					forwardTo = "";
				}
				newIncomingRequest.put("forwardedTo", forwardTo);

				String rejectedBy = (String)incomingRequest.get("rejectedBy");
				if (rejectedBy == null)
				{
					rejectedBy = "";
				}
				newIncomingRequest.put("rejectedBy", rejectedBy);
				newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));

				process.executeProcess(newIncomingRequest);
				this.setStatus(process.getStatus());
			}
			else
			{
				this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
