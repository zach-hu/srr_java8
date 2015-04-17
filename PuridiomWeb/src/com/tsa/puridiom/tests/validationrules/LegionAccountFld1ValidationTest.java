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
public class LegionAccountFld1ValidationTest {
    
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
			
			accountPK2.setIcHeader(ic);
			accountPK2.setIcLine(ic);
			accountPK2.setSequence(new BigDecimal(2));
			account2.setComp_id(accountPK2);
			account2.setFld1("CSG");
			
			accountPK3.setIcHeader(ic);
			accountPK3.setIcLine(ic);
			accountPK3.setSequence(new BigDecimal(3));
			account3.setComp_id(accountPK3);
			account3.setFld1("AAA");
			
			accountList1.add(account1);
			accountList1.add(account2);
			accountList1.add(account3);
			
			RequisitionHeader requisitionHeader = new RequisitionHeader();
			
			requisitionHeader.setAccountList(accountList1);
			
			String	organizationId = "LEGION";
			
			DBSession dbsession = new DBSession(organizationId);
			
			map.put("header", requisitionHeader);
			map.put("accounts", accountList1);
			map.put("isValidationRule", "true");
			map.put("newRules", "false");
			map.put("organizationId", organizationId);
			map.put("dbsession", dbsession);
		
			result = rules.evaluate(name, map);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	public static void main(String[] args) {
		LegionAccountFld1ValidationTest pr = new LegionAccountFld1ValidationTest();
		
		System.out.println(pr.rule("account-fld1-mv.xml"));
	}
}
