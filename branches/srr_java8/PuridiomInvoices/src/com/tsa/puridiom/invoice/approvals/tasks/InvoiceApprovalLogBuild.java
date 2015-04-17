package com.tsa.puridiom.invoice.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class InvoiceApprovalLogBuild extends Task
{

	List appLogList = null;

	public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest	= (Map) object;
        List approverList = (List) incomingRequest.get("routingList");
        String oid = (String) incomingRequest.get("organizationId");
        String icHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
        BigDecimal amtToApprove = new BigDecimal(0);

        try
        {
        	if (approverList != null)
        	{
        		int appListSize = approverList.size();
        		appLogList = new ArrayList();
        		for (int i = 0; i < appListSize; i++)
        		{
        			String approver = (String) approverList.get(i);
        			/*

        			String approverType = "U";
        			String poolId = "";
        			String poolDesc = "";
        			*/

        			if (approver.startsWith("@"))
        			{
        				String	poolId = approver.substring(1);
                    	incomingRequest.put("AppPool_poolid",poolId) ;
                    	incomingRequest.put("AppPooluser_poolid",poolId) ;
                    	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
                    	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);

                    	ApprovalLog appLog = new ApprovalLog();
                        ApprovalLogPK pk = new ApprovalLogPK();
                        pk.setIcHeader(new BigDecimal(icHeader));
                        //pk.setUserId(poolId);
                        pk.setUserId(approver);
                        appLog.setComp_id(pk);
                        appLog.setAdvisor("N");
                        appLog.setAmount(amtToApprove) ;
                        appLog.setApproved("N") ;
                        //appLog.setApproverAmount(appRule.getUserAmount()) ;
                    	appLog.setApproverName(appPool.getPooldesc());
                    	appLog.setApproverNotes("") ;
            	        appLog.setApproverSig("") ;
                    	appLog.setApproverType("P");
            	        appLog.setAuthorized("N") ;
                    	//appLog.setCallForward(appRule.getUserCallForward()) ;
                        //appLog.setExcludeLess(appRule.getExcludeLess());
                        appLog.setFyiApprover("N");
                        appLog.setPoolid(poolId);
                        appLog.setPooldesc(appPool.getPooldesc());
                        //appLog.setRequiredApprover(appRule.getRequiredApprover());
                        appLog.setRuleAction("A") ;
            	        //appLog.setRuleSource(source);
            	        appLog.setRuleType("EXT") ;
            	        //appLog.setRuleOrder(new BigDecimal(propertiesManager.getProperty("MISC", "EXTORDER", "20")));
            	        //appLog.setUdfValues(appRule.getRuleData());

            	        appLogList.add(appLog) ;
            	    }
        			else
                    {
        				UserProfile appRuleUser = UserManager.getInstance().getUser(oid, approver);
            			// Add approver to approval_log
                        ApprovalLog appLog = new ApprovalLog();
                        ApprovalLogPK pk = new ApprovalLogPK();
                        pk.setIcHeader(new BigDecimal(icHeader));
                        pk.setUserId(approver);
                        appLog.setComp_id(pk);
                        appLog.setAdvisor("N");
                        appLog.setAmount(amtToApprove) ;
                        appLog.setApproved("N") ;
                        //appLog.setApproverAmount(appRuleUser.getApprovalAmount()) ;
                    	appLog.setApproverName(appRuleUser.getDisplayName());
                    	appLog.setApproverNotes("") ;
            	        appLog.setApproverSig("") ;
                    	appLog.setApproverType("U");
            	        appLog.setAuthorized("N") ;
                    	appLog.setCallForward(appRuleUser.getCallForward());
                        //appLog.setExcludeLess(appRule.getExcludeLess());
                        appLog.setFyiApprover("N");
                        appLog.setPoolid(" ") ;
                        appLog.setPooldesc("");
                        //appLog.setRequiredApprover(appRule.getRequiredApprover());
                        appLog.setRuleAction("A") ;
            	        //appLog.setRuleSource(source);
            	        appLog.setRuleType("EXT") ;
            	        //appLog.setRuleOrder(new BigDecimal(propertiesManager.getProperty("MISC", "EXTORDER", "20")));
            	        //appLog.setUdfValues(appRule.getRuleData());

            	        if (approver.equalsIgnoreCase("#Requisitioner"))
            	        {
            	        	appLog.setApproverName("No Requisitioner was found.  Please add an approver.");
            	        }
            	        if (approver.equalsIgnoreCase("#Approver"))
            	        {
            	        	appLog.setApproverName("No Requisition Approver was found.  Please add an approver.");
            	        }

            	        appLogList.add(appLog) ;
                    }
        		}

                this.setStatus(Status.SUCCEEDED);
        	}

        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return appLogList;
    }

}