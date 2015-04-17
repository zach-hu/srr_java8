/*
 * Created on Sep 11, 2003 
 */
package com.tsa.puridiom.tests.rules;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.rule.RuleManager;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * @author renzo
 */
public class IsRfqLineFromRequisitionLineTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		RfqLine rfqLine = new RfqLine();
		rfqLine.setIcSource(new BigDecimal("999999"));
		map.put("rfqLine", rfqLine);
		RequisitionLine reqLine = new RequisitionLine();
		reqLine.setIcReqLine(new BigDecimal("999999"));
		map.put("reqLine", reqLine);

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsRfqLineFromRequisitionLineTest pr = new IsRfqLineFromRequisitionLineTest();
		System.out.println(pr.rule("is-rfqline-from-requisitionline.xml"));
	}
}
