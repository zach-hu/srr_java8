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
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RfqApprovalLogApprove extends Task
{

    List	routingList = null ;

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        String	organizationId = (String) incomingRequest.get("organizationId");
        routingList = (List)incomingRequest.get("routingList");

        String status = DocumentStatus.RFQ_INPROGRESS ;
        String signed = (String)incomingRequest.get("signed") ;
        String user = (String)incomingRequest.get("userId") ;
        String notes = (String)incomingRequest.get("ApprovalLog_approverNotes") ;
        String routTo = (String) incomingRequest.get("routTo") ;
        String deferTo = (String) incomingRequest.get("deferTo") ;
        String returnTo = (String) incomingRequest.get("returnTo");
        boolean isDeferTo = false;
        boolean fromRfqApprovalPage = HiltonUtility.ckNull((String)incomingRequest.get("fromPage")).equalsIgnoreCase("rfq_approval.jsp");

        String poolId = "" ;

        try
        {
            //check if approval list has been rerouted, get rerouted user id
            if (! Utility.isEmpty(routTo)) {
                for(int i = 0; i < routingList.size(); i++)
                {
                    ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                    if (approvalLog.getApproved().equals("A")) {
                        if (Utility.isEmpty(returnTo)) {
                            returnTo = "N" ;
                        }
                        incomingRequest.put("routTo",routTo) ;
                        incomingRequest.put("returnTo",returnTo) ;
                        if (returnTo.equals("Y")) {
                            incomingRequest.put("insertBefore",Integer.toString(i)) ;
                        } else if (i < routingList.size()) {
                                incomingRequest.put("insertBefore",Integer.toString(i + 1)) ;
                        }
                        incomingRequest.put("addUser",routTo) ;
                        RfqApprovalAddApprover appAdd = new RfqApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        if(1 ==1)
                        {
                            //debug
                        }
                        break;
                    }
                }
            }
            if (Utility.isEmpty(returnTo)) {
                returnTo = "N" ;
            }

            if (! Utility.isEmpty(deferTo)) {
            	isDeferTo = true;
                for(int i = 0; i < routingList.size(); i++)
                {
                    ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                    if (approvalLog.getApproved().equals("A")) {
                        incomingRequest.put("deferTo",deferTo) ;
                        incomingRequest.put("insertBefore",Integer.toString(i + 1)) ;
                        incomingRequest.put("addUser",deferTo) ;
                        RfqApprovalAddApprover appAdd = new RfqApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        break;
                    }
                }
            }
            
            AppPool appPool = null;

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(user)) {
                    if (approvalLog.getApproverType().equals("P")) {
                        // Pool Type
                        poolId = approvalLog.getPoolid() ;
                    }

                    if (!HiltonUtility.isEmpty(poolId))
                    {
                    	appPool = (AppPool)AppPoolManager.getInstance().getAppPool(organizationId, poolId);
                    }

                    if (user.equals(approvalLog.getCallForward())) {
                        // approve current po
                    	if(isDeferTo)
                    	{
                    		approvalLog.setApproved("D") ;
                    		approvalLog.setDeferId(deferTo);
                    	}else{
	                    	if (returnTo.equals("Y")) {
	                            approvalLog.setApproved("N") ;
	                        } else {
	                            approvalLog.setApproved("Y") ;
	                            approvalLog.setApproverSig(signed) ;
	                            approvalLog.setDateApproved(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
	                        }
	                    	approvalLog.setAlternateUserid(user) ;
                    	}
                        approvalLog.setApproverNotes(notes) ;
                        if (approvalLog.getDateAssigned() == null) {
                            approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                        }

                        incomingRequest.put("approvalLog", approvalLog);
                        if (Utility.isEmpty(routTo) && Utility.isEmpty(deferTo)) {
                            /* Do not update if rerouting, the whole list will be done later */
                            ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                            appUpdate.executeTask(incomingRequest);
                            this.setStatus(appUpdate.getStatus());
                            if(this.getStatus() != Status.SUCCEEDED)
                            {
                                throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                            }
                        }
                    } else if (!HiltonUtility.isEmpty(poolId) && poolId.equals(approvalLog.getPoolid())) {
                    	if (appPool != null && !appPool.getPoolflag1().equalsIgnoreCase("Y")) {
	                        // Remove other pool records
                    		if(isDeferTo)
                        	{
                        		approvalLog.setApproved("D") ;
                        		approvalLog.setDeferId(deferTo);
                        		incomingRequest.put("approvalLog",approvalLog) ;
                        	}else{
		                        ApprovalLogDeleteById appDelete = new ApprovalLogDeleteById();
		                        incomingRequest.put("approvalLog",approvalLog) ;
		                        approvalLog.setApproved("Y") ;
		                        appDelete.executeTask(incomingRequest) ;
		                        this.setStatus(appDelete.getStatus());
		                        if(this.getStatus() != Status.SUCCEEDED)
		                        {
		                            throw new TsaException("An error ocurred deleting pool record for user: " + approvalLog.getComp_id().getUserId());
		                        }
                        	}
                    	}
                    } else {
        				appPool = (AppPool)AppPoolManager.getInstance().getAppPool(organizationId, poolId);
                    }
                }
            }

            if (isDeferTo){
            	status = DocumentStatus.RFQ_APPROVING ;
            } else{
	            boolean approved = true ;
	            for(int i = 0; i < routingList.size(); i++)
	            {
	                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
	                if (! approvalLog.getApproved().equals("Y")) {
	                    approved = false ;
	                    break;
	                }
	            }
	
	            if (approved)
	            {
	                status = DocumentStatus.RFQ_PURCHASING ;
	                if (fromRfqApprovalPage) {
	                	status = DocumentStatus.RFQ_OPENSOLICITATION ;
	                }
	            }
	            else
	            {
	                status = DocumentStatus.RFQ_APPROVING ;
	            }
            }
            incomingRequest.put("approvedBy",user) ;
            incomingRequest.put("newStatus",status) ;

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList ;
    }

}


