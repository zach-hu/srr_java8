package com.tsa.puridiom.shipto.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ShipToUpdateByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("shipto-update-by-line.xml");
			Map incomingRequest = new HashMap();
			
			String	shipToCode[] = {"ST-1","ST-2","ST-3", "ST-4", "ST-5", "ST-6"};
			String	qty[] = {"1", "2", "60000000","255", "7", "6"};
			String	attn[] = {"Accounting","Purchasing","Accounting","Bob Newhart","Sonny Bono","Granny" } ;
			String	priority[]={"1", "2", "1","1", "1", "O3"};

			incomingRequest.put("ShipTo_icHeader", "549101601000");
			incomingRequest.put("ShipTo_icLine", "0");
			incomingRequest.put("ShipTo_shipToCode", shipToCode);
			incomingRequest.put("ShipTo_qty", qty);
			incomingRequest.put("ShipTo_attention",attn) ;
			incomingRequest.put("ShipTo_shipToPriority",priority) ;
			
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}