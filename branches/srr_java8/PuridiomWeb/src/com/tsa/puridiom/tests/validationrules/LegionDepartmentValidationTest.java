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
public class LegionDepartmentValidationTest {
    
	public boolean rule(String name) {
	    boolean result = false;
	    
	    try {
			RuleManager rules = RuleManager.getInstance();
			Map map = new HashMap();
			
			BigDecimal ic = new BigDecimal(0);
			
			RequisitionHeader requisitionHeader = new RequisitionHeader();
			
			requisitionHeader.setDepartmentCode("APDEV");
			
			String	organizationId = "LEGION";
			
			DBSession dbsession = new DBSession(organizationId);
			
			map.put("header", requisitionHeader);
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
		LegionDepartmentValidationTest pr = new LegionDepartmentValidationTest();
		
		System.out.println(pr.rule("department-mv.xml"));
	}
}
