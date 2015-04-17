package com.tsa.puridiom.apprulesext.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AppRulesExtSetValuesPK
{
	public void setValues(Map incomingRequest, AppRulesExt apprulesext ) throws Exception
	{
		try
		{
			String ruleNumberString = (String) incomingRequest.get("AppRulesExt_ruleNumber");
			BigDecimal ruleNumber = new BigDecimal ( ruleNumberString );
			String ruleType = (String ) incomingRequest.get("AppRulesExt_ruleType");
			AppRulesExtPK comp_id = new AppRulesExtPK();
			comp_id.setRuleNumber(ruleNumber);
			comp_id.setRuleType(ruleType);
			apprulesext.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}