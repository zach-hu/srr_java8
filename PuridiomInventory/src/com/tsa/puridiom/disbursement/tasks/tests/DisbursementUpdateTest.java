package com.tsa.puridiom.disbursement.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbursementUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("disbursement-update.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("old_DisbLine_qty", "4");
			incomingRequest.put("DisbLine_quantity", "2");
			incomingRequest.put("DisbLine_itemNumber", "test");
			incomingRequest.put("DisbLine_itemLocation", "60");
			incomingRequest.put("DisbLine_icDsbLine", "697885300001");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}