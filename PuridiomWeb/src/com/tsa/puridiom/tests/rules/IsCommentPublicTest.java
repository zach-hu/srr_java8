/*
 * Created on June 10, 2005
 * @author  Kelli
 * project: HiltonWeb
 * package com.tsa.puridiom.tests.rules;IsCommentPublicTest.java
 */
package com.tsa.puridiom.tests.rules;

import java.util.HashMap;
import java.util.Map;
import com.tsagate.foundation.rule.RuleManager;

public class IsCommentPublicTest
{
    public boolean rule(String name)
	{
		RuleManager rules = RuleManager.getInstance();
		Map map = new HashMap();
		map.put("DocComment_commentPublic", "N");
		
		return rules.evaluate(name, map);
	}
	public static void main(String[] args)
	{
	    IsCommentPublicTest pr = new IsCommentPublicTest();
		System.out.println("result: " + pr.rule("is-comment-public.xml"));
	}
}
