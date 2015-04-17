/*
 * Created on June 21, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsMaxLoginAttemptsReachedTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("loginAttempts", new Integer(3));

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsMaxLoginAttemptsReachedTest pr = new IsMaxLoginAttemptsReachedTest();
		System.out.println(pr.rule("is-max-login-attempts-reached.xml"));
	}
}
