/*
 * Created on Nov 19, 2004
 */
package com.tsa.puridiom.approvals.ext.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.approvals.tasks.ApproverRetrieveByUserType;
import com.tsa.puridiom.apprulesext.tasks.ExtendedRulesList;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class AppRulesExtTask extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        List appLogList = new ArrayList();
        Map incomingRequest = (Map)object;
        try
        {
            String organizationId = (String)incomingRequest.get("organizationId");
            String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
            String filename = this.getProcessFilename();
			incomingRequest.put("isAppExtRules", "true");

			if (filename.indexOf(".xml") < 0)
			{
				filename = filename + ".xml";
			}

            ExtendedRulesList list = new ExtendedRulesList(filename, incomingRequest);
            List rulesList = list.getRules();

            for(int i = 0; i < rulesList.size(); i++)
            {
                String ruleName = (String)rulesList.get(i);

                if (list.getResult(ruleName))
	            {
                    BigDecimal defaultRuleOrder = null;
                    BigDecimal bdZero = new BigDecimal(0);

                    try {
                        defaultRuleOrder = new BigDecimal(PropertiesManager.getInstance(organizationId).getProperty("MISC", "RULORDER", "10"));
                    } catch (Exception bde) {
                        defaultRuleOrder = new BigDecimal(0);
                    }

                    ApprovalLog appLog = this.setupNewApprovalLog(list, ruleName, icHeader, defaultRuleOrder, true);

                    boolean setUserApproverAmount = true;
                    BigDecimal approverAmount = list.getApproverAmount (ruleName);
                    if (approverAmount != null) {
                        appLog.setApproverAmount(approverAmount);
                        setUserApproverAmount = false;
                    }

	                String approver = list.getApprover(ruleName);
	                if(approver.startsWith("@"))
	                {
                    	AppPoolRetrieveById retrievePoolTask = new AppPoolRetrieveById();
                    	incomingRequest.put("AppPool_poolid", approver.substring(1));
                    	incomingRequest.put("AppPooluser_poolid", approver.substring(1));
                    	AppPool appPool = (AppPool) retrievePoolTask.executeTask(incomingRequest);

	                    AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
	                    List poolUsersList = (List)task.executeTask(incomingRequest);
	                    for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++)
	                    {
	                        AppPooluser poolUser = (AppPooluser)poolUsersList.get(poolIndex);
	                        String tempApprover = poolUser.getComp_id().getUserId();
	                        appLog.setApproverType("P");
	                        appLog.setPoolid(approver.substring(1));
	                        appLog.setPooldesc(appPool.getPooldesc());
	                        this.addApprover(tempApprover, appLog, organizationId, setUserApproverAmount);
	                        appLogList.add(appLog);
	                    }
	                }
	                else if (approver.startsWith("#"))
	                {
                        RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
                        BigDecimal requiredAuthority = (BigDecimal) requisitionHeader.getTotal();
                        List accountList = (List) incomingRequest.get("accountList");
                        List vpApprovalLogList = new ArrayList();
                        Map vpApprovalLog = new HashMap();

                        if (accountList != null) {
                            List costCenterList = new ArrayList();
                            for (int ia = 0; ia < accountList.size(); ia++) {
                                Account account = (Account) accountList.get(ia);
                                String costCenter = account.getFld2();

                                if (!costCenterList.contains(costCenter)) {
                                    // add to costCenterList so we don't retrieve vp approvers for this cost center again
                                    costCenterList.add(costCenter);

                                    ApproverRetrieveByUserType retrieveApproverTask = new ApproverRetrieveByUserType();
                                    incomingRequest.put("AppRule_udf1Code", costCenter);
                                    incomingRequest.put("AppRule_amount", requiredAuthority);
                                    List userList = (List) retrieveApproverTask.executeTask(incomingRequest);

                                    String additionalRuleNotes = "[Cost Center = " + costCenter + "]";
                                    String newRuleName = ruleName + additionalRuleNotes;
                                    String ruleText = list.getRuleText(ruleName);

                                    if (userList != null) {
                                        for (int iu = 0; iu < userList.size(); iu++) {
                                            String userId = (String) userList.get(iu);
                                            if (vpApprovalLog.containsKey(userId)) {
                                                ApprovalLog existingAppLog = (ApprovalLog) vpApprovalLog.get(userId);
                                                existingAppLog.setRuleNotes(existingAppLog.getRuleNotes() + additionalRuleNotes);
                                                vpApprovalLog.put(userId, existingAppLog);
                                            } else {
                                                list.copyRule(ruleName, newRuleName, ruleText + additionalRuleNotes);

                                                ApprovalLog generatedAppLog = this.setupNewApprovalLog(list, ruleName, icHeader, defaultRuleOrder, false);

                                                if (!setUserApproverAmount) {
                                                    appLog.setApproverAmount(approverAmount);
                                                }

                                                generatedAppLog.setApproverType("U");
                                                generatedAppLog.setPoolid(" ");
                                                generatedAppLog.setAmount(requiredAuthority);
                                                generatedAppLog.setUdfValues(ruleText);
                                                generatedAppLog.setRuleNotes(generatedAppLog.getRuleNotes() + additionalRuleNotes);

                                                vpApprovalLog.put(userId, generatedAppLog);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (vpApprovalLog.size() > 0) {
                            Set set = vpApprovalLog.keySet();
                            Iterator iterator = set.iterator();
                            while (iterator.hasNext()) {
                                String userId = (String) iterator.next();
                                ApprovalLog generatedAppLog = (ApprovalLog) vpApprovalLog.get(userId);

                                this.addApprover(userId, generatedAppLog, organizationId, setUserApproverAmount);

                                vpApprovalLogList.add(generatedAppLog);
                            }
                        } else {
                                this.addApprover("NONE FOUND", appLog, organizationId, false);
                                vpApprovalLogList.add(appLog);
                        }
                        appLogList.addAll(vpApprovalLogList);
                    }
	                else
	                {
	                    appLog.setApproverType("U");
	                    appLog.setPoolid(" ");
	                    this.addApprover(approver, appLog, organizationId, setUserApproverAmount);
	                    appLogList.add(appLog);
	                }
	            }

            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
   //         throw new TsaException(this.getName(), e);
            throw e;
        }
        incomingRequest.remove("isAppExtRules");
        return appLogList;
    }

    public void addApprover(String name, ApprovalLog appLog, String oid, boolean setUserApprovalAmount)
    {
        try
        {
        	String	callForward = null ;
	        UserProfile	user = UserManager.getInstance().getUser(oid,name);
	        ApprovalLogPK pk = appLog.getComp_id();
	        pk.setUserId(name);
	        appLog.setComp_id(pk);
	        if (setUserApprovalAmount) {
	            appLog.setApproverAmount(user.getApprovalAmount());
	        }
	        appLog.setExcludeLess(user.getExcludeLess());
	        callForward = user.getCallForward();
	        if (Utility.isEmpty(callForward)) {
	        	appLog.setCallForward(name) ;
	        } else {
	        	appLog.setCallForward(user.getCallForward());
	        }
	        appLog.setApproverName(user.getDisplayName());
	        appLog.setApproved("N") ;
	        appLog.setAuthorized("N") ;
	        appLog.setRuleSource("");
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }

    }

    private ApprovalLog setupNewApprovalLog(ExtendedRulesList list, String ruleName, String icHeader, BigDecimal defaultRuleOrder, boolean useRuleRequired) {
        ApprovalLog appLog = new ApprovalLog();
        ApprovalLogPK pk = new ApprovalLogPK();
        BigDecimal bdZero = new BigDecimal(0);
        String required = "N";

        pk.setIcHeader(new BigDecimal(icHeader));
        appLog.setRuleAction(ruleName);
        appLog.setComp_id(pk);
        appLog.setRuleType("RUL");

        String ruleAction = list.getRuleAction(ruleName) ;
        if (Utility.isEmpty(ruleAction)) {
            ruleAction = "A" ;
        }
        if (useRuleRequired) {
            required = list.getRequiredApprover(ruleName);
        }
        appLog.setRuleAction(ruleAction);
        appLog.setUdfValues(list.getRuleText(ruleName));
        appLog.setFyiApprover(list.getFyiApprover(ruleName));
        appLog.setRequiredApprover(required);
        appLog.setAdvisor(list.getAdvisor(ruleName));
        appLog.setRuleNotes(list.getNotes(ruleName));
        appLog.setAmount(list.getRequiredAuthority(ruleName));
        appLog.setRuleOrder(list.getRuleOrder(ruleName));

        if (appLog.getRuleOrder() == null || appLog.getRuleOrder().compareTo(bdZero) <= 0) {
            appLog.setRuleOrder(defaultRuleOrder);
        }

        return appLog;
    }
}
