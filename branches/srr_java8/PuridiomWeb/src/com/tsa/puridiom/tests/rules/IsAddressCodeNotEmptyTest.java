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
public class IsAddressCodeNotEmptyTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("Address_addressCode", "TSA");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsAddressCodeNotEmptyTest pr = new IsAddressCodeNotEmptyTest();
		System.out.println(pr.rule("is-address-code-not-empty.xml"));
	}
}
