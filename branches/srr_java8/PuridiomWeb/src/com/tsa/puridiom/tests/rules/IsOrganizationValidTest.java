/*
 * Created on Aug 24, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.*;


/**
 * @author Kelli
 */
public class IsOrganizationValidTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("organizationId", "PURIDIOM");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsOrganizationValidTest pr = new IsOrganizationValidTest();
		System.out.println(pr.rule("is-organization-valid.xml"));
	}
}
