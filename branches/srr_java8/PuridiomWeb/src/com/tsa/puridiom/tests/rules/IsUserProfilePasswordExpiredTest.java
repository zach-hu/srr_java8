/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.*;


/**
 * @author Kelli
 */
public class IsUserProfilePasswordExpiredTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();

		map.put("daysAfter", new Integer("100"));
		map.put("organizationId", "PURIDIOM");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsUserProfilePasswordExpiredTest pr = new IsUserProfilePasswordExpiredTest();
		System.out.println(pr.rule("is-userprofile-password-expired.xml"));
	}
}
