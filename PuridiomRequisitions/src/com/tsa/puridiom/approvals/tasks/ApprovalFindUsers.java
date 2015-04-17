package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ApprovalFindUsers extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

        String      acctList = null ;
        boolean listOk = true ;
        boolean hasLineRules  = false ;
        
        String 		appUdfName[] = (String[]) incomingRequest.get("appUdfName") ;
        String		appUdfSection[] = (String[]) incomingRequest.get("appUdfSection") ;
        String		appUdfType[] = (String[]) incomingRequest.get("appUdfType") ;
        String		appUdfColumn[] = (String[]) incomingRequest.get("appUdfColumn") ;
        
        String		appUdfValue[] = new String[appUdfColumn.length] ;

        ArrayList  result = new ArrayList() ;
        
        List appRuleList =  (List) incomingRequest.get("appRuleList") ;
        List appDataList = (List) incomingRequest.get("dataList");
        List appLogList = new ArrayList();

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
		boolean termApprove = propertiesManager.getProperty("APPROVALS","TermApprove","Y").equalsIgnoreCase("Y") ;
        
		BigDecimal amtToApprove = (BigDecimal) incomingRequest.get("amtToApprove") ;

        String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
        String oid = (String)incomingRequest.get("organizationId");
        StringBuffer ruleString = new StringBuffer("") ;
		
	   try {
	        int ruleCount = appRuleList.size();
	        int dataCount = appDataList.size();
	        int defCount = appUdfColumn.length ;
	        
	        for (int ix = 0; ix < ruleCount; ix++ ) {
	        	
	        	AppRule appRule = (AppRule) appRuleList.get(ix) ;
	        	AppRulePK appRulePK = appRule.getComp_id() ;
                String approver = appRulePK.getUserId();
	        	
	        	//UserProfile appRuleUser = (UserProfile) appRow[1];
	        	
	        	System.out.println("------ Rule " + ix + " started for user " + approver);
	        	boolean passed = false  ;
	        	boolean ruleOk = false ;
	        	ruleString.setLength(0);
	        	
	        	for (int ux = 0; appUdfColumn[ux] != null;ux++)  {
	        		if ( ux == 0) {
	        			appUdfValue[0] = appRulePK.getUdf1Code() ;
	        		} else if ( ux == 1) {
	        			appUdfValue[1] = appRulePK.getUdf2Code() ;
	        		} else if ( ux == 2) {
	        			appUdfValue[2] = appRulePK.getUdf3Code() ;
	        		} else if ( ux == 3) {
	        			appUdfValue[3] = appRulePK.getUdf4Code() ;
	        		} else if ( ux == 4) {
	        			appUdfValue[4] = appRulePK.getUdf5Code() ;
	        		} else if ( ux == 5) {
	        			appUdfValue[5] = appRulePK.getUdf6Code() ;
	        		} else if ( ux == 6) {
	        			appUdfValue[6] = appRulePK.getUdf7Code() ;
	        		} else if ( ux == 7) {
	        			appUdfValue[7] = appRule.getUdf8Code() ;
	        		} else if (ux == 8) {
	        			appUdfValue[8] = appRule.getUdf9Code() ;
	        		} else if (ux == 9) {
	        			appUdfValue[9] = appRule.getUdf10Code() ;
	        		} else {
	        			continue ;
	        		}
	        		ruleString.append("[");
	        		ruleString.append(appUdfName[ux] + " = " + appUdfValue[ux]);
	        		ruleString.append("] ") ;
	        	}

        		System.out.println(ruleString) ;
        		
	        	/* Start Rule Loop */
	        	for (int ux = 0; appUdfColumn[ux] != null;ux++)  {
	        		String ruleValue = appUdfValue[ux] ;
		        	for (int cx = 0; cx < dataCount; cx++) {
		        		Hashtable htColumn = (Hashtable) appDataList.get(cx) ;
		        		String	ruleDataName = (String) htColumn.get("name") ;
		        		System.out.println(ruleDataName + "-" + appUdfColumn[ux]);
		        		if (! ruleDataName.equalsIgnoreCase(appUdfColumn[ux])) {
		        			continue;
		        		}
		        		
		        		String	ruleColumn = (String) htColumn.get("rulecolumn") ;
		        		String 	ruleSource = (String)htColumn.get("sectioncode") ;
		        		
	                    passed = false ;
		        		String dataValue = (String) htColumn.get("value") ;
		        		
		        		System.out.println("RuleValue=" + ruleValue) ;
		        		System.out.println("DataValue=" + dataValue) ;

		        		if (ruleValue.equals("*")) {
		        			passed = true ;
		        		} else if (ruleValue.equals("NB!") && !Utility.isEmpty(dataValue)) {
		        		    passed = true;
		        		} else if (ruleValue.startsWith("%") && ruleValue.endsWith("%")) {
	                		String temp = ruleValue ;
	                		temp = temp.substring(1);
	               			temp = temp.substring(0,temp.length()- 1) ;
	                		passed = (dataValue.toUpperCase().indexOf(temp.toUpperCase()) >= 0) ;
		        		} else if (ruleValue.startsWith("%")) {
	                		String temp = ruleValue.substring(1);
	                		passed = dataValue.endsWith(temp) ;
		        		} else if (ruleValue.endsWith("%")) {
	                		String temp = ruleValue.substring(0,ruleValue.length()-1);
	                		passed = dataValue.startsWith(temp) ;
		        		} else if (ruleValue.equalsIgnoreCase(dataValue)) {
		        			passed = true ;
		        		}
		        		if (passed) {
		        			break ;
		        		}
	        	    }
		        	
	        		System.out.println("Passed=" + passed) ;
	        		if (! passed) { 
	        			System.out.println("Beaking...");
	        			break ;
	        		}
	        	}
	        	 
	        	System.out.println("--------Rule " + ix + " " + passed);
	        	if (! passed) {
	        		continue ;
	        	}
	        	

                System.out.println("****************************************");
                System.out.println("Adding Approver - " + approver) ;
                System.out.println("****************************************");

	        	
                if(approver.startsWith("@"))
                {
                    String	poolId = approver.substring(1);    // Hack @
                	incomingRequest.put("AppPool_poolid",poolId) ;
                	incomingRequest.put("AppPooluser_poolid",poolId) ;
                	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
                	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);
                	String pFlag = "P" ;
                	if (appPool != null) {
                		pFlag = appPool.getPoolflag1() ;
                	}
                	
					AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
                    List poolUsersList = (List)task.executeTask(incomingRequest);
                    for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++)
                    {
	        			// Add approver to approval_log 
	                    ApprovalLog appLog = new ApprovalLog();
       	                ApprovalLogPK pk = new ApprovalLogPK();
       	                pk.setIcHeader(new BigDecimal(icHeader));
       	                appLog.setComp_id(pk);
       	                appLog.setRuleType("EXT");
  //  	       	                appLog.setRuleSource(ruleSource) ;
       	                appLog.setAmount(amtToApprove) ;

                    	AppPooluser poolUser = (AppPooluser)poolUsersList.get(poolIndex);
                        String tempApprover = poolUser.getComp_id().getUserId();
                        appLog.setApproverType(pFlag);
                        appLog.setPoolid(poolId) ;
                        appLog.setPooldesc(appPool.getPooldesc());
                        
        	        	UserProfile pUser = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), poolUser.getComp_id().getUserId());
	        	        if (pUser.getStatus().equals("02")) {
	        	        	BigDecimal approverAmount = pUser.getApprovalAmount() ;
	        	        	BigDecimal excludeLess = pUser.getExcludeLess() ;
	        	        	String callForward = pUser.getCallForward() ;
	                        if (Utility.isEmpty(callForward)) {
	                        	callForward = poolUser.getComp_id().getUserId() ;
	                        }
	        	        	
	                        if (approverAmount == null) approverAmount = new BigDecimal(0) ;
	                        if (excludeLess == null) excludeLess = new BigDecimal(0) ;

	                        appLog.setApproverAmount(approverAmount) ;
	                        appLog.setExcludeLess(excludeLess) ;
	       	                appLog.setCallForward(callForward) ;
	                        
	                        this.addApprover(tempApprover, appLog, oid);

	                        appLogList.add(appLog);
	        	        }
                    }
                }
                else
                {
        			// Add approver to approval_log 
                    ApprovalLog appLog = new ApprovalLog();
   	                ApprovalLogPK pk = new ApprovalLogPK();
   	                pk.setIcHeader(new BigDecimal(icHeader));
   	                appLog.setComp_id(pk);
   	                appLog.setRuleType("EXT");
//           	                appLog.setRuleSource(ruleSource) ;
   	                appLog.setAmount(amtToApprove) ;
                	
                	UserProfile appRuleUser = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), appRule.getComp_id().getUserId());

    	        	BigDecimal approverAmount = appRuleUser.getApprovalAmount() ;
                    BigDecimal approverMin = appRuleUser.getExcludeLess() ;
                    
                    String byRuleLine  = appRuleUser.getApproveByLine().toUpperCase();
                    String callForward = appRuleUser.getCallForward() ;
                    if (Utility.isEmpty(callForward)) {
                    	callForward = appRulePK.getUserId() ;
                    }
                    
                    if (byRuleLine.equals("Y")) {
                    	approverMin = appRule.getExcludeLess() ;
                    	approverAmount = appRule.getAmount() ;
                    }
                    
                    if (approverMin == null) approverMin = new BigDecimal(0) ;
                    if (approverAmount == null) approverAmount = new BigDecimal(0) ;
                    
                    System.out.println("Approver Amount = " + approverAmount.toString()) ;
                	appLog.setApproverAmount(approverAmount) ;
                	appLog.setExcludeLess(approverMin) ;
   	                appLog.setCallForward(callForward) ;
   	                appLog.setApproverType("U");
                    appLog.setPoolid(" ") ;
                    appLog.setPooldesc("");
                    
                    this.addApprover(approver, appLog, oid);
                    appLogList.add(appLog);
                    /* Test */
                    if (approverAmount.compareTo(amtToApprove) >= 0) {
                    	ruleOk = true ;
                    	if (termApprove) {
                    		break ;
                    	}
                    }
                }
	        	
                if (! ruleOk) {
                	System.out.println("Rule failed") ;
                	System.out.println(ruleString) ;
                	incomingRequest.put("BuildStatus","FAILED" ) ;
                	break ;
                }
	        }
	        
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return appLogList ;
	}
	
    public void addApprover(String name, ApprovalLog appLog, String oid)
    {
        try
        {
	        UserProfile	user = UserManager.getInstance().getUser(oid,name);
	        ApprovalLogPK pk = appLog.getComp_id();
	        pk.setUserId(name);
	        appLog.setComp_id(pk);
            appLog.setApproverName(user.getDisplayName());
//	        appLog.setApproverAmount(user.getApprovalAmount());
//	        appLog.setCallForward(user.getCallForward());
	        appLog.setApproved("N") ;
	        appLog.setAuthorized("N") ;
	        appLog.setRuleAction("A") ;
	        appLog.setApproverSig("") ;
	        appLog.setRuleType("EXT") ;
	        appLog.setApproverNotes("") ;
        }
        catch (Exception e) 
        {
            Log.error(this, e.toString());
        }
        
    }
}