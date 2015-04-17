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
public class CacLineTest {
    
	public boolean rule(String name) {
	    boolean result = false;
	    
	    try {
			RuleManager rules = RuleManager.getInstance();
			Map map = new HashMap();
			
			BigDecimal ic = new BigDecimal(0);
			List accountList1 = new ArrayList();
			List accountList2 = new ArrayList();
			List accountList3 = new ArrayList();
			List lineItemList = new ArrayList();
			List lineItemAccountList = new ArrayList();
	
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
			account1.setFld3("P30");
			//valid
			
			accountPK2.setIcHeader(ic);
			accountPK2.setIcLine(ic);
			accountPK2.setSequence(new BigDecimal(2));
			account2.setComp_id(accountPK2);
			account2.setFld3("INVALID CODE");
			//invalid
			
			accountPK3.setIcHeader(ic);
			accountPK3.setIcLine(ic);
			accountPK3.setSequence(new BigDecimal(3));
			account3.setComp_id(accountPK3);
			account3.setFld3("000");
			//valid
			
			accountList1.add(account1);
			accountList1.add(account2);
			accountList1.add(account3);
			//should fail
			
			accountList2.add(account1);
			accountList2.add(account3);
			// should pass
			
			accountList3.add(account3);
			//should pass
			
			RequisitionLine requisitionLine1 = new RequisitionLine();
			RequisitionLine requisitionLine2 = new RequisitionLine();
			RequisitionLine requisitionLine3 = new RequisitionLine();
			
			requisitionLine1.setAccountList(accountList1);
			requisitionLine2.setAccountList(accountList2);
			requisitionLine3.setAccountList(accountList3);
			
			lineItemList.add(requisitionLine1);
			lineItemList.add(requisitionLine2);
//			lineItemList.add(requisitionLine3);
			
			lineItemAccountList.add(requisitionLine1.getAccountList());
			lineItemAccountList.add(requisitionLine2.getAccountList());
//			lineItemAccountList.add(requisitionLine3.getAccountList());
			
			String	organizationId = "PURIDIOM";
			
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
		CacLineTest pr = new CacLineTest();
		
		System.out.println(pr.rule("cac-line.xml"));
	}
}
