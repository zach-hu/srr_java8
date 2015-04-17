/*
 * Created on May 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invapprovals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author kathleen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InventoryApprovalLogApprove extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        String organizationId = (String)incomingRequest.get("organizationId") ;
        List routingList = (List) incomingRequest.get("routingList");

        String status = DocumentStatus.INV_INPROGRESS;
        String signed = (String) incomingRequest.get("signed");
        String user = (String) incomingRequest.get("userId");
        String notes = (String) incomingRequest.get("ApprovalLog_approverNotes");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String approverType = "";
        String firstApprover = "";
        boolean overrideApproval = false;

        try
        {

            for (int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(user))
                {
                	if (i == 0)
                    {
                		//need to know if it is the first approver to know whether or not to update received quantity
                        firstApprover = "Y";
                    }
                    approvalLog.setApproved("Y");
                    approvalLog.setApproverSig(signed);
                    approvalLog.setDateApproved(Dates.getCurrentDate(userTimeZone));
                    approvalLog.setApproverNotes(notes);
                    if (approvalLog.getDateAssigned() == null)
                    {
                    	approvalLog.setDateAssigned(Dates.getCurrentDate(userTimeZone));
                    }
                    approvalLog.setAlternateUserid(user);

                    incomingRequest.put("approvalLog", approvalLog);
                    ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                    appUpdate.executeTask(incomingRequest);
                    this.setStatus(appUpdate.getStatus());
                    if (this.getStatus() != Status.SUCCEEDED)
                    {
                    	throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                    }
                }
            	break;
            }

            boolean bApproved = true;
            for (int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                if (! approvalLog.getApproved().equals("Y"))
                {
                	bApproved = false;
                    break;
                }
            }

            if (bApproved)
            {
                status = DocumentStatus.INV_APPROVED;
            }
            else
            {
                status = DocumentStatus.INV_APPROVING;
            }

            incomingRequest.put("approvedBy", user);
            incomingRequest.put("newStatus", status);
            incomingRequest.put("firstApprover", firstApprover);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList;
    }

}


