package com.tsa.puridiom.approvals.tasks;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.AppRule;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class ApprovalBuildApproverList extends Task
{
	private static final String UP_TO_APPROVER = "U";
	private static final String ONLY_APPROVER = "O";
	private static final String ALL_APPROVER = "A";

    String 		appUdfName[] = null ;
    String		appUdfSection[] = null;
    String		appUdfType[] = null;
    String		appUdfColumn[] = null ;
    String		appUdfValue[] = null ;
    String		organizationId = "puridiom";
    String 		userDateFormat = "";  
	String 		userTimeZone = "";
	
    String 		firstFyi = "";
    List 			appLogList = new ArrayList();

    Map			incomingRequest	;
    CurrencyManager currencyManager;
    PropertiesManager propertiesManager;

	public Object executeTask(Object object) throws Exception
	{
		incomingRequest = (Map)object;

		currencyManager = CurrencyManager.getInstance((String) incomingRequest.get("organizationId"));
		propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

		appUdfName = (String[]) incomingRequest.get("appUdfName") ;
        appUdfSection = (String[]) incomingRequest.get("appUdfSection") ;
        appUdfType = (String[]) incomingRequest.get("appUdfType") ;
        appUdfColumn = (String[]) incomingRequest.get("appUdfColumn") ;
        appUdfValue = new String[appUdfColumn.length] ;
        List acctList = (List)incomingRequest.get("accountList");

        boolean listOk = true ;

		//boolean termApprove = propertiesManager.getProperty("APPROVALS","TermApprove","Y").equalsIgnoreCase("Y") ;
		String termApprove = propertiesManager.getProperty("APPROVALS","TermApprove","Y");
		this.firstFyi = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("APPROVALS","FYIFIRSTAPPROVALS","N");

        List ruleList = (List)incomingRequest.get("ruleList") ;

        String oid = (String)incomingRequest.get("organizationId");
        String formCurrencyCode = (String) incomingRequest.get("formCurrencyCode");
        String	ruleString = null ;
        String	ruleStatus = (String)incomingRequest.get("ruleStatus");
        String	approvalFormType = Utility.ckNull((String) incomingRequest.get("formtype"));
        String	formUserId = "";
        Object	header = null;
        List	lineItemList = null;
        BigDecimal	icHeader = new BigDecimal(0);
        BigDecimal	total = new BigDecimal(0);
        
        userDateFormat = (String) incomingRequest.get("userDateFormat");
        userTimeZone = (String) incomingRequest.get("userTimeZone");
        
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy");
        }
        
        if (approvalFormType.equals("IVC")) {
        	InvoiceHeader ivch = (InvoiceHeader) incomingRequest.get("invoiceHeader");
            icHeader = ivch.getIcIvcHeader();
            total = ivch.getInvoiceTotal();
            formUserId = ivch.getEnteredBy();
            header = ivch;
            lineItemList = (List)incomingRequest.get("invoiceLineList") ;
        } else if (approvalFormType.equals("PO")) {
        	PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
            icHeader = poh.getIcPoHeader();
            total = poh.getTotal();
            formUserId = poh.getBuyerCode();
            header = poh;
            lineItemList = (List)incomingRequest.get("poLineList") ;
        } else {
        	RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
            icHeader = rqh.getIcReqHeader();
            total = rqh.getTotal();
            formUserId = rqh.getRequisitionerCode();
            header = rqh;
            lineItemList = (List)incomingRequest.get("requisitionLineList") ;
        }
        String approvalSection = "APPROVALS";
        if (!approvalFormType.isEmpty()) {
            approvalSection = approvalFormType + " APPROVALS";
        }
        boolean userDefinedApprovals = PropertiesManager.getInstance(oid).getProperty(approvalSection, "UserDefinedApprovals", "N").equals("Y");
        boolean		ruleOk = true;

        this.organizationId = oid;

        try {
/*
 			// Build the Routing List
        	for (int ix = 0; ix < ruleList.size(); ix++) {
	        	AppRule appRule = (AppRule) ruleList.get(ix) ;
	        	AppRulePK appRulePK = appRule.getComp_id() ;

    			this.addApprover(icHeader,amtToApprove,appRule,"HDR") ;
    			ruleString = appRule.getRuleData();
    			BigDecimal	userAmount = appRule.getUserAmount() ;
        		if (userAmount.compareTo(amtToApprove) >= 0) {
        			ruleOk = true ;
//        			break;
        		}
        	}
*/
            if (Utility.isEmpty(formCurrencyCode)) {
                formCurrencyCode = currencyManager.getBaseCurrencyCode();
            }

			Map requiredAmounts = (Map) incomingRequest.get("requiredAmounts");
	        Map costCenterMap = this.getCostCenterLists(ruleList, requiredAmounts);
	        costCenterMap = this.sortListByAmounts(costCenterMap);
	        this.findApproverForCC(costCenterMap, String.valueOf(icHeader), termApprove, formUserId, requiredAmounts, formCurrencyCode, userDefinedApprovals);

	        this.status = Status.SUCCEEDED;
        }
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return appLogList ;
	}

	public Map getCostCenterLists(List ruleList, Map costCenterAmtToApprove)
	{
	    Map costCenterMap = new HashMap();

		Collections.sort(ruleList, new RuleDataComparator());

		for(int i = 0; i < ruleList.size(); i++)
		{
			AppRule appRule = (AppRule) ruleList.get(i) ;

			String ruleData = appRule.getRuleData();
			//String ruleData = appRule.getComp_id().getUdf1Code().trim();
			List cc = null;
			if(costCenterMap.containsKey(ruleData))
			{
				cc = (List)costCenterMap.get(ruleData);
			}
			else
			{
				cc = new ArrayList();
			}
			cc.add(appRule);
			costCenterMap.put(ruleData, cc);
		}
/*
		if (costCenterMap.containsKey("*"))
		{
		    List	starCostCenterList = (List) costCenterMap.get("*");
		    Set costCenterSet = costCenterMap.keySet();
		    Iterator iterator = costCenterSet.iterator();
		    while (iterator.hasNext())
		    {
		        String	costCenter = (String) iterator.next();
		        if (!costCenter.equals("*"))
		        {
		            List costCenterList = (List) costCenterMap.get(costCenter);
		            costCenterList.addAll(starCostCenterList);
		        }
		    }

		    if(costCenterMap.size() == 1)
		    {
		    	if(costCenterMap.containsKey("*"))
		    	{
		    		Set keys = costCenterAmtToApprove.keySet();
		    		List appRules = (List) costCenterMap.get("*");
		    		for (Iterator iter = keys.iterator(); iter.hasNext();)
		    		{
						String costCenter = (String) iter.next();
						List newRulesList = new ArrayList();
						newRulesList.addAll(appRules);
						costCenterMap.put(costCenter, newRulesList);
					}
		    	}
		    }

		    costCenterMap.remove("*");
		}

	    this.lookForWildcards(costCenterMap, costCenterAmtToApprove);
*/
		return costCenterMap;
	}

	/**
	 * <b>lookForWildcards</b> looks for a costcenter containg a <i>"%"</i><p>
	 * then it loops again and matches all costcenters and when it finds a match it adds the list of rules
	 * from the costcenter with the wildcard to the list of rules of the matched costcenter.
	 * @param costCenterMap key = costcenter, value=list of "rules" for this costcenter
	 */
	public void lookForWildcards(Map costCenterMap, Map costCenterAmtToApprove)
	{
		Iterator iterator = costCenterMap.keySet().iterator();
	    Map wildCardCostCenterMap = new HashMap();

	    while (iterator.hasNext())
	    {
	        String	costCenter = (String) iterator.next();
	        if (costCenter.indexOf("%") >= 0)
	        {
	            wildCardCostCenterMap.put(costCenter, costCenterMap.get(costCenter));
	            iterator.remove();
	        }
	    }

	    Iterator wildCardIterator = wildCardCostCenterMap.keySet().iterator();
	    iterator = costCenterAmtToApprove.keySet().iterator();

        while (wildCardIterator.hasNext())
	    {
	        String	wildCardCostCenter = (String) wildCardIterator.next();

		    while (iterator.hasNext())
		    {
		        String	costCenter = (String) iterator.next();

		        if (this.matchPattern(costCenter, wildCardCostCenter))
		        {
		            List costCenterList = (List) costCenterMap.get(costCenter);
		            if(costCenterList == null)
		            {
		            	costCenterList = new ArrayList();
		            }
	                List wildCardCostCenterList = (List) wildCardCostCenterMap.get(wildCardCostCenter);
	                costCenterList.addAll(wildCardCostCenterList);
	                costCenterMap.put(costCenter, costCenterList);
		        }
		    }
	    }
	}

	public boolean matchPattern(String costCenter, String wildCardCostCenter)
	{
		boolean matches = false;
		try
        {
			String patternString = wildCardCostCenter.replaceAll("%", ".*");

            // Compile regular expression
            Pattern pattern = Pattern.compile(patternString);

            // Replace all occurrences of pattern in input
            Matcher matcher = pattern.matcher(costCenter);
            matches = matcher.matches();
        }
        catch (Exception e)
        {
            matches = false;
        }
		return matches;
	}

	public Map sortListByAmounts(Map costCenterMap)
	{
		Set keys = costCenterMap.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			String ruleData = (String) iter.next();
			List approverByCC = (List)costCenterMap.get(ruleData);
			Collections.sort(approverByCC, new RuleListAmountComparator());
			costCenterMap.put(ruleData, approverByCC);
		}
		return costCenterMap;
	}

	public void findApproverForCC(Map costCenterMap, String icHeader, String flag, String requisitionerCode, Map costCenterAmtToApproveMap, String formCurrencyCode, boolean userDefinedApprovals)
	{
		StringBuffer ruleError = new StringBuffer();

		String oid = (String) incomingRequest.get("organizationId");
		Set keys = costCenterMap.keySet();
		boolean passed = true;
		boolean allowSelfApproval = PropertiesManager.getInstance(this.organizationId).getProperty("APPROVALS", "SELF-APPROVER", "Y").equals("Y");
		List userDefinedRuleList = new ArrayList();

		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			boolean ruleOk = false;
			String ruleData = (String) iter.next();
			BigDecimal	userAmount = new BigDecimal(0);

			BigDecimal amtToApprove = (BigDecimal)costCenterAmtToApproveMap.get(ruleData.trim());

			if (amtToApprove == null) amtToApprove = new BigDecimal("0") ;

			List approverByCC = (List)costCenterMap.get(ruleData);

			StringBuffer ruleText = new StringBuffer();

			for(int i = 0; i < approverByCC.size(); i++)
			{
				AppRule appRule = (AppRule) approverByCC.get(i) ;
				boolean isValidUserAmmount = true;

			    if (!Utility.ckNull(formCurrencyCode).equalsIgnoreCase(currencyManager.getBaseCurrencyCode())) {
			        appRule.setUserAmount(currencyManager.convertPriceFromBaseCurrency(appRule.getUserAmount(), formCurrencyCode));
			    }
				if (ruleOk && flag.equalsIgnoreCase("Y")) {
				    BigDecimal appRuleUserAmount = appRule.getUserAmount();

					if (appRuleUserAmount.compareTo(userAmount) != 0) {

						if (appRule.isARequiredApprover() && PropertiesManager.getInstance(oid).getProperty("REQ APPROVALS", "ALLOWREQUIREDAPPROVER", "N").equalsIgnoreCase("Y"))
						{
							isValidUserAmmount = false;
						} else
						{
							continue;
						}
					}
				}
				if(amtToApprove.compareTo(new BigDecimal(0)) <= 0 || amtToApprove.compareTo(appRule.getUserExcludeLess()) > -1)
				{
					this.addApprover(icHeader, amtToApprove, appRule, "HDR") ;

					if (isValidUserAmmount)
					{
						userAmount = appRule.getUserAmount();
					}

					if (userAmount.compareTo(amtToApprove) >= 0 && !appRule.isAnFyiApprover() && !appRule.isAnAdvisor() && (!appRule.getComp_id().getUserId().equalsIgnoreCase(requisitionerCode) || allowSelfApproval))
					{
						ruleOk = true;
						//i = approverByCC.size();
					}
				}
				ruleData = appRule.getRuleData();
			}
			ruleText.append(ruleData + "\n<BR>");

			if(!ruleOk)
			{
			    if (passed) {
			        // First error
			        ruleError.append("No approvers are authorized to approve the following rule(s):\n<BR><BR>");
			    }
				ruleError.append(ruleText);
				ruleError.append("\n<BR>");

				userDefinedRuleList.add(ruleText);

				passed =false;
			}
		}

        if (!passed) {
            if (userDefinedApprovals) {
                incomingRequest.put("ruleError","") ;
	        	incomingRequest.put("ruleStatus","USER-DEFINED") ;
	        	incomingRequest.put("userDefinedRuleList", userDefinedRuleList);
            } else {
                incomingRequest.put("ruleError",ruleError.toString()) ;
                incomingRequest.put("ruleStatus","FAILED") ;
            }
		}
	}

	public void addApprover(String icHeader, BigDecimal amtToApprove, AppRule appRule,String source)
    {
        try
        {
        	String oid = (String) incomingRequest.get("organizationId");
        	String approver = appRule.getComp_id().getUserId() ;
        	String overBudget = HiltonUtility.ckNull((String) incomingRequest.get("OverBudget"));
            if(approver.startsWith("@"))
            {
                String	poolId = approver.substring(1);    // Hack @
            	incomingRequest.put("AppPool_poolid",poolId) ;
            	incomingRequest.put("AppPooluser_poolid",poolId) ;
            	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
            	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);
/*
            	String pFlag = "P" ;
            	if (appPool != null) {
            		pFlag = appPool.getPoolflag1() ;
            	}
            	if (pFlag.equalsIgnoreCase("Y")) {
            		pFlag = "U" ;
            	} else {
            		pFlag = "P" ;
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
   	                appLog.setAmount(amtToApprove) ;

                	AppPooluser poolUser = (AppPooluser)poolUsersList.get(poolIndex);
                    String tempApprover = poolUser.getComp_id().getUserId();

                    appLog.setApproverType(pFlag);
                    appLog.setPoolid(poolId) ;
                    appLog.setPoolDesc(appPool.getPooldesc());

    	        	UserProfile pUser = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), poolUser.getComp_id().getUserId());
        	        if (pUser.getStatus().equals("02")) {
        	        	String callForward = pUser.getCallForward() ;
                        if (Utility.isEmpty(callForward)) {
                        	callForward = poolUser.getComp_id().getUserId() ;
                        }

       	                appLog.setCallForward(callForward) ;
       	            	appLog.setApproverAmount(appRule.getUserAmount()) ;
       	            	appLog.setCallForward(appRule.getUserCallForward()) ;
       	    	        pk.setUserId(tempApprover);
       	    	        appLog.setComp_id(pk);
       	                appLog.setApproverName(pUser.getDisplayName());
       	                appLog.setRuleSource(source);
       	    	        appLog.setApproved("N") ;
       	    	        appLog.setAuthorized("N") ;
       	    	        appLog.setRuleAction("A") ;
       	    	        appLog.setApproverSig("") ;
       	    	        appLog.setRuleType("EXT") ;
       	    	        appLog.setApproverNotes("") ;
       	    	        appLog.setUdfValues(appRule.getRuleData());
       	    	        appLog.setRequiredApprover(appRule.getRequiredApprover());
       	    	        appLog.setFyiApprover(appRule.getFyiApprover());
       	    	        appLog.setAdvisor(appRule.getAdvisor());
       	    	        appLog.setExcludeLess(appRule.getExcludeLess());
       	    	        appLogList.add(appLog) ;
        	        }
                }
*/
            	String pFlag = "P" ;
            	if (appPool != null) {
            		pFlag = appPool.getPoolflag1() ;
            	}

            	if (pFlag.equalsIgnoreCase("Y")) {
//            	if (appRule.isARequiredApprover())
//				{
					this.setApprovalPoolUsers(icHeader, amtToApprove, appRule, source, appPool);
				} 
            	else
				{
					// Add approval pool to approval_log
					ApprovalLog appLog = new ApprovalLog();
					ApprovalLogPK pk = new ApprovalLogPK();
					pk.setIcHeader(new BigDecimal(icHeader));
					pk.setUserId(poolId);
					appLog.setComp_id(pk);
					appLog.setAdvisor(appRule.getAdvisor());
					appLog.setAmount(amtToApprove);
					appLog.setApproved("N");
					appLog.setApproverAmount(appRule.getUserAmount());
					appLog.setApproverName(appPool.getPooldesc());
					appLog.setApproverNotes("");
					appLog.setApproverSig("");
					appLog.setApproverType("P");
					appLog.setAuthorized("N");
					appLog.setCallForward(appRule.getUserCallForward());
					appLog.setExcludeLess(appRule.getExcludeLess());
					if(overBudget.equalsIgnoreCase("[Budget=OverBudget]"))
		        	{
						appLog.setFyiApprover("N");
		        	}
					else
					{
						appLog.setFyiApprover(appRule.getFyiApprover());
					}
					appLog.setPoolid(poolId);
					appLog.setPooldesc(appPool.getPooldesc());
					appLog.setRequiredApprover(appRule.getRequiredApprover());
					appLog.setRuleAction("A");
					appLog.setRuleSource(source);
					if(this.firstFyi.equalsIgnoreCase("Y") && appRule.getFyiApprover().equalsIgnoreCase("N"))
					{
						appLog.setRuleType("RUL");
					}
					else
					{
						appLog.setRuleType("EXT");
					}
					appLog.setRuleOrder(new BigDecimal(propertiesManager.getProperty("MISC", "EXTORDER", "20")));
					appLog.setUdfValues(appRule.getRuleData());
					appLog.setRuleNotes(appRule.getNotes());

					appLogList.add(appLog);
				}
            }
            else
            {
    			// Add approver to approval_log
                ApprovalLog appLog = new ApprovalLog();
                ApprovalLogPK pk = new ApprovalLogPK();
                pk.setIcHeader(new BigDecimal(icHeader));
                pk.setUserId(approver);
                appLog.setComp_id(pk);
                appLog.setAdvisor(appRule.getAdvisor());
                appLog.setAmount(amtToApprove) ;
                appLog.setApproved("N") ;
                appLog.setApproverAmount(appRule.getUserAmount()) ;
            	appLog.setApproverName(appRule.getUserName());
            	appLog.setApproverNotes("") ;
    	        appLog.setApproverSig("") ;
            	appLog.setApproverType("U");
    	        appLog.setAuthorized("N") ;
            	appLog.setCallForward(appRule.getUserCallForward()) ;
                appLog.setExcludeLess(appRule.getExcludeLess());
                if(overBudget.equalsIgnoreCase("[Budget=OverBudget]"))
	        	{
					appLog.setFyiApprover("N");
	        	}
				else
				{
					appLog.setFyiApprover(appRule.getFyiApprover());
				}
                appLog.setPoolid(" ") ;
                appLog.setPooldesc("");
                appLog.setRequiredApprover(appRule.getRequiredApprover());
                appLog.setRuleAction("A") ;
    	        appLog.setRuleSource(source);
    	        if(this.firstFyi.equalsIgnoreCase("Y") && appRule.getFyiApprover().equalsIgnoreCase("N"))
				{
					appLog.setRuleType("RUL");
				}
				else
				{
					appLog.setRuleType("EXT");
				}
    	        appLog.setRuleOrder(new BigDecimal(propertiesManager.getProperty("MISC", "EXTORDER", "20")));
    	        appLog.setUdfValues(appRule.getRuleData());
                appLog.setRuleNotes(appRule.getNotes());

    	        appLogList.add(appLog) ;
            }

        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }

    public Map getAmtToApprove(List acctList)
    {
    	Map costCenterAmtToApprove = new HashMap();
    	List flds = new ArrayList();
    	for(int iSection = 0; iSection < this.appUdfSection.length; iSection++)
    	{
    		String temp = Utility.ckNull(this.appUdfSection[iSection]);
    		if(temp.equals("account"))
    		{
    			flds.add(Utility.ckNull(this.appUdfColumn[iSection]));
    		}
    	}

    	for(int iAcc = 0; iAcc < acctList.size(); iAcc++)
    	{
    		String acctUdfValue = "";
    		Account account = (Account)acctList.get(iAcc);
    		for(int iUdf = 0; iUdf < flds.size(); iUdf++)
    		{
    			acctUdfValue = acctUdfValue + this.getValueFromMethod(account, (String)flds.get(iUdf));
    		}


    		BigDecimal amt = new BigDecimal(0);
			if(costCenterAmtToApprove.containsKey(acctUdfValue))
			{
				amt = (BigDecimal)costCenterAmtToApprove.get(acctUdfValue);
				amt = amt.add(account.getAllocAmount());
			}
			else
			{
				amt = account.getAllocAmount();
			}
			costCenterAmtToApprove.put(acctUdfValue, amt);
    	}

    	return costCenterAmtToApprove;
    }

    public Object getValueFromMethod(Object obj, String methodName)
    {
        Object result = null;
        methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1, methodName.length());
        try
        {
            if (obj != null)
            {
                Class c = obj.getClass();
                Method method = c.getMethod(methodName, null);
                result = method.invoke(obj, null);
                Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromMethod");
            exception.printStackTrace();
        }
        return result;
    }

    private void setApprovalPoolUsers(String icHeader, BigDecimal amtToApprove, AppRule appRule, String source, AppPool appPool) throws Exception
	{
		AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
		List poolUsersList = (List) task.executeTask(this.incomingRequest);

		if ((poolUsersList != null) && (!poolUsersList.isEmpty()))
		{
			for (Iterator iterator = poolUsersList.iterator(); iterator.hasNext();)
			{
				AppPooluser poolUser = (AppPooluser) iterator.next();
				String userId = poolUser.getComp_id().getUserId();
				UserProfile pUser = UserManager.getInstance().getUser(organizationId, userId);

				if (pUser.getStatus().equals("02"))
				{
					// Add approver to approval_log
					ApprovalLog appLog = new ApprovalLog();
					ApprovalLogPK pk = new ApprovalLogPK();
					String callForward = pUser.getCallForward();

					Date forwardOffDate = pUser.getForwardOffDate();
                    Date today = Dates.getDate(Dates.today(this.userDateFormat, this.userTimeZone));
                    
                    if(forwardOffDate != null && HiltonUtility.ckNull(forwardOffDate).after(today)){
						if (HiltonUtility.isEmpty(callForward))
						{
							callForward = userId;
						}
                    } else {
                    	callForward = userId;
                    }

					pk.setIcHeader(new BigDecimal(icHeader));
					pk.setUserId(userId);

					appLog.setComp_id(pk);

					appLog.setAdvisor(appRule.getAdvisor());
					appLog.setAmount(amtToApprove);
					appLog.setApproved("N");
					appLog.setApproverAmount(appRule.getUserAmount());
					appLog.setApproverName(pUser.getDisplayName());
					appLog.setApproverNotes("");
					appLog.setApproverSig("");
					appLog.setApproverType("U");
					appLog.setAuthorized("N");
					appLog.setCallForward(callForward);
					appLog.setExcludeLess(appRule.getExcludeLess());
					appLog.setFyiApprover(appRule.getFyiApprover());
					appLog.setPoolid(poolUser.getComp_id().getPoolid());
					appLog.setPooldesc(appPool.getPooldesc());
//					appLog.setRequiredApprover(appRule.getRequiredApprover());
					appLog.setRequiredApprover("Y");
					appLog.setRuleAction("A");
					appLog.setRuleSource(source);
					appLog.setRuleType("EXT");
					appLog.setRuleOrder(new BigDecimal(propertiesManager.getProperty("MISC", "EXTORDER", "20")));
					appLog.setUdfValues(appRule.getRuleData());
					appLog.setRuleNotes(appRule.getNotes());

					appLogList.add(appLog);
				}
			}
		}
	}

}