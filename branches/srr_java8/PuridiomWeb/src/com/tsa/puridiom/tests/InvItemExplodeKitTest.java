package com.tsa.puridiom.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemExplodeKitTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invitem-explode-kit.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "REQ");
			incomingRequest.put("icHeader", "1084365400000");

			incomingRequest.put("InvItem_itemNumber", "F-1000");
			incomingRequest.put("quantity", "2");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			process.executeProcess(incomingRequest);
			
			System.out.println(incomingRequest);
			System.out.println("InvItemExplodeKitTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}