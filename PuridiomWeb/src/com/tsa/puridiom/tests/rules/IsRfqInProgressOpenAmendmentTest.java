/*
 * Created on March 25, 2004
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.common.documents.*;
import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kelli
 */
public class IsRfqInProgressOpenAmendmentTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		RfqHeader rfqHeader = new RfqHeader();
//		rfqHeader.setStatus(DocumentStatus.RFQ_INPROGRESS);
		rfqHeader.setStatus(DocumentStatus.RFQ_OPENAMENDMENT);
		
		map.put("rfqHeader", rfqHeader);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRfqInProgressOpenAmendmentTest pr = new IsRfqInProgressOpenAmendmentTest();
		System.out.println(pr.rule("is-rfq-inprogress-open-amendment.xml"));
	}
}
