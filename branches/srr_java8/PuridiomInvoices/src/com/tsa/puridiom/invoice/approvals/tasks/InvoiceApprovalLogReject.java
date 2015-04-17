/*
 * Created on May 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author kathleen
 */
public class InvoiceApprovalLogReject extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        String status = DocumentStatus.IVC_INPROGRESS;

        try
        {
            Map incomingRequest = (Map) object;
            List routingList = (List) incomingRequest.get("routingList");

            String organizationId = (String) incomingRequest.get("organizationId");
            
            String override = (String) incomingRequest.get("override");
            String user = (String) incomingRequest.get("userId");
            String notes = (String) incomingRequest.get("ApprovalLog_approverNotes");
            
            UserProfile userProfile = UserManager.getInstance().getUser(organizationId, user);

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
                if (approvalLog.getApproved().equals("A"))
                {
                	if (user.equals(approvalLog.getCallForward()) || (override.equalsIgnoreCase("Y") && userProfile.isAnOverrider()))
                	{
                	    //reject current invoice
                		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();

                		approvalLog.setApproved("R");
                		approvalLog.setApproverNotes(notes);

                        incomingRequest.put("approvalLog", approvalLog);
                        appUpdate.executeTask(incomingRequest);
                        this.setStatus(appUpdate.getStatus());
                        if (this.getStatus() != Status.SUCCEEDED)
                        {
                            throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                        }
                       	status = DocumentStatus.IVC_REJECTED;
                	}
                }
            }
            incomingRequest.put("rejectedBy", user);
            incomingRequest.put("newStatus", status);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
