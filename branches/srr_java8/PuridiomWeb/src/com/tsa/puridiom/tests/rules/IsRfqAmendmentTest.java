/*
 * Created on March 25, 2004
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsRfqAmendmentTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		RfqHeader rfqHeader = new RfqHeader();
//		rfqHeader.setRfqAmendment(null);
		rfqHeader.setRfqAmendment("");
//		rfqHeader.setRfqAmendment(" ");
//		rfqHeader.setRfqAmendment("0000");
//		rfqHeader.setRfqAmendment("0001");
//		rfqHeader.setRfqAmendment("0002");
		
		map.put("rfqHeader", rfqHeader);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRfqAmendmentTest pr = new IsRfqAmendmentTest();
		System.out.println(pr.rule("is-rfq-amendment.xml"));
	}
}
