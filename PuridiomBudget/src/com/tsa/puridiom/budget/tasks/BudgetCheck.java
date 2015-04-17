package com.tsa.puridiom.budget.tasks;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.lang.reflect.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.poheader.tasks.*;

public class BudgetCheck extends Task	{

    ArrayList			errorList = new ArrayList() ;
    DBSession dbs = null ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

//		 Table of Action Types
//		  	1	-	Forward REQ
//	 	  	2	-	Reject REQ
//	 	  	3	-	Delete or Cancel REQ
//		  	4	-	Award PO
//		  	5	-	Cancel PO
//		  	6	-	Expense PO
//		  	7	-	Manual Expense

//			Table of Action Codes (bAction)
//			1	-	Pre-Encumber
//			2	-	Encumber
//			3	-	Expense

        String organizationId = (String)incomingRequest.get("organizationId");
        String	userId = (String) incomingRequest.get("userId");
        String  userTimeZone = (String) incomingRequest.get("userTimeZone");

        // Parameters
		String		bAction = (String) incomingRequest.get("budgetAction") ;
		String		bType = (String) incomingRequest.get("budgetType") ;
		String		bYear = (String) incomingRequest.get("budgetYear") ;
		String		bMake = (String) incomingRequest.get("budgetMake") ;

		// Properties
		String		bAuthority = (String) incomingRequest.get("budgetAuthority") ;
		String		bEmpty = (String) incomingRequest.get("budgetEmpty") ;
		String		bCreate = (String) incomingRequest.get("budgetCreate") ;
		BigDecimal	bdTolerance = (BigDecimal)incomingRequest.get("budgetTolerance") ;

		/* Flags for errors - F=Fail (Default) W=Warn  I= Ignore */

		String 		bExistsErr = (String) incomingRequest.get("budgetExistsErr") ;
		String 		bOverErr = (String) incomingRequest.get("budgetOverErr") ;
		String 		bTolErr = (String) incomingRequest.get("budgetTolErr") ;
		String 		bAuthErr = (String) incomingRequest.get("budgetAuthErr") ;

		boolean	bCheckBud = false ;

		List			accountList = (List) incomingRequest.get("accountList") ;
        List 			auditList = new ArrayList() ;
        List			budgetList = new ArrayList()  ;
        List			tempList = new ArrayList() ;
        List			wildCardList = (List) incomingRequest.get("wildcardList") ;

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

		String poHeader = "", ls_action_field = "", ls_prev_action_field = "", ls_action_type = "", ls_prev_action_code = "";
		String ls_prev_action_type = "", ls_status = "", ls_auth = "" ;
		BigDecimal bdFact = new BigDecimal("0.00"), bdAmt = new BigDecimal("0.00"), bdTotalTran = new BigDecimal("0.00");
		BigDecimal audIc = new BigDecimal("0"), budId = new BigDecimal("0") ;
		BigDecimal	bdPrevId = new BigDecimal("0") ;

        dbs = (DBSession)incomingRequest.get("dbsession");

        String	bUdfArray[] = (String[])incomingRequest.get("budgetUdfArray");

		String		status = "PASSED" ;
		String		returnStatus = status ;

		int items = accountList.size() ;

		if(bType.equals("PO") || bType.equals("REV"))
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
			BigDecimal rev = poh.getRevisionNumber() ;
			if (rev == null) { rev = new BigDecimal("0") ; }
			if (rev.compareTo(new BigDecimal("0")) > 0) {
//				Account account = (Account)accountList.get(0);
// 				BigDecimal icPoHeader = account.getComp_id().getIcHeader() ;
				BigDecimal icPoHeader = poh.getIcPoHeader() ;
				// get the previous revision
				icPoHeader = this.getPrevRev(icPoHeader,poh.getPoNumber(), poh.getReleaseNumber(),rev);
				bType = "PO";
				boolean bRevision = true;
	            //find revision accounts
	            String sql = "From BudgetAudit bAudit Where bAudit.icHeader = " + icPoHeader.toString() +
	                               " AND bAudit.actionCode = '2' AND bAudit.actionMake = '1'";
	            Object [] args = {icPoHeader.toString()};
	            org.hibernate.type.Type [] types = {Hibernate.STRING};

	            tempList = dbs.query(sql, args, types);
	            auditList = this.appendAuditList(tempList,auditList) ;

	            if (auditList.size() > 0) {
		            for (int ix = 0; ix < auditList.size(); ix++) {
		              	BudgetAudit bAudit = (BudgetAudit)auditList.get(ix) ;
		              	bAudit.setActionMake("R") ;
		               	bAudit.setBOperator(new BigDecimal("-1")) ;
		            }
	            } else {
	                auditList = new ArrayList() ;
	                Log.error(this, "Revision for ic: " + icPoHeader.toString() + "was not Found!");
	            }
			}
		}   		// End if bType = "REV"7

		if(bAction.equals("1"))
		{
			ls_action_field = "";
			ls_prev_action_field = "";

			ls_action_type = "1";
			ls_prev_action_code = "1";
		}
		else if(bAction.equals("2"))			// Awarded PO
		{
			if(bType.equals("REJ"))
			{
				ls_action_field 		= "pre_encumbered";
				ls_prev_action_field = "encumbered";
				ls_action_type = "5";							// Cancel PO
				ls_prev_action_type = "4";
				ls_prev_action_code = "2";
			}
			else
			{
				ls_action_field 		= "encumbered";
				ls_prev_action_field = "pre_encumbered";
				ls_action_type = "4"	;							// Award PO
				ls_prev_action_type = "1";
				ls_prev_action_code = "1";
			}
		}
		else if(bAction.equals("3"))	// Expensed
		{
			ls_action_field 		= "expensed";
			ls_prev_action_field = "encumbered";
			ls_action_type = "6";							// Expense PO
			ls_prev_action_type = "4";
			ls_prev_action_code = "2";
		}

		String ls_auth_type = bAuthority.substring(0, 1).toUpperCase();
		for(int ii = 0; ii < items; ii++)
		{
			String ls_acct_string = "";
			boolean	lb_passed = true ;
			String budString = "" ;
			Account	account = (Account) accountList.get(ii) ;
			int budCount = budgetList.size() ;

			String	ls_ic_line = account.getAccountTitle() ;
			String	ls_ic_header = account.getComp_id().getIcHeader().toString() ;
			ls_auth = account.getOwner() ;

			if(bAction.equals("2") || bAction.equals("3"))
			{
				incomingRequest.put("PoHeader_icPoHeader",ls_ic_header) ;
				PoHeaderRetrieveById poh = new PoHeaderRetrieveById();

                try
                {
                    PoHeader po = (PoHeader) poh.executeTask(incomingRequest);
    				bdFact = po.getCurrencyFactor() ;
    				if(bdFact.compareTo(new BigDecimal("0")) == 0)
    				{
    					bdFact = new BigDecimal("1");
    				}
                }
                catch (Exception e)
                {
                    Log.error(this, "Error occurred retrieving Po Header by icPoHeader " + ls_ic_header + e.toString());
					bdFact = new BigDecimal("1");
                }
				bdAmt = account.getAllocAmount().divide(bdFact, 2, BigDecimal.ROUND_UP);
			}
			else
			{
				bdAmt = account.getAllocAmount();
			}

			String ls_where = "bCenter.budgetPeriod = '" + bYear + "'";

			String	ls_data = "";
			StringBuffer sb = new StringBuffer();
			sb.setLength(0);
			String sCol[] = new String[16];
			sCol[0] = "BudgetPeriod";
			String sVal[] = new String[16];
			sVal[0] = bYear;
			String sOper[] = new String[16];
			sOper[0] = "=";

			for(int ib = 0; ib < 15; ib++)
			{
				String ls_field = bUdfArray[ib];
				//for the puridiom version we are skipping this check.. fields are already named fld.
				//might need to be changed in the future.
				if(ls_field.indexOf("Fld") == 0 || ls_field.indexOf("Commodity") == 0)
				{
					try {
							String	fld = "get" + ls_field ;
				           Class[] types = new Class[] {};

				           Method meth = account.getClass().getMethod(fld, types) ;
				           ls_data  = (String) meth.invoke(account,new Object[0]) ;
				     }
				     catch (Throwable e) {
				    	 Log.error(this, "Account class reflection error get" + ls_field + e.toString());
			         }

				     if(! Utility.isEmpty(ls_data))
				     {
							ls_where = ls_where + " AND bCenter.budget" + (ib + 1) + " = '" + ls_data + "'";
				     } else {
							ls_data = " ";
							ls_where = ls_where + " AND bCenter.budget" + (ib + 1) + " = ' '";
					}

					budString = budString + ls_data ;

					sCol[ib + 1] = "Budget" + (ib + 1);
					sVal[ib + 1] = ls_data;
					sOper[ib + 1] = "=";
					if(sb.length() == 0)
					{
						sb.append(ls_data);
					}
					else
					{
						sb.append(";" + ls_data);
					}
				}
			}

			if (Utility.isEmpty(budString) && bEmpty.equalsIgnoreCase("N")) {
				//	Ignore empty string budgets
				account.setOwner("BUDGETIGNORE") ;
				continue;
			}

			/*jDataObject tempBudget = this.ids_budget;
			tempBudget.modifyWhere("");
			tempBudget.modifyWhere(ls_where);
			Hashtable htTemp = new Hashtable();
			htTemp.put(tempBudget.dataObject, new Hashtable());
			int row = tempBudget.populate(htTemp);
			*/
			int row = findBudget(budgetList, sCol, sOper, sVal, false);
			//int row = this.ids_budget.find("budget_period", asYear);
			if(row <= 0)
			{
	            String sql = "from BudgetCenter bCenter WHERE " + ls_where ;
	            tempList = this.executeSql(sql) ;
	            int tempCount = tempList.size() ;
	            budgetList = this.appendBudgetList(tempList,budgetList) ;
				budCount = budgetList.size() ;

				if(tempCount > 0)
				{
					row = findBudget(budgetList,sCol, sOper, sVal, false);
				}
				else
				{
					budCount = findBudget(wildCardList, sCol, sOper, sVal,true) ;
					if (budCount > 0) {
						tempList.add(wildCardList.get(budCount - 1)) ;
			            budgetList = this.appendBudgetList(tempList,budgetList) ;
						row = findBudget(budgetList,sCol, sOper, sVal, true);
					} else {
						row = 0;
					}
				}
			}

			if(row < 1)
			{
				//Budget does not exist, Generate NEW Budget //will be done later now it will plain fail
				if (bExistsErr.equals("W")) {
					//	Warn on non-existent budgets
					account.setOwner("BUDGETIGNORE") ;
					this.budgetError("Warning! Budget does not exist", sb.toString(), ls_auth, "0.00", "0.00", 2);
	 				status = "WARNING";
					continue;
				}
				if (bExistsErr.equals("I")) {
					//	Ignore budget exists error
					account.setOwner("BUDGETIGNORE") ;
	 				status = "IGNORE";
					continue;
				}

				if (bCheckBud) {
					this.budgetError("Budget Does Not Exist", sb.toString(), ls_auth, "0", "0", 3);
					status = "FAILED";
					lb_passed = false ;
				} else {
					if (! (bCreate.equalsIgnoreCase("Y") || bCreate.equalsIgnoreCase("A"))) {
						this.budgetError("Budget Does Not Exist", sb.toString(), ls_auth, "0", "0", 3);
						status = "FAILED";
						lb_passed = false ;
					} else {
						// Create Budget here
						BudgetCenter bCenter = new BudgetCenter() ;
						BigDecimal budgetId = new BigDecimal(ukg.getUniqueKey().toString()) ;
						bCenter.setBudgetId(budgetId) ;
						bCenter.setBudgetPeriod(bYear) ;
						bCenter.setStatus("03") ;
						bCenter.setOwner("BUDGETADMIN") ;
						for(int li_b = 1; li_b < 16; li_b++ )
						{

							String	fld = bUdfArray[li_b - 1] ;
							if (Utility.isEmpty(fld)) { fld = "" ; }
							String	sdata = " " ;
							if (fld.startsWith("Fld"))
							{
								try {
							           Class[] types = new Class[] {};
							           Method meth = account.getClass().getMethod("get" + fld,types) ;
							           sdata  = (String) meth.invoke(account,new Object[0]) ;
							     }
							     catch (Throwable e) {
							    	 Log.error(this, "Account class reflection error get " + fld + e.toString());
						         }
							     if (Utility.isEmpty(sdata)) {
							    	 sdata = " " ;
							     }
							}

							String mth = "setBudget" + Integer.toString(li_b) ;
							try {
						           Class[] types = new Class[] { String.class };
						           Method meth = bCenter.getClass().getMethod(mth, types) ;
						           meth.invoke(bCenter, new Object[] { sdata } ) ;
						     }
						     catch (Throwable e) {
						    	 Log.error(this, "BudgetCenter class reflection error get " + mth + e.toString());
					         }
						}
					}
				}
			}
			else
			{
				//row = 1;
				String ls_stat = "";
				BudgetCenter bCenter = (BudgetCenter) budgetList.get(row - 1) ;
				ls_status = bCenter.getStatus() ;
				if(ls_status.equals("03"))
				{
					if (bExistsErr.equals("W")) {
						//	Warn on non-existent budgets
						account.setOwner("BUDGETIGNORE") ;
						this.budgetError("Warning! Budget is Inactive", sb.toString(), ls_auth, "0.00", "0.00", 2);
		 				status = "WARNING";
						continue;
					}
					if (bExistsErr.equals("I")) {
						//	Ignore budget exists error
						account.setOwner("BUDGETIGNORE") ;
		 				status = "IGNORE";
						continue;
					}

					status = "FAILED";
					lb_passed = false ;
					this.budgetError("Budget is Inactive", sb.toString(), ls_auth, "0", "0", 3);
				}
				else
				{
					//budget exists check authority
					budId = bCenter.getBudgetId() ;
					if(!ls_status.equals("03"))
					{
			            //find revision accounts
			            String sql = "From BudgetDrawer bDrawer Where bDrawer.id.budgetId = ? AND bDrawer.id.authority = ?" ;
			            List drawerList = new ArrayList();
			            Object [] args = {budId.toString(), ls_auth};
			            org.hibernate.type.Type [] types = {Hibernate.STRING, Hibernate.STRING};
			            try
			            {
			                drawerList = dbs.query(sql, args, types);
			            }
			            catch (Exception e)
			            {
			                Log.error(this, "Error retrieving budgetdrawer id=" + budId + "  authority = " + ls_auth);
			            }
			            if (drawerList.size() > 0) {
			            	BudgetDrawer bDrawer = (BudgetDrawer) drawerList.get(0) ;
			            	ls_stat = bDrawer.getStatus() ;
			            } else {
							// No Authority for Budget selected
							if (bAuthErr.equals("W")) {
								//	Warn on non-existent budgets
								account.setOwner("BUDGETIGNORE") ;
								this.budgetError("Warning! Authority does not exist", sb.toString(), ls_auth, "0.00", "0.00", 2);
				 				status = "WARNING";
							} else if (bAuthErr.equals("I")) {
								//	Ignore budget exists error
								account.setOwner("BUDGETIGNORE") ;
				 				status = "IGNORE";
							} else {
								lb_passed = false ;
								status = "FAILED";
								returnStatus = status ;
								this.budgetError("Authority Does Not Exist", sb.toString(), ls_auth, "0", "0", 3);
							}
			            }
					}
				}
			}

			if(lb_passed)
			{
				System.out.println("bType=" + bType) ;
				System.out.println("bmake=" + bMake) ;
				System.out.println("baction=" + bAction) ;
				System.out.println("bYear=" + bYear) ;
				System.out.println("ActionType=" + ls_action_type) ;
				System.out.println("prevActionCode=" + ls_prev_action_code) ;
				System.out.println("prevActionType=" + ls_prev_action_type) ;

				if(!bType.equals("REJ"))
				{
		            String sql = "From BudgetAudit bAudit Where bAudit.icLine = ? and bAudit.budgetId = ?" +
                        " AND bAudit.actionCode = ? AND bAudit.actionMake = '1'";
		            Object [] args = {ls_ic_line, budId.toString(), ls_prev_action_code};
		            org.hibernate.type.Type [] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};

		            tempList = dbs.query(sql, args, types) ;
		            auditList = this.appendAuditList(tempList,auditList) ;

					int li_row = auditList.size();

					if(li_row > 0)
					{
						String sCol1[] = {"IcLine", "BudgetId", "ActionCode"};
						String sVal1[] = {ls_ic_line, budId.toString(), ls_prev_action_code};
						String sOper1[] = {"=", "=", "="};
						li_row = this.findAudit(auditList,sCol1, sOper1, sVal1 );
						if(li_row > 0)
						{
							BudgetAudit bAudit = (BudgetAudit) auditList.get(li_row - 1) ;
							bAudit.setActionMake(bMake) ;
							bAudit.setBOperator(new BigDecimal("0")) ;

							BudgetAudit bAuditNew = this.copyBudgetAudit(bAudit) ;
							audIc = new BigDecimal(ukg.getUniqueKey().toString()) ;

							bAuditNew.setAuditId(audIc) ;
							bAuditNew.setActionType(ls_action_type) ;
							bAuditNew.setBOperator(new BigDecimal("-1")) ;

//							bAudit.setActionDate(Dates.today("")) ;
//							bAudit.setActionTime(Dates.getNow(""));
//							bAudit.setOwner(userId) ;

							bAuditNew.setNewRecord(true) ;
							auditList.add(bAuditNew) ;
						}
					}

//					li_row = this.ids_audits.insertRow();
					BudgetAudit bAudit = new BudgetAudit() ;
					audIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
					bAudit.setAuditId(audIc) ;
					bAudit.setBudgetId(budId) ;
					bAudit.setActionType(ls_action_type) ;
					bAudit.setAuthority(ls_auth) ;
					bAudit.setIcHeader(new BigDecimal(ls_ic_header)) ;
					bAudit.setIcLine(new BigDecimal(ls_ic_line)) ;
					bAudit.setParentType(bType) ;
					bAudit.setLineType(bType) ;
					bAudit.setActionCode(bAction) ;
					bAudit.setActionType(ls_action_type) ;
					bAudit.setActionMake("1") ;
					bAudit.setActionDate(Dates.today("", userTimeZone)) ;
					bAudit.setActionTime(Dates.getNow("", userTimeZone)) ;
					bAudit.setPriorAmt(new BigDecimal("0")) ;
					bAudit.setTranAmt(bdAmt) ;
					bAudit.setOwner(userId) ;
					bAudit.setExportCode("");
					bAudit.setExportDate("");
					bAudit.setBOperator(new BigDecimal("1")) ;
					bAudit.setNewRecord(true) ;
					auditList.add(bAudit) ;
				}
				else
				{
		            String sql = "From BudgetAudit bAudit Where bAudit.icLine = ? and bAudit.budgetId = ?" +
                    " AND bAudit.actionCode = ? AND bAudit.actionMake = '1'";
		            Object [] args = {ls_ic_line, budId.toString(), ls_prev_action_code};
		            org.hibernate.type.Type [] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};

		            tempList = dbs.query(sql, args, types) ;
		            auditList = this.appendAuditList(tempList,auditList) ;

					int li_row = auditList.size() ;

					if(li_row > 0)
					{
						String sCol1[] = {"IcLine", "BudgetId", "ActionCode"};
						String sVal1[] = {ls_ic_line, budId.toString(), ls_prev_action_code};
						String sOper1[] = {"=", "=", "="};
						li_row = this.findAudit(auditList,sCol1, sOper1, sVal1 );
					}
					// KK also check for li_row == 1
					if (li_row >= 1)
					{
						BudgetAudit bAudit = (BudgetAudit) auditList.get(li_row - 1) ;

//						BudgetAudit bAuditNew = this.copyBudgetAudit(bAudit) ;
//						audIc = new BigDecimal(ukg.getUniqueKey().toString()) ;

//						bAuditNew.setAuditId(audIc) ;
//						bAuditNew.setActionType(ls_action_type) ;
//						bAuditNew.setBOperator(new BigDecimal(-1)) ;
//						bAuditNew.setActionMake(bMake) ;
//						bAudit.setActionDate(Dates.today("")) ;

						bAudit.setBOperator(new BigDecimal("-1")) ;
						bAudit.setActionMake(bMake) ;

//						bAuditNew.setNewRecord(true) ;
//						auditList.add(bAuditNew) ;
					}
					if(bAction.equals("2"))
					{
			            String newSql = "From BudgetAudit bAudit Where bAudit.icLine = ? and bAudit.budgetId = ?" +
	                    " AND bAudit.actionCode = '1' AND bAudit.actionType = '1' AND bAudit.actionMake = 'A'" ;
			            args = new Object[2];
			            types = new org.hibernate.type.Type[2];
			            args[0] = ls_ic_line;
			            types[0] = Hibernate.STRING; 
			            args[1] = budId.toString();
			            types[1] = Hibernate.STRING;
			            
			            tempList = dbs.query(newSql, args, types) ;
			            auditList = this.appendAuditList(tempList,auditList) ;

						li_row = auditList.size() ;

						if(li_row > 0)
						{
							String sCol1[] = {"IcLine", "BudgetId", "ActionCode"};
							String sVal1[] = {ls_ic_line, budId.toString(), "1"};
							String sOper1[] = {"=", "=", "="};
							li_row = this.findAudit(auditList,sCol1, sOper1, sVal1 );
						}
						if(li_row > 0)
						{
							BudgetAudit bAudit = (BudgetAudit) auditList.get(li_row - 1) ;
							BudgetAudit bAuditNew = this.copyBudgetAudit(bAudit) ;

							audIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
							bAuditNew.setAuditId(audIc) ;
							bAuditNew.setActionMake("1") ;
							bAuditNew.setBOperator(new BigDecimal("1")) ;
							bAuditNew.setNewRecord(true) ;
							auditList.add(bAuditNew) ;
						}

			            newSql = "From BudgetAudit bAudit Where bAudit.icLine = ? and bAudit.budgetId = ?" +
	                    " AND bAudit.actionCode = '1' AND bAudit.actionMake = 'A'" ;
			            
			            args = new Object[2];
			            types = new org.hibernate.type.Type[2];
			            args[0] = ls_ic_line;
			            types[0] = Hibernate.STRING;
			            args[1] = budId.toString();
			            types[1] = Hibernate.STRING;;

			            tempList = dbs.query(newSql, args, types) ;
			            auditList = this.appendAuditList(tempList,auditList) ;
						li_row = auditList.size() ;

						String sCol1[] = {"IcLine", "BudgetId", "ActionCode", "ActionMake"};
						String sVal1[] = {ls_ic_line, budId.toString(), "1", "A"};
						String sOper1[] = {"=", "=", "="};
						li_row = this.findAudit(auditList,sCol1, sOper1, sVal1 );
						while(li_row > 0)
						{
							BudgetAudit bAudit = (BudgetAudit) auditList.get(li_row - 1) ;
							bAudit.setActionMake(bMake) ;
							bAudit.setBOperator(new BigDecimal("0")) ;
							li_row = this.findAudit(auditList,sCol1, sOper1, sVal1, li_row );
						}
//						this.ids_audits.setSqlWhere(sql);
					}
				}
			}
		}

		java.util.Collections.sort(auditList, new BudgetOperatorComparator() ) ;

//		this.ids_audits.StringSort("b_operator", false, 'A');
		int li_aud_count = auditList.size();
		int li_err_count = errorList.size() ;
//		LogFile.debug(this.sClassName, this.ids_audits.toString());

//		if(this.status.equals("PASSED"))
//		{
		if (! (li_aud_count > 1 || li_err_count > 1) &&  bEmpty.equalsIgnoreCase("N")) {
 			status = "IGNORE";
		} else {
			for(int li_l = 1; li_l <= li_aud_count; li_l++)
			{
				String ls_acct_string = "";
				BudgetAudit bAudit = (BudgetAudit) auditList.get(li_l - 1) ;
				budId = bAudit.getBudgetId() ;
				BigDecimal bdOperator = bAudit.getBOperator();
				BigDecimal bdTran = bAudit.getTranAmt();
				bdTran = bdOperator.multiply(bdTran);
				int ll_row = findBudget(budgetList, budId) ;
				if(ll_row > 0)
				{
					BudgetCenter bud = (BudgetCenter) budgetList.get(ll_row - 1) ;
					for(int li_b = 1; li_b < 16; li_b++ )
					{
						String sField = "getBudget" + String.valueOf(li_b);
						String	sData = "" ;
						try {
					           Class[] types = new Class[] {};
					           Method meth = bud.getClass().getMethod(sField,types) ;
					           sData  = (String) meth.invoke(bud,new Object[0]) ;
					     }
					     catch (Throwable e) {
					    	 Log.error(this, "Budget class reflection error get" + sField + e.toString());
				         }

						if(Utility.isEmpty(ls_acct_string))
						{
							ls_acct_string = sData;
						}
						else
						{
							if(!Utility.isEmpty(sData)){	ls_acct_string = ls_acct_string + ";" + sData;		}
						}
					}

					String ls_action = bAudit.getActionCode() ;
					ls_action_field = "";
					BigDecimal bdOrigAmount = new BigDecimal("0") ;
					if(ls_action.equals("1"))
					{
						ls_action_field = "pre_encumbered";
						bdOrigAmount = bud.getPreEncumbered() ;
					}
					else if(ls_action.equals("2"))
					{
						ls_action_field = "encumbered";
						bdOrigAmount = bud.getEncumbered() ;
					}
					else if(ls_action.equals("3"))
					{
						ls_action_field = "expensed";
						bdOrigAmount = bud.getExpensed() ;
					}
					BigDecimal bdBudget = bud.getBudgeted().add(budgetIncrement(bud.getSpecificPeriod(),bud.getAmountIncrement(),bud.getBudgetIncrementDate(),bud.getBudgetPeriod()));
					BigDecimal bdNewAmount = bdOrigAmount.add(bdTran);
					//if(bdTran.compareTo(Form.bdZero) > -1)
					//{
						bdTotalTran = bdTotalTran.add(bdTran);
					//}

					BigDecimal budgeted = bud.getBudgeted().add(budgetIncrement(bud.getSpecificPeriod(),bud.getAmountIncrement(),bud.getBudgetIncrementDate(),bud.getBudgetPeriod()));
					BigDecimal pre = bud.getPreEncumbered();
					BigDecimal emc = bud.getEncumbered() ;
					BigDecimal exp = bud.getExpensed();

					BigDecimal bdCurBal = budgeted.subtract(pre).subtract(emc).subtract(exp) ;
					String		tranType = "" ;
					BigDecimal	tranIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
					bud.setLastTranId(tranIc) ;
					bud.setLastChangeBy(bAudit.getOwner()) ;
					bud.setLastChangeDate(Dates.getCurrentDate(userTimeZone)) ;
					bud.setLastAuditId(bAudit.getAuditId()) ;

					if(ls_action.equals("1"))
					{
						bud.setPreEncumbered(bdNewAmount) ;
						tranType = "PRE" ;
					}
					else if(ls_action.equals("2"))
					{
						bud.setEncumbered(bdNewAmount) ;
						tranType = "ENC" ;
					}
					else if(ls_action.equals("3"))
					{
						bud.setExpensed(bdNewAmount) ;
						tranType = "EXP" ;
					}

					bud.setLastAuditId(bAudit.getAuditId()) ;

					// Get values after change
					pre = bud.getPreEncumbered();
					emc = bud.getEncumbered() ;
					exp = bud.getExpensed();

					BigDecimal bdBudAmt = budgeted.subtract(pre);
					bdBudAmt = bdBudAmt.subtract(emc).subtract(exp);

					// Save budget Back to list
					budgetList.set(ll_row - 1, bud) ;

					/* Add Transaction Record */

					bAudit.setBalance(bdBudAmt) ;

					bAudit.setPriorAmt(bdOrigAmount) ;
					BigDecimal bdBal = bdBudget.subtract(bdOrigAmount);

					//	Checks out the new total with the Tolerance
					 if(!bud.getStatus().equals("03"))
					 {
					 	BigDecimal bdtemp = bdTolerance.multiply(new BigDecimal("0.01")).multiply(bdBudget);
					 	BigDecimal bdTemp1 = bdBudAmt.add(bdtemp);
					 	if(bdTemp1.compareTo(new BigDecimal("0")) > -1)
					 	{
					 		if(bdTemp1.compareTo(new BigDecimal("0")) < 0)
					 		{
								if (bTolErr.equals("W")) {
									//	Warn on non-existent budgets
						 			this.budgetError("Warning! Budget Not within Tolerance", ls_acct_string, ls_auth, bdBudAmt.toString(), budgeted.toString(), 2);
					 				status = "WARNING";
								} else if (bTolErr.equals("I")) {
									//	Ignore budget exists error
					 				status = "IGNORE";
								} else {
						 			this.budgetError("Budget Not within Tolerance", ls_acct_string, ls_auth, bdBudAmt.toString(), budgeted.toString(), 2);
						 			if(status.equals("FAILED"))
						 			{
						 				status = "WARNING";
						 			}
								}
					 		}
					 		if(! status.equals("FAILED") && ! status.equals("WARNING"))
					 		{
					 			status = "PASSED";
					 		}
					 	}
				 		else
				 		{
				 			// Error, Over Budgeted amount
							if (bOverErr.equals("W")) {
								//	Warn on non-existent budgets
								this.budgetError("Warning! Over Budget", ls_acct_string, ls_auth, bdCurBal.toString(), budgeted.toString(), ll_row);
				 				status = "WARNING";
							} else if (bOverErr.equals("I")) {
								//	Ignore budget exists error
				 				status = "IGNORE";
							} else {
								status = "FAILED";
								this.budgetError("Over Budget", ls_acct_string, ls_auth, bdCurBal.toString(), budgeted.toString(), ll_row);
							}
				 		}
					 }
				}	//ll_row > 0
			}
			if (status.equals("FAILED")) {
				returnStatus = status ;
			} else if (! returnStatus.equals("FAILED")) {
				returnStatus = status ;
			}
		}

		//rest of the code show to display results
//		this.ids_audits.setSqlWhere(ls_aud_pos_select);

		if (returnStatus.equals("FAILED")) {
			incomingRequest.put("budgetCenterList", new ArrayList()) ;
			incomingRequest.put("budgetAuditList", new ArrayList()) ;
		} else {
			incomingRequest.put("budgetCenterList", budgetList) ;
			incomingRequest.put("budgetAuditList", auditList) ;
		}
		incomingRequest.put("errorList",errorList) ;
		this.setStatus(Status.SUCCEEDED);

	 return  returnStatus;

	}

	public String getStatus(String code)
	{
		String		sStat = "FAILED" ;
		if (code == null) code = "F" ;
		if (code.equals("W")) sStat = "WARNING" ;
		if (code.equals("I")) sStat = "IGNORE" ;
		return sStat ;
	}

	public BigDecimal getPrevRev(BigDecimal aic, String po, BigDecimal rel, BigDecimal arev)
	{
		boolean bFoundPrev = false;
		List		poList ;
		String	sql = "" ;

		BigDecimal ic = aic ;
		BigDecimal rev = arev ;

		while(!bFoundPrev && rev.compareTo(new BigDecimal("0")) >= 0)
		{
			rev = rev.subtract(new BigDecimal("1"));

			sql = "From PoHeader poh Where poh.poNumber =? AND " +
					"poh.releaseNumber = ? AND poh.revisionNumber = ?";
			Object [] args = {po, rel.toString(), rev.toString()};
			org.hibernate.type.Type [] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};

			try
			{
				poList = dbs.query(sql, args, types);
			}
			catch (Exception e)
			{
				poList = new ArrayList();
			}

			if(poList.size() > 0)
			{
				PoHeader poHeader = (PoHeader) poList.get(0) ;
				String status = poHeader.getStatus() ;
				ic = poHeader.getIcPoHeader() ;
				if(rev.compareTo(new BigDecimal("0")) == 0)
				{
					break ;
				}
				if(!status.equals("9020"))
				{
					bFoundPrev = true;
				}
			} else {
				if(rev.compareTo(new BigDecimal("0")) == 0)
				{
					break ;
				}
			}
		}
		return ic;
	}

	private int findBudget(List bList, String[] sCol,String[] sOper,String[] sVal, boolean wildCards)
	{
		int found=0;

		List 		foundList		= new ArrayList() ;
		for (int row = 0; row < bList.size(); row++)
		{
			BudgetCenter bud = (BudgetCenter) bList.get(row) ;
			boolean	ok = true ;
			for (int col = 0; col < sCol.length; col++)
			{
				if (sCol[col] == null) continue ;
				String	ls_field = "get" + sCol[col] ;
				String	ls_data = null ;
				try {
			           Class[] types = new Class[] {};
			           Method meth = bud.getClass().getMethod(ls_field,types) ;
			           ls_data  = (String) meth.invoke(bud,new Object[0]) ;
			           if (ls_data == null) ls_data = "" ;


			           ls_data = ls_data.replaceAll("%", ".*");
			           if (wildCards && ls_data.indexOf("*") >= 0) {
			        	   if (! this.matchPattern(sVal[col], ls_data)) {
			        		   ok = false ;
			        		   break ;
			        	   }
			           } else {
				           if (! sVal[col].equalsIgnoreCase(ls_data.trim())) {
				        	   ok = false;
				        	   break ;
				           }
			           }
			     }
			     catch (Throwable e) {
			    	 Log.error(this, "BudgetCenter class reflection error " + ls_field + " - " + e.toString());
		         }
			}
			if (ok)
			{
				found = row + 1;
				break ;
			}
		}
		return found ;
	}

	private int findAudit(List aList, String[] sCol,String[] sOper,String[] sVal) {
		return findAudit(aList, sCol, sOper, sVal, 0) ;
	}

	private int findAudit(List aList, String[] sCol,String[] sOper,String[] sVal, int sRow)
	{
		int found=0;
		if (sRow < 0) sRow = 0 ;
		for (int row = sRow; row < aList.size(); row++)
		{
			BudgetAudit bud = (BudgetAudit) aList.get(row) ;
			boolean	ok = true;
			for (int col = 0; col < sCol.length; col++)
			{
				if (sCol[col] == null) continue ;
				String	ls_field = "get" + sCol[col] ;
				String	ls_data = null ;
				try {
			           Class[] types = new Class[] {};
			           Method meth = bud.getClass().getMethod(ls_field,types) ;
			           String ls_type = meth.getReturnType().toString() ;
			           if (ls_type.indexOf("BigDecimal") >= 0) {
			        	   BigDecimal ld = (BigDecimal) meth.invoke(bud,new Object[0]) ;
			        	   ls_data = ld.toString();
			           } else {
			        	   ls_data  = (String) meth.invoke(bud,new Object[0]) ;
			           }
			           if (ls_data == null) ls_data = "" ;

			           if (! sVal[col].equalsIgnoreCase(ls_data.trim())) {
			        	   ok = false;
			        	   break ;
			           }
			     }
			     catch (Throwable e) {
			    	 Log.error(this, "BudgetAudit class reflection error " + ls_field + " - " + e.toString());
		         }
			}
			if (ok)
			{
				found = row + 1;
				break ;
			}
		}
		return found ;
	}

	private int findBudget(List bList, BigDecimal budId)
	{
		int found=0;
		for (int row = 0; row < bList.size(); row++)
		{
			BudgetCenter bud = (BudgetCenter) bList.get(row) ;
			if (bud.getBudgetId().compareTo(budId) == 0) {
				found = row + 1;
				break ;
			}
		}

		return found ;

	}

	private int findBudget(List bList, String budId)
	{
		return findBudget(bList, new BigDecimal(budId)) ;
	}

	private BudgetAudit copyBudgetAudit(BudgetAudit originalBudgetAudit)
	{

		BudgetAudit	budgetAudit = new BudgetAudit();

		budgetAudit.setAuditId(originalBudgetAudit.getAuditId());
		budgetAudit.setBudgetId(originalBudgetAudit.getBudgetId());
		budgetAudit.setAuthority(originalBudgetAudit.getAuthority());
		budgetAudit.setIcHeader(originalBudgetAudit.getIcHeader());
		budgetAudit.setIcLine(originalBudgetAudit.getIcLine());
		budgetAudit.setActionCode(originalBudgetAudit.getActionCode());
		budgetAudit.setActionType(originalBudgetAudit.getActionType());
		budgetAudit.setActionMake(originalBudgetAudit.getActionMake());
		budgetAudit.setActionDate(originalBudgetAudit.getActionDate());
		budgetAudit.setActionTime(originalBudgetAudit.getActionTime());
		budgetAudit.setPriorAmt(originalBudgetAudit.getPriorAmt());
		budgetAudit.setBOperator(originalBudgetAudit.getBOperator()) ;
		budgetAudit.setTranAmt(originalBudgetAudit.getTranAmt());
		budgetAudit.setParentType(originalBudgetAudit.getParentType());
		budgetAudit.setLineType(originalBudgetAudit.getLineType());
		budgetAudit.setOwner(originalBudgetAudit.getOwner());
		budgetAudit.setBudgetFlag(originalBudgetAudit.getBudgetFlag());
		budgetAudit.setExportCode(originalBudgetAudit.getExportCode());
		budgetAudit.setExportDate(originalBudgetAudit.getExportDate());
		budgetAudit.setExportGroup(originalBudgetAudit.getExportGroup());

		return budgetAudit;
	}

	private BudgetCenter copyBudgetCenter(BudgetCenter originalBudgetCenter)
	{

		BudgetCenter	budgetCenter = new BudgetCenter();

		budgetCenter.setBudgetId(originalBudgetCenter.getBudgetId());
		budgetCenter.setBudgetPeriod(originalBudgetCenter.getBudgetPeriod());
		budgetCenter.setBudget1(originalBudgetCenter.getBudget1());
		budgetCenter.setBudget2(originalBudgetCenter.getBudget2());
		budgetCenter.setBudget3(originalBudgetCenter.getBudget3());
		budgetCenter.setBudget4(originalBudgetCenter.getBudget4());
		budgetCenter.setBudget5(originalBudgetCenter.getBudget5());
		budgetCenter.setBudget6(originalBudgetCenter.getBudget6());
		budgetCenter.setBudget7(originalBudgetCenter.getBudget7());
		budgetCenter.setBudget8(originalBudgetCenter.getBudget8());
		budgetCenter.setBudget9(originalBudgetCenter.getBudget9());
		budgetCenter.setBudget10(originalBudgetCenter.getBudget10());
		budgetCenter.setBudget11(originalBudgetCenter.getBudget11());
		budgetCenter.setBudget12(originalBudgetCenter.getBudget12());
		budgetCenter.setBudget13(originalBudgetCenter.getBudget13());
		budgetCenter.setBudget14(originalBudgetCenter.getBudget14());
		budgetCenter.setBudget15(originalBudgetCenter.getBudget15());
		budgetCenter.setBudgeted(originalBudgetCenter.getBudgeted());
		budgetCenter.setPreEncumbered(originalBudgetCenter.getPreEncumbered());
		budgetCenter.setEncumbered(originalBudgetCenter.getEncumbered());
		budgetCenter.setExpensed(originalBudgetCenter.getExpensed());
		budgetCenter.setOwner(originalBudgetCenter.getOwner());
		budgetCenter.setOwnerPassword(originalBudgetCenter.getOwnerPassword());
		budgetCenter.setStatus(originalBudgetCenter.getStatus());
		budgetCenter.setApproved(originalBudgetCenter.getApproved());
		budgetCenter.setProjectId(originalBudgetCenter.getProjectId());
		budgetCenter.setComments(originalBudgetCenter.getComments());

		return budgetCenter;
	}


	private List	executeSql(String sql )
	{
		List	tempList = new ArrayList() ;
        try
        {
            tempList = dbs.query(sql);
        }
        catch (Exception e)
        {
            Log.error(this, "Error occurred executing SQL: " + sql);
        }
		return tempList ;
	}

	public List	appendAuditList(List fromList, List toList)
	{
		int	cx = fromList.size() ;
		for (int lx = 0; lx < cx; lx++) {
			BudgetAudit	ba = (BudgetAudit) fromList.get(lx) ;
			toList.add(ba) ;
		}
		return toList ;
	}

	public List	appendBudgetList(List fromList, List toList)
	{
		int	cx = fromList.size() ;
		for (int lx = 0; lx < cx; lx++) {
			BudgetCenter 	bc = (BudgetCenter) fromList.get(lx) ;
			toList.add(bc) ;
		}
		return toList ;
	}


	public void budgetError(String asError, String asBudId, String asAuth, String asBal, String asInitial, int aiError )
	{
		Hashtable ht = new Hashtable() ;
		ht.put("budgetError", asError) ;
		ht.put("budgetId",asBudId) ;
		ht.put("budgetAuth", asAuth) ;
		ht.put("budgetBalance", new BigDecimal(asBal)) ;
		ht.put("budgetInitial",new BigDecimal(asInitial)) ;
		ht.put("errorSeverity",String.valueOf(aiError));
		errorList.add(ht) ;
	}

	public boolean matchPattern(String sData, String wildCardData)
	{
		boolean isMatch = false;
		try
        {
			String patternString = wildCardData.replaceAll("%", ".*");
            // Compile regular expression
			if (patternString.equals("*")) {
				isMatch = true ;
			} else if  (patternString.startsWith("*")) {
				isMatch = sData.endsWith(patternString.substring(1)) ;
			} else if (patternString.endsWith("*")) {
				isMatch = sData.startsWith(patternString.substring(0, patternString.length() - 1)) ;
			}
        }
        catch (Exception e)
        {
            isMatch = false;
        }
		return isMatch;
	}

	public BigDecimal budgetIncrement(String specificPeriod, BigDecimal amountIncrement, Date budgetIncrementDate, String BudgetPeriod)
	{
		BigDecimal increment = new BigDecimal("0");
		int difMonth = 0;
		int x = 0;
		Date today = new Date();
		int iDay = today.getDate();
		int iMonth = today.getMonth() + 1;
		int iYear = today.getYear() + 1900;
		int FiscalYear = Integer.parseInt(BudgetPeriod);

		if(specificPeriod != null && amountIncrement != null && budgetIncrementDate != null)
		{
			int iDayb = budgetIncrementDate.getDate();
			int iMonthb = budgetIncrementDate.getMonth() + 1;
			int iYearb = budgetIncrementDate.getYear() + 1900;

			if(FiscalYear == iYear)
			{
				increment = increment.add(amountIncrement);
				difMonth = iMonth - iMonthb;
				if(specificPeriod.equalsIgnoreCase("M"))
				{
					while(x < difMonth)
					{
						increment = increment.add(amountIncrement);
						x++;
					}
				}
				else if(specificPeriod.equalsIgnoreCase("Q"))
				{
					x = x + 2;
					while(x < difMonth)
					{
						increment = increment.add(amountIncrement);
						x = x + 3;
					}
				}
				else if(specificPeriod.equalsIgnoreCase("S"))
				{
					x = x + 5;
					while(x < difMonth)
					{
						increment = increment.add(amountIncrement);
						x = x + 6;
					}
				}
			}
		}

		return increment;
	}
}
