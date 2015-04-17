package com.tsa.puridiom.handlers.test.reports.usage;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemDetailUsageTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("item-usage.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("InvItem_itemNumber", "1000");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}