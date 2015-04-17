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
public class IsPoLinePoNumberSetTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		PoLine poLine = new PoLine();
		
		poLine.setPoNumber("");
//		poLine.setPoNumber(" ");
//		poLine.setPoNumber("N/A");
//		poLine.setPoNumber("000112");
//		poLine.setPoNumber(null);
		
		map.put("poLine", poLine);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsPoLinePoNumberSetTest pr = new IsPoLinePoNumberSetTest();
		System.out.println(pr.rule("is-poline-ponumber-set.xml"));
	}
}
