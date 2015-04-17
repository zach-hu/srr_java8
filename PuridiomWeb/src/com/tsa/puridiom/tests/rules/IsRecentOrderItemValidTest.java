/*
 * Created on January 21, 2005 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.RecentOrderItem;
import com.tsa.puridiom.entity.RecentOrderItemPK;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelli
 */
public class IsRecentOrderItemValidTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		RecentOrderItem recentOrderItem = new RecentOrderItem();
		RecentOrderItemPK pk = new RecentOrderItemPK();
		
		pk.setBuyerCode("");
		pk.setItemLocation("INVENTORY");
		pk.setItemNumber("2303-24");
		recentOrderItem.setComp_id(pk);
		
		map.put("recentOrderItem", recentOrderItem);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRecentOrderItemValidTest pr = new IsRecentOrderItemValidTest();
		System.out.println(pr.rule("is-recentorderitem-valid.xml"));
	}
}
