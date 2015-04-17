package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApprovalLogListResetSequence extends Task {

	public Object executeTask(Object object) throws Exception	{
		Map incomingRequest = (Map)object;
		Map routingListGroups = new HashMap();

        try {
            List routingList = new ArrayList();
            List finalRuleList =(List) incomingRequest.get("finalRuleList");
            int seq = 1;
            
            routingListGroups =(Map) incomingRequest.get("routingListGroups");
            
            for (int ir=0; ir < finalRuleList.size(); ir++) {
    			String approvalRule = (String) finalRuleList.get(ir);
    			Map ruleMap = (Map) routingListGroups.get(approvalRule);
                List groupList = (List) ruleMap.get("ruleRoutingList");
                
                int groupApprovers = groupList.size();
                for (int ia=0; ia < groupApprovers; ia++) {
                    ApprovalLog approvalLog = (ApprovalLog) groupList.get(ia);
                    ApprovalLogPK pk = approvalLog.getComp_id();                    
                    pk.setSequence(new BigDecimal(seq));
                    approvalLog.setComp_id(pk);
                    groupList.set(ia, approvalLog);
                    seq++;
                }
                
                ruleMap.put("ruleRoutingList", groupList);
                routingListGroups.put(approvalRule, ruleMap);
            }
            
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