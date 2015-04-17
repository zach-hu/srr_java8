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
public class IsUserProfilePasswordResetRequiredTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setRegistered(true);
		userProfile.setPassChanged("ADMIN");

		map.put("userProfile", userProfile);
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfilePasswordResetRequiredTest pr = new IsUserProfilePasswordResetRequiredTest();
		System.out.println(pr.rule("is-userprofile-password-reset-required.xml"));
	}
}
