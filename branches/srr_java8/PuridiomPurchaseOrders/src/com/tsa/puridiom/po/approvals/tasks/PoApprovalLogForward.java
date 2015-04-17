/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoApprovalLogForward extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        List routingList = (List)incomingRequest.get("routingList");

        String 	poolId = "" ;
        String 	nextUser = null ;
        String 	status = DocumentStatus.PO_AWARDED ;
        String	user = null ;

        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
        String	routTo = (String) incomingRequest.get("routTo") ;
        String  deferTo = (String) incomingRequest.get("deferTo") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String	poType = poHeader.getPoType() ;
        incomingRequest.put("poType", poType) ;
        Boolean awarded = Boolean.FALSE;
         try
        {
            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                if (approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R"))
                {
                	if (approvalLog.getApproverType().equals("P") && Utility.isEmpty(poolId)) {
                		// Pool Type
                		poolId = approvalLog.getPoolid() ;
                	}

                	user = approvalLog.getCallForward() ;

               	    // set the flag
               		approvalLog.setApproved("A") ;
               		approvalLog.setDateAssigned(Dates.getCurrentDate(userTimeZone));

                    incomingRequest.put("approvalLog", approvalLog);

                    if (Utility.isEmpty(routTo) && Utility.isEmpty(deferTo))
                    {
                    	/* Do not update if rerouting, the whole list will be readded later */
                   		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
	                    appUpdate.executeTask(incomingRequest);
	                    this.setStatus(appUpdate.getStatus());
	                    if(this.getStatus() != Status.SUCCEEDED)
	                    {
	                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
	                    }
                    }
                    status = DocumentStatus.PO_APPROVING ;
                    break ;
                }
            }

            // Get the remaining pool users
            if (! Utility.isEmpty(poolId)) {
	            for(int i = 0; i < routingList.size(); i++)
	            {
	                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
	                if ((approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R")) && approvalLog.getPoolid().equals(poolId)) {
	               	    // set the flag
	               		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();

	               		approvalLog.setApproved("A") ;
	               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

	                    incomingRequest.put("approvalLog", approvalLog);
	                    appUpdate.executeTask(incomingRequest);
	                    this.setStatus(appUpdate.getStatus());
	                    if(this.getStatus() != Status.SUCCEEDED)
	                    {
	                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
	                    }
	                    status = DocumentStatus.PO_APPROVING ;
	                    break ;
	                }
	            }
	            nextUser = poolId;
            } else {
            	nextUser = user ;
            }

            if(status.equals(DocumentStatus.PO_AWARDED ))
            {
                nextUser = "Awarded";
            	awarded = Boolean.TRUE;
            }

            if (poHeader != null && poHeader.getStatus().equals(DocumentStatus.CT_APPROVING)) {
                if (status.equals(DocumentStatus.PO_AWARDED )) {
                    status = DocumentStatus.CT_AWARDED;
                } else {
                    status = DocumentStatus.CT_APPROVING;
                }
            }

            incomingRequest.put("awarded", awarded);
            incomingRequest.put("newStatus",status) ;
            incomingRequest.put("forwardedTo",nextUser) ;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList ;
    }
}