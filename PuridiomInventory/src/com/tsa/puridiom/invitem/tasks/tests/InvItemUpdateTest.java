package com.tsa.puridiom.invitem.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invitem-update.xml");

		Map incomingRequest = new HashMap();
		incomingRequest.put("InvItem_itemNumber", "132");
		incomingRequest.put("InvItem_commodity", "Office2");
		
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}

}
