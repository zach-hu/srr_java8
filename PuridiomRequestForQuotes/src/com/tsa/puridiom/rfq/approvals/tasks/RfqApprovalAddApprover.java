package com.tsa.puridiom.rfq.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RfqApprovalAddApprover extends Task
{

    List appLogList = null ;

    public Object executeTask(Object object) throws Exception
    {
        Map			incomingRequest	= (Map)object ;

        String oid = (String)incomingRequest.get("organizationId");
        appLogList = (List)incomingRequest.get("routingList") ;
        String	addUser = (String)incomingRequest.get("addUser") ;
        String  deferTo = (String)incomingRequest.get("deferTo") ;
        String	insertBefore = (String) incomingRequest.get("insertBefore") ;
        String 	icHeader = (String)incomingRequest.get("RfqHeader_icRfqHeader");
        BigDecimal	amtToApprove = new BigDecimal(0);
        int				insertRow = 0;

        if (insertBefore == null) {		insertBefore = "0" ; // Insert Before first record 0 = append
        }

        try
        {
            insertRow = Integer.parseInt(insertBefore) ;
            if (insertRow > appLogList.size()) {		insertRow = 0;	}
            UserProfile appRuleUser = UserManager.getInstance().getUser(oid, addUser);
            if (appRuleUser.isRegistered() && appRuleUser.isAnApprover() && appRuleUser.getStatus().equals("02"))
            {
                // Add approver to approval_log
                ApprovalLog appLog = new ApprovalLog();
                ApprovalLogPK pk = new ApprovalLogPK();
                pk.setIcHeader(new BigDecimal(icHeader));
                pk.setIcLine(new BigDecimal(0)) ;
                pk.setUserId(addUser) ;
                pk.setSequence(new BigDecimal(appLogList.size() + 1)) ;
                appLog.setComp_id(pk);

                BigDecimal approverAmount = appRuleUser.getApprovalAmount() ;
                String callForward = "";
                if(!Utility.isEmpty(deferTo))
                {
                	callForward = deferTo ;
                }else{
	                callForward = appRuleUser.getCallForward() ;
	                if (Utility.isEmpty(callForward)) {		callForward = addUser ;			}
                }

                appLog.setRuleType("MAN");
                appLog.setRuleSource(" ");
                appLog.setAmount(amtToApprove) ;
                appLog.setApproverAmount(approverAmount) ;
                appLog.setCallForward(callForward) ;
                appLog.setApproverType("U");
                appLog.setPoolid(" ") ;
                appLog.setApproverName(appRuleUser.getFirstName() + " " + appRuleUser.getLastName());
                appLog.setApproved("N") ;
                appLog.setAuthorized("N") ;
                appLog.setRuleAction("A") ;
                appLog.setApproverSig("") ;
                //appLog.setRuleType("EXT") ;
                appLog.setApproverNotes("") ;
                appLog.setUdfValues(" ");
                if (insertRow >=  0)
                {
                    /* Insert befor insertRow */
                    appLogList.add(insertRow,appLog) ;
                    this.reSequence() ;
                }
                else
                {
                    /* Insert At end */
                    appLogList.add(appLog) ;
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