package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ApprovalLogListModifyForACR extends Task {

	public Object executeTask(Object object) throws Exception	{
		Map incomingRequest = (Map)object;
		Map routingListGroups = new HashMap();
		List acrPoolList = new ArrayList();
		List routingList = new ArrayList();
        try {
        	
        	if(incomingRequest.containsKey("acrPoolList") && incomingRequest.get("acrPoolList")!=null && ((List)incomingRequest.get("acrPoolList")).size()>=1)
        	{
        		acrPoolList = (List)incomingRequest.get("acrPoolList");
        		routingList = (List) incomingRequest.get("routingList");
        		if((acrPoolList!=null & acrPoolList.size()>0)&&(routingList!=null & routingList.size()>0))
        		{
        			for(int c = 0 ; c < acrPoolList.size();c++ )
        			{
        				AppPooluser arc = (AppPooluser)acrPoolList.get(c);
        				ApprovalLog belongerToARC = ApprovalLogBelongToACR(routingList,arc);
        				if(belongerToARC != null)
        				{
        					if(belongerToARC.getApproverAmount().compareTo(new BigDecimal(0))>0)
        					{
        						belongerToARC.setApproverAmount(new BigDecimal(0));
        						belongerToARC.setUdfValues("ADMIN CHECK REV");
        						belongerToARC.setRequiredApprover("Y");
        					}
        						moveToLast(routingList,belongerToARC);
        					}
        				}
        			}
        		}
            
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
			this.status = Status.FAILED;
			throw e;
        }
        

		return routingList ;
	}
	
	private ApprovalLog ApprovalLogBelongToACR(List routingList,AppPooluser arc)
	{
		ApprovalLog res = null;
		for(int c = 0; c < routingList.size();c++)
		{
			ApprovalLog appLog = (ApprovalLog)routingList.get(c);
			if(appLog.getComp_id().getUserId().compareToIgnoreCase(arc.getComp_id().getUserId())==0)
			{
				res=appLog;
				break;
			}
		}
		return res;
	}
	private void moveToLast(List routingList,ApprovalLog arc)
	{
		ApprovalLog res = null;
		for(int c = 0; c < routingList.size();c++)
		{
			ApprovalLog appLog = (ApprovalLog)routingList.get(c);
			if(appLog.getComp_id().getUserId().compareToIgnoreCase(arc.getComp_id().getUserId())==0)
			{
				routingList.remove(c);
				break;
			}
		}
		routingList.add(arc);
	}
}
