/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.*;


/**
 * @author Kelli
 */
public class IsUserProfileAccountInactiveTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setRegistered(true);
		userProfile.setStatus("02");
		userProfile.setUserId("SYSADM");
		map.put("userProfile", userProfile);
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfileAccountInactiveTest pr = new IsUserProfileAccountInactiveTest();
		System.out.println(pr.rule("is-userprofile-account-inactive.xml"));
	}
}
