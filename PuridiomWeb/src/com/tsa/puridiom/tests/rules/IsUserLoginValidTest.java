/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsUserLoginValidTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setRegistered(true);
		userProfile.setUserPassword("TEST");
		map.put("userProfile", userProfile);
		map.put("password", "TEST");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserLoginValidTest pr = new IsUserLoginValidTest();
		System.out.println(pr.rule("is-user-login-valid.xml"));
	}
}
