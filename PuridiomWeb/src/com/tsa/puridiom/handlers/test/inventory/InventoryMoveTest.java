package com.tsa.puridiom.handlers.test.inventory;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.InventoryMoveHandler;

public class InventoryMoveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InventoryMoveHandler handler = new InventoryMoveHandler();
			Map incomingRequest = new HashMap();
			incomingRequest.put("qtyToMove", "2");
			//extended inventory
			incomingRequest.put("fromBin", "1380283800000");
			incomingRequest.put("toBin", "852291600000");
			//standard inventory
			incomingRequest.put("toLocation", "65");
			incomingRequest.put("fromLocation", "66");
			incomingRequest.put("itemNumber", "test");
			
			handler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}