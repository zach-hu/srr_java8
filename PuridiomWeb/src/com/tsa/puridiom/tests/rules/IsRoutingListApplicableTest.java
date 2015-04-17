/*
 * Created on Feb 10, 2004 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsRoutingListApplicableTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("RequisitionHeader_status", DocumentStatus.REQ_INPROGRESS);
		//map.put("RequisitionHeader_status", DocumentStatus.REQ_APPROVED);
		map.put("RequisitionHeader_requisitionType", "N");
		
		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRoutingListApplicableTest pr = new IsRoutingListApplicableTest();
		System.out.println(pr.rule("is-routinglist-applicable.xml"));
	}
}
