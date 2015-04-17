package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AppRulesExt;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class SetupApprovalRules extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        Object result = null;

        try
        {
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            boolean activate = false;
            boolean activateBuyerCommodity = false;
            boolean activateBelowNoApproval = false;
			boolean activateAppCommodity = false;
            List approvalRulesList = new ArrayList();
            List appCommodityList = new ArrayList();
            String noApprovalNeed = "N";
            String noApprovalNeedWithOtherApproval = "N";
            String passAppCommodity = "N";
            String buyerCommodityCode = "";
            String belowNoApproval = "";
            String onlyApproval = "N";
            String rqSubType = "";
            String querySpecialRules = "from AppRulesExt as a where a.id.ruleType = 'REQ' and a.ruleAction = 'S' and a.enabled = 'Y' order by a.ruleOrder ASC";
            List specialRulesList = dbs.query(querySpecialRules, new Object[] { },new Type[] { });

            if (rqh != null) {
            	if(rqh.getUdf1Code().indexOf("RESALECUST") >= 0)
            	{
            		noApprovalNeed = "Y";
            		incomingRequest.put("NoApprovalNeed", noApprovalNeed);
            		incomingRequest.put("NoApprovalNeedAutoAward", noApprovalNeed);
            		rqSubType = "RC";
            	}
            	else if(rqh.getUdf1Code().indexOf("RESALEEXP") >= 0)
            	{
            		noApprovalNeed = "N";
            		incomingRequest.put("NoApprovalNeed", noApprovalNeed);
            		incomingRequest.put("NoApprovalNeedAutoAward", noApprovalNeed);
            		rqSubType = "RE";
            	}
            	else
            	{
	                String queryString = "from Account as a where a.id.icHeader = ?";
	                List accountList = dbs.query(queryString, new Object[] { rqh.getIcReqHeader() },new Type[] { Hibernate.BIG_DECIMAL});
	
	                if ((accountList != null && accountList.size() > 0)) 
	                {
	                    for (Iterator it = accountList.iterator(); it.hasNext();) 
	                    {
	                        Account account = (Account) it.next();
	                        String fld1 = account.getFld1();
	                        String fld2 = account.getFld2();
	                        String fld3 = account.getFld3();
	                        String fld4 = account.getFld4();
	                        String accountString = fld1 + "-" + fld2 + "-" + fld3;
	                        noApprovalNeed = "N";
	                        if ((specialRulesList != null && specialRulesList.size() > 0)) 
	                        {
	    	                    for (Iterator it2 = specialRulesList.iterator(); it2.hasNext();) 
	    	                    {
	    	                    	AppRulesExt appRulesExt = (AppRulesExt) it2.next();
	    	                    	String ls_acct_string = appRulesExt.getNotes();
	    	                    	String fld_account[] = ls_acct_string.split(";");
	    	                    	String typeRule = fld_account[0];
	    	                        String appRules_fld1 = fld_account[1];
	    	                        String appRules_fld2 = fld_account[2];
	    	                        String appRules_fld3 = fld_account[3];
	    	                        String appRules_fld4 = null;
	    	                        if (fld_account.length > 4) {
	    	                        	appRules_fld4 = fld_account[4];
	    	                        } 
	    	                        
	    	                        if(appRulesExt.getRuleText().indexOf("No Approval Need") >= 0)
	    	                        {
	    	                        	if ((fld1.equalsIgnoreCase(appRules_fld1) || appRules_fld1.equalsIgnoreCase("*")))
	    	                        	{
	    	                        		if(fld2.equalsIgnoreCase(appRules_fld2) || appRules_fld2.equalsIgnoreCase("*"))
	    	                        		{
	    	                        			String passRule = "N";
	    	                        		    if (fld3.equalsIgnoreCase(appRules_fld3) 
	    	                        		    		|| appRules_fld3.equalsIgnoreCase("*")
	    	                        		    		|| ( fld3.startsWith(appRules_fld3) && appRulesExt.getRuleText().indexOf("%") >= 0))
		    	                        		{
	    	                        		    	
	    	                        		    	if (!HiltonUtility.isEmpty(fld4)) {
	    	                        		    		if (fld4.equalsIgnoreCase(appRules_fld4) 
	    	    	                        		    		|| appRules_fld4.equalsIgnoreCase("*")
	    	    	                        		    		|| ( fld4.startsWith(appRules_fld4) && appRulesExt.getRuleText().indexOf("%") >= 0))
	    		    	                        		{
	    	                        		    			passRule = "Y";
			    	                        		    	approvalRulesList.add(accountString);
	    		    	                        		}
	    	                        		    	} else {
	    	                        		    		passRule = "Y";
		    	                        		    	approvalRulesList.add(accountString);
	    	                        		    	}
	    	                        		    	
		    	                        		}
	    	                        		    
	    	                        		    if(passRule.equalsIgnoreCase("Y") && (!activate))
	    	                        		    {
	    	                        		    	noApprovalNeed = "Y";
	    					                        activateBuyerCommodity = true;
	    					                        buyerCommodityCode = "N";
	    					                        if(typeRule.equalsIgnoreCase("OA"))
	    					                        {
	    					                        	onlyApproval = "Y";
	    					                        }	    					                       
	    					                        break;
	    	                        		    }
	    	                        		}
	    	                        	}
	    	                        }
	    	                        if(appRulesExt.getRuleText().indexOf("Below Approval") >= 0)
	    	                        {
	    	                        	BigDecimal amount = new BigDecimal(fld_account[4]);
	    	                        	if ((fld1.equalsIgnoreCase(appRules_fld1) || appRules_fld1.equalsIgnoreCase("*")))
	    	                        	{
	    	                        		if(fld2.equalsIgnoreCase(appRules_fld2) || appRules_fld2.equalsIgnoreCase("*"))
	    	                        		{
	    	                        			String passAmount = "N";
	    	                        		    if((fld3.equalsIgnoreCase(appRules_fld3) || appRules_fld3.equalsIgnoreCase("*")))
		    	                        		{
	    	                        		    	if(typeRule.equalsIgnoreCase("BA"))
	    	                        		    	{
	    	                        		    		if(rqh.getTotal().compareTo(amount) <= 0)
	    	                        		    		{
	    	                        		    			passAmount = "Y";
	    	                        		    			approvalRulesList.add(accountString);
	    	                        		    		}
	    	                        		    	}
	    	                        		    	if(typeRule.equalsIgnoreCase("AA"))
	    	                        		    	{
	    	                        		    		if(rqh.getTotal().compareTo(amount) > 0)
	    	                        		    		{
	    	                        		    			passAmount = "Y";
	    	                        		    		}
	    	                        		    	}
	    	                        		    	
		    	                        		}
	    	                        		    else if(((fld3.startsWith(appRules_fld3)) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
	    	                        		    {
	    	                        		    	if(typeRule.equalsIgnoreCase("BA"))
	    	                        		    	{
	    	                        		    		if(rqh.getTotal().compareTo(amount) <= 0)
	    	                        		    		{
	    	                        		    			passAmount = "Y";
	    	                        		    			approvalRulesList.add(accountString);
	    	                        		    		}
	    	                        		    	}
	    	                        		    	if(typeRule.equalsIgnoreCase("AA"))
	    	                        		    	{
	    	                        		    		if(rqh.getTotal().compareTo(amount) > 0)
	    	                        		    		{
	    	                        		    			passAmount = "Y";
	    	                        		    		}
	    	                        		    	}
	    	                        		    }  	 
	    	                        		    if(passAmount.equalsIgnoreCase("Y") && (!activate))
	    	                        		    {		   
	    	    		                       		activateBuyerCommodity = true;
	    	    			                       	buyerCommodityCode = "N"; 
	    	    			                       	if(typeRule.equalsIgnoreCase("AA"))
	    					                        {
	    	    			                       		noApprovalNeed = "N";
	    	    			                       		onlyApproval = "N";
	    	    			                       		belowNoApproval = "N";
	    	    			                       		activate = true;
	    					                        }
	    	    			                       	else
	    	    			                       	{
	    	    			                       		belowNoApproval = "Y";
	    	    			                       		noApprovalNeed = "Y";
	    	    			                       	}	    					                     
	    					                        break;
	    	                        		    }
	    	                        		}
	    	                        	}    
	    	                        }  
				                    if(appRulesExt.getRuleText().indexOf("Commodity Code") >= 0)
				                    {
				                    	if ((fld1.equalsIgnoreCase(appRules_fld1) || appRules_fld1.equalsIgnoreCase("*")) && (!activateBuyerCommodity))
	    	                        	{
	    	                        		if(fld2.equalsIgnoreCase(appRules_fld2) || appRules_fld2.equalsIgnoreCase("*"))
	    	                        		{
	    	                        		    if(fld3.equalsIgnoreCase(appRules_fld3) || appRules_fld3.equalsIgnoreCase("*"))
		    	                        		{
	    	                        		    	buyerCommodityCode = "Y";
		    	                        		}
	    	                        		}
	    	                        	} 
				                    }
				                    
	    	                    }
	    	                    if(approvalRulesList.size() < 1 || noApprovalNeed.equalsIgnoreCase("N"))
	    	                    {
	    	                    	noApprovalNeed = "N";     	
	    	                    	onlyApproval = "N";
    		                        activate = true;
	    	                    }
	    	                    if(buyerCommodityCode.equalsIgnoreCase("N"))
	    	                   	{
	    	                    	buyerCommodityCode = "N";
                    		    	activateBuyerCommodity = true;
	    	                   	}
	                        }
	                    }         
	    	            if(noApprovalNeed.equalsIgnoreCase("N") && !buyerCommodityCode.equalsIgnoreCase("Y"))
	    	            {
	    	            	String queryReqLineString = "from RequisitionLine as rl where rl.icReqHeader = ?";
         	                List reqLineList = dbs.query(queryReqLineString, new Object[] { rqh.getIcReqHeader() },new Type[] { Hibernate.BIG_DECIMAL});
         	     	               
         	                if ((reqLineList != null && reqLineList.size() > 0)) 
          	                {
          	                    for (Iterator rl = reqLineList.iterator(); rl.hasNext();) 
          	                    {
          	                    	RequisitionLine requisitionLine = (RequisitionLine) rl.next();
         	                        String commodityLine = requisitionLine.getCommodityCode();
         	                        if ((specialRulesList != null && specialRulesList.size() > 0)) 
        		                    {
         	                        	for (Iterator it3 = specialRulesList.iterator(); it3.hasNext();) 
        		    	                {
         	                        		AppRulesExt appRulesExt = (AppRulesExt) it3.next();
        		    	                   	String ls_acct_string = appRulesExt.getNotes();
        		    	                   	String fld_account[] = ls_acct_string.split(";");
        		    	                   	String typeRule = fld_account[0];
        		    	                    String appRules_fld1 = "";
        		    	                    String appRules_fld2 = "";
        		    	                    String appRules_fld3 = "";
        		    	                    String appRules_fld4 = "";
        		    	                    String appRules_fld5 = "";
        		    	                    String appRules_fld6 = "";
        		    	                    String appRules_fld7 = "";
        		    	                    String appRules_fld8 = "";
            		    	                String appRules_fld9 = "";
        		    	                    String commodity = "";
        		    	                    String accountString = "";
        		    	                    boolean passRule = false;
        		    	                    BigDecimal amount = new BigDecimal(0);
        		    	                    for(int x=1;fld_account.length > x; x++)
    		    	                    	{
    		    	                    		String commodityCode[] = fld_account[x].split("=");
    		    	                    		if(commodityCode.length > 1)
    		    	                    		{
    		    	                    			if(commodityCode[0].equalsIgnoreCase("COMM"))
    		    	                    			{
    		    	                    				commodity = commodityCode[1];
    		    	                    			}
    		    	                    			else if(commodityCode[0].equalsIgnoreCase("T"))
    		    	                    			{
    		    	                    				amount = new BigDecimal(commodityCode[1]);
    		    	                    			}
    		    	                    		}
    		    	                    		else
    		    	                    		{
    		    	                    			if(x == 1)
        		    	                    		{
    		    	                    				appRules_fld1 = fld_account[x];
        		    	                    		}
    		    	                    			else if(x == 2)
        		    	                    		{
    		    	                    				appRules_fld2 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 3)
        		    	                    		{
    		    	                    				appRules_fld3 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 4)
        		    	                    		{
    		    	                    				appRules_fld4 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 5)
        		    	                    		{
    		    	                    				appRules_fld5 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 6)
        		    	                    		{
    		    	                    				appRules_fld6 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 7)
        		    	                    		{
    		    	                    				appRules_fld7 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 8)
        		    	                    		{
    		    	                    				appRules_fld8 = fld_account[x];              
        		    	                    		}
    		    	                    			else if(x == 9)
        		    	                    		{
    		    	                    				appRules_fld9 = fld_account[x];              
        		    	                    		}
    		    	                    			
    		    	                    		} 	
    		    	                    	} 
        		    	                    if(appRulesExt.getRuleText().indexOf("Commodity No Need Approval") >= 0)
        		    	                    {
                     	                    	if((commodityLine.equalsIgnoreCase(commodity) || commodity.equalsIgnoreCase("*")))
                    	                        {
                     	                    		passRule = true;
                     	                    	}
                    	                        else if((commodityLine.startsWith(commodity) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                    	                        {
                    	                        	passRule = true;
                    	                        }
                     	                    }
        		    		                else if(appRulesExt.getRuleText().indexOf("Approval Commodity") >= 0)
        		    	                    {
                     	                    	if((commodityLine.equalsIgnoreCase(commodity) || commodity.equalsIgnoreCase("*")))
                    	                        {
                     	                    		passRule = true;
                     	                    	}
                    	                        else if((commodityLine.startsWith(commodity) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                    	                        {
                    	                        	passRule = true;
                    	                        }
                     	                    }
        		    	                    else if(appRulesExt.getRuleText().indexOf("Approval Below Commodity") >= 0)
        		    	                    {            
        		    	                    	if((commodityLine.equalsIgnoreCase(commodity) || commodity.equalsIgnoreCase("*")))
                        	                    {
    		    	                    			if(rqh.getTotal().compareTo(amount) <= 0)
    	                        		    		{
    		    	                    				passRule = true;
    	                        		    		}
                        	                    }
                        	                    else if(((commodityLine.startsWith(commodity)) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                        	                    {
                        	                    	if(rqh.getTotal().compareTo(amount) <= 0)
    	                        		    		{
                        	                    		passRule = true;
    	                        		    		}
                        	                    }
        		    	                    }
        		    	                    if(passRule)
        		    	                    {
        		    	                    	String accountLine = "from Account as a where a.id.icHeader = ? and a.id.icLine = ?";
            		    		                List accountLineList = dbs.query(accountLine, new Object[] { requisitionLine.getIcReqHeader(), requisitionLine.getIcReqLine() },new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});
            		    		                if ((accountLineList != null && accountLineList.size() > 0)) 
            		    		                {
            		    		                	List account_Line_List = new ArrayList(); 
            		    		                    for (Iterator itl = accountLineList.iterator(); itl.hasNext();) 
            		    		                    {
            		    		                        Account accountL = (Account) itl.next();
            		    		                        String fld_line_1 = HiltonUtility.ckNull(accountL.getFld1());
            		    		                        String fld_line_2 = HiltonUtility.ckNull(accountL.getFld2());
            		    		                        String fld_line_3 = HiltonUtility.ckNull(accountL.getFld3());
            		    		                        String fld_line_4 = HiltonUtility.ckNull(accountL.getFld4());
            		    		                        String fld_line_5 = HiltonUtility.ckNull(accountL.getFld5());
            		    		                        String fld_line_6 = HiltonUtility.ckNull(accountL.getFld6());
            		    		                        String fld_line_7 = HiltonUtility.ckNull(accountL.getFld7());
            		    		                        String fld_line_8 = HiltonUtility.ckNull(accountL.getFld8());
            		    		                        String fld_line_9 = HiltonUtility.ckNull(accountL.getFld9());
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld1))
            		    		                        {
            		    		                        	if((fld_line_1.equalsIgnoreCase(appRules_fld1) || appRules_fld1.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_1);
                                 	                    	}
                                	                        else if((fld_line_1.startsWith(appRules_fld1) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_1);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld2))
            		    		                        {
            		    		                        	if((fld_line_1.equalsIgnoreCase(appRules_fld2) || appRules_fld2.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_2);
                                 	                    	}
                                	                        else if((fld_line_2.startsWith(appRules_fld2) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_2);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld3))
            		    		                        {
            		    		                        	if((fld_line_3.equalsIgnoreCase(appRules_fld3) || appRules_fld3.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_3);
                                 	                    	}
                                	                        else if((fld_line_3.startsWith(appRules_fld3) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_3);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld4))
            		    		                        {
            		    		                        	if((fld_line_4.equalsIgnoreCase(appRules_fld4) || appRules_fld4.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_4);
                                 	                    	}
                                	                        else if((fld_line_4.startsWith(appRules_fld4) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_4);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld5))
            		    		                        {
            		    		                        	if((fld_line_5.equalsIgnoreCase(appRules_fld5) || appRules_fld5.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_5);
                                 	                    	}
                                	                        else if((fld_line_5.startsWith(appRules_fld5) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_5);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld6))
            		    		                        {
            		    		                        	if((fld_line_6.equalsIgnoreCase(appRules_fld6) || appRules_fld6.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_6);
                                 	                    	}
                                	                        else if((fld_line_6.startsWith(appRules_fld6) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_6);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld7))
            		    		                        {
            		    		                        	if((fld_line_7.equalsIgnoreCase(appRules_fld7) || appRules_fld7.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_7);
                                 	                    	}
                                	                        else if((fld_line_7.startsWith(appRules_fld7) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_7);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld8))
            		    		                        {
            		    		                        	if((fld_line_8.equalsIgnoreCase(appRules_fld8) || appRules_fld8.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_8);
                                 	                    	}
                                	                        else if((fld_line_8.startsWith(appRules_fld8) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_8);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        if(!HiltonUtility.isEmpty(appRules_fld9))
            		    		                        {
            		    		                        	if((fld_line_9.equalsIgnoreCase(appRules_fld9) || appRules_fld9.equalsIgnoreCase("*")))
                                	                        {
                                 	                    		passRule = true;
                                 	                    		account_Line_List.add(fld_line_9);
                                 	                    	}
                                	                        else if((fld_line_9.startsWith(appRules_fld9) && (appRulesExt.getRuleText().indexOf("%") >= 0)))
                                	                        {
                                	                        	passRule = true;
                                	                        	account_Line_List.add(fld_line_9);
                                	                        }
                                	                        else
                                	                        {
                                	                        	passRule = false;
                                	                        	break;
                                	                        }
            		    		                        }
            		    		                        for (Iterator it = account_Line_List.iterator(); it.hasNext();) 
            		               	                    {
            		    		                        	accountString += (String) it.next();
            		    		                        	if(it.hasNext())
            		    		                        	{
            		    		                        		accountString += "-";
            		    		                        	}
            		               	                    }
            		    		                        if(passRule)
            		    		                        {
            		    		                        	approvalRulesList.add(accountString);
            		    		                        }
            		    		                    }  
            		    		                }
            		    		                if(passRule)
            		    		                {
            		    		                	appCommodityList.add(typeRule);
            		    		                }
        		    	                    }     		    	                            		    	                    
                     	                }
                        		    }  	 
                        		}
          	                    if(appCommodityList.size() == reqLineList.size())
          	                    {		   
          	                    	for (Iterator it = appCommodityList.iterator(); it.hasNext();) 
               	                    {
          	                    		String typeRule = (String) it.next();
          	                    		if(typeRule.equalsIgnoreCase("AA"))
          	                    		{
          	                    			noApprovalNeed = "N";
          	                    			onlyApproval = "N";
          	                    			belowNoApproval = "N";
          	                    			noApprovalNeedWithOtherApproval = "N";
          	                    			break;
          	                    		}
          	                    		else if(typeRule.equalsIgnoreCase("NA"))
          	                    		{
          	                    			noApprovalNeed = "Y";
          	                    			belowNoApproval = "N";
          	                    		}
          	                    		else
          	                    		{
          	                    			belowNoApproval = "Y";
          	                    			noApprovalNeed = "Y";
          	                    		}	    					                     
               	                    }
          	                    }
                        	}    
                        }
	    	            if(noApprovalNeed.equalsIgnoreCase("N") && !buyerCommodityCode.equalsIgnoreCase("Y") && approvalRulesList.size() > 0)
	    	            {
	    	            	noApprovalNeedWithOtherApproval = "Y";
	    	            }
	                    incomingRequest.put("NoApprovalNeed", noApprovalNeed);
	                    incomingRequest.put("BuyerCommodityCode", buyerCommodityCode);
	                    incomingRequest.put("BelowNoApproval", belowNoApproval);
	                    incomingRequest.put("ApprovalNeedWithOtherApproval", noApprovalNeedWithOtherApproval);
	                    incomingRequest.put("approvalRulesList", approvalRulesList);
	                    incomingRequest.put("OnlyApproval", onlyApproval);
	                }
            	}
            	if(noApprovalNeed.equalsIgnoreCase("Y") && HiltonUtility.isEmpty(rqSubType))
            	{
            		if(onlyApproval.equalsIgnoreCase("Y"))
            		{
            			rqSubType = "OA";
            		}
            		else if(belowNoApproval.equalsIgnoreCase("Y"))
            		{
            			rqSubType = "BA";
            		}
            		else
            		{
            			rqSubType = "NA";
            		}
            	}
            	else if(noApprovalNeed.equalsIgnoreCase("N") && HiltonUtility.isEmpty(rqSubType))
            	{
            		if(belowNoApproval.equalsIgnoreCase("N"))
            		{
            			rqSubType = "AA";
            			incomingRequest.put("ruleStatus","PASSED") ; 
            		}
            	}
            	incomingRequest.put("RequisitionHeader_rqSubType", rqSubType);
            }

        } catch (Exception e) {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at SetupApprovalRules", e);
        }
        return result;
    }

}