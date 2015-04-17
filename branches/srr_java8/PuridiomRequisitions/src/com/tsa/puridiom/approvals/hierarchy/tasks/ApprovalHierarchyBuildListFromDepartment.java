package com.tsa.puridiom.approvals.hierarchy.tasks;

import com.tsa.puridiom.entity.ApprovalLog;


import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.Department;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.department.tasks.DepartmentRetrieveById;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApprovalHierarchyBuildListFromDepartment extends Task
{
	List extList = new ArrayList();
    DBSession dbs = null;
	String organizationId = "";
    String userId = "";
	String extRuleOrder = "";
    boolean addZeroDollarApprovers = false;
    CurrencyManager currencyManager;
    
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
            this.dbs = (DBSession)incomingRequest.get("dbsession") ;
            this.organizationId = (String) incomingRequest.get("organizationId");
            this.userId = (String) incomingRequest.get("userId");
            this.currencyManager = CurrencyManager.getInstance(this.organizationId);

            this.addZeroDollarApprovers = (PropertiesManager.getInstance(this.organizationId).getProperty("APPROVALS HIERARCHY", "ADDZERODOLLARAPPROVERS", "N")).equals("Y");
			this.extRuleOrder = PropertiesManager.getInstance(this.organizationId).getProperty("MISC", "EXTORDER", "20");
            this.extList = (List) incomingRequest.get("extList");
            if (extList == null) {
                extList = new ArrayList();
            }

            String formtype = HiltonUtility.ckNull((String) incomingRequest.get("formtype"));
            String formUser = "";
            String departmentCode = "";
            BigDecimal authorityRequired = new BigDecimal(0);
            BigDecimal icHeader = new BigDecimal(0);

            if (formtype.equals("PO")) {
                PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
                formUser = (String) incomingRequest.get("buyerCode");

                if (poHeader != null) {
                    if (HiltonUtility.isEmpty(formUser)) {
                    	formUser = poHeader.getBuyerCode();
                        if (HiltonUtility.isEmpty(formUser)) {
                        	formUser = poHeader.getOwner();
                        }
                    }
                    if (HiltonUtility.isEmpty(formUser)) {
                    	formUser = userId;
                    }

                    authorityRequired = poHeader.getTotal();
                    icHeader = poHeader.getIcPoHeader();
                    departmentCode = poHeader.getDepartmentCode();
                }
            } else if (formtype.equals("IVC")) {
            	InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
                formUser = (String) incomingRequest.get("buyerCode");

                if (invoiceHeader != null) {
                    if (HiltonUtility.isEmpty(formUser)) {
                    	formUser = invoiceHeader.getEnteredBy();
                    }
                    if (HiltonUtility.isEmpty(formUser)) {
                    	formUser = userId;
                    }

                    authorityRequired = invoiceHeader.getInvoiceTotal();
                    icHeader = invoiceHeader.getIcIvcHeader();
                    departmentCode = invoiceHeader.getDepartmentCode();
                }
            } else {
                RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
                formUser = (String) incomingRequest.get("requisitionerCode");

                if (requisitionHeader != null) {
	                if (HiltonUtility.isEmpty(formUser)) {
	                	formUser = requisitionHeader.getRequisitionerCode();
	                    if (HiltonUtility.isEmpty(formUser)) {
	                    	formUser = requisitionHeader.getOwner();
	                    }
	                }
                    authorityRequired = requisitionHeader.getTotal();
                    icHeader = requisitionHeader.getIcReqHeader();
                    departmentCode = requisitionHeader.getDepartmentCode();
                }
                if (HiltonUtility.isEmpty(formUser)) {
                	formUser = userId;
                }
            }
            String reportView = (String) incomingRequest.get("reportView");
            String formCurrencyCode = (String) incomingRequest.get("formCurrencyCode");            

            this.addApprovalHierarchy(icHeader, authorityRequired, departmentCode, HiltonUtility.ckNull(reportView).equals("Y"), formCurrencyCode);
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

    private void addApprovalHierarchy(BigDecimal icHeader, BigDecimal authorityRequired, String departmentCode, boolean reportView, String formCurrencyCode) {
        try {
            Map requestParams = new HashMap();
            requestParams.put("Department_departmentCode", departmentCode);
            requestParams.put("dbsession", this.dbs);
            requestParams.put("userId", this.userId);
            requestParams.put("organizationId", this.organizationId);

            DepartmentRetrieveById retrieveDepartment = new DepartmentRetrieveById();
            Department department = (Department) retrieveDepartment.executeTask(requestParams);

            /**
             * First add department manager
             */
        	String approverId = (String) department.getDeptManager();
        	BigDecimal approvalAuthority = department.getManagerAmount();
        	UserProfile approver = UserManager.getInstance().getUser(this.organizationId, approverId);

		    if (!Utility.ckNull(formCurrencyCode).equalsIgnoreCase(currencyManager.getBaseCurrencyCode())) {
		    	approvalAuthority = currencyManager.convertPriceFromBaseCurrency(approvalAuthority, formCurrencyCode);
		    }
        	if (approver.isAnApprover()) {
        		this.addApprover(approver, approvalAuthority, authorityRequired, icHeader, department);
        	} else {
                Log.debug(this, "manager [" + approver.getUserId() + "] has no approval authority and therefore will not be added to the list.");
            }

            for (int i=1; i <= 5; i++) {

            	Method mth = department.getClass().getMethod("getDeptApprv" + i, null);
            	approverId = (String) mth.invoke(department, null);
            	approver = UserManager.getInstance().getUser(this.organizationId, approverId);

	            if (approver.isAnApprover()) {
					Method mthAmount = department.getClass().getMethod("getApprv" + i + "Amount", null);
					approvalAuthority = (BigDecimal) mthAmount.invoke(department, null);

				    if (!Utility.ckNull(formCurrencyCode).equalsIgnoreCase(currencyManager.getBaseCurrencyCode())) {
				    	approvalAuthority = currencyManager.convertPriceFromBaseCurrency(approvalAuthority, formCurrencyCode);
				    }
					
	                if (approvalAuthority.compareTo(new BigDecimal(0)) > 0 || addZeroDollarApprovers) {
	                    Log.debug(this, "add approver [" + approver.getUserId() + "] with " + String.valueOf(approvalAuthority) + " approval authority.");
	                    this.addApprover(approver, approvalAuthority, authorityRequired, icHeader, department);
	                    Log.debug(this, "add approver  [" + approver.getUserId() + "] done");
	                } else {
	                    Log.debug(this, "approver [" + approver.getUserId() + "] has no approval authority and therefore will not be added to the list.");
	                }
	            } else {
                    Log.debug(this, "user [" + approver.getUserId() + "] has no approval authority and therefore will not be added to the list.");
                }
            }
      }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            Log.error(this, e.getMessage());
            e.printStackTrace();
        }
    }

	private void addApprover(UserProfile approver, BigDecimal approvalAuthority, BigDecimal amtToApprove, BigDecimal icHeader, Department department) {
        try
        {
        	String departmentDesc = department.getDepartmentCode();
        	if (!Utility.isEmpty(department.getDepartmentName())) {
            	departmentDesc = departmentDesc + " - " + department.getDepartmentName();
            }

            String callForward = null;
            callForward = approver.getCallForward();
            if (Utility.isEmpty(callForward)) {
                callForward = approver.getUserId();
            } else {
                callForward = approver.getCallForward();
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
	        appLog.setRuleType("HIER") ;
	        appLog.setRuleOrder(new BigDecimal(this.extRuleOrder));
	        appLog.setUdfValues(departmentDesc + " Departmental Hierarchy");
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