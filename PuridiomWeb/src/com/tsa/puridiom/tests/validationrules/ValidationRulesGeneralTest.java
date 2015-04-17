/*
 * Created on Sep 11, 2003
 */
package com.tsa.puridiom.tests.validationrules;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.rule.ValidationRules;


/**
 * @author renzo
 */
public class ValidationRulesGeneralTest
{
    public static void  main (String[] args) throws Exception
	{
		try
		{
		    System.out.println("****start*****");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("validation-rules.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RequisitionHeader_icReqHeader", "18025721900044");

			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			process.executeProcess(incomingRequest);

			ValidationRules rules = (ValidationRules)incomingRequest.get("rules");
			System.out.println(rules);
			System.out.println("Passed?: " + rules.getResult());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
