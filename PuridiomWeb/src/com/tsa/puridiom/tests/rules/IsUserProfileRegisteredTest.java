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
public class IsUserProfileRegisteredTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setRegistered(false);
		map.put("userProfile", userProfile);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfileRegisteredTest pr = new IsUserProfileRegisteredTest();
		System.out.println(pr.rule("is-userprofile-registered.xml"));
	}
}
