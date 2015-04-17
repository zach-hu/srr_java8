/*
 * Created on August 19, 2008
 */
package com.tsa.puridiom.apprulesext.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.apprulesext.tasks.ExtendedRulesListXmlToDbConverter.ExtendedRule;
import com.tsa.puridiom.entity.AppRulesExt;
import com.tsa.puridiom.entity.AppRulesExtPK;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class AppRulesExtUpdateFromXml extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
            String filename = "general-approval-rules.xml";
            String organizationId = (String) incomingRequest.get("organizationId");

            incomingRequest.put("filename", filename);
            incomingRequest.put("isAppExtRules", "true");

            ExtendedRulesListXmlToDbConverter converter = new ExtendedRulesListXmlToDbConverter(filename, incomingRequest);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("apprulesext-add.xml");

            Map updateParameters = new HashMap();
            updateParameters.put("organizationId", organizationId);
            updateParameters.put("userId", "SYSADM");
            updateParameters.put("dbsession", incomingRequest.get("dbsession"));

            List extRulesList = converter.getRules();

            for (int i = 0; i < extRulesList.size(); i++) {
                ExtendedRule rule = (ExtendedRule) extRulesList.get(i);
                AppRulesExt appRulesExt = new AppRulesExt();
                AppRulesExtPK appRulesExtPK = new AppRulesExtPK();

                appRulesExtPK.setRuleNumber(new BigDecimal(i + 1));
                appRulesExtPK.setRuleType("REQ");
                appRulesExt.setComp_id(appRulesExtPK);

                appRulesExt.setAdvisor(rule.getAdvisor());
                appRulesExt.setApproverAmount(rule.getApproverAmount());
                appRulesExt.setEnabled("Y");
                appRulesExt.setFyiApprover(rule.getFyiApprover());
                appRulesExt.setNotes(rule.getNotes());
                appRulesExt.setRequiredApprover(rule.getRequiredApprover());
                appRulesExt.setRoutto(rule.getRoutTo());
                appRulesExt.setRuleAction(rule.getRuleAction());
                appRulesExt.setRuleFilename(rule.getName());
                appRulesExt.setRuleOrder(rule.getRuleOrder());
                appRulesExt.setRuleText(rule.getRuleText());
                appRulesExt.setSuperRule(rule.getNewRules());

                updateParameters.put("appRulesExt", appRulesExt);

                process.executeProcess(updateParameters);
            }

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
