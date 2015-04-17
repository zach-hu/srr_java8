package com.tsa.puridiom.requisition.history.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.history.HistoryText;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHistory extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

			List history = new ArrayList();
			this.historyCreate(history, requisitionHeader, incomingRequest);
			this.historyApproval(history, requisitionHeader, incomingRequest);

			//this.historyApproved(history, requisitionHeader, incomingRequest);
			this.historyAssigned(history, requisitionHeader, incomingRequest);

			ret = history;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition History encountered an Error. ", e);
		}


		return ret;
	}

	public void historyCreate(List history, RequisitionHeader requisitionHeader, Map incomingRequest)
	{
		String organizationId = (String)incomingRequest.get("organizationId");
//		Requisition Created
		HistoryText historyText = new HistoryText();
		StringBuffer text = new StringBuffer();
		text.append("Requisition Created by ");
		try {
			text.append(UserManager.getInstance().getUser(organizationId, requisitionHeader.getOwner()).getDisplayName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		text.append(" on ");
		text.append(requisitionHeader.getDateEntered());
		historyText.setText(text.toString());
		history.add(historyText);
	}

	public void historyAssigned(List history, RequisitionHeader requisitionHeader, Map incomingRequest)
	{
		String organizationId = (String)incomingRequest.get("organizationId");
//		Buyer Assingment
		String assignedBuyer = requisitionHeader.getAssignedBuyer();
		if(!Utility.isEmpty(assignedBuyer))
		{
			HistoryText historyText = new HistoryText();
			StringBuffer text = new StringBuffer();
			text.append("Requisition Assigned to ");
			try {
				text.append(UserManager.getInstance().getUser(organizationId, assignedBuyer).getDisplayName(requisitionHeader.getRequisitionType()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			text.append(" on ");
			text.append(requisitionHeader.getAssignedDate());
			historyText.setText(text.toString());
			history.add(historyText);
		}
	}

	public void historyApproval(List history, RequisitionHeader requisitionHeader, Map incomingRequest)
	{
		String organizationId = (String)incomingRequest.get("organizationId");
		//			Requisition ApprovalLog
		List approvalsList = new ArrayList();
		List approvalLogList = (List)incomingRequest.get("routingList");
		List rejectLogList = (List)incomingRequest.get("rejectLogList");
		if(rejectLogList.size() > 0)
		{
			approvalsList.addAll(rejectLogList);
		}
		if(approvalLogList != null)
		{
			approvalsList.addAll(approvalLogList);
		}

		if(approvalsList != null)
		{
			if(approvalsList.size() > 0)
			{
				for (int i = 0; i < approvalsList.size(); i++)
				{

					ApprovalLog approvalLog = (ApprovalLog)approvalsList.get(i);
					HistoryText historyText = new HistoryText();
					StringBuffer text = new StringBuffer();
					text.append("Requisition Forwarded for Approval to ");
					try
					{
						text.append(UserManager.getInstance().getUser(organizationId, approvalLog.getCallForward()).getDisplayName());
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					text.append(" on ");
					text.append(approvalLog.getDateAssigned());
					historyText.setText(text.toString());
					history.add(historyText);

					if(approvalLog.getApproved().equalsIgnoreCase("Y"))
					{
						HistoryText historyTextApproved = new HistoryText();
						text = new StringBuffer();
						text.append("Requisition Approved By ");
						try
						{
							text.append(UserManager.getInstance().getUser(organizationId, approvalLog.getCallForward()).getDisplayName());
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						text.append(" on ");
						text.append(approvalLog.getDateApproved());
						historyTextApproved.setText(text.toString());
						history.add(historyTextApproved);
					}
					if(approvalLog.getApproved().equalsIgnoreCase("Y"))
					{
						HistoryText historyTextApproved = new HistoryText();
						text = new StringBuffer();
						text.append("Requisition Approved By ");
						try
						{
							text.append(UserManager.getInstance().getUser(organizationId, approvalLog.getCallForward()).getDisplayName());
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						text.append(" on ");
						text.append(approvalLog.getDateApproved());
						historyTextApproved.setText(text.toString());
						history.add(historyTextApproved);
					}
				}
			}
		}
	}

	public void historyApproved(List history, RequisitionHeader requisitionHeader, Map incomingRequest)
	{
		if(requisitionHeader.getApproved().equalsIgnoreCase("Y"))
		{
			String organizationId = (String)incomingRequest.get("organizationId");
	//		Requisition Created
			HistoryText historyText = new HistoryText();
			StringBuffer text = new StringBuffer();
			text.append("Requisition Approved by ");
			try {
				text.append(UserManager.getInstance().getUser(organizationId, requisitionHeader.getAppBy()).getDisplayName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			text.append(" on ");
			text.append(requisitionHeader.getAppDate());
			historyText.setText(text.toString());
			history.add(historyText);
		}
	}
}
