package com.tsa.puridiom.requisitionheader.tasks.tests;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class RequisitionHeaderUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionheader-update.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionHeader_icReqHeader","549101601000") ;
			incomingRequest.put("RequisitionHeader_requisitionDate",Dates.today("", ""));
			incomingRequest.put("RequisitionHeader_discountPercent","5.5") ;
			incomingRequest.put("RequisitionHeader_taxPercent","7") ;
			incomingRequest.put("RequisitionHeader_total","995.33");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}