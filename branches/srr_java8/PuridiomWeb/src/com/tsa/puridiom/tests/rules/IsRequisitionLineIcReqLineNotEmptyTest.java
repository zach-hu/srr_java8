/*
 * Created on Feb 10, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsRequisitionLineIcReqLineNotEmptyTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("RequisitionLine_icReqLine", "1235467890");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRequisitionLineIcReqLineNotEmptyTest pr = new IsRequisitionLineIcReqLineNotEmptyTest();
		System.out.println(pr.rule("is-requisitionline-icreqline-not-empty.xml"));
	}
}
