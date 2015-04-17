package com.tsa.puridiom.invitem.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemDeleteTest
{
	public static void  main(String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invitem-delete.xml");
		Map incomingRequest = new HashMap();
		incomingRequest.put("InvItem_itemNumber", "test2");
		
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}
}