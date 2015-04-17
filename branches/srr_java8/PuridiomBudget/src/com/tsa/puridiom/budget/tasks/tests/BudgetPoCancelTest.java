package com.tsa.puridiom.budget.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BudgetPoCancelTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budget-po-cancel.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("Account_icHeader", "549101601000");
			incomingRequest.put("Account_icLine", "0");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}