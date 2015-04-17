package com.tsa.puridiom.coda.test;

import com.tsa.puridiom.coda.tasks.CodaRetrieveSelector;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class CodaSelectorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CodaRetrieveSelector test = new CodaRetrieveSelector() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invoiceNumber", "567") ;
			incomingRequest.put("poNumber", "10-001312") ;
			incomingRequest.put("vendorId", "V*") ;
			incomingRequest.put("fromTestApp", "no") ;

			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}