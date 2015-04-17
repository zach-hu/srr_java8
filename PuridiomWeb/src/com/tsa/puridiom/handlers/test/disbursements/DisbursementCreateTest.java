package com.tsa.puridiom.handlers.test.disbursements;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbursementCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("disbursement-create.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionHeader_icReqHeader", "2389057700000");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}