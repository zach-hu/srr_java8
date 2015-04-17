/*
 * Created on Oct 11, 2005
 */
package com.tsa.puridiom.tests.validationrules;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.rule.RuleManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * @author Kelli
 */
public class LegionLineAccountFld4ValidationTest {
    
	public boolean rule(String name) {
	    boolean result = false;
	    
	    try {
			RuleManager rules = RuleManager.getInstance();
			Map map = new HashMap();
			
			BigDecimal ic = new BigDecimal(0);
			List accountList1 = new ArrayList();
			List accountList2 = new ArrayList();
			List accountList3 = new ArrayList();
	
			Account account1 = new Account();
			AccountPK accountPK1 = new AccountPK();
			Account account2 = new Account();
			AccountPK accountPK2 = new AccountPK();
			Account account3 = new Account();
			AccountPK accountPK3 = new AccountPK();
			
			accountPK1.setIcHeader(ic);
			accountPK1.setIcLine(ic);
			accountPK1.setSequence(new BigDecimal(1));
			account1.setComp_id(accountPK1);
			account1.setFld1("NYCM");
			account1.setFld2("10101");
			account1.setFld4("SOUTH");
			//VALID & REQUIRED - should pass mandatory & validation checks
			
			accountPK2.setIcHeader(ic);
			accountPK2.setIcLine(ic);
			accountPK2.setSequence(new BigDecimal(2));
			account2.setComp_id(accountPK2);
			account2.setFld1("CSG");
			account2.setFld2("108");
			account2.setFld4("");
			//NOT VALID & NOT REQUIRED - should pass mandatory check & should fail validation check
			
			accountPK3.setIcHeader(ic);
			accountPK3.setIcLine(ic);
			accountPK3.setSequence(new BigDecimal(3));
			account3.setComp_id(accountPK3);
			account3.setFld1("CGS");
			account3.setFld2("10101");
			account3.setFld4("");
			//VALID (since it is empty) & NOT REQUIRED - should pass mandatory & validation checks
			
			accountList1.add(account1);
			accountList1.add(account2);
			//accountList1.add(account3);
			
			accountList2.add(account1);
			accountList2.add(account3);
//			accountList2.add(account2);
			
			//test w/ no accounts
			//accountList3.add(account1);
			
			RequisitionLine requisitionLine1 = new RequisitionLine();
			RequisitionLine requisitionLine2 = new RequisitionLine();
			RequisitionLine requisitionLine3 = new RequisitionLine();
			
			requisitionLine1.setAccountList(accountList1);
			requisitionLine2.setAccountList(accountList2);
			requisitionLine3.setAccountList(accountList3);
			
			List lineItemList = new ArrayList();
			
			lineItemList.add(requisitionLine1);
			lineItemList.add(requisitionLine2);
//			lineItemList.add(requisitionLine3);
			
			List	lineItemAccountList = new ArrayList();
			lineItemAccountList.add(accountList1);
			lineItemAccountList.add(accountList2);
//			lineItemAccountList.add(accountList3);
			
			String	organizationId = "LEGION";
			
			DBSession dbsession = new DBSession(organizationId);
			
			map.put("lineitems", lineItemList);
			map.put("accountLineList", lineItemAccountList);
			map.put("isValidationRule", "true");
			map.put("newRules", "true");
			map.put("organizationId", organizationId);
			map.put("dbsession", dbsession);
		
			result = rules.evaluate(name, map);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	public static void main(String[] args) {
		LegionLineAccountFld4ValidationTest pr = new LegionLineAccountFld4ValidationTest();
		
		System.out.println("MANDATORY - " + pr.rule("line-account-fld4-m.xml"));
		//System.out.println("VALIDATION - " + pr.rule("line-account-fld4-v.xml"));
	}
}
