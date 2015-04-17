package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ApprovalAddApproverAppPooluser extends Task
{
	List appLogList = null;

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest	= (Map)object;

        String oid = (String)incomingRequest.get("organizationId");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

        appLogList = (List)incomingRequest.get("routingList");
        String	addUser = (String)incomingRequest.get("addUser");
        String	addUserRule = (String)incomingRequest.get("addUserRule");
        String	fyiApprover = (String)incomingRequest.get("fyiApprover");
        String	advisor = (String)incomingRequest.get("advisor");
        String	insertBefore = (String) incomingRequest.get("insertBefore");
        String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
        String	addUserAmount = (String)incomingRequest.get("addUserAmount");
        BigDecimal	amtToApprove = new BigDecimal(0);
        int				insertRow = 0;
        if(1 == 1)
        {
            //debug
        }
        if (insertBefore == null) {
            insertBefore = "0" ; // Insert Before first record 0 = append
        }

        try {
            amtToApprove = new BigDecimal(addUserAmount);
        } catch (Exception nfe) {
        }

        try
        {
        	List appPooluserList = (List)incomingRequest.get("appPooluserList");
        	if (appPooluserList != null)
        	{
        		for (int i=0; i<appPooluserList.size(); i++)
        		{
        			AppPooluser appPooluser = (AppPooluser)appPooluserList.get(i);

        			boolean noApprovalUserInGroupList = true;
        			if (appLogList != null && appPooluser != null)
        			{
        				for (int j=0; j<appLogList.size(); j++)
        				{
        					ApprovalLog approvalLog = (ApprovalLog)appLogList.get(j);
        					if (approvalLog != null && approvalLog.getComp_id() != null)
        					{
        						if (approvalLog.getComp_id().getUserId().equalsIgnoreCase(appPooluser.getComp_id().getUserId()) &&
        							approvalLog.getUdfValues().equalsIgnoreCase(addUserRule))
        						{
        							noApprovalUserInGroupList = false;
        							break;
        						}
        					}
        				}
        			}

        			if (appPooluser != null && appPooluser.getComp_id() != null && noApprovalUserInGroupList)
        			{
        				addUser = appPooluser.getComp_id().getUserId();

			            insertRow = Integer.parseInt(insertBefore) ;
			            if (insertRow > appLogList.size()) {
			                insertRow = 0;
			            }
			            UserProfile appRuleUser = UserManager.getInstance().getUser(oid, addUser);
			            if (appRuleUser.isRegistered() && appRuleUser.isAnApprover() && appRuleUser.getStatus().equals("02")) {
			                // Add approver to approval_log
			                ApprovalLog appLog = new ApprovalLog();
			                ApprovalLogPK pk = new ApprovalLogPK();
			                pk.setIcHeader(new BigDecimal(icHeader));
			                pk.setIcLine(new BigDecimal(0)) ;
			                pk.setUserId(addUser) ;
			                pk.setSequence(new BigDecimal(appLogList.size() + 1)) ;
			                appLog.setComp_id(pk);

			                BigDecimal approverAmount = new BigDecimal(0);
			                BigDecimal approverMin = appRuleUser.getExcludeLess() ;

			                if (!propertiesManager.getProperty("APPROVALS", "ManualApproverZeroAuthority", "N").equals("Y")) {
			                    approverAmount = appRuleUser.getApprovalAmount() ;
			                }

			                String byRuleLine  = appRuleUser.getApproveByLine().toUpperCase();
			                String callForward = appRuleUser.getCallForward() ;
			                if (Utility.isEmpty(callForward)) {
			                    callForward = addUser ;
			                }
			                if (Utility.isEmpty(fyiApprover)) {
			                    fyiApprover = "N" ;
			                }
			                if (Utility.isEmpty(advisor)) {
			                    advisor = "N" ;
			                }
			                if (Utility.isEmpty(addUserRule)) {
			                    addUserRule = " ";
			                }
			                appLog.setPoolid(appPooluser.getComp_id().getPoolid());
			                appLog.setRuleType("MAN");
			                appLog.setRuleSource(" ");
			                appLog.setAmount(amtToApprove) ;
			                appLog.setApproverAmount(approverAmount) ;
			                appLog.setCallForward(callForward) ;
			                appLog.setApproverType("U");
			                //appLog.setPoolid(" ") ;
			                if(!appLog.getPoolid().isEmpty()) {
			                	AppPool appPool = AppPoolManager.getInstance().getAppPool(oid, appLog.getPoolid());
			                	if(appPool != null) {
			                		appLog.setPooldesc(appPool.getPooldesc()) ;
			                	}
			                }

			                appLog.setApproverName(appRuleUser.getDisplayName());
			                if (i == appPooluserList.size() - 1) {
			                	appLog.setApproved("A") ;
			                } else {
			                	appLog.setApproved("N") ;
			                }
			                appLog.setAuthorized("N") ;
			                appLog.setRuleAction("A") ;
			                appLog.setApproverSig("") ;
			                //appLog.setRuleType("EXT") ;
			                appLog.setApproverNotes("") ;
			                appLog.setUdfValues(addUserRule);
			                appLog.setFyiApprover(fyiApprover);
			                appLog.setAdvisor(advisor);
			                appLog.setExcludeLess(approverMin);
			                appLog.setSendTo(true);
			                if (insertRow >=  0) {
			                    /* Insert befor insertRow */
			                    appLogList.add(insertRow,appLog) ;
			                    this.reSequence() ;
			                } else {
			                    /* Insert At end */
			                    appLogList.add(appLog) ;
			                }
			            }
        			}
        		}
        	}
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return appLogList;
    }

    public void reSequence( )
    {
        for(int i = 0; i < this.appLogList.size(); i++)
        {
            ApprovalLog userLog = (ApprovalLog) this.appLogList.get(i);
            ApprovalLogPK pk = userLog.getComp_id();
            pk.setSequence(new BigDecimal(i +1));
            userLog.setComp_id(pk);
        }
    }
}
