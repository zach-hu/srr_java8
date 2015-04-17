/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.rfq.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RfqHeader;
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
public class RfqApprovalLogForward extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        String	organizationId = (String) incomingRequest.get("organizationId");
        List routingList = (List)incomingRequest.get("routingList");
        boolean fromRfqApprovalPage = HiltonUtility.ckNull((String)incomingRequest.get("fromPage")).equalsIgnoreCase("rfq_approval.jsp");

        String 	poolId = "" ;
        String 	nextUser = null ;
        String 	status = DocumentStatus.RFQ_PURCHASING ;
        if (fromRfqApprovalPage) {
        	status = DocumentStatus.RFQ_OPENSOLICITATION ;
        }
        String	user = null ;

        RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;
        String	routTo = (String) incomingRequest.get("routTo") ;
        String  deferTo = (String) incomingRequest.get("deferTo");
        String	rfqType = rfqHeader.getRfqType();
        incomingRequest.put("rfqType", rfqType) ;
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
               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

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
                    status = DocumentStatus.RFQ_APPROVING ;
                    break ;
                }
            }

            // Get the remaining pool users
            if (! Utility.isEmpty(poolId)) {
            	AppPool appPool = (AppPool)AppPoolManager.getInstance().getAppPool(organizationId, poolId);
				if (appPool != null && !appPool.getPoolflag1().equalsIgnoreCase("Y"))
				{
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
		                    status = DocumentStatus.RFQ_APPROVING ;
		                    //break ;
		                }
		            }
				}
	            nextUser = poolId;
            } else {
            	nextUser = user ;
            }

            if(status.equals(DocumentStatus.RFQ_PURCHASING ) || status.equals(DocumentStatus.RFQ_OPENSOLICITATION ))
            {
            	nextUser = "Awarded";
            	awarded = Boolean.TRUE;
            }
            incomingRequest.put("awarded", awarded);
            incomingRequest.put("newStatus",status) ;
            incomingRequest.put("forwardedTo",nextUser) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList ;
    }
}