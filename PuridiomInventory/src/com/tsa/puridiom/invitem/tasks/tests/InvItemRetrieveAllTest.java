package com.tsa.puridiom.invitem.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemRetrieveAllTest
{
	public static void  test (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-all.xml");
		Map incomingRequest = new HashMap();
		// TODO add your parameters to incomingRequest here
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}

}