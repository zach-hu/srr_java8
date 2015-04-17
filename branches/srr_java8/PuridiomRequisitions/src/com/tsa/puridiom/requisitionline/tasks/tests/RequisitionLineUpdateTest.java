package com.tsa.puridiom.requisitionline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-update.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("RequisitionLine_icReqLine","713135500000") ;
			incomingRequest.put("RequisitionLine_lineTotal", "55.00");
			incomingRequest.put("RequisitionLine_quantity","4") ;
			incomingRequest.put("RequisitionLine_unitPrice","56") ;
			incomingRequest.put("RequisitionLine_status","1010");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}