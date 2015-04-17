package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.apprule.tasks.AppRuleCopy;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApprovalBuildRuleList extends Task
{
    String 		appUdfName[] = null ;
    String		appUdfSection[] = null;
    String		appUdfType[] = null;
    String		appUdfColumn[] = null ;
    String		appUdfValue[] = null ;
    String 		userDateFormat = "";  
	String 		userTimeZone = "";

	public Object executeTask(Object object) throws Exception
	{
		/* ApprovalBuildRuleList
		 * 		Build a new list of rules based on the data from the request. The new list of rules must pass validation
		 * 		before approval can occur..
		 */
		Map incomingRequest = (Map)object;

        String      acctList = null ;
        boolean listOk = true ;
        boolean hasLineRules  = false ;
        
        RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
        String requisitionType = "";
        if (requisitionHeader != null) {
        	requisitionType = requisitionHeader.getRequisitionType();
        }
        
		BigDecimal amtToApprove = (BigDecimal) incomingRequest.get("amtToApprove");

        String		orgId = (String)incomingRequest.get("organizationId");

        appUdfName = (String[]) incomingRequest.get("appUdfName") ;
        appUdfSection = (String[]) incomingRequest.get("appUdfSection") ;
        appUdfType = (String[]) incomingRequest.get("appUdfType") ;
        appUdfColumn = (String[]) incomingRequest.get("appUdfColumn") ;
        appUdfValue = new String[appUdfColumn.length] ;

        userDateFormat = (String) incomingRequest.get("userDateFormat");
        userTimeZone = (String) incomingRequest.get("userTimeZone");
        
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(orgId).getProperty("MISC", "DateFormat", "MM-dd-yyyy");
        }
        
        ArrayList  result = new ArrayList() ;

        List ruleList =  (List) incomingRequest.get("appRuleList") ;
        List dataList = (List) incomingRequest.get("dataList") ;

    	List newRuleList = new ArrayList() ;
    	StringBuffer	ruleString = new StringBuffer() ;

    	boolean ruleOk[] = new boolean[dataList.size()]   ;

        try
        {
            CurrencyManager currencyManager = CurrencyManager.getInstance(orgId);

        	int ruleCount = ruleList.size() ;
	        for (int ix = 0; ix < ruleCount; ix++ ) {

	        	AppRule appRule = (AppRule) ruleList.get(ix) ;
	        	AppRulePK appRulePK = appRule.getComp_id() ;

	        	boolean passed = false  ;

	        	int dataCount = dataList.size() ;

	        	/* Start Rule Loop */

	        	for (int cx = 0; cx < dataCount; cx++) {
	        		List rowList = (List) dataList.get(cx) ;
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

		        		String ruleValue = appUdfValue[ux].trim() ;

		        		for (int bx = 0; bx < rowList.size(); bx++) {
		        			Hashtable htColumn = (Hashtable) rowList.get(bx) ;

		        			String	ruleDataName = (String) htColumn.get("name") ;
		        			if (! ruleDataName.equalsIgnoreCase(appUdfColumn[ux])) {
		        				continue;
		        			}

		        			String dataValue = (String) htColumn.get("value") ;
		        			if (dataValue == null) dataValue = "";
		        			dataValue = dataValue.trim() ;

		        			passed = false ;

			        		if (ruleValue.equals("*")) {
			        			passed = true ;
			        		} else if (ruleValue.equals("NB!") && !Utility.isEmpty(dataValue)) {
			        		    passed = true;
			        		} else if (ruleValue.startsWith("%") && ruleValue.endsWith("%")) {
		                		String temp = ruleValue ;
		                		temp = temp.substring(1);
		                		if (!HiltonUtility.isEmpty(temp)) {
		                			temp = temp.substring(0,temp.length()- 1) ;
		                		}
		                		passed = (dataValue.toUpperCase().indexOf(temp.toUpperCase()) >= 0) ;
			        		} else if (ruleValue.startsWith("%")) {
		                		String temp = ruleValue.substring(1);
		                		passed = dataValue.endsWith(temp) ;
			        		} else if (ruleValue.endsWith("%")) {
		                		String temp = ruleValue.substring(0,ruleValue.length()-1);
		                		passed = dataValue.startsWith(temp) ;
			        		} else if (ruleValue.equalsIgnoreCase(dataValue)) {
			        			passed = true ;
			        		} else {
			        			passed = false ;
			        		}
			        		if (! passed) {
			        			break ;
			        		}
			        		appUdfValue[ux] = dataValue;
		        		}  // Next bx

		        		if (! passed) {
		        			break ;
		        		}

		        		ruleString.append("[");
		        		ruleString.append(appUdfName[ux]);
		        		ruleString.append("=");
		        		ruleString.append(appUdfValue[ux]);
						ruleString.append("]");
		        	}   // Next ux

		        	if ( passed) {
		        		// Add to list
		        	    // First get a copy of appRule so values such as ruleData do not get overwritten for all records (stored in the list by reference)
		        	    Map copyParameters = new  HashMap();
		        	    copyParameters.put("appRule", appRule);

		        	    AppRuleCopy copyTask = new AppRuleCopy();
		        	    AppRule appRuleToAdd = (AppRule) copyTask.executeTask(copyParameters);
		        	    String approver = appRuleToAdd.getComp_id().getUserId() ;
		        	    appRuleToAdd.setRuleData(ruleString.toString()) ;

		                if(approver.startsWith("@")) {
		                	// Just add the POOL record
		                    appRuleToAdd.setUserAmount(appRule.getAmount()) ;
		                    appRuleToAdd.setUserCallForward(approver) ;
		                    newRuleList.add(appRuleToAdd) ;
		                    ruleOk[cx] = true ;
		        		} else {
		                	UserProfile appRuleUser = UserManager.getInstance().getUser(orgId, appRuleToAdd.getComp_id().getUserId());
		                	String userCurrencyCode = currencyManager.getCurrencyCodeForCountry(appRuleUser.getLocale());

		                	if (appRuleUser.isRegistered() && appRuleUser.getStatus().equals("02"))
		                	{
			    	        	BigDecimal approverAmount = currencyManager.convertPriceToBaseCurrency(appRuleUser.getApprovalAmount(), userCurrencyCode);
			                    BigDecimal approverMin = appRuleUser.getExcludeLess() ;

			                    String byRuleLine  = appRuleUser.getApproveByLine().toUpperCase();
			                    String callForward = appRuleUser.getCallForward() ;
			                    
			                    Date forwardOffDate = appRuleUser.getForwardOffDate();
			                    Date today = Dates.getDate(Dates.today(this.userDateFormat, this.userTimeZone));
			                    
			                    if(forwardOffDate != null && HiltonUtility.ckNull(forwardOffDate).after(today)){
				                    if (Utility.isEmpty(callForward)) {
				                    	callForward = appRuleToAdd.getComp_id().getUserId() ;
				                    }
			                    } else {
			                    	callForward = appRuleToAdd.getComp_id().getUserId() ;
			                    }

			                    if (byRuleLine.equals("Y")) {
			                    	approverMin = appRuleToAdd.getExcludeLess() ;
			                    	approverAmount = currencyManager.convertPriceToBaseCurrency(appRuleToAdd.getAmount(), userCurrencyCode);
			                    }
			                    appRuleToAdd.setUserName(appRuleUser.getDisplayName());
			                    appRuleToAdd.setUserAmount(approverAmount) ;
			                    appRuleToAdd.setUserExcludeLess(approverMin) ;
			                    appRuleToAdd.setUserCallForward(callForward);
			                    if (callForward.equals(appRuleToAdd.getComp_id().getUserId())) {
			                    	appRule.setUserName(appRuleUser.getFirstName().trim() + " " + appRuleUser.getLastName().trim()) ;
			                    } else {
			                    	appRuleUser = UserManager.getInstance().getUser(orgId, callForward);
			                    	appRule.setUserName(appRuleUser.getFirstName().trim() + " " + appRuleUser.getLastName().trim()) ;
			                    }
			                    ruleOk[cx] = true ;
			                    
			                    // ***ISSUE BCBSRI - 375.
			                    if (requisitionType != null && requisitionType.equals(RequisitionType.ADMIN_CHECK_REQUEST)) {
			                    	if (appRuleUser.getBuyer().equals("Y") && approverAmount.equals(new BigDecimal("0"))){
			                    		continue;
			                    	}
			                    }
			                    
			                    newRuleList.add(appRuleToAdd) ;
		                	}
		                	else
		                	{
		                	//	ruleOk[cx] = false ;
		                	}
		                	/*doing it in the next task for each account.
		                	 * if (appRule.getUserAmount().compareTo(amtToApprove) >= 0) {
		                		ruleOk[cx] = true ;
		                	}*/
	                	}
		        	}
        	    }   // Next cx
	        } // Next ix

	        ApprovalRuleAmountComparator amtComparator = new ApprovalRuleAmountComparator();
	        amtComparator.setOrder("A");
	        Collections.sort(newRuleList, amtComparator);

	        List userDefinedRuleList = new ArrayList();
	        boolean userDefinedApprovals = PropertiesManager.getInstance(orgId).getProperty("APPROVALS", "UserDefinedApprovals", "N").equals("Y");

	        /* Verify All Rules Passed */
	        StringBuffer ruleError = new StringBuffer("") ;
	        boolean passed = true ;
	        for (int i = 0;i < ruleOk.length;i++) {
	        	if (! ruleOk[i]) {
	        		List rowList = (List)dataList.get(i) ;
	        		if (passed && !userDefinedApprovals) {
	        			// First error
	        			ruleError.append("No approvers are authorized to approve the following rule(s):") ;
	        			ruleError.append("\n<BR><BR>") ;
	        		}
	        		for (int x=0;x < rowList.size();x++) {
	        		    Hashtable column = (Hashtable) rowList.get(x);
	        		    String	ruleText = "[" + (String) column.get("label") + "=" + (String) column.get("value") + "]";

	        		    if (userDefinedApprovals) {
	        		        userDefinedRuleList.add(ruleText);
	        		    }
	        		     else {
			            	ruleError.append(ruleText);
	        		     }
	        		}
	            	ruleError.append("\r\n<BR>") ;
	        		passed = false ;
	        	}
	        }

	        String errorString = Utility.ckNull((String) incomingRequest.get("ruleStatus"));
	        String ruleStatus = Utility.ckNull((String) incomingRequest.get("ruleStatus"));
	        if ( Utility.isEmpty(errorString) && !ruleStatus.equals("FAILED") )
	        {
		        if (passed) {
		        	incomingRequest.put("ruleError","") ;
		        	incomingRequest.put("ruleStatus","PASSED") ;
		        } else if (userDefinedApprovals) {
		        	incomingRequest.put("ruleError","") ;
		        	incomingRequest.put("ruleStatus","USER-DEFINED") ;
		        	incomingRequest.put("userDefinedRuleList", userDefinedRuleList);
		        } else {
		        	incomingRequest.put("ruleError",ruleError.toString()) ;
		        	incomingRequest.put("ruleStatus","FAILED") ;
		        }
	        }

			this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
			this.status = Status.FAILED;
			throw new TsaException("Routing List could not be generated", e);
        }

        return newRuleList ;
	}
}
