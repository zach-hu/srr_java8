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
public class IsHiltonSessionIdEmptyTest
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
	//	map.put("puridiomSessionId", "000000111111");

		return rules.evaluate(name, map);
	}

	public static void main(String[] args)
	{
		IsHiltonSessionIdEmptyTest pr = new IsHiltonSessionIdEmptyTest();
		System.out.println(pr.rule("is-hiltonsessionid-empty.xml"));
	}
}
