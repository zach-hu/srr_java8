package com.tsa.puridiom.handlers.test.invitem;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class InvItemSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvItemSaveasHandler test = new InvItemSaveasHandler();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("InvItem_itemNumber", "CHI-15163(6/02)");
			incomingRequest.put("newItemNumber", "CHI-15165");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS"))
			{
				System.out.println("InvtemSaveas - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}