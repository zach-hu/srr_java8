/*
 * Created on Nov 24, 2004
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class ApprovalLogListBuild extends Task
{
    private List rulList = null;
    private List extList = null;
    private List rioList = null;
    private List amsList = null;
    private List approvalLogList = new ArrayList();
    private ApprovalLog lastUserLog = null;

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String	reqAction = (String)incomingRequest.get("reqaction") ;
            String firstFyi = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("APPROVALS","FYIFIRSTAPPROVALS","N");

            if (reqAction == null) {
                reqAction = "";
            }

            this.setRulList((List)incomingRequest.get("rulList"));
            this.setExtList((List)incomingRequest.get("extList"));
            this.setRioList((List)incomingRequest.get("rioList"));
            this.setAmsList((List)incomingRequest.get("amsList"));

            int byPassRule = this.byPassRules();
            if(byPassRule > -1)
            {
                incomingRequest.put("ruleStatus", "PASSED");
                incomingRequest.put("byPassRoutingList", "true");
                return new ArrayList();
            }

            int finalUser = this.findFinalUser();
            if(finalUser == -1)
            {
            	int lastUser = this.findLastUser();
            	String organizationId = (String)incomingRequest.get("organizationId");
            	if(firstFyi.equalsIgnoreCase("Y"))
            	{
            		ApprovalLogRuleTypeComparator authorityComparator = new ApprovalLogRuleTypeComparator();
            		authorityComparator.setOrder("A");
            		if(this.extList != null)
                    {
                        Collections.sort(this.extList, authorityComparator);
                    }
                    if(this.rulList != null)
                    {
                    	Collections.sort(this.rulList, authorityComparator);
                    }
            	}
            	else
            	{
            		ApprovalLogAuthorityComparator authorityComparator = new ApprovalLogAuthorityComparator();
            		authorityComparator.setOrder("A");
            		if(this.extList != null)
                    {
                        Collections.sort(this.extList, authorityComparator);
                    }
                    if(this.rulList != null)
                    {
                    	Collections.sort(this.rulList, authorityComparator);
                    }
                    if(this.amsList != null)
                    {
                    	Collections.sort(this.amsList, authorityComparator);
                    }
            	}

                int rulOrder = this.getRulOrder("RULORDER", organizationId, "10");
                int extOrder = this.getRulOrder("EXTORDER", organizationId, "20");
                int rioOrder = this.getRulOrder("RIOORDER", organizationId, "30");
                int amsOrder = this.getRulOrder("AMSORDER", organizationId, "40");

                if ((rulOrder < extOrder) && (rulOrder < rioOrder))
                {
                    if (extOrder < rioOrder)
                    {
                        this.joinLists(this.rulList, this.extList, this.rioList, this.amsList);
                    }
                    else
                    {
                        this.joinLists(this.rulList, this.rioList, this.extList, this.amsList);
                    }
                }
                else if((rioOrder < rulOrder) && (rioOrder < extOrder))
                {
                    if (rulOrder < extOrder)
                    {
                        this.joinLists(this.rioList, this.rulList, this.extList, this.amsList);
                    }
                    else
                    {
                        this.joinLists(this.rioList, this.extList, this.rulList, this.amsList);
                    }
                }
                else if((extOrder < rulOrder) && (extOrder < rioOrder))
                {
                    if (rulOrder < rioOrder)
                    {
                        this.joinLists(this.extList, this.rulList, this.rioList, this.amsList);
                    }
                    else
                    {
                        this.joinLists(this.extList, this.rioList, this.rulList, this.amsList);
                    }
                }

/*
                if(rulOrder < extOrder)
                {
                    this.joinLists(this.rulList, this.extList);
                }
                else
                {
                    this.joinLists(this.extList, this.rulList);
                }
*/
                if (lastUser > -1)
            	{
                	if (this.lastUserLog != null)
                	{
                		this.approvalLogList.add(this.lastUserLog);
                	}
            	}
            }
            else
            {//bypass rules. This rules set an Approver that overwrittes any other approver
                this.approvalLogList.add(this.rulList.get(finalUser));
                incomingRequest.put("ruleStatus", "PASSED");
            }

           this.setSequence();

//           if (approvalLogList.size() > 0 && reqAction.equalsIgnoreCase("forward")) {
//           		/* Initial Forward - Set Current Approver*/
//           		ApprovalLog appLog = (ApprovalLog)this.approvalLogList.get(0);
//           		appLog.setApproved("A") ;
//           		appLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
//           }

           ret = this.approvalLogList;
            this.setStatus(Status.SUCCEEDED);
        }

        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }

    public int getRulOrder(String rulType, String organizationId, String defaultOrder)
    {
        String order = PropertiesManager.getInstance(organizationId).getProperty("MISC", rulType, defaultOrder);
        return Integer.parseInt(order);
    }

    public void setSequence()
    {
        for(int i = 0; i < this.approvalLogList.size(); i++)
        {
            ApprovalLog userLog = (ApprovalLog) this.approvalLogList.get(i);
            ApprovalLogPK pk = userLog.getComp_id();
            pk.setSequence(new BigDecimal(i +1));
            userLog.setComp_id(pk);
        }
    }

    public void joinLists(List list1, List list2, List list3, List list4)
    {
        Map mappedUsers = new HashMap();
        if (list1 != null)
        {
            list1 = this.cleanList(list1, null);
        }
        if (list2 != null)
        {
            list2 = this.cleanList(list2, null);
        }
        if (list3 != null)
        {
            list3 = this.cleanList(list3, null);
        }
        if (list4 != null)
        {
        	list4 = this.cleanList(list4, null);
        }
        if (list1 != null)
        {
            this.approvalLogList.addAll(list1);
        }
        if (list2 != null)
        {
            this.approvalLogList.addAll(list2);
        }
        if (list3 != null)
        {
            this.approvalLogList.addAll(list3);
        }
        if (list4 != null)
        {
        	this.approvalLogList.addAll(list4);
        }
    }

    public void joinLists(List startList, List endList)
    {
        Map mapedUsers = new HashMap();
        if(startList != null)
        {
            this.cleanList(startList, mapedUsers);
        }
        if(endList != null)
        {
            this.cleanList(endList, mapedUsers);
        }
        if(startList != null)
        {
            this.approvalLogList.addAll(startList);
        }
        if(endList != null)
        {
            this.approvalLogList.addAll(endList);
        }
    }

    public List cleanList(List listToClean, Map usersMap)
    {
        if(usersMap == null)
        {
            usersMap = new HashMap();
        }

        for(int i = (listToClean.size() - 1); i >= 0; i--)
        {
            ApprovalLog approvalLog = (ApprovalLog) listToClean.get(i);
            String userId = approvalLog.getComp_id().getUserId();
            String	rule = approvalLog.getUdfValues();
            String	poolId = approvalLog.getPoolid();
            String fyi = approvalLog.getFyiApprover();

            if(usersMap.containsKey(userId + "-" + rule + "-" + poolId + "-" + fyi))
            {
    			listToClean.remove(i);
            }
            else
            {
            	usersMap.put(userId + "-" + rule + "-" + poolId + "-" + fyi, approvalLog);
            }
        }

        return listToClean;
    }

    public int byPassRules()
    {
        if(this.rulList != null)
        {
            for(int i = 0; i < this.rulList.size(); i++)
            {
                ApprovalLog log = (ApprovalLog)this.rulList.get(i);
                String action = log.getRuleAction();
                if(action.equalsIgnoreCase("Z"))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public int findFinalUser()
    {
        if(this.rulList != null)
        {
            for(int i = 0; i < this.rulList.size(); i++)
            {
                ApprovalLog log = (ApprovalLog)this.rulList.get(i);
                String action = log.getRuleAction();
                if(action.equalsIgnoreCase("B"))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public int findLastUser()
    {
        if(this.rulList != null)
        {
            for(int i = 0; i < this.rulList.size(); i++)
            {
                ApprovalLog log = (ApprovalLog)this.rulList.get(i);
                String action = log.getRuleAction();
                if(action.equalsIgnoreCase("L"))
                {
                	this.lastUserLog = log;
                	this.rulList.remove(i);
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * @return Returns the extList.
     */
    public List getExtList()
    {
        return extList;
    }
    /**
     * @param extList The extList to set.
     */
    public void setExtList(List extList)
    {
        this.extList = extList;
    }
    /**
     * @return Returns the rulList.
     */
    public List getRulList()
    {
        return rulList;
    }
    /**
     * @param rulList The rulList to set.
     */
    public void setRulList(List rulList)
    {
        this.rulList = rulList;
    }
    /**
     * @return Returns the rioList.
     */
    public List getRioList()
    {
        return rioList;
    }
    /**
     * @param rioList The rioList to set.
     */
    public void setRioList(List rioList)
    {
        this.rioList = rioList;
    }
    /**
     * @return the amsList
     */
    public List getAmsList()
    {
    	return amsList;
    }
    /**
     * @param amsList The amsList to set.
     */
    public void setAmsList(List amsList)
    {
    	this.amsList = amsList;
    }
}
