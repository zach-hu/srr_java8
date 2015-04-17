package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppRule;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class SetupApproverApprovalRules extends Task
{
	List toList = new ArrayList() ;
	
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String oid = (String)incomingRequest.get("organizationId") ;
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			List rqlList = (List) incomingRequest.get("requisitionLineList");
			String ruleError = HiltonUtility.ckNull((String) incomingRequest.get("ruleError"));
			String noApprovalNeed = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeed"));
			String belowNoApproval = HiltonUtility.ckNull((String) incomingRequest.get("BelowNoApproval"));
			String buyerCommodityCode = HiltonUtility.ckNull((String) incomingRequest.get("BuyerCommodityCode"));
			String noApprovalNeedWithOtherApproval = HiltonUtility.ckNull((String) incomingRequest.get("ApprovalNeedWithOtherApproval"));
			String rqSubType = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionHeader_rqSubType"));
			List approvalRuleList =  new ArrayList();
			approvalRuleList =(List) incomingRequest.get("approvalRulesList");
			List routingList = new ArrayList();
			if (incomingRequest.containsKey("routingList")) {
                // Used when generating approval routing list
                routingList = (List) incomingRequest.get("routingList");
            } else {
                // Used when retrieving approval routing list
                routingList = (List) incomingRequest.get("approvalLogList") ;
            }
			BigDecimal icHeader = new BigDecimal(icReqHeader) ;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
			String 	appUdfName[] = new String[10];
			String	appUdfColumn[] = new String[10];
			String appUdfSection[] = new String[10];
			String	appUdfType[] = new String[10];
            
			for (int cnt = 0; cnt < 10; cnt++ ) {
	    		String appTemp = propertiesManager.getProperty("APPROVALS", "AppUdf" + Integer.toString(cnt + 1),"") ;

	    		// Remove Quotes
	    		appTemp = appTemp.replaceAll("\"","");

	    		if (! Utility.isEmpty(appTemp)) {
	                //Pick out the parameters
	                StringTokenizer st = new StringTokenizer(appTemp,",");
	                for (int ix = 1;st.hasMoreElements();ix++) {
	                	appUdfName[cnt] = st.nextToken() ;
	                	appUdfSection[cnt] = st.nextToken() ;
	                	appUdfColumn[cnt] = st.nextToken() ;
	                	appUdfType[cnt] = st.nextToken();
	                	if (ix == 4) { break ; }
	                }
	            } else {
                	appUdfName[cnt] = null ;
                	appUdfSection[cnt] = null;
                	appUdfColumn[cnt] = null ;
                	appUdfType[cnt] = null ;
	            }
	        }

			/* Expects incoming request to contain approvalLog */
			if((rqh.getRequisitionerCode() != null) && (noApprovalNeed.equalsIgnoreCase("Y") || belowNoApproval.equalsIgnoreCase("Y")) && !buyerCommodityCode.equalsIgnoreCase("Y"))
			{
				String approverId = "";
				approverId = rqh.getRequisitionerCode();
				String nameApprover = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
				String udfValue = "No Approval Necessary";
				String approver = "Y";
				String fyiApprover = "N";
				String advisor = "N";
				
				this.addApprover(icHeader, approverId, nameApprover, fyiApprover,advisor, udfValue, approver);

				this.status = Status.SUCCEEDED;
			}
			else if((rqh.getBuyer() != null) && buyerCommodityCode.equalsIgnoreCase("Y"))
			{
				List buyers = new ArrayList() ;
				for (Iterator it = rqlList.iterator(); it.hasNext();)
				{
					RequisitionLine rql = (RequisitionLine) it.next();
					String queryString = "from Commodity as Commodity where Commodity = (select RequisitionLine.commodityCode from RequisitionLine as RequisitionLine where RequisitionLine.icReqLine = ?)";
                	List resultList = dbs.query(queryString, new Object[] { rql.getIcReqLine() },new Type[] { Hibernate.BIG_DECIMAL});
                	if ( resultList != null || resultList.size() > 0  )
    				{
                		Commodity Commodity = (Commodity) resultList.get(0);
                		String BuyerCode = Commodity.getBuyerCode().substring(0, 6);
                		buyers.add(BuyerCode);
    				}
				}
				for(int ii = 0; ii < buyers.size(); ii++)
				{
				    String buyer = (String) buyers.get(ii);
				    for(int ji = ii+1; ji < buyers.size(); ji++ )
				    {
				    	String buyersAux = (String) buyers.get(ji);
				        if (buyer.equalsIgnoreCase(buyersAux))
				        {
				        	buyers.remove(ji);
				        }
				    }
				}
				for(int x = 0; x < buyers.size(); x++)
				{
					String approverId = (String)buyers.get(x);
					String nameApprover = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
					String udfValue = "Route to Buyer Based On Commodity";
					String approver = "N";
					String fyiApprover = "N";
					String advisor = "N";
					
					this.addApprover(icHeader, approverId, nameApprover, fyiApprover,advisor, udfValue, approver);
				}
				incomingRequest.put("ruleError","") ;
        		incomingRequest.put("ruleStatus","PASSED") ;
			}
			else if(noApprovalNeedWithOtherApproval.equalsIgnoreCase("Y"))
			{
				for (int ix = 0; ix < routingList.size(); ix++) 
				{
					/* Expects incoming request to contain approvalLog */
					ApprovalLogPK comp_id = new ApprovalLogPK();
					ApprovalLog	originalApprovalLog = (ApprovalLog) routingList.get(ix);
					this.existApprover(originalApprovalLog);
				}
				if(routingList.size() < 1 && HiltonUtility.isEmpty(rqSubType))
				{
					incomingRequest.put("RequisitionHeader_rqSubType", "WOA");
				}
				String queryString = "from Account as a where a.id.icHeader = ?";
                List accountList = dbs.query(queryString, new Object[] { rqh.getIcReqHeader() },new Type[] { Hibernate.BIG_DECIMAL});
                if ((approvalRuleList != null && approvalRuleList.size() > 0)) 
                {
                	for(int ii = 0; ii < approvalRuleList.size(); ii++)
        			{
        			    String removeApproval = (String) approvalRuleList.get(ii);
        			    for(int ji = ii + 1; ji < approvalRuleList.size(); ji++ )
        			    {
        			    	String removeApprovalAux = (String) approvalRuleList.get(ji);
        			        if (removeApproval.equalsIgnoreCase(removeApprovalAux))
        			        {
        			        	approvalRuleList.remove(ji);
        			        }
        			    }
        			}
                	for (Iterator it = approvalRuleList.iterator(); it.hasNext();) 
                	{
                		String accountString = (String) it.next();
                		 if ((accountList != null && accountList.size() > 0)) 
                		 {
     	                    for (Iterator it2 = accountList.iterator(); it2.hasNext();) 
     	                    {
     	                        Account account = (Account) it2.next();
     	                        String fld1 = account.getFld1();
     	                        String fld2 = account.getFld2();
     	                        String fld3 = account.getFld3();
     	                        String accountCompare = fld1 + "-" + fld2 + "-" + fld3;
     	                        if(accountString.equalsIgnoreCase(accountCompare))
     	                        {
     	                        	String approverId = "";
     	                        	approverId = rqh.getRequisitionerCode();
     	                        	String nameApprover = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
     	                        	String udfValue = "No Approval Necessary";
	     	           				String approver = "Y";
	     	           				String fyiApprover = "N";
	     	           				String advisor = "N";
     	                        	String commodityCode = "";
     	                        	String queryCommodity = "select rl.commodityCode from Account as ac, RequisitionLine as rl where ac.id.icLine = rl.icReqLine " +
     	                        		"and ac.id.icHeader = ? and ac.fld1 = ? and ac.fld2 = ? and ac.fld3 = ?";
     	                        	List resultList = dbs.query(queryCommodity, new Object[] { rqh.getIcReqHeader(), fld1, fld2, fld3 },new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING });
     	                        	if (resultList != null && resultList.size() > 0)
     	                        	{
     	                        		commodityCode = (String) resultList.get(0);
     	                        	}
     	                        	String approvalRuleValue =  "[" + appUdfName[0] + "=" + fld1 + "][" + appUdfName[1] + "=" + fld2 + "][" + appUdfName[2] + "=" + fld3 + "][" + appUdfName[3] + "=" + commodityCode + "]";
     	                        	String approvalRuleCommodity =  "[" + appUdfName[3] + "=" + commodityCode + "][" + appUdfName[0] + "=" + fld1 + "][" + appUdfName[1] + "=" + fld2 + "][" + appUdfName[2] + "=" + fld3 + "]";
     	                        	if(!HiltonUtility.isEmpty(ruleError))
     	                        	{
     	                        		String splitRuleError[] = ruleError.split("<BR>");
     	                        		String auxRuleError = "";
     	                        		String newRuleError = "";
     	                        		for (int c = 0; c < splitRuleError.length; c++)
     	                        		{
     	                        			auxRuleError = splitRuleError[c].trim();
     	                        			if(!approvalRuleValue.equalsIgnoreCase(auxRuleError) && !approvalRuleCommodity.equalsIgnoreCase(auxRuleError))
     	                        			{
     	                        				if(!HiltonUtility.isEmpty(auxRuleError))
     	                        				{
     	                        					newRuleError += auxRuleError + "<BR>";
     	                        				}
     	                        			}
     	                        		}
     	                        		if(newRuleError.equalsIgnoreCase("No approvers are authorized to approve the following rule(s):<BR>"))
     	                        		{
     	                        			ruleError = "";
     	                        		}
     	                        		else
     	                        		{
     	                        			ruleError = newRuleError;
     	                        		}
     	                        	}
     	                        	this.addWithOtherApprover(icHeader, approverId, nameApprover, fyiApprover,advisor, udfValue, approver, approvalRuleValue);
     	                        	break;
     	                        }
     	                    }
                		 }
                	}
                	if(HiltonUtility.isEmpty(ruleError))
        			{
                		incomingRequest.put("ruleError","") ;          
        			}
                	else
                	{
                		incomingRequest.put("ruleError",ruleError) ;
                	}
                	incomingRequest.put("ruleStatus","PASSED") ;
                }
			}
			for(int i = 0; i < toList.size(); i++)
	        {
	            ApprovalLog userLog = (ApprovalLog) toList.get(i);
	            ApprovalLogPK pk = userLog.getComp_id();
	            pk.setSequence(new BigDecimal(i +1));
	            userLog.setComp_id(pk);
	        }
			result = toList ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
	
	public void existApprover(ApprovalLog appLog)
    {
        try
        {
        	ApprovalLog approvalLog = new ApprovalLog();
			ApprovalLogPK comp_id = new ApprovalLogPK();
			comp_id.setIcHeader(appLog.getComp_id().getIcHeader());
			comp_id.setIcLine(appLog.getComp_id().getIcLine());
			comp_id.setSequence(appLog.getComp_id().getSequence());
			comp_id.setUserId(appLog.getComp_id().getUserId());
			approvalLog.setApproverName(appLog.getApproverName()) ;
			approvalLog.setAmount(appLog.getAmount());
			approvalLog.setApproverAmount(appLog.getApproverAmount());
			approvalLog.setApproved(appLog.getApproved());
			approvalLog.setUdfValues(appLog.getUdfValues());
			approvalLog.setAuthorized(appLog.getAuthorized());
			approvalLog.setAlternateUserid(appLog.getAlternateUserid());
			approvalLog.setDateAssigned(Dates.getDate(Dates.today("")));
			approvalLog.setApproverType(appLog.getApproverType());
			approvalLog.setRuleType(appLog.getRuleType());
			approvalLog.setRuleAction(appLog.getRuleAction());
			approvalLog.setCallForward(appLog.getCallForward());
			approvalLog.setApproverNotes(appLog.getApproverNotes());
			approvalLog.setFyiApprover(appLog.getFyiApprover());
			approvalLog.setExcludeLess(appLog.getExcludeLess());
			approvalLog.setBackupApprover(appLog.getBackupApprover());
			approvalLog.setAdvisor(appLog.getAdvisor());
			approvalLog.setRecommendation(appLog.getRecommendation());
			approvalLog.setComp_id(comp_id);
			toList.add(approvalLog) ;
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }
	
	public void addApprover(BigDecimal icHeader, String approverId, String nameApprover,String fyiApprover, String advisor,String udfValue,String approver)
    {
        try
        {
        	ApprovalLogPK comp_id = new ApprovalLogPK();
			ApprovalLog	approvalLog = new ApprovalLog();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(new BigDecimal(0));
			comp_id.setSequence(new BigDecimal(1));
			comp_id.setUserId(approverId);
			approvalLog.setApproverName(nameApprover) ;
			approvalLog.setAmount(new BigDecimal(1000000));
			approvalLog.setApproverAmount(new BigDecimal(1000000));
			approvalLog.setApproved(approver);
			approvalLog.setUdfValues(udfValue);
			approvalLog.setAuthorized("N");
			approvalLog.setAlternateUserid(approverId);
			approvalLog.setDateAssigned(Dates.getDate(Dates.today("")));
			approvalLog.setApproverType("U");
			approvalLog.setRuleType("RUL");
			approvalLog.setRuleAction("A");
			approvalLog.setCallForward(approverId);
			approvalLog.setApproverNotes("");
			approvalLog.setFyiApprover(fyiApprover);
			approvalLog.setExcludeLess(new BigDecimal("0"));
			approvalLog.setBackupApprover("");
			approvalLog.setAdvisor(advisor);
			approvalLog.setRecommendation("");
			approvalLog.setComp_id(comp_id);
			toList.add(approvalLog) ;
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }
	
	public void addWithOtherApprover(BigDecimal icHeader, String approverId, String nameApprover,String fyiApprover, String advisor,String udfValue,String approver, String approvalRuleValue)
    {
        try
        {
        	ApprovalLogPK comp_id = new ApprovalLogPK();
			ApprovalLog	approvalLog = new ApprovalLog();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(new BigDecimal(0));
			comp_id.setSequence(new BigDecimal(1));
			comp_id.setUserId(approverId);
			approvalLog.setApproverName(nameApprover) ;
			approvalLog.setAmount(new BigDecimal(1000000.01));
			approvalLog.setApproverAmount(new BigDecimal(1000000.01));
			approvalLog.setApproved(approver);
			approvalLog.setUdfValues(approvalRuleValue);
			approvalLog.setAuthorized("N");
			approvalLog.setAlternateUserid(approverId);
			approvalLog.setDateAssigned(Dates.getDate(Dates.today("")));
			approvalLog.setApproverType("U");
			approvalLog.setRuleType("RUL");
			approvalLog.setRuleAction("A");
			approvalLog.setCallForward(approverId);
			approvalLog.setApproverNotes(udfValue);
			approvalLog.setFyiApprover(fyiApprover);
			approvalLog.setExcludeLess(new BigDecimal("0"));
			approvalLog.setBackupApprover("");
			approvalLog.setAdvisor(advisor);
			approvalLog.setRecommendation("");
			approvalLog.setComp_id(comp_id);
			toList.add(approvalLog) ;
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }
}