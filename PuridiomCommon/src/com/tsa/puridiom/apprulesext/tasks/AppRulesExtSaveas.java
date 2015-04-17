package com.tsa.puridiom.apprulesext.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class AppRulesExtSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain appRulesExt */
			AppRulesExtPK comp_id = new AppRulesExtPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			AppRulesExt	originalAppRulesExt = (AppRulesExt) incomingRequest.get("appRulesExt");
			AppRulesExt	appRulesExt = new AppRulesExt();

			comp_id.setRuleNumber(originalAppRulesExt.getComp_id().getRuleNumber());
			comp_id.setRuleType(originalAppRulesExt.getComp_id().getRuleType());
			appRulesExt.setRuleOrder(originalAppRulesExt.getRuleOrder());
			appRulesExt.setRuleFilename(originalAppRulesExt.getRuleFilename());
			appRulesExt.setRoutto(originalAppRulesExt.getRoutto());
			appRulesExt.setRuleAction(originalAppRulesExt.getRuleAction());
			appRulesExt.setRuleText(originalAppRulesExt.getRuleText());
			appRulesExt.setNotes(originalAppRulesExt.getNotes());
			appRulesExt.setApproverAmount(originalAppRulesExt.getApproverAmount());
			appRulesExt.setRequiredAuthority(originalAppRulesExt.getRequiredAuthority());
			appRulesExt.setReqdAuthObject(originalAppRulesExt.getReqdAuthObject());
			appRulesExt.setReqdAuthName(originalAppRulesExt.getReqdAuthName());
			appRulesExt.setReqdAuthSource(originalAppRulesExt.getReqdAuthSource());
			appRulesExt.setReqdAuthValue(originalAppRulesExt.getReqdAuthValue());
			appRulesExt.setFyiApprover(originalAppRulesExt.getFyiApprover());
			appRulesExt.setRequiredApprover(originalAppRulesExt.getRequiredApprover());
			appRulesExt.setAdvisor(originalAppRulesExt.getAdvisor());
			appRulesExt.setSuperRule(originalAppRulesExt.getSuperRule());
			appRulesExt.setEnabled(originalAppRulesExt.getEnabled());
			appRulesExt.setComp_id(comp_id);

			incomingRequest.put("appRulesExt", appRulesExt);

			AppRulesExtAdd addTask = new AppRulesExtAdd();
			appRulesExt = (AppRulesExt) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = appRulesExt;
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
