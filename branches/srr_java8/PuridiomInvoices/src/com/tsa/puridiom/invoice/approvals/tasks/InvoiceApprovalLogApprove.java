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
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

/**
 * @author kathleen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoiceApprovalLogApprove extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        String organizationId = (String)incomingRequest.get("organizationId") ;
        List routingList = (List) incomingRequest.get("routingList");

        String status = DocumentStatus.IVC_INPROGRESS;
        String signed = (String) incomingRequest.get("signed");
        String user = (String) incomingRequest.get("userId");
        String notes = (String) incomingRequest.get("ApprovalLog_approverNotes");
        String routeTo = (String) incomingRequest.get("routeTo");
        String deferTo = (String) incomingRequest.get("deferTo") ;
        String returnTo = (String) incomingRequest.get("returnTo");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String approverType = "";
        String firstApprover = "";
        boolean isDeferTo = false;
        boolean overrideApproval = false;

        try
        {
//        	check approval override settings
            if (UserManager.getInstance().getUser(organizationId, user).isAnOverrider())
            {
            	overrideApproval = true;
            }

            //check if approval list has been rerouted, get rerouted user id
            if (! Utility.isEmpty(routeTo))
            {
                for (int i = 0; i < routingList.size(); i++)
                {
                    ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
                    if (approvalLog.getApproved().equals("A"))
                    {
                        if (Utility.isEmpty(returnTo))
                        {
                            returnTo = "N" ;
                        }
                        incomingRequest.put("routeTo", routeTo);
                        incomingRequest.put("returnTo", returnTo);
                        if (returnTo.equals("Y"))
                        {
                            incomingRequest.put("insertBefore", Integer.toString(i));
                        }
                        else if (i < routingList.size())
                        {
                                incomingRequest.put("insertBefore", Integer.toString(i + 1));
                        }
                        incomingRequest.put("addUser", routeTo);
                        InvoiceApprovalAddApprover appAdd = new InvoiceApprovalAddApprover();
                        routingList = (List) appAdd.executeTask(incomingRequest);
                        if (1 ==1)
                        {
                            //debug
                        }
                        break;
                    }
                }
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
                        InvoiceApprovalAddApprover appAdd = new InvoiceApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        break;
                    }
                }
            }
            
            if (Utility.isEmpty(returnTo))
            {
                returnTo = "N";
            }

            for (int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog) routingList.get(i);
                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(user))
                {
                	approverType = approvalLog.getApproverType();
                    if (approverType.equals("P"))
                    {
                        // Pool Type
                        //poolId = approvalLog.getPoolid();
                    }

                    if (user.equals(approvalLog.getCallForward()) || approvalLog.getApproverType().equals("P") || overrideApproval)
                    {
                        // approve current invoice
                    	if(isDeferTo)
                    	{
                    		approvalLog.setApproved("D") ;
                    		approvalLog.setDeferId(deferTo);
                    	}else{
	                        if (returnTo.equals("Y"))
	                        {
	                            approvalLog.setApproved("N");
	                        }
	                        else
	                        {
	                        	if (i == 0)
	                        	{
	                        		//need to know if it is the first approver to know whether or not to update received quantity
	                        		firstApprover = "Y";
	                        	}
	                            approvalLog.setApproved("Y");
	                            approvalLog.setApproverSig(signed);
	                            approvalLog.setDateApproved(Dates.getCurrentDate(userTimeZone));
	                        }
	                        approvalLog.setAlternateUserid(user);
                    	}
                        approvalLog.setApproverNotes(notes);
                        if (approvalLog.getDateAssigned() == null)
                        {
                            approvalLog.setDateAssigned(Dates.getCurrentDate(userTimeZone));
                        }
                        

                        incomingRequest.put("approvalLog", approvalLog);
                        if (Utility.isEmpty(routeTo) && Utility.isEmpty(deferTo))
                        {
                            /* Do not update if rerouting, the whole list will be done later */
                            ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                            appUpdate.executeTask(incomingRequest);
                            this.setStatus(appUpdate.getStatus());
                            if (this.getStatus() != Status.SUCCEEDED)
                            {
                                throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                            }
                        }
                    }
                    /*
                    else if (poolId.equals(approvalLog.getPoolid()))
                    {
                        // Remove other pool records
                        if(isDeferTo)
                    	{
                    		approvalLog.setApproved("D") ;
                    		approvalLog.setDeferId(deferTo);
                    		incomingRequest.put("approvalLog",approvalLog) ;
                    	}else{
                        
	                        ApprovalLogDeleteById appDelete = new ApprovalLogDeleteById();
	                        incomingRequest.put("approvalLog", approvalLog);
	                        incomingRequest.put("ApprovalLog_sequence", approvalLog.getComp_id().getSequence().toString());
	                        incomingRequest.put("ApprovalLog_userId", approvalLog.getComp_id().getUserId());
	                        approvalLog.setApproved("Y");
	                        appDelete.executeTask(incomingRequest);
	                        if (appDelete.getStatus() != Status.SUCCEEDED)
	                        {
	                            throw new TsaException("An error ocurred deleting pool record for user: " + approvalLog.getComp_id().getUserId());
	                        }
	                       }
                    }
                    */
                    break;
                }
            }
            
            for (int idx = 0; idx < routingList.size(); idx++) {
            	ApprovalLog approvalLog = (ApprovalLog) routingList.get(idx);
                if (approvalLog.getApproved().equals("A")){
                	if(isDeferTo){
                		approvalLog.setApproved("D") ;
                		approvalLog.setDeferId(deferTo);
                	} else {
                		if (returnTo.equals("Y"))
                        {
                            approvalLog.setApproved("N");
                        }
                        else
                        {
                            approvalLog.setApproved("Y");
                            approvalLog.setApproverSig(signed);
                            approvalLog.setDateApproved(Dates.getCurrentDate(userTimeZone));
                        }
                        approvalLog.setAlternateUserid(user);
                	}
                }
			}

            if (isDeferTo){
            	status = DocumentStatus.IVC_APPROVING;
            } else{
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
	                status = DocumentStatus.IVC_APPROVED;
	            }
	            else
	            {
	                status = DocumentStatus.IVC_APPROVING;
	            }
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


