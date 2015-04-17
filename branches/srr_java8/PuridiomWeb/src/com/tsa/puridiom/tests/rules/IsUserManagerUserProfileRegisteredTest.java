/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.*;


/**
 * @author Kelli
 */
public class IsUserManagerUserProfileRegisteredTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();

		map.put("organizationId", "PURIDIOM");
		map.put("userId", "KELLIMSS");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserManagerUserProfileRegisteredTest pr = new IsUserManagerUserProfileRegisteredTest();
		System.out.println(pr.rule("is-usermanager-userprofile-registered.xml"));
	}
}
