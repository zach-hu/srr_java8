/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.budget.tasks;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility ;
/**
 * @author Administrator
 */
public class BudgetDistribute extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String 		organizationId = (String)incomingRequest.get("organizationId");
		String		userId = (String) incomingRequest.get("userId");
        String      userTimeZone = (String)incomingRequest.get("userTimeZone");
		String		sProcess = (String) incomingRequest.get("formtype") ; // REQ, PO, INV

		String		bAction = (String) incomingRequest.get("budgetAction") ;
		String		bType = (String) incomingRequest.get("budgetType") ;
		String		bYear = (String) incomingRequest.get("budgetYear") ;
		String		bMake = (String) incomingRequest.get("budgetMake") ;
		String		bAuthority = (String) incomingRequest.get("budgetAuthority") ;
		String		bAuthorityUdfField = (String) incomingRequest.get("budgetAuthorityUdfField") ;
		String		bAuthorityUdf = (String) incomingRequest.get("budgetAuthorityUdf") ;
		String		bEmpty = (String) incomingRequest.get("budgetEmpty") ;
		String		bCreate = (String) incomingRequest.get("budgetCreate") ;
		BigDecimal	bdTolerance = (BigDecimal)incomingRequest.get("budgetTolerance") ;
		String		bUdfArray[] = (String[]) incomingRequest.get("budgetUdfArray") ;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");

		BigDecimal	bdZero = new BigDecimal(0) ;

		List			ll6List = (List) incomingRequest.get("accountList") ;
		List			accountList = new ArrayList() ;
		List			usedList = new ArrayList() ;
		List			lineList = null ;

		String		status = "";

		int iRet = 0, items = 0;
		String ls_status = "", ls_lin_ic = "";
		BigDecimal  bdLinTotal = new BigDecimal("0.00");

		BigDecimal	bdLineKey = new BigDecimal(0);
		BigDecimal	bdAccountIc = new BigDecimal(0);
		BigDecimal	bdLinQty = new BigDecimal(0);
		BigDecimal	bdTemp1 = new BigDecimal(0);
		BigDecimal	bdTemp2 = new BigDecimal(0);
		BigDecimal	bdTemp3 = new BigDecimal(0);
		BigDecimal	bdTaxAmt = new BigDecimal(0);
		BigDecimal	bdOtherAmt = new BigDecimal(0);
		BigDecimal	bdShipAmt = new BigDecimal(0);
		BigDecimal	bdOtherTaxAmt = new BigDecimal(0);
		BigDecimal	bdShipTaxAmt = new BigDecimal(0);
		BigDecimal	bdDiscAmt = new BigDecimal(0);
		InvoiceHeader		iHdr = null ;
		RequisitionHeader	rHdr = null ;
		PoHeader				pHdr = null ;

		String			sDepartment = "" ;
		String			sRequisitioner = "" ;

		if (sProcess.equals("INV")) {
			iHdr = (InvoiceHeader) incomingRequest.get("header") ;
			bYear = iHdr.getFiscalYear() ;
			incomingRequest.put("header", iHdr) ;
			lineList = (List) incomingRequest.get("invoiceLineList") ;
		} else if(sProcess.equals("PO")) {
			pHdr = (PoHeader) incomingRequest.get("poHeader") ;
			lineList = (List) incomingRequest.get("poLineList") ;
			bYear = pHdr.getFiscalYear() ;
			incomingRequest.put("header", pHdr) ;
		} else if (sProcess.equals("REQ")) {
			rHdr = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			lineList = (List) incomingRequest.get("requisitionLineList") ;
			bYear = rHdr.getFiscalYear() ;
			incomingRequest.put("header", rHdr) ;
		}

		incomingRequest.put("lineList", lineList) ;
		incomingRequest.put("budgetYear", bYear) ;

//		this.ids_items.setBReset(true);
		int ll_lines = lineList.size() ;

		for(int ll2 = 1; ll2 <= ll_lines; ll2++)
		{
			String			sCommodity = "" ;
			if(sProcess.equals("PO"))
			{
				PoLine pLine = (PoLine) lineList.get(ll2 - 1) ;
				ls_status = pLine.getStatus() ;
				bdLineKey = pLine.getIcReqLine() ;
				if (bdLineKey.compareTo(bdZero) == 0) {bdLineKey = pLine.getIcLineKey() ; }
				bdLinQty = pLine.getQuantity() ;
				bdTemp1 = pLine.getQuantity();
				bdTemp2 = pLine.getUnitPrice() ;
				bdTemp3 = pLine.getUmFactor() ;

				bdTaxAmt = pLine.getTaxAmount() ;
				bdOtherAmt = pLine.getOtherCharges() ;
				bdShipAmt = pLine.getShippingCharges() ;
				bdOtherTaxAmt = pLine.getOtherTax() ;
				bdShipTaxAmt = pLine.getShippingTax() ;
				bdDiscAmt = pLine.getDiscountAmount() ;
				bdLinTotal = bdTemp1.multiply(bdTemp2).multiply(bdTemp3).setScale(2, BigDecimal.ROUND_UP);
				sDepartment = pLine.getDepartmentCode() ;
				sRequisitioner = pLine.getRequisitionerCode() ;
				sCommodity = pLine.getCommodity() ;
				bdAccountIc = pLine.getIcAccount() ;
			}
			else if(sProcess.equals("REQ"))
			{
				RequisitionLine rLine = (RequisitionLine) lineList.get(ll2 - 1) ;
				ls_status =rLine.getStatus() ;
				bdLineKey = rLine.getIcReqLine() ;
				bdLinQty = rLine.getQuantity() ;
				bdTemp1 = rLine.getQuantity();
				bdTemp2 = rLine.getUnitPrice() ;
				bdTemp3 = rLine.getUmFactor() ;

				bdTaxAmt = rLine.getTaxAmount() ;
				bdOtherAmt = rLine.getOtherCharges() ;
				bdShipAmt = rLine.getShippingCharges() ;
				bdOtherTaxAmt = rLine.getOtherTaxAmount() ;
				bdShipTaxAmt = rLine.getShippingTaxAmt() ;
				bdDiscAmt = rLine.getDiscountAmount() ;

				bdLinTotal = bdTemp1.multiply(bdTemp2).multiply(bdTemp3).setScale(2, BigDecimal.ROUND_UP);
				bdLinTotal.add(bdTaxAmt) ;
				bdLinTotal.add(bdOtherAmt) ;
				bdLinTotal.add(bdShipTaxAmt) ;
				bdLinTotal.subtract(bdDiscAmt) ;

				sDepartment = rLine.getDepartmentCode() ;
				sRequisitioner = rLine.getRequisitionerCode() ;
				sCommodity = rLine.getCommodityCode() ;
				bdAccountIc = rLine.getIcAccount() ;

				if (ls_status.equals("99")) {
					ls_status = "98";
				}
			}
			else if (sProcess.equals("INV"))
			{
				InvoiceLine iLine = (InvoiceLine) lineList.get(ll2 - 1) ;
				ls_status =iLine.getStatus() ;
				bdLineKey = iLine.getIcIvcLine() ;
				bdLinQty = iLine.getQuantity() ;
				bdTemp1 = iLine.getQuantity();
				bdTemp2 = iLine.getUnitPrice() ;
				bdTemp3 = iLine.getUmFactor() ;

				bdTaxAmt = iLine.getTaxAmount() ;
				bdOtherAmt = new BigDecimal(0) ;
				bdShipAmt = new BigDecimal(0) ;
				bdOtherTaxAmt = new BigDecimal(0) ;
				bdShipTaxAmt = new BigDecimal(0) ;
				bdDiscAmt = iLine.getDiscountAmount() ;

				bdLinTotal = bdTemp1.multiply(bdTemp2).multiply(bdTemp3).setScale(2, BigDecimal.ROUND_UP);
				bdLinTotal.add(bdTaxAmt) ;
				bdLinTotal.add(bdOtherAmt) ;
				bdLinTotal.add(bdShipTaxAmt) ;
				bdLinTotal.subtract(bdDiscAmt) ;
				sCommodity = iLine.getCommodity() ;
				sDepartment = iHdr.getDepartmentCode() ;
				sRequisitioner = "" ;

				int iSize = 0 ;
				if (iLine.getAccountList() != null) {
					iSize = iLine.getAccountList().size() ;
				}
				if (iSize == 0) {
					bdAccountIc = new BigDecimal(0) ;
				} else {
					bdAccountIc = iLine.getIcIvcLine() ;
				}

				BigDecimal icLineKey = iLine.getIcPoLine() ;
				if (icLineKey.compareTo(new BigDecimal(0)) != 0 ) {
					bdLineKey = icLineKey ;
		            String sql = "From PoLine p Where p.icPoLine = ?";
		            Object [] args = {icLineKey.toString()};
		            Type [] types = {Hibernate.STRING};
					List		polList = dbs.query(sql, args, types) ;

		            if (polList.size() > 0) {
		            	PoLine pol = (PoLine) polList.get(0) ;
		            	if (pol.getIcReqLine().compareTo(new BigDecimal(0)) > 0) {
		            		bdLineKey = pol.getIcReqLine() ;
		            	}
		            	sRequisitioner = pol.getRequisitionerCode() ;
		            	sDepartment = pol.getDepartmentCode() ;
		            	sCommodity = pol.getCommodity() ;
		            } else {
						// No Authority for Budget selected
	//					status = "FAILED";
		            }
				}
			} else {
				ls_status = "10";
			}

			if (bdAccountIc == null) { bdAccountIc = bdZero ; }

			BigDecimal bdLinRun = bdLinTotal;
			if(!ls_status.equals("98") )
			{
				if(bdLinQty.compareTo(bdZero) == 0)
				{
					status = "FAILED";
					continue;
				}

				if(bdLinTotal.compareTo(bdZero) == 0)
				{
					status = "FAILED";
					continue;
				}
			}

			String ls_auth = "";

			if (bAuthority.equalsIgnoreCase("department")||bAuthority.equalsIgnoreCase("division"))
			{
				ls_auth = sDepartment;
			} else if(bAuthority.equalsIgnoreCase("cost center")) {
//				ls_auth = included in each account row
			} else
			{
				ls_auth = sRequisitioner ;
			}
//			LogFile.debug(this.sClassName, "authority: " + ls_auth);

			List fList = filterAccountsByIc(ll6List, bdAccountIc) ;
			int acts = fList.size() ;

//			LogFile.debug(this.sClassName, "account ic: " + ls_lin_acc + " has accounts: " + acts);
			for(int ll6 = 1; ll6 <= acts; ll6++)
			{
				Account account = (Account) fList.get(ll6 - 1) ;
				char method = account.getAllocMethod().charAt(0) ;
				BigDecimal bdAccAmt = new BigDecimal("0.00");
				BigDecimal bdSeq = account.getComp_id().getSequence() ;
				if(method == 'Q') //**allocating by qty
				{
					bdAccAmt = account.getAllocQty();
					bdAccAmt = bdAccAmt.divide(bdLinQty, 2, BigDecimal.ROUND_HALF_UP).multiply(bdLinTotal);
				}
				else
				{
					if(method == 'A')
					{
						bdAccAmt = account.getAllocAmount();
					}
					else
					{
						bdAccAmt = account.getAllocAmount();
						String			acKey = bdAccountIc.toString() + bdSeq.toString() ;
						if (usedList.contains(acKey)) {
							bdAccAmt = new BigDecimal(0) ;
						} else {
							usedList.add(acKey) ;
						}

						//*** Recalculate amount based on percentage
//						bdAccAmt = account.getAllocPercent().multiply(bdLinTotal).multiply(new BigDecimal("0.01"));
					}
				}

				items = accountList.size() ;
				account.setAllocAmount(bdAccAmt) ;
				if (ll6 == acts && method == 'Q') {
					account.setAllocAmount(bdLinRun) ;
				}

				Log.debug(this, "Account amt: " + bdAccAmt.toString());

				account.setAccountTitle(bdLineKey.toString()) ;

				if (bAuthority.equalsIgnoreCase("cost center")) {
					try {
				           Class[] types = new Class[] {};
				           Method meth = account.getClass().getMethod(bAuthorityUdfField,types) ;
				           ls_auth  = (String) meth.invoke(account,new Object[0]) ;
				     }
				     catch (Throwable e) {
				    	 Log.error(this, "Budget class reflection error get" + bAuthorityUdfField + e.toString());
			         }
				}
				account.setCommodity(sCommodity) ;
				account.setOwner(ls_auth) ;
				Account acc = copyAccount(account, userTimeZone) ;
				accountList.add(acc) ;

				bdLinRun = bdLinRun.subtract(bdAccAmt);
			}
		}
//		aJDO6.resetFilter();

		// Roll it up - Use alloc_method to flag for delete
		items = accountList.size() ;

		for (int ii = 1;ii <= items; ii++) {
			Account	account = (Account) accountList.get(ii -1) ;

			if (account.getAllocMethod().equals("*")) {
				continue ;
			}
			String sCol[] = new String[16];
			String sVal[] = new String[16];
			String sOper[] = new String[16];
			sCol[0] = "Owner";
			sVal[0] = account.getOwner() ;
			sOper[0] = "=";

			for (int ib = 0; ib < 15; ib++)
			{
				String ls_field = bUdfArray[ib] ;
				if (Utility.isEmpty(ls_field)) { ls_field = ""; }
				//for the puridiom version we are skipping this check.. fields are already named fld.
				//might need to be changed in the future.
				if(ls_field.indexOf("Fld") == 0 || ls_field.indexOf("Commodity") == 0)
				{
					String	ls_data = "";
					try {
				           Class[] types = new Class[] {};
				           Method meth = account.getClass().getMethod("get" + ls_field,types) ;
				           ls_data  = (String) meth.invoke(account,new Object[0]) ;
				     }
				     catch (Throwable e) {
				    	 Log.error(this, "Account class reflection error get " + ls_field + e.toString());
			         }

					if(Utility.isEmpty(ls_data)) {
						ls_data = " ";
					}

					sCol[ib + 1] = ls_field ;
					sVal[ib + 1] = ls_data;
					sOper[ib + 1] = "=";
				}
			}

			int row = ii ;
			BigDecimal bdAmt = account.getAllocAmount();
			row = findAccount(accountList, sCol, sOper, sVal, row + 1) ;
			while (row > 0) {
				Account	acc = (Account) accountList.get(row - 1) ;
				bdAmt = bdAmt.add(acc.getAllocAmount());
				acc.setAllocMethod("*");
				row = findAccount(accountList,sCol, sOper, sVal, row + 1) ;
			}
			Account	acc = (Account) accountList.get(ii - 1);
			acc.setAllocAmount(bdAmt) ;
		}

		// Remove unused
		items = accountList.size() ;
		for (int ii = items - 1;ii >= 0; ii--) {
			Account	acc = (Account) accountList.get(ii) ;
			if (acc.getAllocMethod().equals("*")) {
				accountList.remove(ii) ;
			}
		}

//		LogFile.debug(this.sClassName, methodName + "done with " + items);
		incomingRequest.put("accountLineCount", Integer.toString(accountList.size())) ;
		incomingRequest.put("accountList",accountList) ;

		this.setStatus(Status.SUCCEEDED);

		return null;
	}


	private List filterAccountsByIc(List aList, BigDecimal accIc)
	{
		List	rList = new ArrayList() ;
		for (int row = 0; row < aList.size(); row++)
		{
			Account acc = (Account) aList.get(row) ;
			if (acc.getComp_id().getIcLine().compareTo(accIc) == 0.) {
				rList.add(acc) ;
			}
		}
		return rList ;
	}

	private int findAccount(List aList, String[] sCol, String[] sOper, String[] sVal, int start)
	{
		int found=0;
		if (start > aList.size()) return 0 ;
		for (int row = start - 1; row < aList.size(); row++)
		{
			Account acc = (Account) aList.get(row) ;
			boolean	ok = false;
			for (int col = 0; col < sCol.length; col++)
			{
				if (sCol[col] == null) continue ;
				String	ls_field = "get" + sCol[col] ;
				String	ls_data = "" ;
				ok = true ;
				try {
			           Class[] types = new Class[] {};
			           Method meth = acc.getClass().getMethod(ls_field,types) ;
			           ls_data  = (String) meth.invoke(acc,new Object[0]) ;
			     }
			     catch (Throwable e) {
			    	 Log.error(this, "Account class reflection error get" + ls_field + e.toString());
		         }
			     if (! sVal[col].equalsIgnoreCase(ls_data)) {
		           ok = false;
		           break ;
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

	private Account copyAccount(Account originalAccount, String userTimeZone)
	{
		AccountPK	comp_id = new AccountPK();
		Account	account = new Account();

		comp_id.setIcHeader(originalAccount.getComp_id().getIcHeader());
		comp_id.setIcLine(originalAccount.getComp_id().getIcLine());
		comp_id.setSequence(originalAccount.getComp_id().getSequence());
		account.setAccountType(originalAccount.getAccountType());
		account.setFld1(originalAccount.getFld1());
		account.setFld2(originalAccount.getFld2());
		account.setFld3(originalAccount.getFld3());
		account.setFld4(originalAccount.getFld4());
		account.setFld5(originalAccount.getFld5());
		account.setFld6(originalAccount.getFld6());
		account.setFld7(originalAccount.getFld7());
		account.setFld8(originalAccount.getFld8());
		account.setFld9(originalAccount.getFld9());
		account.setFld10(originalAccount.getFld10());
		account.setFld11(originalAccount.getFld11());
		account.setFld12(originalAccount.getFld12());
		account.setFld13(originalAccount.getFld13());
		account.setFld14(originalAccount.getFld14());
		account.setFld15(originalAccount.getFld15());
		account.setAllocPercent(originalAccount.getAllocPercent());
		account.setAllocAmount(originalAccount.getAllocAmount());
		account.setAccountTitle(originalAccount.getAccountTitle());
		account.setDateEntered(Dates.getCurrentDate(userTimeZone));
		account.setDateExpires(Dates.getCurrentDate(userTimeZone));
		account.setStatus(originalAccount.getStatus());
		account.setOwner(originalAccount.getOwner());
		account.setAllocMethod(originalAccount.getAllocMethod());
		account.setAllocQty(originalAccount.getAllocQty());
		account.setRecQty(originalAccount.getRecQty());
		account.setIcLastRec(originalAccount.getIcLastRec());
		account.setCommodity(originalAccount.getCommodity()) ;
		account.setComp_id(comp_id);
		account.setAllocAmount(originalAccount.getAllocAmount());

		return account ;
	}
}
