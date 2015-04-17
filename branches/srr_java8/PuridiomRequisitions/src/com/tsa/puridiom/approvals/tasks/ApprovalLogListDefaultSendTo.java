package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ApprovalLogListDefaultSendTo extends Task {

	public Object executeTask(Object object) throws Exception	{
		Map incomingRequest = (Map)object;
		Map routingListGroups = new HashMap();

        try {
            String currentUserId = (String) incomingRequest.get("userId");
            String organizationId = (String) incomingRequest.get("organizationId");
            
        	List finalRuleList = (List) incomingRequest.get("finalRuleList");
        	routingListGroups = (Map) incomingRequest.get("routingListGroups");
        	String firstFyi = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("APPROVALS","FYIFIRSTAPPROVALS","N");
        	String overBudget = HiltonUtility.ckNull((String) incomingRequest.get("OverBudget"));
			
        	/* Routing lists for each rule should be sorted as follows:
        	 * 		Advisors
        	 * 		Approval Authority (ASC)
        	 * 		Required Approvers
        	 * 		Current User
        	 * 		FYI Approvers
        	*/


    		for (int ir=0; ir < finalRuleList.size(); ir++) {
    			String approvalRule = (String) finalRuleList.get(ir);
    			Map ruleMap = (Map) routingListGroups.get(approvalRule);
    			List routingList = (List) ruleMap.get("ruleRoutingList");
    			BigDecimal amountToApprove = (BigDecimal) ruleMap.get("amountToApprove");
    			if (routingList == null) {
    			    routingList = new ArrayList();
    			    continue;
    			}
    			if(firstFyi.equalsIgnoreCase("Y"))
            	{
            		ApprovalLogRuleTypeComparator authorityComparator = new ApprovalLogRuleTypeComparator();
            		authorityComparator.setOrder("A");

        			Collections.sort(routingList, authorityComparator);
            	}
            	else
            	{
            		ApprovalLogAuthorityComparator authorityComparator = new ApprovalLogAuthorityComparator();
        			authorityComparator.setOrder("A");

        			Collections.sort(routingList, authorityComparator);
            	}

    			boolean authorityFound = false;

				for (int i = 0; i < routingList.size(); i++) {
					ApprovalLog appLog = (ApprovalLog) routingList.get(i);
					boolean sendTo = false;

					if (appLog.getRuleType().equals("MAN") || appLog.isAnFyiApprover() || appLog.isAnAdvisor() || overBudget.equalsIgnoreCase("[Budget=OverBudget]")) {
					    sendTo = true;
					    // Do not set authorityFound here b/c manually added approvers do not have the authority to approve and
					    // FYI approvers will only receive a notification - they cannot approve and
					    // Advisors will only recommend for/against - they will not approve
					}
					else if (appLog.getExcludeLess().compareTo(amountToApprove) <= 0) {
					    if (appLog.isARequiredApprover()) {
					        sendTo = true;
					    }
					    if (appLog.getApproverAmount().compareTo(amountToApprove) >= 0) {
					        if (appLog.getUserId().equalsIgnoreCase(currentUserId) || appLog.isARequiredApprover()) {
						        sendTo = true;
					            if (authorityFound) {
						            // loop back through previous approvers and set sendTo = false
							        for (int ip = 0; ip < i; ip++) {
										ApprovalLog previousAppLog = (ApprovalLog) routingList.get(ip);
										if (!previousAppLog.isARequiredApprover()) {
										    previousAppLog.setSendTo(false);
										    routingList.set(ip, previousAppLog);
										}
							        }
					            }
					        }
					        if (!authorityFound) {
						        sendTo = true;
						        authorityFound = true;
					        }
					    }
					    else if (!authorityFound) {
					        sendTo = true;
					    }
					}

					appLog.setSendTo(sendTo);
					routingList.set(i, appLog);
				}
    		}

            incomingRequest.put("finalRuleList", finalRuleList);

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
			this.status = Status.FAILED;
			throw e;
        }

		return routingListGroups ;
	}
}
