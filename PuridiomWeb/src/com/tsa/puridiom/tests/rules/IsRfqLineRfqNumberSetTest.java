/*
 * Created on January 21, 2005
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelli
 */
public class IsRfqLineRfqNumberSetTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		RfqLine rfqLine = new RfqLine();
		
//		rfqLine.setRfqNumber("");
//		rfqLine.setRfqNumber(" ");
//		rfqLine.setRfqNumber("N/A");
//		rfqLine.setRfqNumber("000112");
		rfqLine.setRfqNumber(null);
		
		map.put("rfqLine", rfqLine);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRfqLineRfqNumberSetTest pr = new IsRfqLineRfqNumberSetTest();
		System.out.println(pr.rule("is-rfqline-rfqnumber-set.xml"));
	}
}
