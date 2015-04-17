package com.tsa.puridiom.fdcs.tasks;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class FdcsUpdatePoLineToMisWOTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			FdcsUpdatePoLineToMisWO test = new FdcsUpdatePoLineToMisWO() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");

			incomingRequest.put("poNumber","10-450002") ;
			incomingRequest.put("workNumber","PA34389");
			incomingRequest.put("opNumber", "") ;
			incomingRequest.put("segNumber","01") ;
			incomingRequest.put("custNumber","");
			incomingRequest.put("chargeCode","95");
			incomingRequest.put("vendorName", "3T TRANSPORT INC 1234567890 123456789");
			incomingRequest.put("seqNumber", new BigDecimal(20)) ;

			incomingRequest.put("lineNumber", new BigDecimal(1)) ;
			incomingRequest.put("dbsKey","");
			incomingRequest.put("description","MINIMUM PRESSURE VALVE REPLACEMENT");
			incomingRequest.put("quantity", new BigDecimal(1));
			incomingRequest.put("cost", new BigDecimal("30.79"));

			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}