package com.tsa.puridiom.invlocation.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvLocationAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invlocation-add.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("InvLocation_itemNumber", "test");
			incomingRequest.put("InvLocation_itemLocation", "007");
			incomingRequest.put("InvLocation_qtyOnHand", "0");
			incomingRequest.put("InvLocation_qtyOnOrder", "0");
			incomingRequest.put("InvLocation_minOnHand", "0");
			incomingRequest.put("InvLocation_maxOnHand", "0");
			incomingRequest.put("InvLocation_qtyEoq", "0");
			incomingRequest.put("InvLocation_qtyEsq", "0");
			incomingRequest.put("InvLocation_averageCost", "0");
			incomingRequest.put("InvLocation_udf1Code", "Ran");
			incomingRequest.put("InvLocation_udf2Code", "As");
			incomingRequest.put("InvLocation_udf3Code", "Test");
			incomingRequest.put("InvLocation_udf4Code", "from");
			incomingRequest.put("InvLocation_udf5Code", "Eclispe");
			incomingRequest.put("InvLocation_qtyAlloc", "0");
			incomingRequest.put("InvLocation_icInvAccount", "0");
			incomingRequest.put("InvLocation_icInvHeader", "0");
			incomingRequest.put("InvLocation_qtyRequested", "0");
			incomingRequest.put("InvLocation_autoReplenish", "");
			incomingRequest.put("InvLocation_physActual", "0");
			incomingRequest.put("InvLocation_physOriginal", "0");
			incomingRequest.put("InvLocation_primeLocation", "");
			incomingRequest.put("InvLocation_physAlloc", "0");
			incomingRequest.put("InvLocation_originalAlloc", "0");
			incomingRequest.put("InvLocation_qtyPendOrder", "0");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
