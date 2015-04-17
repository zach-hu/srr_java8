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
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoApprovalLogApprove extends Task
{

    List	routingList = null ;

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        routingList = (List)incomingRequest.get("routingList");

        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        String status = DocumentStatus.PO_INPROGRESS ;
        String signed = (String)incomingRequest.get("signed") ;
        String user = (String)incomingRequest.get("userId") ;
        String notes = (String)incomingRequest.get("ApprovalLog_approverNotes") ;
        String routTo = (String) incomingRequest.get("routTo") ;
        String deferTo = (String) incomingRequest.get("deferTo") ;
        String returnTo = (String) incomingRequest.get("returnTo");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String override = (String) incomingRequest.get("override");
        String approveForAll = (String) incomingRequest.get("approveForAll");
        String organizationId = (String)incomingRequest.get("organizationId") ;
        String poolId = "" ;
        
        boolean isDeferTo = false;
        boolean overrideApproval = false;
        boolean overrideApprovalForAll = false;

        if (poHeader != null) {
            status = poHeader.getStatus();
        }

        try
        {	
        	//check approval override settings
			if (UserManager.getInstance().getUser(organizationId, user).isAnOverrider()) {
			    if (override == null) {
			        override = "N";
				} else if (override.equals("Y")) {
				    overrideApproval = true;
				}
				if (approveForAll == null) {
				    approveForAll = "N";
				}
				if (override.equals("Y") && approveForAll.equals("Y")) {
				        overrideApprovalForAll = true;
				}
			} else {
			    overrideApproval = false;
			}
        	
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
                        PoApprovalAddApprover appAdd = new PoApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        if(1 ==1)
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
                        PoApprovalAddApprover appAdd = new PoApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        break;
                    }
                }
            }
            
            if (Utility.isEmpty(returnTo)) {
                returnTo = "N" ;
            }

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(user) || (overrideApprovalForAll && approvalLog.getApproved().equals("N"))) {
                    if (approvalLog.getApproverType().equals("P")) {
                        // Pool Type
                        poolId = approvalLog.getPoolid() ;
                    }

                    if (user.equals(approvalLog.getCallForward()) || overrideApproval) {
                        // approve current po
                    	
                    	if(isDeferTo)
                    	{
                    		approvalLog.setApproved("D") ;
                    		approvalLog.setDeferId(deferTo);
                    	}else{ 
	                    	if(returnTo.equals("Y")) {
	                            approvalLog.setApproved("N") ;
	                        }else{
	                            approvalLog.setApproved("Y") ;
	                            approvalLog.setApproverSig(signed) ;
	                            approvalLog.setDateApproved(Dates.getCurrentDate(userTimeZone));
	                        }
	                    	approvalLog.setAlternateUserid(user) ;
                    	}
                    	if (approvalLog.getDateAssigned() == null) {
                            approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                        }
                        approvalLog.setApproverNotes(notes) ;
                        incomingRequest.put("approvalLog", approvalLog);
                        if (Utility.isEmpty(routTo) && Utility.isEmpty(deferTo)) {
                            /* Do not update if rerouting or deferring, the whole list will be done later */
                            ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                            appUpdate.executeTask(incomingRequest);
                            this.setStatus(appUpdate.getStatus());
                            if(this.getStatus() != Status.SUCCEEDED)
                            {
                                throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                            }
                        }
                    } else if (poolId.equals(approvalLog.getPoolid())) {
                        // Remove other pool records
                    	if(isDeferTo)
                    	{
                    		approvalLog.setApproved("D") ;
                    		approvalLog.setDeferId(deferTo);
                    		incomingRequest.put("approvalLog",approvalLog) ;
                    	}else{
	                        ApprovalLogDeleteById appDelete = new ApprovalLogDeleteById();
	                        approvalLog.setApproved("Y") ;
	                        incomingRequest.put("approvalLog",approvalLog) ;
	                        appDelete.executeTask(incomingRequest) ;
	                        if(this.getStatus() != Status.SUCCEEDED)
	                        {
	                            throw new TsaException("An error ocurred deleting pool record for user: " + approvalLog.getComp_id().getUserId());
	                        }
                    	}
                    }
                }
            }

            if (isDeferTo){
            	if (!status.equals(DocumentStatus.CT_APPROVING)){
            		status = DocumentStatus.PO_APPROVING ;
            	}
            } else
            {
            	 boolean approved = true ;
                 for(int i = 0; i < routingList.size(); i++)
                 {
                     ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                     if (! approvalLog.getApproved().equals("Y")) {
                         approved = false ;
                         break;
                     }
                 }
	            if (status.equals(DocumentStatus.CT_APPROVING)) {
	                if (approved) {
	                    status = DocumentStatus.CT_AWARDED;
	                } else {
	                    status = DocumentStatus.CT_APPROVING;
	                }
	            }
	            else if (approved) {
	                status = DocumentStatus.PO_AWARDED ;
	            }
	            else {
	                status = DocumentStatus.PO_APPROVING ;
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


