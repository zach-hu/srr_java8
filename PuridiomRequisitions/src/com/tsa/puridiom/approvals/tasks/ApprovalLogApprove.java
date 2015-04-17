/*
/*
 * Created on Dec 7, 2004
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class ApprovalLogApprove extends Task
{

    List	routingList = null ;

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        routingList = (List)incomingRequest.get("routingList");
        String oid = (String)incomingRequest.get("organizationId");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;

        String status = DocumentStatus.REQ_INPROGRESS ;
        String signed = (String)incomingRequest.get("signed") ;
        String user = (String)incomingRequest.get("userId") ;
        String notes = (String)incomingRequest.get("ApprovalLog_approverNotes") ;
        String recommendation = (String)incomingRequest.get("ApprovalLog_recommendation") ;
        String routTo = (String) incomingRequest.get("routTo") ;
        String deferTo = (String) incomingRequest.get("deferTo") ;
        String returnTo = (String) incomingRequest.get("returnTo");
        String override = (String) incomingRequest.get("override");
        String approveForAll = (String) incomingRequest.get("approveForAll");
        String organizationId = (String)incomingRequest.get("organizationId") ;
        //String rerouteNextApprover = propertiesManager.getProperty("APPROVALS","REROUTENEXTAPPROVER","N");
        boolean approvedByBackupApprover = false;
        boolean reroutedByBackupApprover = false;
        boolean overrideApproval = false;
        boolean overrideApprovalForAll = false;
        boolean reroutedByOverrider = false;
        boolean deferredByBackupApprover = false;
        boolean deferredByOverrider = false;
        boolean isDeferTo = false;

        RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
        String reqType = "P" ;
        if (rqh == null) {
        	reqType = "P" ;
        } else {
        	reqType = rqh.getRequisitionType() ;
        }

        if (reqType.equals("M")) status = DocumentStatus.REQ_PLANNING ;


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
                        ApprovalAddApprover appAdd = new ApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        if(1 ==1)
                        {
                            //debug
                        }
                        break;
                    }
                }
                /*if(rerouteNextApprover.equalsIgnoreCase("Y"))
                {
                	for(Iterator it = routingList.iterator(); it.hasNext();)
                    {
                        ApprovalLog approvalLog = (ApprovalLog) it.next();
                        ApprovalLogPK pk = approvalLog.getComp_id();
                        BigDecimal newSequence = new BigDecimal(1);
                        if(routingList.size() > 2)
                        {
                        	if(!approvalLog.getApproved().equalsIgnoreCase("A"))
                        	{
                        		newSequence = newSequence.add(pk.getSequence());
                        		pk.setSequence(newSequence);
                        		if(!it.hasNext())
                            	{
                            		pk.setSequence(new BigDecimal(2));
                            	}
                            	approvalLog.setComp_id(pk);
                        	}
                        }
                    }
                	ApprovalLogSequenceComparator sequenceComparator = new ApprovalLogSequenceComparator();
                	sequenceComparator.setOrder("A");
            		if(routingList != null)
                    {
                        Collections.sort(routingList, sequenceComparator);
                    }
                }*/
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
                        ApprovalAddApprover appAdd = new ApprovalAddApprover() ;
                        routingList = (List)appAdd.executeTask(incomingRequest);
                        break;
                    }
                }
            }

            if (Utility.isEmpty(returnTo)) {
                returnTo = "N" ;
            }

            Map poolsToApprove = new HashMap();

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);

                if (approvalLog.getApproverType().equals("P")) {
                    // Pool Type
                    String	poolId = approvalLog.getPoolid();
	            	incomingRequest.put("AppPooluser_poolid",poolId) ;

	            	AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
	                List poolUsersList = (List)task.executeTask(incomingRequest);

	                for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++) {
	                    AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
	                    String	userId = poolUser.getComp_id().getUserId();
	                    if (user.equals(userId)) {
	                        poolsToApprove.put(poolId, "Y");
	                        break;
	                    }
	                }
                }
            }

            // Update the approver notes only for the first approvalLog with approved field equal to A
            for(int i = 0; i < routingList.size(); i++)
            {
            	ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
            	if( approvalLog.getApproved().equals("A")&& (approvalLog.getUserId().equals(user) || approvalLog.getCallForward().equals(user) || approvalLog.getBackupApprover().equals(user)) && (!HiltonUtility.isEmpty(notes)))
                {
                	String previousNotes=approvalLog.getApproverNotes();
            		if(!HiltonUtility.isEmpty(previousNotes))
                	{
                		previousNotes=previousNotes+"\\n";
                	}
            		approvalLog.setApproverNotes(previousNotes+"[" + HiltonUtility.getFormattedDate("", organizationId)+ " " +
                    		HiltonUtility.getFormattedDate("", organizationId, "HH:mm:ss") + "] " + notes);
                	break;
                }
            }

            boolean isUserCurrentApprover = false;
            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                List poolUsers = this.getPoolUsers(approvalLog, incomingRequest);

                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(user) || approvalLog.getBackupApprover().equals(user) || (overrideApprovalForAll && approvalLog.getApproved().equals("N")) || ((!HiltonUtility.isEmpty(approvalLog.getPoolid())) && approvalLog.getApproved().equals("N") && isUserCurrentApprover && poolUsers.contains(user))) {
                    if (user.equals(approvalLog.getCallForward()) || user.equals(approvalLog.getBackupApprover()) || overrideApproval || poolUsers.contains(user) || (approvalLog.getApproverType().equals("P") && poolsToApprove.containsKey(approvalLog.getPoolid()))) {
                        // approve current req

                    	if(isDeferTo){
                        	approvalLog.setApproved("D") ;
                        	approvalLog.setDeferId(deferTo);
                        	 if (user.equals(approvalLog.getBackupApprover())) {
                                 deferredByBackupApprover = true;
                             }else if (!approvalLog.getApproved().equals("A") && overrideApproval) {
                                 deferredByOverrider = true;
                             }
                        } else { 
	                        if (returnTo.equals("Y")) {
	                            approvalLog.setApproved("N") ;
	                            if (user.equals(approvalLog.getBackupApprover())) {
	                                reroutedByBackupApprover = true;
	                            } else if (!approvalLog.getApproved().equals("A") && overrideApproval) {
	                                reroutedByOverrider = true;
	                            }
	                        } else {
	                            approvalLog.setApproved("Y") ;
	                            approvalLog.setApproverSig(signed) ;
	                            approvalLog.setDateApproved(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
	                            if (user.equals(approvalLog.getBackupApprover())) {
	                                approvedByBackupApprover = true;
	                            }
	                            incomingRequest.put("approver", approvalLog);
	
	                            AppPool poolObject = AppPoolManager.getInstance().getAppPool(organizationId, approvalLog.getPoolid());
	
	                            if("P".equals(approvalLog.getApproverType()) && poolObject != null && "Y".equalsIgnoreCase(poolObject.getPoolflag1())) {
	                            	isUserCurrentApprover = true;
	                            }
	
	                        }
	                    	approvalLog.setAlternateUserid(user) ;
                        }
                    	
                        // approvalLog.setApproverNotes(notes) ;
                        approvalLog.setRecommendation(recommendation);
                        if (approvalLog.getDateAssigned() == null) {
                            approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                        }

                        incomingRequest.put("approvalLog", approvalLog);
                        if (Utility.isEmpty(routTo)&&Utility.isEmpty(deferTo)) {
                            /* Do not update if rerouting or deferring, the whole list will be done later */
                            ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();
                            appUpdate.executeTask(incomingRequest);
                            this.setStatus(appUpdate.getStatus());
                            if(this.getStatus() != Status.SUCCEEDED)
                            {
                                throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                            }
                        }
                    } else {
                    	if (approvalLog.getApproved().equals("A")) approvalLog.setApproved("N") ;
                    }
/*                    else if (poolId.equals(approvalLog.getPoolid())) {
                        // Remove other pool records
                        ApprovalLogDeleteById appDelete = new ApprovalLogDeleteById();
                        incomingRequest.put("approvalLog",approvalLog) ;
                        approvalLog.setApproved("Y") ;
                        appDelete.executeTask(incomingRequest) ;
                        if(this.getStatus() != Status.SUCCEEDED)
                        {
                            throw new TsaException("An error ocurred deleting pool record for user: " + approvalLog.getComp_id().getUserId());
                        }
                    }
*/
                } else {
                	if (approvalLog.getApproved().equals("A")) approvalLog.setApproved("N") ;
                }

            }

            if (isDeferTo){
            	if (reqType.equals("M")) {
            		status = DocumentStatus.REQ_PLANNING_APPROVING ;
            	} else {
            		status = DocumentStatus.REQ_APPROVING ;
            	}
            }else {
            	 boolean approved = true ;
                 for(int i = 0; i < routingList.size(); i++)
                 {
                     ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                     if (! approvalLog.getApproved().equals("Y") && !approvalLog.isAnFyiApprover() && !approvalLog.isAnAdvisor()) {
                         approved = false ;
                         break;
                     }
                 }
	            if (approved) {
	            	if (reqType.equals("M")) {
	            		status = DocumentStatus.REQ_PLANNING_APPROVED ;
	            	} else {
	            		status = DocumentStatus.REQ_APPROVED ;
	            	}
	            } else {
	            	if (reqType.equals("M")) {
	            		status = DocumentStatus.REQ_PLANNING_APPROVING ;
	            	} else {
	            		status = DocumentStatus.REQ_APPROVING ;
	            	}
	            }
            }

            incomingRequest.put("approvedBy",user) ;
            incomingRequest.put("newStatus",status) ;

            if (reroutedByBackupApprover) {
                incomingRequest.put("backupApproverAction", "rerouted");
            }
            else if (approvedByBackupApprover) {
                incomingRequest.put("backupApproverAction", "approved");
            } else  if (deferredByBackupApprover) {
                incomingRequest.put("backupApproverAction", "deferred");
            } else {
                incomingRequest.put("backupApproverAction", "");
            }

            if (reroutedByOverrider) {
                incomingRequest.put("overrideAction", "rerouted");
            }
            else if (overrideApproval) {
                incomingRequest.put("overrideAction", "approved");
            } else if (deferredByOverrider) {
                incomingRequest.put("overrideAction", "deferred");
            } else {
                incomingRequest.put("overrideAction", "");
            }

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return routingList ;
    }

    private List getPoolUsers(ApprovalLog approvalLog, Map incomingRequest) throws Exception
	{
		List poolUsers = new ArrayList();
		String poolId = approvalLog.getPoolid();

		if (!HiltonUtility.isEmpty(poolId))
		{
			incomingRequest.put("AppPooluser_poolid", poolId);

			AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
			List poolUsersList = (List) task.executeTask(incomingRequest);

			for (int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++)
			{
				AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
				poolUsers.add(poolUser.getComp_id().getUserId());
			}
		}

		return poolUsers;
	}

}




