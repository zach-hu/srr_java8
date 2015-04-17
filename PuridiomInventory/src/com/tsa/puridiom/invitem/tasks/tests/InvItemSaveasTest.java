package com.tsa.puridiom.invitem.tasks.tests;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.invitem.tasks.InvItemSaveas;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class InvItemSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			dbs.startTransaction();
			
			InvItemSaveas saveasTask = new InvItemSaveas();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "KELLI");
			
			InvItem invItem = new InvItem();
			
			invItem.setItemNumber("ORIGINAL TEST ITEM");
			invItem.setAbcCode("A");
			invItem.setActionCode("F");
			invItem.setAverageCost(new BigDecimal("10.00"));
			invItem.setBuyerCode("KELLI");
			invItem.setChargable("Y");
			invItem.setDescription("This is the original test item for the saveas.");
			
			incomingRequest.put("invItem", invItem) ;
			incomingRequest.put("InvItem_itemNumber", invItem.getItemNumber());
			incomingRequest.put("newItemNumber", "NEW ITEM NUMBER");
			
			saveasTask.executeTask(incomingRequest);
			
			if (saveasTask.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Status: " + saveasTask.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}