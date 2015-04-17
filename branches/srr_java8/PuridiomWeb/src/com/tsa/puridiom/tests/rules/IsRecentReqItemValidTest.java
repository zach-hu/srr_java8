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
public class IsRecentReqItemValidTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		map.put("RecentReqItem_itemNumber", "123321");
		map.put("RecentReqItem_itemLocation", "MAIN");
		map.put("RecentReqItem_requisitionerCode", "KELLI");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRecentReqItemValidTest pr = new IsRecentReqItemValidTest();
		System.out.println(pr.rule("is-recentreqitem-valid.xml"));
	}
}
