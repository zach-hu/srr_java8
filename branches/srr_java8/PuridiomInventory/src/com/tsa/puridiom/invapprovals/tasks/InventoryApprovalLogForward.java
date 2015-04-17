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
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kathleen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InventoryApprovalLogForward extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        List routingList = (List) incomingRequest.get("routingList");
        String organizationId = (String) incomingRequest.get("organizationId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userId = (String) incomingRequest.get("userId");
        String poolId = "";
        String approverPool = "";
        String approverType = "";
        String nextUser = null;
        String status = DocumentStatus.INV_APPROVED;
        String user = null;

        //InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
        Boolean bApproved = Boolean.FALSE;
        try
        {
            for (int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
                if (approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R"))
                {
                	approverType = approvalLog.getApproverType();
                	if (approverType.equals("P") && Utility.isEmpty(poolId))
                	{
                		//Pool Type
                		poolId = approvalLog.getPoolid();
                	}

                	user = approvalLog.getCallForward();

               	    //set the flag
               		approvalLog.setApproved("A");
               		approvalLog.setDateAssigned(Dates.getCurrentDate(userTimeZone));

                    incomingRequest.put("approvalLog", approvalLog);

                    ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
	                appUpdate.executeTask(incomingRequest);
	                this.setStatus(appUpdate.getStatus());
	                if (this.getStatus() != Status.SUCCEEDED)
	                {
	                	throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
	                }
                    status = DocumentStatus.INV_APPROVING;
                    break;
                }
            }

            // Get the remaining pool users
            if (! Utility.isEmpty(poolId))
            {
	            for (int i = 0; i < routingList.size(); i++)
	            {
	                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
	                if ((approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R")) && approvalLog.getPoolid().equals(poolId))
	                {
	               	    //set the flag
	               		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();

	               		approvalLog.setApproved("A");
	               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

	                    incomingRequest.put("approvalLog", approvalLog);
	                    appUpdate.executeTask(incomingRequest);
	                    this.setStatus(appUpdate.getStatus());
	                    if (this.getStatus() != Status.SUCCEEDED)
	                    {
	                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
	                    }
	                    status = DocumentStatus.INV_APPROVING;
	                    break;
	                }
	            }
	            nextUser = poolId;
            }
            else
            {
            	nextUser = user;
            }

            if (status.equals(DocumentStatus.INV_APPROVED))
            {
            	nextUser = userId;
            	bApproved = Boolean.TRUE;
            }
            else
            {
            	status = DocumentStatus.INV_APPROVING;
            }

            incomingRequest.put("approved", bApproved);
            incomingRequest.put("newStatus", status);
            incomingRequest.put("forwardedTo", nextUser);
            incomingRequest.put("approverPool", approverPool);
            incomingRequest.put("historyStatus", status);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList;
    }
}