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
public class IsUserProfileLoginLockedTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setRegistered(true);
		userProfile.setLockLogin("Y");
//		userProfile.setUserId("SYSADM");
		map.put("userProfile", userProfile);
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfileLoginLockedTest pr = new IsUserProfileLoginLockedTest();
		System.out.println(pr.rule("is-userprofile-login-locked.xml"));
	}
}
