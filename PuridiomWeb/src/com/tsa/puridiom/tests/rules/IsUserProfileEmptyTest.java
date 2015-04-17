/*
 * Created on August 9, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelli
 */
public class IsUserProfileEmptyTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		//userProfile.setMailId("TEST");
		map.put("userProfile", userProfile);
		map.put("organizationId", "PURIDIOM");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfileEmptyTest pr = new IsUserProfileEmptyTest();
		System.out.println(pr.rule("is-userprofile-empty.xml"));
	}
}
