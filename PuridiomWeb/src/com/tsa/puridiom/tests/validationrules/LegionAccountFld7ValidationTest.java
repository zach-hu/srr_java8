/*
 * Created on Oct 11, 2005
 */
package com.tsa.puridiom.tests.validationrules;

import com.tsa.puridiom.entity.RequisitionHeader;
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
public class LegionAccountFld7ValidationTest {
    
	public boolean rule(String name) {
	    boolean result = false;
	    
	    try {
			RuleManager rules = RuleManager.getInstance();
			Map map = new HashMap();
			
			BigDecimal ic = new BigDecimal(0);
			List accountList1 = new ArrayList();
	
			Account account1 = new Account();
			AccountPK accountPK1 = new AccountPK();
			Account account2 = new Account();
			AccountPK accountPK2 = new AccountPK();
			Account account3 = new Account();
			AccountPK accountPK3 = new AccountPK();
			Account account4 = new Account();
			AccountPK accountPK4 = new AccountPK();
			
			accountPK1.setIcHeader(ic);
			accountPK1.setIcLine(ic);
			accountPK1.setSequence(new BigDecimal(1));
			account1.setComp_id(accountPK1);
			account1.setFld1("NYCM");
			account1.setFld2("10101");
			account1.setFld3("ACCTS");
			account1.setFld7("AAA");
			//VALID & REQUIRED - should pass mandatory & validation checks
			
			accountPK2.setIcHeader(ic);
			accountPK2.setIcLine(ic);
			accountPK2.setSequence(new BigDecimal(2));
			account2.setComp_id(accountPK2);
			account2.setFld1("CSG");
			account2.setFld2("108");
			account2.setFld3("AR234234");
			account2.setFld7("INVALID");
			//NOT VALID & NOT REQUIRED - should pass mandatory check & should fail validation check
			
			accountPK3.setIcHeader(ic);
			accountPK3.setIcLine(ic);
			accountPK3.setSequence(new BigDecimal(3));
			account3.setComp_id(accountPK3);
			account3.setFld1("AAA");
			account3.setFld2("1000");
			account3.setFld3("AUTO");
			account3.setFld7("");
			//VALID (since it is empty) & NOT REQUIRED - should pass mandatory & validation checks
			
			accountPK4.setIcHeader(ic);
			accountPK4.setIcLine(ic);
			accountPK4.setSequence(new BigDecimal(3));
			account4.setComp_id(accountPK4);
			account4.setFld1("NYCM");
			account4.setFld2("10101");
			account4.setFld3("AUTO");
			account4.setFld7("");
			//NOT VALID since it is empty & REQUIRED - should fail mandatory check & pass validation checks
			
			accountList1.add(account1);
			//accountList1.add(account2);
			accountList1.add(account3);
			//accountList1.add(account4);
			
			RequisitionHeader requisitionHeader = new RequisitionHeader();
			
			requisitionHeader.setAccountList(accountList1);
			
			String	organizationId = "LEGION";
			
			DBSession dbsession = new DBSession(organizationId);
			
			map.put("header", requisitionHeader);
			map.put("accounts", accountList1);
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
		LegionAccountFld7ValidationTest pr = new LegionAccountFld7ValidationTest();
		
		//System.out.println("MANDATORY FLD VALIDATION = " + pr.rule("account-fld7-m.xml"));
		System.out.println("VALID FLD VALIDATION = " + pr.rule("account-fld7-v.xml"));
	}
}
