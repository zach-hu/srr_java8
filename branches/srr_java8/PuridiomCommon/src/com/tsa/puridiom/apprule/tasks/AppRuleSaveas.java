package com.tsa.puridiom.apprule.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class AppRuleSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain appRule */
			AppRulePK comp_id = new AppRulePK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			AppRule	originalAppRule = (AppRule) incomingRequest.get("appRule");
			AppRule	appRule = new AppRule();

			comp_id.setUserId(originalAppRule.getComp_id().getUserId());
			appRule.setAmount(originalAppRule.getAmount());
			comp_id.setUdf1Code(originalAppRule.getComp_id().getUdf1Code());
			comp_id.setUdf2Code(originalAppRule.getComp_id().getUdf2Code());
			comp_id.setUdf3Code(originalAppRule.getComp_id().getUdf3Code());
			comp_id.setUdf4Code(originalAppRule.getComp_id().getUdf4Code());
			comp_id.setUdf5Code(originalAppRule.getComp_id().getUdf5Code());
			comp_id.setUdf6Code(originalAppRule.getComp_id().getUdf6Code());
			comp_id.setUdf7Code(originalAppRule.getComp_id().getUdf7Code());
			appRule.setUdf8Code(originalAppRule.getUdf8Code());
			appRule.setUdf9Code(originalAppRule.getUdf9Code());
			appRule.setUdf10Code(originalAppRule.getUdf10Code());
			appRule.setApproverLevel(originalAppRule.getApproverLevel());
			appRule.setExcludeLess(originalAppRule.getExcludeLess());
			appRule.setFyiApprover(originalAppRule.getFyiApprover());
			appRule.setRequiredApprover(originalAppRule.getRequiredApprover());
			appRule.setNotes(originalAppRule.getNotes());
			appRule.setAdvisor(originalAppRule.getAdvisor());
			appRule.setComp_id(comp_id);

			incomingRequest.put("appRule", appRule);

			AppRuleAdd addTask = new AppRuleAdd();
			appRule = (AppRule) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = appRule;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
