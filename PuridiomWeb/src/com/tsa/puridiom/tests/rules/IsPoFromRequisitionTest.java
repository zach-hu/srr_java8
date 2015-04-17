/*
 * Created on Aug 20, 2004
 *
 * @author  * renzo
 * project: HiltonWeb
 * package com.tsa.puridiom.tests.rules;.IsPoFromRequisition.java
 * 
 */
package com.tsa.puridiom.tests.rules;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.rule.RuleManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IsPoFromRequisitionTest
{
    public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		PoHeader header = new PoHeader();
		//header.setIcReqHeader(new BigDecimal("999999"));
		map.put("poHeader", header);
		
		return rules.evaluate(name, map);
	}
	public static void main(String[] args)
	{
	    IsPoFromRequisitionTest pr = new IsPoFromRequisitionTest();
		System.out.println("result: " + pr.rule("is-po-from-req.xml"));
	}
}
