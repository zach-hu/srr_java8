/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.*;


/**
 * @author Kelli
 */
public class IsUserProfileAuthenticatedTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();

		map.put("organizationId", "PURIDIOM");
		//map.put("authenticated", "false");
		map.put("authenticated", "true");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfileAuthenticatedTest pr = new IsUserProfileAuthenticatedTest();
		System.out.println(pr.rule("is-userprofile-authenticated.xml"));
	}
}
