package com.tsa.puridiom.fdcs.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class FdcsUpdateStockOrderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			FdcsUpdateStockOrder test = new FdcsUpdateStockOrder() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");

			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}