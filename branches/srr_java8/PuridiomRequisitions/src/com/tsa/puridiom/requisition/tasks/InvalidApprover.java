/*
 * Created on Apr 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvalidApprover extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String user = (String)incomingRequest.get("userId") ;
            List approvalList = (List)incomingRequest.get("approvalLogList");
            String isPoolApprover = "N";
            boolean   foundUser = false ;

            if(approvalList == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Approver List could not be initialized!");
            }
            for(int i = 0; i < approvalList.size(); i ++)
            {
                ApprovalLog approvalLog = (ApprovalLog)approvalList.get(i);
                List poolUsers;
                
                if (approvalLog.getApproved().equals("A"))
                {
                    if (approvalLog.getApproverType().equals("P")) {
                        String	poolId = approvalLog.getPoolid();
    	            	incomingRequest.put("AppPooluser_poolid",poolId) ;

    	            	AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
		                List poolUsersList = (List)task.executeTask(incomingRequest);
		                boolean existsInPool = false;

		                for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++) {
		                    AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
		                    String	userId = poolUser.getComp_id().getUserId();
		                    if (user.equals(userId)) {
		                        existsInPool = true;
		                    }
		                }

		                if (!existsInPool)
						{
							incomingRequest.put("Message", "You are not the current Approver!");
							incomingRequest.put("approvalAbleVerification", "N");
							ret = "true";
						} else
						{
							isPoolApprover = "Y";
						}
                    } else {
                    	
                    	poolUsers = this.getPoolUsers(approvalLog, incomingRequest);
                    	
                    	if (! foundUser) {
		                    if(!approvalLog.getComp_id().getUserId().equalsIgnoreCase(user))
		                    {
		                    	if (!approvalLog.getComp_id().getUserId().equals("AP"))
		                    	{
			                    	if(!approvalLog.getCallForward().equalsIgnoreCase(user))
			                        {
			                    	    if(!approvalLog.getBackupApprover().equalsIgnoreCase(user))
				                        {
			                    	    	if (!poolUsers.contains(user))
											{
												incomingRequest.put("Message", "You are not the current Approver!");
												incomingRequest.put("approvalAbleVerification", "N");
												ret = "true";
											} else
											{
												foundUser = true;
											}
				                        } else {
				                        	foundUser = true ;
				                        }
			                        } else {
			                        	foundUser = true ;
			                        }
		                    	} else {
		                    		foundUser = true ;
		                    	}
		                    } else {
		                    	foundUser = true ;
		                    }
                    	} else {
		                    if((!approvalLog.getComp_id().getUserId().equalsIgnoreCase(user)) &&
		                    	(!approvalLog.getComp_id().getUserId().equals("AP")) &&
			                    	(!approvalLog.getCallForward().equalsIgnoreCase(user)) &&
			                    	    (!approvalLog.getBackupApprover().equalsIgnoreCase(user)) &&
			                    	    (!poolUsers.contains(user)))      {
                        		approvalLog.setApproved("N") ;
                    		}
                    	}
                    }
                }
            }

            incomingRequest.put("poolApprover", isPoolApprover);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
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
