/*
 * Created on August 5, 2004
 */
package com.tsa.puridiom.tests.rules;

import com.tsagate.foundation.rule.RuleManager;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelli
 */
public class IsReceiptLineQtyReceivedZeroTest 
{
	public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		
		map.put("ReceiptLine_qtyReceived", "-3.0");

		return rules.evaluate(name, map);
	}
	
	public static void main(String[] args)
	{
		IsReceiptLineQtyReceivedZeroTest pr = new IsReceiptLineQtyReceivedZeroTest();
		System.out.println(pr.rule("is-receiptline-qtyreceived-zero.xml"));
	}
}
