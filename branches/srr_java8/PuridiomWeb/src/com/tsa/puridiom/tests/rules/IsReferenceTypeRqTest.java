/*
 * Created on March 18, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsReferenceTypeRqTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("referenceType", "RQ");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsReferenceTypeRqTest pr = new IsReferenceTypeRqTest();
		System.out.println(pr.rule("is-reference-type-rq.xml"));
	}
}
