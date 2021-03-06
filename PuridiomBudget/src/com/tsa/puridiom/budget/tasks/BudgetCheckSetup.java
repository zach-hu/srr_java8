/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator
 */
public class BudgetCheckSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

        String oid = (String)incomingRequest.get("organizationId");
		String		sProcess = (String) incomingRequest.get("formtype") ; // REQ, PO, INV
		String 		icHeader = "" ;
		String		sYear = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BudgetYear", "");

		String		sFld1 = (String) incomingRequest.get("BFld1") ;

		if (sProcess == null) sProcess = "none" ;
		if (! sProcess.equalsIgnoreCase("none")) {
	        if (sProcess.equals("PO")) {
	        	icHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
				incomingRequest.put("PoLine_icPoHeader",icHeader) ;
				sYear = (String) incomingRequest.get("PoHeader_fiscalYear") ;
	        } else if ( sProcess.equals("INV")) {
	        	icHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
				incomingRequest.put("InvoiceLine_icIvcHeader",icHeader) ;
				sYear = (String) incomingRequest.get("InvoiceHeader_fiscalYear") ;
			} else {
				icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
				sYear = (String) incomingRequest.get("RequisitionHeader_fiscalYear") ;
				incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			}
			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("Account_icHeader",icHeader) ;
				this.setStatus(dbs.getStatus());
			}
		}
		if(Utility.isEmpty(sYear))
		{
			Calendar d = Calendar.getInstance();
			sYear = String.valueOf(d.get(Calendar.YEAR));
		}
		incomingRequest.put("budgetYear",sYear) ;
		incomingRequest.put("budgetPeriod",sYear) ;

		String arraytemp[] = new String[16];
		String	sUdf	= null ;

		for(int li = 0; li < 15; li++)
		{
			sUdf = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Udf" + String.valueOf(li + 1),"BUDGET_" + String.valueOf(li + 1) );
			if(Utility.isEmpty(sUdf))	sUdf = "budget_" + String.valueOf(li + 1);
			arraytemp[li] = sUdf;
		}
		// Commodity
		arraytemp[15] = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Commodity" ,"BUDGET_COMMODITY" );


		incomingRequest.put("budgetUdfArray",arraytemp) ;

		String	sAuthority = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Authority", "Department");
		if(Utility.isEmpty(sAuthority))	sAuthority = "Department";
		incomingRequest.put("budgetAuthority",sAuthority) ;

		String 	sAuthorityUdf = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Authority_Udf", "00").trim();
		// pad left with 0
		sAuthorityUdf = Integer.valueOf(sAuthorityUdf).toString();
		incomingRequest.put("budgetAuthorityUdf",sAuthorityUdf) ;
		incomingRequest.put("budgetAuthorityUdfField","fld_" + sAuthorityUdf) ;

		String sAccSep = PropertiesManager.getInstance(oid).getProperty("MISC", "ACCOUNTSEPARATOR", "-");
		incomingRequest.put("accountSeparator",sAccSep);

		String sPassword = PropertiesManager.getInstance(oid).getProperty("BUDGET", "PASSWORD", "N");
		incomingRequest.put("budgetPassword",sPassword);

		String sCreate = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_CREATE", "N");
		incomingRequest.put("budgetCreate",sCreate) ;

		String sExistsErr = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_EXISTSERR", "F");
		incomingRequest.put("budgetExistsErr",sExistsErr) ;

		String sOverErr = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_OVERERR", "F");
		incomingRequest.put("budgetOverErr",sOverErr) ;

		String sTolErr = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_TOLERR", "F");
		incomingRequest.put("budgetTolErr",sTolErr) ;

		String sAuthErr = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_AUTHERR", "F");
		incomingRequest.put("budgetAuthErr",sAuthErr) ;

		String sEmpty = PropertiesManager.getInstance(oid).getProperty("BUDGET", "BUDGET_EMPTY", "Y");
		incomingRequest.put("budgetEmpty",sEmpty) ;

		String sTol = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Tolerance", "0");
		BigDecimal bdTolerance = new BigDecimal(sTol);
		incomingRequest.put("budgetTolerance",bdTolerance) ;

		this.setStatus(Status.SUCCEEDED);
		return null ;
	}
}
