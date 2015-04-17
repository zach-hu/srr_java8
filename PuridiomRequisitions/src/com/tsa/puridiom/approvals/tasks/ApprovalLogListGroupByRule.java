package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ApprovalLogListGroupByRule extends Task {

	public Object executeTask(Object object) throws Exception	{
		Map incomingRequest = (Map)object;
		Map routingListGroups = new HashMap();

        try {
            List routingList = new ArrayList();
            List routingListHIETemp = new ArrayList();
            List routingListEXTTemp = new ArrayList();
            List routingListRULTemp = new ArrayList();
            List routingListTemp = new ArrayList();
            List finalRuleList = new ArrayList();
            String organizationId = (String) incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
            String firstFyi = propertiesManager.getProperty("APPROVALS","FYIFIRSTAPPROVALS","N");

            if (incomingRequest.containsKey("routingList")) {
                // Used when generating approval routing list
                routingList = (List) incomingRequest.get("routingList");
            } else {
                // Used when retrieving approval routing list
                routingList = (List) incomingRequest.get("approvalLogList") ;
            }
            
            if (routingList != null) {
                BigDecimal bdZero = new BigDecimal(0);

                for (int ix = 0; ix < routingList.size(); ix++ )
                {
                	ApprovalLog approvalLog = (ApprovalLog) routingList.get(ix);
                	if(approvalLog.getRuleType().equalsIgnoreCase(("HIE")))
                	{
                		routingListHIETemp.add(approvalLog);
                	}
                	else if(approvalLog.getRuleType().equalsIgnoreCase(("RUL")))
                	{
                		routingListRULTemp.add(approvalLog);
                	}
                	else
                	{
                		routingListEXTTemp.add(approvalLog);
                	}
                }

                if(firstFyi.equalsIgnoreCase("Y"))
                {
                	for(int ix = 0; ix < routingListEXTTemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListEXTTemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                	for(int ix = 0; ix < routingListHIETemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListHIETemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                	for(int ix = 0; ix < routingListRULTemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListRULTemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                }
                else
                {
                	for(int ix = 0; ix < routingListRULTemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListRULTemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                	for(int ix = 0; ix < routingListEXTTemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListEXTTemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                	for(int ix = 0; ix < routingListHIETemp.size(); ix++ )
                	{
                		ApprovalLog approvalLog = (ApprovalLog) routingListHIETemp.get(ix);
                		routingListTemp.add(approvalLog);
                	}
                }
                
                routingList = routingListTemp;

                for (int ix = 0; ix < routingList.size(); ix++ ) {
                    ApprovalLog approvalLog = (ApprovalLog) routingList.get(ix);
                    //String	rule = approvalLog.getRuleNotes();
                    String	rule = approvalLog.getUdfValues();
                    BigDecimal amtToApprove = approvalLog.getAmount();
                    Map ruleMap = null;
                    List	groupList = null;
                    BigDecimal ruleOrder = approvalLog.getRuleOrder();
                    
                    if (routingListGroups.containsKey(rule)) {
                        ruleMap = (Map) routingListGroups.get(rule);
                        groupList = (List) ruleMap.get("ruleRoutingList");
                    } else {
                        finalRuleList.add(rule);
                    }
                    if (ruleMap == null) {
                        ruleMap = new HashMap();
                    }
                    if (groupList == null) {
                        groupList = new ArrayList();
                    }
                    groupList.add(approvalLog);
                    ruleMap.put("ruleRoutingList", groupList);
                    ruleMap.put("amountToApprove", amtToApprove);
                    ruleMap.put("ruleOrder", ruleOrder);
                    routingListGroups.put(rule, ruleMap);
                }
            }
            
            /*****	Sort finalRuleList by ruleOrder from ApprovalLog *****/
            /*****	ruleOrder is set based on settings for each rule and rule type in the approval rules xml file	*****/
            if (finalRuleList != null) {
                List groupList = new ArrayList();
                for (int ifl = 0; ifl < finalRuleList.size(); ifl++) {
                    String	rule = (String) finalRuleList.get(ifl);
                    Map ruleMap = (Map) routingListGroups.get(rule);
                    ruleMap.put("rule", rule);
                    groupList.add(ruleMap);
                }
                if(firstFyi.equalsIgnoreCase("Y"))
                {
                	 ApprovalLogRuleTypeComparator ruleOrderComparator = new ApprovalLogRuleTypeComparator();
                	 ruleOrderComparator.setOrder("A");
	    			 Collections.sort(groupList, ruleOrderComparator);
                }
                else
                {
                	ApprovalLogRuleOrderComparator ruleOrderComparator = new ApprovalLogRuleOrderComparator();
	    			ruleOrderComparator.setOrder("A");
	                Collections.sort(groupList, ruleOrderComparator);
                }

    			finalRuleList = new ArrayList();
    			
                for (int ig = 0; ig < groupList.size(); ig++) {
                    Map ruleMap = (Map) groupList.get(ig);
                    String	rule = (String) ruleMap.get("rule");
                    
                    finalRuleList.add(rule);
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
