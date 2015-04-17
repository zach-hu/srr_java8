package com.tsa.puridiom.handlers.test.disbursements;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.handlers.InvItemLookupHandler;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class OtcCreateWithItemTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("otc-header-create.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RENZO");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
			DisbHeader otc = (DisbHeader)incomingRequest.get("disbHeader");
			
			InvItemLookupHandler test = new InvItemLookupHandler();
			incomingRequest = new HashMap();
			String	itemLocations[] = {"43", "100", "60"};
			String itemNumbers[] = {"test", "test", "Mazda3"};
			String quantities[] = {"5", "10", "15"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RENZO");
			incomingRequest.put("formtype", "DISB");
			incomingRequest.put("icHeader", otc.getIcDsbHeader().toString());
/*			incomingRequest.put("InvLocation_itemLocation", itemLocations);
			incomingRequest.put("InvItem_itemNumber", itemNumbers);
			incomingRequest.put("quantity", quantities);
*/
			incomingRequest.put("InvLocation_itemLocation", "1000");
			incomingRequest.put("InvItem_itemNumber", "M-1000");
			incomingRequest.put("InvBinLocation_icRc", "1369203700004");
			incomingRequest.put("quantity", "3");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			String disbNumber = (String)incomingRequest.get("Disbursemet_number");
			if(disbNumber != null)
			{
			    System.out.println("disb number: " + disbNumber);
			}
			System.out.println("Otc InvItemLookup - " + (String) incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}