package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ApprovalLogListSetFinalSequence extends Task {

	public Object executeTask(Object object) throws Exception	{
		Object result = null;

        try {
            Map incomingRequest = (Map)object;
            
            List routingList = (List) incomingRequest.get("routingList");
            BigDecimal tmpSequence = new BigDecimal(0);
            int seq = 1;
            
            Collections.sort(routingList, new ApprovalLogSequenceComparator());
            
            for (int ia=0; ia < routingList.size(); ia++) {
                ApprovalLog approvalLog = (ApprovalLog) routingList.get(ia);
                ApprovalLogPK pk = approvalLog.getComp_id();
                
                if (ia == 0)
				{
					tmpSequence = pk.getSequence();
				}

				if (tmpSequence.compareTo(pk.getSequence()) != 0)
				{
					tmpSequence = pk.getSequence();
					seq++;
				}
                
                pk.setSequence(new BigDecimal(seq));
                approvalLog.setComp_id(pk);
                routingList.set(ia, approvalLog);
            }
            
            result = routingList;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
			this.status = Status.FAILED;
			throw e;
        }
        
		return result ;
	}
}