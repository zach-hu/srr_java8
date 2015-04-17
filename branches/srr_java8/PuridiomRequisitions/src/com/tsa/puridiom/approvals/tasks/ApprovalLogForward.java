/*
 * Created on Dec 7, 2004
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.approvals.QueueAddReqFYIApprovalEmail;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class ApprovalLogForward extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        List routingList = (List)incomingRequest.get("routingList");

        RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
        String	routTo = (String) incomingRequest.get("routTo") ;
        String  deferTo = (String) incomingRequest.get("deferTo") ;
        String	reqType = rqh.getRequisitionType() ;
        String	organizationId = (String) incomingRequest.get("organizationId") ;
        String reuseApprovers =  PropertiesManager.getInstance(organizationId).getProperty("APPROVERS", "REUSEAPPROVERS", "N");
        String 	status = DocumentStatus.REQ_APPROVED ;

        List	nextUsers = new ArrayList() ;
        List	users = new ArrayList() ;
        BigDecimal sequence = new BigDecimal(-1);
        String 	poolId = "" ;
        String approverType = "";
        String nextUser = "";
        String	user = null ;
        Map currentFyiUsers = new HashMap();

        incomingRequest.put("reqType",reqType) ;
        if (reqType.equals("M"))
        	status = DocumentStatus.REQ_PLANNING_APPROVED ;

         try
        {

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                if(approvalLog.getUdfValues().equalsIgnoreCase("[Budget=OverBudget]"))
                {
                	incomingRequest.put("overBudget", "[Budget=OverBudget]");
                }
                
                
                
                if ((approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R")) && (nextUsers.isEmpty() || nextUsers.contains(approvalLog.getComp_id().getUserId()) || (sequence.compareTo(approvalLog.getComp_id().getSequence()) == 0))) {

                	boolean isFyiApproverOrKeepApprover = false;
                	if(reuseApprovers.equalsIgnoreCase("Y")&&approvalLog.isAnKeepApprover()||approvalLog.isAnFyiApprover()){
                		isFyiApproverOrKeepApprover=true;
                	}

                	if (!isFyiApproverOrKeepApprover) {
                		users.add(approvalLog.getCallForward());
	                	if (approvalLog.getApproverType().equals("P") && Utility.isEmpty(poolId)) {
	                		// Pool Type
	                		poolId = approvalLog.getPoolid() ;
	                	}

	                	user = approvalLog.getCallForward() ;
	                	nextUsers.add(approvalLog.getComp_id().getUserId());
	                	sequence = approvalLog.getComp_id().getSequence();
                        approverType = approvalLog.getApproverType();

	               	    // set the flag

	               		approvalLog.setApproved("A") ;
	               		approvalLog.setKeepApprovedFlag("");
	               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

	                    incomingRequest.put("approvalLog", approvalLog);

	                    if (Utility.isEmpty(routTo) && Utility.isEmpty(deferTo)) {
	                    	/* Do not update if rerouting or deferring, the whole list will be readded later */
	                   		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
		                    appUpdate.executeTask(incomingRequest);
		                    this.setStatus(appUpdate.getStatus());
		                    if(this.getStatus() != Status.SUCCEEDED)
		                    {
		                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
		                    }
	                    }
	                    if (reqType.equals("M")) {
	                    	status = DocumentStatus.REQ_PLANNING_APPROVING ;
	                    } else {
	                    	status = DocumentStatus.REQ_APPROVING ;
	                    }
	                    //break ;
                    } else {

                    	if(reuseApprovers.equalsIgnoreCase("Y")&&approvalLog.isAnKeepApprover()){
                    		approvalLog.setApproved("Y") ;
                    		approvalLog.setKeepApprovedFlag("");
                    		incomingRequest.put("approvalLog", approvalLog);

                    		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
		                    appUpdate.executeTask(incomingRequest);
		                    this.setStatus(appUpdate.getStatus());
		                    if(this.getStatus() != Status.SUCCEEDED)
		                    {
		                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
		                    }
                    	}else{
                    		user = approvalLog.getCallForward() ;
    	               	    // set the flag
    	               		approvalLog.setApproved("Y") ;
    	               		approvalLog.setKeepApprovedFlag("");
    	               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

    	               		if (!currentFyiUsers.containsKey(user)) {
    		                    incomingRequest.put("approvalLog", approvalLog);

    		                    // Send FYI notification to approver
    		                    QueueAddReqFYIApprovalEmail fyiNotification = new QueueAddReqFYIApprovalEmail();
    		                    fyiNotification.executeTask(incomingRequest);
    		                    this.setStatus(fyiNotification.getStatus());
    		                    if(this.getStatus() != Status.SUCCEEDED)
    		                    {
    		                        throw new TsaException("An error ocurred writing a send queue record for FYI approver: " + approvalLog.getComp_id().getUserId());
    		                    }
    		                    if (Utility.isEmpty(routTo) && Utility.isEmpty(deferTo)) {
    		                    	/* Do not update if rerouting or deferring, the whole list will be readded later */
    		                   		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
    			                    appUpdate.executeTask(incomingRequest);
    			                    this.setStatus(appUpdate.getStatus());
    			                    if(this.getStatus() != Status.SUCCEEDED)
    			                    {
    			                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
    			                    }
    		                    }
    		                    // Add this user to currentFyiUsers so multiple FYI notifications are not sent
    		                    currentFyiUsers.put(user, "Y");
    	               		}
                    	}
                    }
                }else if(reuseApprovers.equalsIgnoreCase("Y")&&approvalLog.isAnKeepApprover()&&(approvalLog.getApproved().equals("N"))){
                	approvalLog.setApproved("Y") ;
                	approvalLog.setKeepApprovedFlag("");
            		incomingRequest.put("approvalLog", approvalLog);

            		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                    appUpdate.executeTask(incomingRequest);
                    this.setStatus(appUpdate.getStatus());
                    if(this.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                    }
                }
            }

            // Reset nextUser
            nextUsers = new ArrayList();

            // Get the remaining pool users
            if (! Utility.isEmpty(poolId)) {
	            for(int i = 0; i < routingList.size(); i++)
	            {
	                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
	                if ((approvalLog.getApproved().equals("N") || approvalLog.getApproved().equals("R")) && approvalLog.getPoolid().equals(poolId)) {
	               	    // set the flag
	               		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();

	               		approvalLog.setApproved("A") ;
	               		approvalLog.setKeepApprovedFlag("");
	               		approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));

	                    incomingRequest.put("approvalLog", approvalLog);
	                    appUpdate.executeTask(incomingRequest);
	                    this.setStatus(appUpdate.getStatus());
	                    if(this.getStatus() != Status.SUCCEEDED)
	                    {
	                        throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
	                    }
	                    if (reqType.equals("M")) {
	                    	status = DocumentStatus.REQ_PLANNING_APPROVING ;
	                    } else {
	                    	status = DocumentStatus.REQ_APPROVING ;
	                    }
	                    break ;
	                }
	            }
	            nextUsers.add(poolId);
            } else {
            	nextUsers = users ;
            }

            /* Iterate the list "nextUsers" in order to build a new String separated by ";" */
            for (Iterator iterator = nextUsers.iterator(); iterator.hasNext();)
			{
            	nextUser += HiltonUtility.ckNull((String) iterator.next()) + ";";
			}

            if(status.equals(DocumentStatus.REQ_APPROVED ) || status.equals(DocumentStatus.REQ_PLANNING_APPROVED)) {
            	String	invTypes = "SZTI" ;
            	if (invTypes.indexOf(reqType) ==  -1 ) {
            		nextUser = "Purchasing" ;
            	} else {
            		nextUser = "Supply Room" ;
            	}
            }
            incomingRequest.put("newStatus",status) ;
            incomingRequest.put("forwardedTo", nextUser) ;
            incomingRequest.put("approverType", approverType);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList ;
    }
}
