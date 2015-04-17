package com.tsa.puridiom.coda.test;

import com.tsa.puridiom.coda.tasks.BrowseRetrieveElementFinder;
import com.tsa.puridiom.coda.tasks.ElementFinderRetrieve;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class CodaElementFinderRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			BrowseRetrieveElementFinder test = new BrowseRetrieveElementFinder() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("codaLevel","2");
			incomingRequest.put("codeFilter","V");
			incomingRequest.put("nameFilter","");
			incomingRequest.put("shortNameFilter","");

			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}