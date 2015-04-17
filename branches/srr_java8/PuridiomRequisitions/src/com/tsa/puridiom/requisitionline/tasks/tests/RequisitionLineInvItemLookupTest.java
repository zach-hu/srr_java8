package com.tsa.puridiom.requisitionline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineInvItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-invitem-lookup.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("RequisitionLine_icReqLine", "713135500000");
			incomingRequest.put("RequisitionLine_itemNumber","100") ;
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}