package com.tsa.puridiom.approvals.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionRetrieveExtendedApprovalRulesTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-retrieve-extended-approval-rules.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}