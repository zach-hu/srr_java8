/*
 * Created on Sep 11, 2003 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author renzo
 */
public class IsRfqTypeSolicitationTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("RfqHeader_rfqType", "RQ");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRfqTypeSolicitationTest pr = new IsRfqTypeSolicitationTest();
		System.out.println(pr.rule("is-rfq-type-solicitation.xml"));
	}
}
