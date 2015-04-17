package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;


public class RequisitionBuildMSRRoutingList extends Task
{
    DBSession dbs = null;
	String organizationId = "";
    String userId = "";
    String userDateFormat = "";
    String userTimeZone = "";
    List	extList = new ArrayList() ;


	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
            this.dbs = (DBSession)incomingRequest.get("dbsession") ;
            this.organizationId = (String) incomingRequest.get("organizationId");
            this.userId = (String) incomingRequest.get("userId");
            this.userDateFormat = (String) incomingRequest.get("userDateFormat");
            this.userTimeZone = (String) incomingRequest.get("userTimeZone");
            
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
            }
            
            String formUser = "";
            BigDecimal icHeader = new BigDecimal(0);

            RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            formUser = (String) incomingRequest.get("requisitionerCode");

            List userList = new ArrayList() ;

            if (requisitionHeader != null) {
            	String fpeId = requisitionHeader.getOwner() ;
            	String engId = requisitionHeader.getBuyer() ;
                BigDecimal authorityRequired = requisitionHeader.getTotal();
            	icHeader = requisitionHeader.getIcReqHeader() ;

            	if (! HiltonUtility.isEmpty(engId) && propertiesManager.getProperty("APPROVALS", "ENGINEERAPPROVE", "N").equalsIgnoreCase("Y")) {
                    if (! HiltonUtility.isEmpty(fpeId) && fpeId.compareTo(engId) != 0) {
                    	UserProfile approver = UserManager.getInstance().getUser(this.organizationId, engId);
                    	this.addApprover(approver,  new BigDecimal(0),  authorityRequired , icHeader, "Engineer Approver");
                    }
                }
                if (! HiltonUtility.isEmpty(fpeId)) {
                	UserProfile approver = UserManager.getInstance().getUser(this.organizationId, fpeId);
            		this.addApprover(approver,  authorityRequired,  authorityRequired, icHeader, "FPE Approver");

                }

                incomingRequest.put("extList",extList) ;

            }
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
            Log.error(this, e.getMessage());
            e.printStackTrace();
        }
		return extList ;
	}

	private void addApprover(UserProfile approver, BigDecimal approvalAuthority, BigDecimal amtToApprove, BigDecimal icHeader, String description) {
        try
        {
            String callForward = null;
            Date forwardOffDate = null;
            //Date today = Dates.getCurrentDate(approver.getTimeZone());
            Date today = Dates.getDate(Dates.today(this.userDateFormat, this.userTimeZone));
            
            callForward = approver.getCallForward();
            forwardOffDate = approver.getForwardOffDate();
            
            if(forwardOffDate != null && HiltonUtility.ckNull(forwardOffDate).after(today)){
	            if (Utility.isEmpty(callForward)) {
	                callForward = approver.getUserId();
	            } else {
	                callForward = approver.getCallForward();
	            }
            } else {
            	callForward = approver.getUserId();
            }

			// Add approver to approval_log
	        ApprovalLog appLog = new ApprovalLog();
	        ApprovalLogPK pk = new ApprovalLogPK();
	        pk.setIcHeader(icHeader);
	        pk.setUserId(approver.getUserId());
	        appLog.setComp_id(pk);
	        appLog.setAdvisor("N");
	        appLog.setAmount(amtToApprove) ;
	        appLog.setApproved("N") ;
	        appLog.setApproverAmount(approvalAuthority) ;
	    	appLog.setApproverName(approver.getDisplayName());
	    	appLog.setApproverNotes("") ;
	        appLog.setApproverSig("") ;
	    	appLog.setApproverType("U");
	        appLog.setAuthorized("N") ;
	    	appLog.setCallForward(callForward) ;
	        appLog.setExcludeLess(new BigDecimal(0));
	        appLog.setFyiApprover("N");
	        appLog.setPoolid(" ") ;
	        appLog.setPooldesc("");
	        appLog.setRequiredApprover("N");
	        appLog.setRuleAction("A") ;
	        appLog.setRuleSource("HDR");
	        appLog.setRuleType("MSR") ;
	        appLog.setRuleOrder(new BigDecimal(0));
	        appLog.setUdfValues(description);
	        appLog.setRuleNotes("");

            extList.add(appLog) ;
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            e.printStackTrace();
        }
	}
}