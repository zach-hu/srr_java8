package com.tsa.puridiom.fdcs.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class FdcsGetWorkOrderValidationTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			FdcsGetWorkOrderValidation test = new FdcsGetWorkOrderValidation() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("workNumber", "RN00976") ;
			incomingRequest.put("segNumber", "") ;
			incomingRequest.put("opNumber", "");
			incomingRequest.put("custNumber", "") ;

			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}