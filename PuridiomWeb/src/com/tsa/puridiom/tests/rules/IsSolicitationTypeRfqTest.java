/*
 * Created on Sep 11, 2003 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author renzo
 */
public class IsSolicitationTypeRfqTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		RfqHeader rfqHeader = new RfqHeader();
		rfqHeader.setStatus("2000");
		rfqHeader.setRfqType("RQ");
//		map.put("rfqHeader", rfqHeader);
		map.put("RfqHeader_rfqType", "RQ");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsSolicitationTypeRfqTest pr = new IsSolicitationTypeRfqTest();
		System.out.println(pr.rule("is-solicitation-type-rfq.xml"));
	}
}
