package com.tsa.puridiom.billto.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BillToUpdateByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("billto-update-by-line.xml");
			Map incomingRequest = new HashMap();
			
			String	billToCode[] = {"BT-1","BT-2","BT-3", "BT-4", "BT-5", "BT-6"};
			String	qty[] = {"1", "2", "10","255", "7", "6"};
			String	attn[] = {"Accounting","Purchasing","Accounting","Bob Newhart","Sonny Bono","Granny" } ;

			incomingRequest.put("BillTo_icHeader", "549101601000");
			incomingRequest.put("BillTo_icLine", "0");
			incomingRequest.put("BillTo_billToCode", billToCode);
			incomingRequest.put("BillTo_qty", qty);
			incomingRequest.put("BillTo_attention",attn) ;
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}