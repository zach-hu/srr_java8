/*
 * Created on March 28, 2007
 */
package com.tsa.puridiom.apprule.tasks;

import java.util.*;

import com.tsa.puridiom.entity.AppRule;
import com.tsa.puridiom.entity.AppRulePK;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author KC
 */
public class AppRuleDeleteByOriginalId extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List	appRuleList = (List) incomingRequest.get("appRuleList");

			String userId = (String) incomingRequest.get("NewApprover_userId");

			for (int i=0; i < appRuleList.size(); i++)
			{
				AppRule appRule = (AppRule) appRuleList.get(i);
				AppRulePK appRulePk = appRule.getComp_id();
				appRulePk.setUserId(userId);
				appRule.setComp_id(appRulePk);

				incomingRequest.put("AppRule_userId", userId);
				incomingRequest.put("AppRule_udf1Code", appRulePk.getUdf1Code());
				incomingRequest.put("AppRule_udf2Code", appRulePk.getUdf2Code());
				incomingRequest.put("AppRule_udf3Code", appRulePk.getUdf3Code());
				incomingRequest.put("AppRule_udf4Code", appRulePk.getUdf4Code());
				incomingRequest.put("AppRule_udf5Code", appRulePk.getUdf5Code());
				incomingRequest.put("AppRule_udf6Code", appRulePk.getUdf6Code());
				incomingRequest.put("AppRule_udf7Code", appRulePk.getUdf7Code());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("apprule-delete-by-originalid.xml");

				incomingRequest.put("appRule", appRule);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("AppRule swap approver failed.");
				}
			}

			result = appRuleList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
