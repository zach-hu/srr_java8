package com.tsa.puridiom.coda.test;

import com.tsa.puridiom.coda.tasks.BrowseRetrieveElementFinder;
import com.tsa.puridiom.coda.tasks.CodaRetrieveElementMaster;
import com.tsa.puridiom.coda.tasks.ElementFinderRetrieve;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class CodaSupplierLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{

					// Find Vendor by Statutory Code
					CodaRetrieveElementMaster test = new CodaRetrieveElementMaster() ;
					Map newRequest = new HashMap() ;

					newRequest.put("organizationId", "TTR09P");
					newRequest.put("userId", "SYSADM");
					newRequest.put("codaLevel","2");
					newRequest.put("codeFilter","V372995");
					newRequest.put("fromTestApp", "yes") ;

					test.executeTask(newRequest) ;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}