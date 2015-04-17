package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ApprovalAddApprover extends Task
{

    List appLogList = null ;

    public Object executeTask(Object object) throws Exception
    {
        Map			incomingRequest	= (Map)object ;

        String oid = (String)incomingRequest.get("organizationId");
         PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;

        appLogList = (List)incomingRequest.get("routingList") ;
        String	addUser = (String)incomingRequest.get("addUser") ;
        String	addUserRule = (String)incomingRequest.get("addUserRule") ;
        String	fyiApprover = (String)incomingRequest.get("fyiApprover") ;
        String	advisor = (String)incomingRequest.get("advisor") ;
        String	routTo = (String)incomingRequest.get("routTo") ;
        String	deferTo = (String)incomingRequest.get("deferTo") ;
        String	insertBefore = (String) incomingRequest.get("insertBefore") ;
        String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
        String formtype = HiltonUtility.ckNull((String)incomingRequest.get("formtype"));
        if (HiltonUtility.isEmpty(icHeader) && formtype.equalsIgnoreCase("PO")) {
        	icHeader = HiltonUtility.ckNull((String)incomingRequest.get("PoHeader_icPoHeader"));
        } else if (HiltonUtility.isEmpty(icHeader) && formtype.equalsIgnoreCase("IVC")) {
        	icHeader = HiltonUtility.ckNull((String)incomingRequest.get("InvoiceHeader_icIvcHeader"));
        }
        String	addUserAmount = (String)incomingRequest.get("addUserAmount") ;
        String firstFyi = propertiesManager.getProperty("APPROVALS","FYIFIRSTAPPROVALS","N");
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

        try {
            insertRow = Integer.parseInt(insertBefore) ;
            if (insertRow > appLogList.size()) {
                insertRow = 0;
            }

            if(Utility.isEmpty(routTo) && Utility.isEmpty(deferTo))
            {
            	if(firstFyi.equalsIgnoreCase("Y") && fyiApprover.equalsIgnoreCase("Y"))
                {
                	insertRow = 0;
                }
                else
                {
                	insertRow = appLogList.size();
                }
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
                String callForward = "";
                
                if(!Utility.isEmpty(deferTo))
                {
                	callForward = deferTo ;
                }else{
                	callForward =	appRuleUser.getCallForward() ;
                    if (Utility.isEmpty(callForward)) {
                        callForward = addUser ;
                    }
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

                appLog.setRuleType("MAN");
                appLog.setRuleSource(" ");
                appLog.setAmount(amtToApprove) ;
                appLog.setApproverAmount(approverAmount) ;
                appLog.setCallForward(callForward) ;
                appLog.setApproverType("U");
                appLog.setPoolid(" ") ;
                appLog.setApproverName(appRuleUser.getDisplayName());
                appLog.setApproved("N") ;
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