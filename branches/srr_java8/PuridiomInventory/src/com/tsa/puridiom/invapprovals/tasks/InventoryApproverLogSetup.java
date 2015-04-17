package com.tsa.puridiom.invapprovals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class InventoryApproverLogSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String organizationId = (String) incomingRequest.get("organizationId");
			InvItem invItem = (InvItem) incomingRequest.get("invItem");
			String status = DocumentStatus.INV_APPROVING;
			List appLogList = new ArrayList();

			String invoiceApprovers = (String) incomingRequest.get("ApprovalLog_approvers");
			String invoiceApproversArray[] = new String[0];
			if (!HiltonUtility.isEmpty(invoiceApprovers))
			{
				invoiceApproversArray = invoiceApprovers.split(";");

				for (int i = 0; i < invoiceApproversArray.length; i++)
				{
					Map newIncomingRequest = new HashMap();
					String userId =  invoiceApproversArray[i];
					newIncomingRequest.put("ApprovalLog_userId", userId);
					newIncomingRequest.put("organizationId", organizationId);
					newIncomingRequest.put("InvItem_icHeaderHistory", invItem.getIcHeaderHistory().toPlainString());
					newIncomingRequest.put("dbsession", dbs);

					InventoryApproverLogAdd approver = new InventoryApproverLogAdd();
					Object appLog = approver.executeTask(newIncomingRequest);
					if (appLog != null)
					{
						ApprovalLog temp = (ApprovalLog) appLog;
						temp.getComp_id().setSequence(new BigDecimal(i + 1));
						appLogList.add(appLog);
					}
				}
			} else {
				appLogList = (List) incomingRequest.get("routingList");
			}
			
			if (appLogList != null && appLogList.size() > 0)
			{
				ApprovalLog appLog = (ApprovalLog) appLogList.get(0);
				incomingRequest.put("forwardedTo", appLog.getComp_id().getUserId());
				appLog.setApproved("A");
				appLog.setDateAssigned(Dates.getSqlDate(""));
				appLogList.set(0, appLog);
			}

			incomingRequest.put("newStatus", status);
			
			ret = appLogList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
