package com.tsa.puridiom.invhistory.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvHistoryRetrieveAllTest
{
	public static void  test (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invhistory-retrieve-all.xml");
		Map incomingRequest = new HashMap();
		// TODO add your parameters to incomingRequest here
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}

}