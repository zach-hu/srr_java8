package com.tsa.puridiom.invitem.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-by-id.xml");
		Map incomingRequest = new HashMap();
		incomingRequest.put("InvItem_itemNumber", "test");
		
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}

}
